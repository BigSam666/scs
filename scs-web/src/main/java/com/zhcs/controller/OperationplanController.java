package com.zhcs.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zhcs.context.PlatformContext;
import com.zhcs.entity.OperationplanEntity;
import com.zhcs.entity.OperationplandataEntity;
import com.zhcs.service.OperationplanService;
import com.zhcs.utils.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

//*****************************************************************************
/**
 * Title:OperationplanController
 * Description: operationplan(作业计划)
 * Copyright: Copyright (c) 2017
 * Company: 深圳市智慧城市管家信息科技有限公司
 * @author JasonPeng - Alter
 * @version v1.0 2017年2月23日
 */
// *****************************************************************************
@Controller
@RequestMapping("operationplan")
public class OperationplanController extends AbstractController {
	@Autowired
	private OperationplanService operationplanService;
	
	@RequestMapping("/operationplan.html")
	public String list() {
		return "operationplan/operationplan.html";
	}

	
	/**
	 * 获取列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("operationplan:list")
	public R list(String sidx, String order, String name, Integer page, Integer limit){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", name);
		map.put("sidx", sidx);
		map.put("order", order);
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		
		//查询列表数据
//		List<Map<String, Object>> operationplanList = operationplanService.queryLists(map);
		List<Map<String, Object>> operationplanList = operationplanService.queryLists1(map);
		int total = operationplanService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(operationplanList, total, limit, page);
		return R.ok().put("page", pageUtil);
	}
	
	/**
	 * 获取所有班次信息
	 */
	@ResponseBody
	@RequestMapping("/ban")
	@RequiresPermissions("operationplan:ban")
	public R queryBan() {
		List<Map<String, Object>> bancimap = operationplanService.queryBanCiMap();
		return R.ok().put("bancimap", bancimap);
	}
	
	/**
	 * 获取重复排班数据信息
	 */
	@ResponseBody
	@RequestMapping("/month")
	@RequiresPermissions("operationplan:month")
	public R queryMonth(@RequestBody Map<String, Object> data){
			Map<String, Object> oob = new HashMap<String, Object>();
			oob.put("optime", DateUtil.getYtoM(data.get("date")));
			oob.put("dept", StringUtil.valueOf(data.get("dept")));
			oob.put("bsment", StringUtil.valueOf(data.get("bsment")));
			OperationplanEntity o = operationplanService.queryODB(oob);
			if(StringUtil.isValid(o)){
				return R.error(500,"已排班数据");
			}
		return R.error(200,"没有排班数据");
	}
	
	/**
	 * 获取排班信息
	 */
	@ResponseBody
	@RequestMapping("/paiban")
	@RequiresPermissions("operationplan:paiban")
	public R queryPaiban(@RequestBody Map<String, Object> map) {
			//获取某月的   => 年月日星期
			Map<String, Object> maps;
			try {
				maps = new HashMap<String, Object>();
				maps.put("days", DateUtil.getMapYMDW(map.get("time")));
			} catch (Exception e) {
				throw new ZHCSException("服务器异常：(获取日期失败)" + e.getMessage());
			}
			
			//获取班次的 => 开始和结束时间
			String[] sedate = StringUtil.StrList(StringUtil.listToString((JSONArray)map.get("banci")));
			List<OperationplanEntity> sEdate = operationplanService.querySEdate(sedate);
			if(StringUtil.isValid(sEdate)){
				maps.put("sedate", sEdate);
			}else{
				return R.error(500,"获取班次失败和开始结束时间失败!");
			}
							
			List<Map<String, Object>> business;
			//根据相关的项目部获取对应的班级
			Map<String, Object> classotypemap = new HashMap<String, Object>();
			classotypemap.put("otype", PlatformContext.getGoalbalContext("classotype", String.class));
			classotypemap.put("userid", getUserId());
			business = operationplanService.queryDept(classotypemap);
			if(StringUtil.isValid(business)){
				maps.put("business", business);
			}else{
				return R.error(500,"由项目部获取对应的班级失败!");
			}
			
			//获取班级下的所有成员
			List<Map<String, Object>> deptnames;
			deptnames = null;
			Map<String, Object> temp =null;
			List<Integer> bl = new ArrayList<Integer>();
			List<Map<String, Object>> nmap = new ArrayList<Map<String, Object>>();
			for (Map<String, Object> bs : business) {
				temp = new HashMap<String, Object>();
				deptnames = operationplanService.queryDeptname(StringUtil.valueOf(bs.get("value")));
				temp.put("name", StringUtil.valueOf(bs.get("value")));
				temp.put("value", deptnames);
				bl.add(deptnames.size());
				nmap.add(temp);
			}
			if(StringUtil.isValid(nmap)){
				maps.put("nmap", nmap);
			}else{
				return R.error(500,"获取班级下的成员失败!");
			}
			maps.put("bl", bl);
			
/*			//获取成员司机信息
			List<String> driver = new ArrayList<>();
			for (Map<String, Object> dp : deptnames) {
				driver.add(operationplanService.queryDrivername(StringUtil.valueOf(dp.get("value"))));
			}
			if(StringUtil.isValid(driver)){
				maps.put("driver", driver);
			}else{
				return R.error(500,"获取成员司机信息失败!");
			}*/
			
			//获取项目部下所有的车辆
			List<Map<String, Object>> carlist = operationplanService.queryCar(StringUtil.valueOf(map.get("project")));
			if(StringUtil.isValid(carlist)){
				maps.put("carlist", carlist);
			}else{
				return R.error(500,"获取项目部下所有的车辆失败!");
			}
			
			return R.ok().put("maps", maps);
	}
	
	
	/**
	 * 获取 id 主表 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{oid}")
	@RequiresPermissions("operationplan:info")
	public R info(@PathVariable("oid") Long oid){
		List<Map<String, Object>> operationplan = operationplanService.queryObjects(oid);
		return R.ok().put("operationplan", operationplan);
	}
	
	
	/**
	 * 获取 id 数据表 信息
	 */
	@ResponseBody
	@RequestMapping("/datalist/{oid}")
	@RequiresPermissions("operationplan:datalist")
	public R datalist(@PathVariable("oid") Long oid){
		List<Map<String, Object>> operationplan = operationplanService.queryDataList(oid);
		return R.ok().put("operationplan", operationplan);
	}
	
