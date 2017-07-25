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
import com.zhcs.entity.DriverEntity;
import com.zhcs.service.DriverService;
import com.zhcs.utils.BeanUtil;
import com.zhcs.utils.DateUtil;
import com.zhcs.utils.PageUtils;
import com.zhcs.utils.R;
import com.zhcs.utils.StringUtil;

//*****************************************************************************
/**
 * <p>Title:DriverController</p>
 * <p>Description: 司机管理</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
@Controller
@RequestMapping("driver")
public class DriverController extends AbstractController  {
	@Autowired
	private DriverService driverService;
	
	@RequestMapping("/driver.html")
	public String list(){
		return "driver/driver.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("driver:list")
	public R list(String sidx, String order, Integer page, Integer limit){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sidx", sidx);
		map.put("order", order);
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		
		//查询列表数据
		List<DriverEntity> driverList = driverService.queryList(map);
		int total = driverService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(driverList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}

	/**
	 * 完整的展示列表
	 */
	@ResponseBody
	@RequestMapping("/showList")
	@RequiresPermissions("driver:list")
	public R showList(String sidx, String order, Integer page, Integer limit,String condition){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sidx", sidx);
		map.put("order", order);
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		
		map.put("condition", condition);
		
		//查询列表数据
//		List<Map<String, Object>> driverList = driverService.queryFullList(map);
		List<Map<String, Object>> driverList = driverService.queryFullList1(map);
		int total = driverService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(driverList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{id}")
	@RequiresPermissions("driver:info")
	public R info(@PathVariable("id") Long id){
		DriverEntity driver = driverService.queryObject(id);
		Map<String, String> map = BeanUtil.bean2Map(driver);
		map.put("bdate", DateUtil.dateToStr(driver.getBdate(), DateUtil.DATE_CONSTANT));
		map.put("edate", DateUtil.dateToStr(driver.getEdate(), DateUtil.DATE_CONSTANT));
		return R.ok().put("driver", map);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("driver:save")
	public R save(@RequestBody DriverEntity driver){
	
		BeanUtil.fillCCUUD(driver, getUserId(), getUserId());
		driverService.save(driver);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("driver:update")
	public R update(@RequestBody DriverEntity driver){
		
		BeanUtil.fillCCUUD(driver, getUserId());
		driverService.update(driver);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete/{id}")
	@RequiresPermissions("driver:delete")
	public R delete(@PathVariable("id") Long id){
		if(PlatformContext.getGoalbalContext("adminId", String.class).equals(StringUtil.valueOf(getUserId()))){
			driverService.delete(id);
		} else {
			DriverEntity driver = new DriverEntity();
			driver.setId(id);
			driver.setStatus("0");
			BeanUtil.fillCCUUD(driver, getUserId());
			driverService.update(driver);
		}
		return R.ok();
	}
	
}
