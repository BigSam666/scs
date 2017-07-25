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

import com.zhcs.context.PlatformContext;
import com.zhcs.entity.SysDepartmentEntity;
import com.zhcs.service.BaseCodeService;
import com.zhcs.service.SysDepartmentService;
import com.zhcs.service.SysRoleService;
import com.zhcs.utils.BeanUtil;
import com.zhcs.utils.LogUtil;
import com.zhcs.utils.PageUtils;
import com.zhcs.utils.R;
import com.zhcs.utils.StringUtil;

//*****************************************************************************
/**
 * <p>Title:DepartmentController</p>
 * <p>Description: 部门</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
@Controller
@RequestMapping("/sys/department")
public class SysDepartmentController extends AbstractController  {
	@Autowired
	private SysDepartmentService sysDepartmentService;
	@Autowired
	private BaseCodeService baseCodeService;
	@Autowired
	private SysRoleService sysRoleService;
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("department:list")
	public R list(String sidx, String order, Integer page, Integer limit){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		map.put("sidx", sidx);
		map.put("order", order);
		
		//查询列表数据
		List<Map<String, Object>> departmentList = sysDepartmentService.queryList(map);
		Map<String, String> m = null;
		String data = "";
		for (Map<String, Object> map2 : departmentList) {
			data = StringUtil.valueOf(map2.get("otype"));
			m = new HashMap<String, String>();
			m.put("data", data);
			m.put("value", baseCodeService.selectByTypeValue("otype", data).getCnm());
			map2.put("otype", m);
		}
		
		int total = sysDepartmentService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(departmentList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	/**
	 * 选择部门(添加、修改菜单)
	 */
	@ResponseBody
	@RequestMapping("/select")
	@RequiresPermissions("department:select")
	public R select(){
		//查询列表数据
		//List<SysDepartmentEntity> deptList = sysDepartmentService.queryListTree();

//		List<Map<String, Object>> deptList = sysDepartmentService.queryListTree2();
		List<Map<String, Object>> deptList = sysDepartmentService.queryListTree3();
		List<Map<String, Object>> newDeptList = new ArrayList<Map<String, Object>>();
		for (Map<String, Object> map : deptList) {
			int otype = Integer.valueOf(map.get("otype").toString());
			if (otype <= 3) {
				newDeptList.add(map);
			}
		}
		//添加顶级菜单
		/*SysDepartmentEntity root = new SysDepartmentEntity();
		root.setId(0L);
		root.setName("company");
		root.setPid(0L);
		root.setOpen(true);
		deptList.add(root);*/
		
		return R.ok().put("deptList", newDeptList);
	}
	
	/**
	 * 选择部门+角色
	 */
	@ResponseBody
	@RequestMapping("/selectDR")
	@RequiresPermissions("department:selectDR")
	public R selectDR(){
		//查询列表数据
		List<Map<String, Object>> deptList = sysDepartmentService.queryListTree2();
		List<Map<String, Object>> sysRoles = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> sysRole = null;
		String children[] = null;
		Map<String, Object> r = null;
		List<Map<String, Object>> childrens = new ArrayList<Map<String, Object>>();;
		for (Map<String, Object> dept : deptList) {
			sysRole = sysRoleService.queryDept(Long.valueOf(StringUtil.valueOf(dept.get("id"))));
			sysRoles.addAll(sysRole);
			for (Map<String, Object> role : sysRole) {
				if(StringUtil.isValid(role)){
					children = StringUtil.valueOf(role.get("chren")).split(",");
					for (String temp : children) {
						r = sysRoleService.queryTree(temp);
						if(StringUtil.isValid(r)){
							LogUtil.debug(""+r);
							childrens.add(r);
						}
					}
				}
			}
		}
		if(StringUtil.isValid(sysRoles)){
			deptList.addAll(sysRoles);
		}
		if(StringUtil.isValid(childrens)){
			deptList.addAll(childrens);
		}
		return R.ok().put("deptList", deptList);
	}
	
	/**
	 * 用户树，角色-姓名
	 */
	@ResponseBody
	@RequestMapping("/selectForCmn")
	@RequiresPermissions("department:selectForCmn")
	public R selectForCmn(){
		//查询列表数据
//		List<Map<String, Object>> deptList = sysDepartmentService.queryListTreeForCmn();
		List<Map<String, Object>> deptList = sysDepartmentService.queryListTreeForCmn2();
		return R.ok().put("deptList", deptList);
	}
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{id}")
	@RequiresPermissions("department:info")
	public R info(@PathVariable("id") Long id){
		SysDepartmentEntity department = sysDepartmentService.queryObject(id);
		
		return R.ok().put("department", department);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("department:save")
	public R save(@RequestBody SysDepartmentEntity department){
	
		BeanUtil.fillCCUUD(department, getUserId(), getUserId());
		sysDepartmentService.save(department);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("department:update")
	public R update(@RequestBody SysDepartmentEntity department){
		
		BeanUtil.fillCCUUD(department, getUserId());
		sysDepartmentService.update(department);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete/{id}")
	@RequiresPermissions("department:delete")
	public R delete(@PathVariable("id") Long id){
		if(PlatformContext.getGoalbalContext("adminId", String.class).equals(StringUtil.valueOf(getUserId()))){
			sysDepartmentService.delete(id);
		} else {
			SysDepartmentEntity department = new SysDepartmentEntity();
			department.setId(id);
			department.setValid("0");
			BeanUtil.fillCCUUD(department, getUserId());
			sysDepartmentService.update(department);
		}
		return R.ok();
	}
	
}
