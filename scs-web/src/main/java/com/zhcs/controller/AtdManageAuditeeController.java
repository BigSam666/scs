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

import com.zhcs.entity.AtdManageAuditeeEntity;
import com.zhcs.service.AtdManageAuditeeService;
import com.zhcs.utils.BeanUtil;
import com.zhcs.utils.PageUtils;
import com.zhcs.utils.R;

//*****************************************************************************
/**
 * <p>Title:AtdManageAuditeeController</p>
 * <p>Description: 考勤管理审核</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
@Controller
@RequestMapping("atdmanageauditee")
public class AtdManageAuditeeController extends AbstractController  {
	@Autowired
	private AtdManageAuditeeService atdManageAuditeeService;
	
	@RequestMapping("/atdmanageauditee.html")
	public String list(){
		return "atdmanageauditee/atdmanageauditee.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("atdmanageauditee:list")
	public R list(String sidx, String order, Integer page, Integer limit,String condition){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sidx", sidx);
		map.put("order", order);
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		map.put("condition", condition);
		//查询列表数据
//		List<AtdManageAuditeeEntity> atdManageAuditeeList = atdManageAuditeeService.queryList(map);
		List<AtdManageAuditeeEntity> atdManageAuditeeList = atdManageAuditeeService.queryList1(map);
		int total = atdManageAuditeeService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(atdManageAuditeeList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{id}")
	@RequiresPermissions("atdmanageauditee:info")
	public R info(@PathVariable("id") Long id){
		AtdManageAuditeeEntity atdManageAuditee = atdManageAuditeeService.queryObject(id);
		
		return R.ok().put("atdManageAuditee", atdManageAuditee);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("atdmanageauditee:save")
	public R save(@RequestBody AtdManageAuditeeEntity atdManageAuditee){
	
		BeanUtil.fillCCUUD(atdManageAuditee, getUserId(), getUserId());
		atdManageAuditeeService.save(atdManageAuditee);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("atdmanageauditee:update")
	public R update(@RequestBody AtdManageAuditeeEntity atdManageAuditee){
		
		BeanUtil.fillCCUUD(atdManageAuditee, getUserId());
		atdManageAuditeeService.update(atdManageAuditee);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete/{id}")
	@RequiresPermissions("atdmanageauditee:delete")
	public R delete(@PathVariable("id") Long id){
		atdManageAuditeeService.delete(id);
		return R.ok();
	}
	
	/**
	 * 审核
	 */
	@ResponseBody
	@RequestMapping("/auditing")
	@RequiresPermissions("atdmanageauditee:auditing")
	public R auditing(@RequestBody AtdManageAuditeeEntity atdManageAuditee){
		
		atdManageAuditeeService.update(atdManageAuditee);
		
		return R.ok();
	}
	
}
