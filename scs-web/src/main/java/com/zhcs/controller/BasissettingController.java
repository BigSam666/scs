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

import com.zhcs.entity.BasissettingEntity;
import com.zhcs.service.BasissettingService;
import com.zhcs.utils.BeanUtil;
import com.zhcs.utils.PageUtils;
import com.zhcs.utils.R;

//*****************************************************************************
/**
 * <p>Title:BasissettingController</p>
 * <p>Description: 基础设置</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
@Controller
@RequestMapping("basissetting")
public class BasissettingController extends AbstractController  {
	@Autowired
	private BasissettingService basissettingService;
	
	@RequestMapping("/basissetting.html")
	public String list(){
		return "basissetting/basissetting.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("basissetting:list")
	public R list(String sidx, String order, Integer page, Integer limit){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sidx", sidx);
		map.put("order", order);
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		
		//查询列表数据
		List<BasissettingEntity> basissettingList = basissettingService.queryList(map);
		int total = basissettingService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(basissettingList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{id}")
	@RequiresPermissions("basissetting:info")
	public R info(@PathVariable("id") Long id){
		BasissettingEntity basissetting = basissettingService.queryObject(id);
		
		return R.ok().put("basissetting", basissetting);
	}
	
	
	/**
	 * ID
	 */
	@ResponseBody
	@RequestMapping("/getid")
	@RequiresPermissions("basissetting:getid")
	public R getid(){
		String id = basissettingService.queryId();
		
		return R.ok().put("id", id);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("basissetting:save")
	public R save(@RequestBody BasissettingEntity basissetting){
	
		BeanUtil.fillCCUUD(basissetting, getUserId(), getUserId());
		basissetting.setStatus(1);
		basissettingService.save(basissetting);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("basissetting:update")
	public R update(@RequestBody BasissettingEntity basissetting){
		
		BeanUtil.fillCCUUD(basissetting, getUserId(), getUserId());
		basissettingService.update(basissetting);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete/{id}")
	@RequiresPermissions("basissetting:delete")
	public R delete(@PathVariable("id") Long id){
		basissettingService.delete(id);
		return R.ok();
	}
	
}
