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

import com.zhcs.entity.PositionEntity;
import com.zhcs.service.PositionService;
import com.zhcs.utils.BeanUtil;
import com.zhcs.utils.PageUtils;
import com.zhcs.utils.R;

//*****************************************************************************
/**
 * <p>Title:PositionController</p>
 * <p>Description: 定位信息</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
@Controller
@RequestMapping("position")
public class PositionController extends AbstractController  {
	@Autowired
	private PositionService positionService;
	
	@RequestMapping("/position.html")
	public String list(){
		return "position/position.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("position:list")
	public R list(String sidx, String order, Integer page, Integer limit){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sidx", sidx);
		map.put("order", order);
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		
		//查询列表数据
		List<PositionEntity> positionList = positionService.queryList(map);
		int total = positionService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(positionList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{id}")
	@RequiresPermissions("position:info")
	public R info(@PathVariable("id") Long id){
		PositionEntity position = positionService.queryObject(id);
		
		return R.ok().put("position", position);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("position:save")
	public R save(@RequestBody PositionEntity position){
	
		BeanUtil.fillCCUUD(position, getUserId(), getUserId());
		positionService.save(position);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("position:update")
	public R update(@RequestBody PositionEntity position){
		
		BeanUtil.fillCCUUD(position, getUserId());
		positionService.update(position);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete/{id}")
	@RequiresPermissions("position:delete")
	public R delete(@PathVariable("id") Long id){
		positionService.delete(id);
		return R.ok();
	}
	
}