	/**
	 * 保存
	 * @param data
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("operationplan:save")
	public R savePlan(@RequestBody Map<String, Object> data) throws Exception{
		Map<String, Object> operationplan = new HashMap<String, Object>();
		Map<String, Object> ccuu = new HashMap<String, Object>();
		ccuu.put("crtid", getUserId());
		ccuu.put("updid", getUserId());
		ccuu.put("crttm", DateUtil.getSystemDate());
		ccuu.put("updtm", DateUtil.getSystemDate());
		operationplan.putAll(ccuu);
		operationplan.put("optime", DateUtil.getYtoM(data.get("date")));
		operationplan.put("dept", data.get("project"));
		operationplan.put("bsment", data.get("bsment"));
		operationplan.put("banci", StringUtil.listToString((List<String>)data.get("banci")));
		
		// 查询详细班级信息
		Map<String, Object> classotypemap = new HashMap<String, Object>();
		classotypemap.put("otype", PlatformContext.getGoalbalContext("classotype", String.class));
		classotypemap.put("userid", getUserId());
		List<Map<String, Object>> business = operationplanService.queryDept(classotypemap);
		StringBuffer cla = new StringBuffer();
		for (Map<String, Object> map : business) {
			if(StringUtil.isValid(StringUtil.valueOf(cla))){
				cla.append(",");
			}
			cla.append(map.get("value"));
		}
		operationplan.put("cla", StringUtil.valueOf(cla));
		OperationplanEntity o = BeanUtil.map2Bean(operationplan, OperationplanEntity.class);
		// 保存主表信息
		o.setStatus("1");
		operationplanService.save(o);
		
		JSONObject datas = (JSONObject) data.get("datas");
		Map<String, Object> operationplandata = null;
		OperationplandataEntity od = null;
		Object[] otemp = null;
		for (String tempa : datas.keySet()) {
			if(StringUtil.isValid(tempa)){
				otemp = tempa.split("#");
				if(otemp.length == 4){
					operationplandata = new HashMap<String, Object>();
					operationplandata.putAll(ccuu);
					operationplandata.put("oid", o.getId());
					operationplandata.put("optime", otemp[0]);
					operationplandata.put("shift", otemp[1]);
					operationplandata.put("cla", otemp[2]);
					operationplandata.put("member", otemp[3]);
					operationplandata.put("driver", StringUtil.isValid(datas.get(tempa + "#driver")) ? 1 : 0);
					operationplandata.put("car", StringUtil.isValid(datas.get(tempa + "#driver#car")) ? datas.get(tempa + "#driver#car") : null);
					// 执行保存操作
					od = BeanUtil.map2Bean(operationplandata, OperationplandataEntity.class);
					od.setStatus("1");
					operationplanService.updatedata(od);
					// 保存完成之后,清理对象
					operationplandata = null;
					od = null;
				} else {
					// 不是成员信息不保存
					continue;
				}
			} else {
				// 数据无效不执行保存
				continue;
			}
		}
		return R.ok();
	}
	
	
	/**
	 * 修改
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("operationplan:update")
	public R update(@RequestBody Map<String, Object> data) throws Exception{
		Map<String, Object> operationplan = new HashMap<String, Object>();
		Map<String, Object> ccuu = new HashMap<String, Object>();
		ccuu.put("crtid", getUserId());
		ccuu.put("updid", getUserId());
		ccuu.put("crttm", DateUtil.getSystemDate());
		ccuu.put("updtm", DateUtil.getSystemDate());
		operationplan.putAll(ccuu);
		operationplan.put("oid", data.get("oid"));
		// 保存主表信息
		JSONObject datas = (JSONObject) data.get("datas");
		Map<String, Object> operationplandata = null;
		OperationplandataEntity od = null;
		Object[] otemp = null;
		operationplanService.deleteup(Long.parseLong(StringUtil.valueOf(data.get("oid")))); //删除
		for (String tempa : datas.keySet()) {
			if(StringUtil.isValid(tempa)){
				otemp = tempa.split("#");
				if(otemp.length == 4){
					operationplandata = new HashMap<String, Object>();
					operationplandata.putAll(ccuu);
					operationplandata.put("oid", data.get("oid"));
					operationplandata.put("optime", otemp[0]);
					operationplandata.put("shift", otemp[1]);
					operationplandata.put("cla", otemp[2]);
					operationplandata.put("member", otemp[3]);
					operationplandata.put("driver", StringUtil.isValid(datas.get(tempa + "#driver")) ? 1 : 0);
					operationplandata.put("car", StringUtil.isValid(datas.get(tempa + "#driver#car")) ? datas.get(tempa + "#driver#car") : null);
					// 执行修改操作
					od = BeanUtil.map2Bean(operationplandata, OperationplandataEntity.class);
					od.setStatus("1");
					operationplanService.saveData(od);
					// 保存完成之后,清理对象
					operationplandata = null;
					od = null;
				} else {
					// 不是成员信息不保存
					continue;
				}
			} else {
				// 数据无效不执行保存
				continue;
			}
		}
		return R.ok();
	}
	
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete/{id}")
	@RequiresPermissions("operationplan:delete")
	public R delete(@PathVariable("id") Long id){
		operationplanService.delete(id);
		return R.ok();
	}
	
	
	/**
	 * 判断是否有下一个月的数据
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/judegtime/{id}")
	@RequiresPermissions("operationplan:judegtime")
	public R judegtime(@PathVariable("id") Long id) throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		String optime = operationplanService.queryId(id);
		Date date = DateUtil.fomatDate(StringUtil.valueOf(optime), "yyyy-MM");
		String dateString = DateUtil.monthOption(date,1);
		String judegMonth = operationplanService.queryMonth(dateString);
		map.put("judegMonth", judegMonth);
		map.put("optime", optime);
		map.put("dateString", dateString);
		return R.ok().put("map", map);
	}
	
	
	/**
	 * 复制
	 */
	@ResponseBody
	@RequestMapping("/copy")
	@RequiresPermissions("operationplan:copy")
	public R copy(@RequestBody Map<String, Object> data) throws Exception{
		
		List<Map<String, Object>> operationplan = operationplanService.queryOpList(data.get("id"));
		OperationplanEntity o = null;
		for (Map<String, Object> map : operationplan) {
			map.put("optime", data.get("optime"));
			map.put("crtid", getUserId());
			map.put("updid", getUserId());
			map.put("crttm", DateUtil.getSystemDate());
			map.put("updtm", DateUtil.getSystemDate());
			map.put("status", "1");
			o = BeanUtil.map2Bean(map, OperationplanEntity.class);
		}
		operationplanService.save(o);//主表保存
		
		int dayCount = DateUtil.getDayCount(DateUtil.getYear(DateUtil.fomatDate(StringUtil.valueOf(data.get("optime")), "yyyy-MM")), DateUtil.getMonth(DateUtil.fomatDate(StringUtil.valueOf(data.get("optime")), "yyyy-MM")));
		List<Map<String, Object>> operationplandata = operationplanService.queryObjects(data.get("id"));
		Map<String, Object> sMap = null;
		for (Map<String, Object> map : operationplandata) {
			int day = DateUtil.getDay(DateUtil.fomatDate(StringUtil.valueOf(map.get("optime")), "yyyy-MM-dd"));
			if(day <= dayCount){
				sMap = new HashMap<String,Object>();
				sMap.put("optime", DateUtil.strToDate(StringUtil.valueOf(data.get("optime"))+"-"+day,"yyyy-MM-dd"));
				sMap.put("shift", map.get("shift"));
				sMap.put("oid",  o.getId());
				sMap.put("driver", map.get("driver"));
				sMap.put("car",  map.get("car"));
				sMap.put("member",  map.get("member"));
				sMap.put("cla", map.get("cla"));
				sMap.put("crtid", getUserId());
				sMap.put("updid", getUserId());
				sMap.put("crttm", DateUtil.getSystemDate());
				sMap.put("updtm", DateUtil.getSystemDate());
				sMap.put("status", "1");
				OperationplandataEntity opd = BeanUtil.map2Bean(sMap, OperationplandataEntity.class);
				operationplanService.saveData(opd);//附表保存
			}else{
				continue;
			}
		}
		return R.ok();
	}
}
