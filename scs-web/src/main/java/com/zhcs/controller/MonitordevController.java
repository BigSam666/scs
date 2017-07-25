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

import com.zhcs.entity.MonitordevEntity;
import com.zhcs.service.MonitordevService;
import com.zhcs.utils.BeanUtil;
import com.zhcs.utils.PageUtils;
import com.zhcs.utils.R;
import com.zhcs.utils.StringUtil;

//*****************************************************************************
/**
 * <p>Title:MonitordevController</p>
 * <p>Description: 监控设备</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
@Controller
@RequestMapping("monitordev")
public class MonitordevController extends AbstractController  {
	@Autowired
	private MonitordevService monitordevService;
	
	@RequestMapping("/monitordev.html")
	public String list(){
		return "monitordev/monitordev.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("monitordev:list")
	public R list(String sidx,String qdevid, String order, Integer page, Integer limit){
		Map<String, Object> map = new HashMap<String, Object>();
		if (!StringUtil.isBlank(qdevid)) {
			map.put("qdevid", qdevid.trim());
		}
		map.put("sidx", sidx);
		map.put("order", order);
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		
		//查询列表数据
		List<MonitordevEntity> monitordevList = monitordevService.queryList(map);
		int total = monitordevService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(monitordevList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{id}")
	@RequiresPermissions("monitordev:info")
	public R info(@PathVariable("id") Long id){
		MonitordevEntity monitordev = monitordevService.queryObject(id);
		
		return R.ok().put("monitordev", monitordev);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("monitordev:save")
	public R save(@RequestBody MonitordevEntity monitordev){
	
		BeanUtil.fillCCUUD(monitordev, getUserId(), getUserId());
		monitordevService.save(monitordev);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("monitordev:update")
	public R update(@RequestBody MonitordevEntity monitordev){
		
		BeanUtil.fillCCUUD(monitordev, getUserId());
		monitordevService.update(monitordev);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete/{id}")
	@RequiresPermissions("monitordev:delete")
	public R delete(@PathVariable("id") Long id){
		monitordevService.delete(id);
		return R.ok();
	}
	
}
