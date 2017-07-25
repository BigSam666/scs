package com.zhcs.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhcs.entity.AtdRecordEntity;
import com.zhcs.entity.ClassesEntity;
import com.zhcs.entity.OperationplanEntity;
import com.zhcs.entity.SysDepartmentEntity;
import com.zhcs.entity.SysUserEntity;
import com.zhcs.service.AtdRecordService;
import com.zhcs.service.ClassesService;
import com.zhcs.service.OperationplanService;
import com.zhcs.service.SysUserService;
import com.zhcs.utils.BeanUtil;
import com.zhcs.utils.DateUtil;
import com.zhcs.utils.PageUtils;
import com.zhcs.utils.R;
import com.zhcs.utils.StringUtil;

import freemarker.template.SimpleDate;

//*****************************************************************************
/**
 * <p>Title:AtdRecordController</p>
 * <p>Description: 考勤记录</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
@Controller
@RequestMapping("atdrecord")
public class AtdRecordController extends AbstractController  {
	@Autowired
	private AtdRecordService atdRecordService;
	@Autowired
	private OperationplanService operationplanService;
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private ClassesService classesService;
	
	@RequestMapping("/atdrecord.html")
	public String list(){
		return "atdrecord/atdrecord.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("atdrecord:list")
	public R list(String sidx, String order, Integer page, Integer limit,String name){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sidx", sidx);
		map.put("order", order);
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		map.put("realname", name);
		
		String yesterday = DateUtil.getAfterDayDate("-1");
		
		map.put("yesterday", yesterday);
		
		// 查询列表数据
		// List<AtdRecordEntity> atdRecordList = atdRecordService.queryList(map);
		// 先查询排班数据，因为涉及到部门，所以先要查询主表信息，
		// 考虑到当前用户可能存在多个部门，所以使用函数查询到当前用户所属的所有部门，查询所有数据，然后交由前端进行处理（下拉框）
		// 不考虑用户不属于项目部的情况，一般由上至下查询，在部门下不会有权限查询出勤数据，故不考虑改种情况
		// 查询考勤不考虑部门负责人
		// 当前费时：24小时
		// 当前数据为当前用户的所属部门信息
		List<SysDepartmentEntity> projectList = sysUserService.getUserProject(getUserId());
		// 循环部门列表，拿到部门下的所有用户信息
		List<Long> deptList = new ArrayList<Long>();
		for (SysDepartmentEntity department : projectList) {
			deptList.add(department.getId());
		}
		// 只查询排班表的信息，此时与用户表无关
		// List<SysUserEntity> userList = sysUserService.queryUsersBydepts(deptList);
		// 用部门和用户查询对应的排班信息
		// 用项目id去排班主表查询主表信息
		//排班 作业计划
		List<OperationplanEntity> ls = operationplanService.queryByProject(deptList);
		// 用得到的排班主表信息查询排班详细信息
		// 正常上班的人xxxx
		List<Map<String, Object>> all = new ArrayList<Map<String, Object>>();
		
		//作业计划的ids
		StringBuffer  sb = new StringBuffer();
		for (OperationplanEntity oe : ls) {
			//all.addAll(operationplanService.queryDataListConctOptm(oe.getId()));
			sb.append(",").append(oe.getId());
		}
		String oids = sb.substring(1);
		//all = operationplanService.queryDataListConctOptmByOids(oids);
		Map<String, Object> dataMap =  atdRecordService.queryOperatorRecordDatalist(map);
		all = (List<Map<String, Object>>) dataMap.get("datalist");
		int total = (int) dataMap.get("total");
		// 判断列表不为空
		/*if(StringUtil.isValid(all)){
			ClassesEntity ce = null;
			SysUserEntity user = null;
			List<SysDepartmentEntity> tempDept = null;
			List<Object> deptsText = null;
			List<Object> deptsList = null;
			for (Map<String, Object> map2 : all) {
				// 查询上下班信息 cla
				ce = classesService.queryObject(Long.valueOf(StringUtil.valueOf(map2.get("shift"))));
				user = sysUserService.queryObject(Long.valueOf(StringUtil.valueOf(map2.get("member"))));
				deptsText = new ArrayList<Object>();
				deptsList = new ArrayList<Object>();
				tempDept = sysUserService.queryDepartmentByUser(user.getId());
				for (SysDepartmentEntity dept : tempDept) {
					deptsText.add(dept.getName());
					deptsList.add(dept.getId());
				}
				map2.put("deptsText", StringUtil.valueOf(deptsText));
				map2.put("deptsList", StringUtil.valueOf(deptsList));
				map2.put("member", user);
				map2.put("gtw", ce.getStarttime());
				map2.put("gow", ce.getEmdtime());
				map2.put("classText", ce.getClasses());
				map2.put("isAdjustment", false);
				map2.put("atdStatus", true);
			}
		}*/
		// 从考勤申报表获取需要上班的用户

		// 获取是否有迟到早退的。
		 getStatus(all);
		PageUtils pageUtil = new PageUtils(all, total, limit, page);
		return R.ok().put("page", pageUtil);
	}
	
	//*************************************************************************
	/** 
	* 【内部方法】内部方法，获取用户的经纬度信息，然后判断是否有迟到，早退，旷工，请假
	* @param all
	* @return  
	*/
	//*************************************************************************
	private void getStatus(List<Map<String, Object>> all){
		// 判断是否有迟到，早退， 调休调班
		long diff = 30*60*1000;
		Map<String, Object> queryMap = new HashMap<>();
		for (Map<String, Object> map : all) {
			//日期
			String optime = map.get("optime").toString();
			//班次 开始结束时间
			String starttime = map.get("starttime").toString();
			String emdtime = map.get("emdtime").toString();
			//实际 打卡时间 
			String btime = "";
			int num = 0;
			String statusStr = "";
			if(map.get("btime")==null || "".equals(map.get("btime").toString())){
				num++;
			}else{
				btime = map.get("btime").toString();
				//判断迟到
				boolean beLate = beLate(optime + " " + starttime, btime, diff);
				map.put("beLate", "0");
				if(!beLate){
					statusStr += ",迟到";
					map.put("beLate", "1");
				}
				
			}
			String etime = "";
			if(map.get("etime")==null || "".equals(map.get("etime").toString())){
				num++;
			}else{
				etime = map.get("etime").toString();
				//判断早退
				boolean leaveEarly = leaveEarly(optime + " " + emdtime, etime, diff);
				map.put("leaveEarly", "0");
				if(!leaveEarly){
					statusStr += ",早退";
					map.put("leaveEarly", "1");
				}
				
			}
			if(statusStr.length() > 0){
				map.put("statusStr", statusStr.substring(1));
			}else{
				map.put("statusStr", "正常");
			}
			
			
			if(num > 0){
				System.err.println("漏打卡：" + num);
			}
			
			//判断 调休 与调班
			queryMap.put("person", map.get("member"));
			queryMap.put("ftime", optime + " " + starttime);
			queryMap.put("stime",  optime + " " + emdtime);
			
			List<Map<String, Object>> list =  atdRecordService.findTiaoxiuOrBan(queryMap);
			if(list.size() > 0){
				Map<String, Object> txMap = list.get(0);
				//3 调班   4 调休
				int atdtype = Integer.valueOf(txMap.get("atdtype").toString());
				map.put("atdtype", atdtype);
			}
			
			
			
		}
	}
	private static String format = "yyyy-dd-MM HH:mm:ss";
	/**
	 * 比较 迟到开始时间 结束时间在 误差之内
	 * @param start 正常上班时间
	 * @param end   实际上班时间
	 * @param diff  误差 毫秒
	 * @return
	 */
	private boolean beLate(String start,String end, long diff){
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			long startl = sdf.parse(start).getTime();
			long endl = sdf.parse(end).getTime();
			if(endl <= startl){
				return true;
			}else{
				long dValue = endl - startl;
				if(dValue <= diff){
					return true;
				}else{
					return false;
				}
			}
		} catch (ParseException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return false;
	}
	/**
	 * 早退
	 * @param start 正常时间
	 * @param end 实际时间
	 * @param diff 差值
	 * @return
	 * @throws ParseException
	 */
	private boolean leaveEarly(String start,String end, long diff) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			long startl = sdf.parse(start).getTime();
			long endl = sdf.parse(end).getTime();
			if(endl >= startl){
				return true;
			}else{
				long dValue = startl - endl;
				if(dValue <= diff){
					return true;
				}else{
					return false;
				}
			}
			
		} catch (ParseException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return false;
		
	}
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{id}")
	@RequiresPermissions("atdrecord:info")
	public R info(@PathVariable("id") Long id){
		AtdRecordEntity atdRecord = atdRecordService.queryObject(id);
		
		return R.ok().put("atdRecord", atdRecord);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("atdrecord:save")
	public R save(@RequestBody AtdRecordEntity atdRecord){
	
		BeanUtil.fillCCUUD(atdRecord, getUserId(), getUserId());
		atdRecordService.save(atdRecord);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("atdrecord:update")
	public R update(@RequestBody AtdRecordEntity atdRecord){
		
		BeanUtil.fillCCUUD(atdRecord, getUserId());
		atdRecordService.update(atdRecord);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete/{id}")
	@RequiresPermissions("atdrecord:delete")
	public R delete(@PathVariable("id") Long id){
		atdRecordService.delete(id);
		return R.ok();
	}
	
}
