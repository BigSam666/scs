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

import com.zhcs.entity.CentpointEntity;
import com.zhcs.service.CentpointService;
import com.zhcs.utils.BeanUtil;
import com.zhcs.utils.PageUtils;
import com.zhcs.utils.R;

//*****************************************************************************
/**
 * <p>Title:CentpointController</p>
 * <p>Description: 中心点设置</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
@Controller
@RequestMapping("centpoint")
public class CentpointController extends AbstractController  {
	@Autowired
	private CentpointService centpointService;
	
	@RequestMapping("/centpoint.html")
	public String list(){
		return "centpoint/centpoint.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("centpoint:list")
	public R list(String sidx, String order, Integer page, Integer limit){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sidx", sidx);
		map.put("order", order);
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		
		//查询列表数据
		List<Map<String, Object>> centpointList = centpointService.queryList(map);
		int total = centpointService.queryTotal(map);
		PageUtils pageUtil = new PageUtils(centpointList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{id}")
	@RequiresPermissions("centpoint:info")
	public R info(@PathVariable("id") Long id){
		CentpointEntity centpoint = centpointService.queryObject(id);
		
		return R.ok().put("centpoint", centpoint);
	}
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/infoByProject/{id}")
	@RequiresPermissions("centpoint:info")
	public R infoByProject(@PathVariable("id") Long id){
		CentpointEntity centpoint = centpointService.queryObjectByProject(id);
		
		return R.ok().put("centpoint", centpoint);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("centpoint:save")
	public R save(@RequestBody CentpointEntity centpoint){
	
		BeanUtil.fillCCUUD(centpoint, getUserId(), getUserId());
		centpointService.save(centpoint);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("centpoint:update")
	public R update(@RequestBody CentpointEntity centpoint){
		
		BeanUtil.fillCCUUD(centpoint, getUserId());
		centpointService.update(centpoint);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete/{id}")
	@RequiresPermissions("centpoint:delete")
	public R delete(@PathVariable("id") Long id){
		centpointService.delete(id);
		return R.ok();
	}
	
}
