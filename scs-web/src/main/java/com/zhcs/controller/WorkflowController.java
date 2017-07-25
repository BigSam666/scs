package com.zhcs.controller;

import java.util.ArrayList;
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

import com.zhcs.entity.WorkflowEntity;
import com.zhcs.service.SysUserRoleService;
import com.zhcs.service.WorkflowService;
import com.zhcs.utils.BeanUtil;
import com.zhcs.utils.PageUtils;
import com.zhcs.utils.R;
import com.zhcs.utils.Str2PYUtil;

//*****************************************************************************
/**
 * <p>Title:WorkflowController</p>
 * <p>Description: 工作流</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
@Controller
@RequestMapping("workflow")
public class WorkflowController extends AbstractController  {
	@Autowired
	private WorkflowService workflowService;
	@Autowired
	private SysUserRoleService sysUserRoleService;
	
	@RequestMapping("/workflow.html")
	public String list(){
		return "workflow/workflow.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("workflow:list")
	public R list(String sidx, String order, Integer page, Integer limit){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sidx", sidx);
		map.put("order", order);
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		
		//查询列表数据
		List<WorkflowEntity> workflowList = workflowService.queryList(map);
		List<Long> roleIdList = null;
		
		for (WorkflowEntity workflowEntity : workflowList) {
			roleIdList = new ArrayList<Long>();
			for (String str : workflowEntity.getRoleids().split(",")) {
				roleIdList.add(Long.valueOf(str));
			}
			workflowEntity.setRoleids(sysUserRoleService.queryRoleText(roleIdList));
		}
		int total = workflowService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(workflowList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{id}")
	@RequiresPermissions("workflow:info")
	public R info(@PathVariable("id") Long id){
		WorkflowEntity workflow = workflowService.queryObject(id);
		return R.ok().put("workflow", workflow);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("workflow:save")
	public R save(@RequestBody WorkflowEntity workflow){
		workflow.setNodeno(Str2PYUtil.getFirstSpell(workflow.getNodename()));
		workflow.setNumber(Str2PYUtil.getFirstSpell(workflow.getNm()));
		BeanUtil.fillCCUUD(workflow, getUserId(), getUserId());
		workflowService.save(workflow);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("workflow:update")
	public R update(@RequestBody WorkflowEntity workflow){
		workflow.setNodeno(Str2PYUtil.getFirstSpell(workflow.getNodename()));
		workflow.setNumber(Str2PYUtil.getFirstSpell(workflow.getNm()));
		BeanUtil.fillCCUUD(workflow, getUserId());
		workflowService.update(workflow);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete/{id}")
	@RequiresPermissions("workflow:delete")
	public R delete(@PathVariable("id") Long id){
			workflowService.delete(id);
		return R.ok();
	}
	
}
