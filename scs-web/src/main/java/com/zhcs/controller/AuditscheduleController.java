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

import com.zhcs.entity.AuditscheduleEntity;
import com.zhcs.service.AuditscheduleService;
import com.zhcs.utils.BeanUtil;
import com.zhcs.utils.PageUtils;
import com.zhcs.utils.R;

//*****************************************************************************
/**
 * <p>Title:AuditscheduleController</p>
 * <p>Description: 审核进度</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
@Controller
@RequestMapping("auditschedule")
public class AuditscheduleController extends AbstractController  {
	@Autowired
	private AuditscheduleService auditscheduleService;
	
	@RequestMapping("/auditschedule.html")
	public String list(){
		return "auditschedule/auditschedule.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("auditschedule:list")
	public R list(String sidx, String order, Integer page, Integer limit){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sidx", sidx);
		map.put("order", order);
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		
		//查询列表数据
		List<AuditscheduleEntity> auditscheduleList = auditscheduleService.queryList(map);
		int total = auditscheduleService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(auditscheduleList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{id}")
	@RequiresPermissions("auditschedule:info")
	public R info(@PathVariable("id") Long id){
		AuditscheduleEntity auditschedule = auditscheduleService.queryObject(id);
		
		return R.ok().put("auditschedule", auditschedule);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("auditschedule:save")
	public R save(@RequestBody AuditscheduleEntity auditschedule){
	
		BeanUtil.fillCCUUD(auditschedule, getUserId(), getUserId());
		auditscheduleService.save(auditschedule);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("auditschedule:update")
	public R update(@RequestBody AuditscheduleEntity auditschedule){
		
		BeanUtil.fillCCUUD(auditschedule, getUserId());
		auditscheduleService.update(auditschedule);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete/{id}")
	@RequiresPermissions("auditschedule:delete")
	public R delete(@PathVariable("id") Long id){
		auditscheduleService.delete(id);
		return R.ok();
	}
	
}
