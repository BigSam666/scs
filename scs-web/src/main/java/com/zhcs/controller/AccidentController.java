package com.zhcs.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.stereotype.Controller;

import com.zhcs.context.PlatformContext;
import com.zhcs.entity.AccidentEntity;
import com.zhcs.service.AccidentService;
import com.zhcs.utils.BeanUtil;
import com.zhcs.utils.PageUtils;
import com.zhcs.utils.StringUtil;
import com.zhcs.utils.R;

//*****************************************************************************
/**
 * <p>Title:AccidentController</p>
 * <p>Description: 事故管理</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
@Controller
@RequestMapping("accident")
public class AccidentController extends AbstractController  {
	@Autowired
	private AccidentService accidentService;
	
	@RequestMapping("/accident.html")
	public String list(){
		return "accident/accident.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("accident:list")
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
//		List<AccidentEntity> accidentList = accidentService.queryList(map);
		List<AccidentEntity> accidentList = accidentService.queryList1(map);
		int total = accidentService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(accidentList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{id}")
	@RequiresPermissions("accident:info")
	public R info(@PathVariable("id") Long id){
		AccidentEntity accident = accidentService.queryObject(id);
		return R.ok().put("accident", accident);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("accident:save")
	public R save(@RequestBody AccidentEntity accident){
	
		BeanUtil.fillCCUUD(accident, getUserId(), getUserId());
		accidentService.save(accident);
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("accident:update")
	public R update(@RequestBody AccidentEntity accident){
		
		BeanUtil.fillCCUUD(accident, getUserId());
		accidentService.update(accident);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete/{id}")
	@RequiresPermissions("accident:delete")
	public R delete(@PathVariable("id") Long id){
		if(PlatformContext.getGoalbalContext("adminId", String.class).equals(StringUtil.valueOf(getUserId()))){
			accidentService.delete(id);
		} else {
			AccidentEntity accident = new AccidentEntity();
			accident.setId(id);
			accident.setStatus("0");
			BeanUtil.fillCCUUD(accident, getUserId());
			accidentService.update(accident);
		}
		return R.ok();
	}
	
}
