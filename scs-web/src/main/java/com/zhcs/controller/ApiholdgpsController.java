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

import com.zhcs.entity.ApiholdgpsEntity;
import com.zhcs.service.ApiholdgpsService;
import com.zhcs.utils.BeanUtil;
import com.zhcs.utils.PageUtils;
import com.zhcs.utils.R;

//*****************************************************************************
/**
 * <p>Title:ApiholdgpsController</p>
 * <p>Description: 阿姨机-gps</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
@Controller
@RequestMapping("apiholdgps")
public class ApiholdgpsController extends AbstractController  {
	@Autowired
	private ApiholdgpsService apiholdgpsService;
	
	@RequestMapping("/apiholdgps.html")
	public String list(){
		return "apiholdgps/apiholdgps.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("apiholdgps:list")
	public R list(String sidx, String order, Integer page, Integer limit){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sidx", sidx);
		map.put("order", order);
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		
		//查询列表数据
		List<ApiholdgpsEntity> apiholdgpsList = apiholdgpsService.queryList(map);
		int total = apiholdgpsService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(apiholdgpsList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{id}")
	@RequiresPermissions("apiholdgps:info")
	public R info(@PathVariable("id") Long id){
		ApiholdgpsEntity apiholdgps = apiholdgpsService.queryObject(id);
		
		return R.ok().put("apiholdgps", apiholdgps);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("apiholdgps:save")
	public R save(@RequestBody ApiholdgpsEntity apiholdgps){
	
		BeanUtil.fillCCUUD(apiholdgps, getUserId(), getUserId());
		apiholdgpsService.save(apiholdgps);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("apiholdgps:update")
	public R update(@RequestBody ApiholdgpsEntity apiholdgps){
		
		BeanUtil.fillCCUUD(apiholdgps, getUserId());
		apiholdgpsService.update(apiholdgps);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete/{id}")
	@RequiresPermissions("apiholdgps:delete")
	public R delete(@PathVariable("id") Long id){
		apiholdgpsService.delete(id);
		return R.ok();
	}
	
}
