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

import com.zhcs.entity.TrashtabEntity;
import com.zhcs.service.TrashtabService;
import com.zhcs.utils.BeanUtil;
import com.zhcs.utils.PageUtils;
import com.zhcs.utils.R;
import com.zhcs.utils.StringUtil;

//*****************************************************************************
/**
 * <p>Title:TrashtabController</p>
 * <p>Description: 垃圾桶标记</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
@Controller
@RequestMapping("trashtab")
public class TrashtabController extends AbstractController  {
	@Autowired
	private TrashtabService trashtabService;
	
	@RequestMapping("/trashtab.html")
	public String list(){
		return "trashtab/trashtab.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("trashtab:list")
	public R list(String qstreet, String sidx, String order, Integer page, Integer limit){
		Map<String, Object> map = new HashMap<String, Object>();
		if (!StringUtil.isBlank(qstreet)) {
			map.put("qstreet", qstreet.trim());
		}
		map.put("sidx", sidx);
		map.put("order", order);
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		
		//查询列表数据
		List<TrashtabEntity> trashtabList = trashtabService.queryList(map);
		int total = trashtabService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(trashtabList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{id}")
	@RequiresPermissions("trashtab:info")
	public R info(@PathVariable("id") Long id){
		TrashtabEntity trashtab = trashtabService.queryObject(id);
		
		return R.ok().put("trashtab", trashtab);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("trashtab:save")
	public R save(@RequestBody TrashtabEntity trashtab){
	
		BeanUtil.fillCCUUD(trashtab, getUserId(), getUserId());
		trashtabService.save(trashtab);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("trashtab:update")
	public R update(@RequestBody TrashtabEntity trashtab){
		
		BeanUtil.fillCCUUD(trashtab, getUserId());
		trashtabService.update(trashtab);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete/{id}")
	@RequiresPermissions("trashtab:delete")
	public R delete(@PathVariable("id") Long id){
		trashtabService.delete(id);
		return R.ok();
	}
	
}
