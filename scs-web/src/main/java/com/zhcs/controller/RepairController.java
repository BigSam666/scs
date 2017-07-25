package com.zhcs.controller;

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

import com.zhcs.context.PlatformContext;
import com.zhcs.entity.RepairEntity;
import com.zhcs.service.RepairService;
import com.zhcs.utils.BeanUtil;
import com.zhcs.utils.PageUtils;
import com.zhcs.utils.R;
import com.zhcs.utils.StringUtil;

//*****************************************************************************
/**
 * <p>Title:RepairController</p>
 * <p>Description: 维修管理</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
@Controller
@RequestMapping("repair")
public class RepairController extends AbstractController  {
	@Autowired
	private RepairService repairService;
	
	@RequestMapping("/repair.html")
	public String list(){
		return "repair/repair.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("repair:list")
	public R list(String sidx, String order, Integer page, Integer limit,String condition,String starttime,String endtime){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sidx", sidx);
		map.put("order", order);
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		
		map.put("condition", condition);
		map.put("starttime", starttime);
		map.put("endtime", endtime);
		//查询列表数据
//		List<RepairEntity> repairList = repairService.queryList(map);
		List<RepairEntity> repairList = repairService.queryList1(map);
		int total = repairService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(repairList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{id}")
	@RequiresPermissions("repair:info")
	public R info(@PathVariable("id") Long id){
		RepairEntity repair = repairService.queryObject(id);
		
		return R.ok().put("repair", repair);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("repair:save")
	public R save(@RequestBody RepairEntity repair){
	
		BeanUtil.fillCCUUD(repair, getUserId(), getUserId());
		repairService.save(repair);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("repair:update")
	public R update(@RequestBody RepairEntity repair){
		
		BeanUtil.fillCCUUD(repair, getUserId());
		repairService.update(repair);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete/{id}")
	@RequiresPermissions("repair:delete")
	public R delete(@PathVariable("id") Long id){
		if(PlatformContext.getGoalbalContext("adminId", String.class).equals(StringUtil.valueOf(getUserId()))){
			repairService.delete(id);
		} else {
			RepairEntity repair = new RepairEntity();
			repair.setId(id);
			repair.setStatus("0");
			BeanUtil.fillCCUUD(repair, getUserId());
			repairService.update(repair);
		}
		return R.ok();
	}
	
}
