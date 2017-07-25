package com.zhcs.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.zhcs.annotation.SysLog;
import com.zhcs.entity.SysRoleEntity;
import com.zhcs.service.SysRoleMenuService;
import com.zhcs.service.SysRoleService;
import com.zhcs.utils.BeanUtil;
import com.zhcs.utils.PageUtils;
import com.zhcs.utils.R;

//*****************************************************************************
/**
 * <p>Title:SysRoleController</p>
 * <p>Description:角色管理</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司</p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
@RestController
@RequestMapping("/sys/role")
public class SysRoleController extends AbstractController {
	@Autowired
	private SysRoleService sysRoleService;
	@Autowired
	private SysRoleMenuService sysRoleMenuService;
	
	/**
	 * 角色列表
	 */
	@SysLog("获取角色列表")
	@RequestMapping("/list")
	@RequiresPermissions("sys:role:list")
	public R list(String name, String sidx, String order, Integer page, Integer limit){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", name);
		map.put("sidx", sidx);
		map.put("order", order);
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		
		//查询列表数据
		List<Map<String, Object>> list = sysRoleService.queryList(map);
		int total = sysRoleService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(list, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	/**
	 * 角色列表
	 */
	@RequestMapping("/select")
	@RequiresPermissions("sys:role:select")
	public R select(){
		//查询列表数据
		List<Map<String, Object>> list = sysRoleService.queryList(new HashMap<String, Object>());
		
		return R.ok().put("list", list);
	}
	
	/**
	 * 角色信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("sys:role:info")
	public R info(@PathVariable("id") Long id){
		SysRoleEntity role = sysRoleService.queryObject(id);
		
		//查询角色对应的菜单
		List<Long> menuIdList = sysRoleMenuService.queryMenuIdList(id);
		role.setMenuIdList(menuIdList);
		
		return R.ok().put("role", role);
	}
	
	/**
	 * 保存角色
	 */
	@RequestMapping("/save")
	@RequiresPermissions("sys:role:save")
	public R save(@RequestBody SysRoleEntity role){
		if(StringUtils.isBlank(role.getName())){
			return R.error("角色名称不能为空");
		}

		BeanUtil.fillCCUUD(role, getUserId(), getUserId());
		sysRoleService.save(role);
		
		return R.ok();
	}
	
	/**
	 * 修改角色
	 */
	@RequestMapping("/update")
	@RequiresPermissions("sys:role:update")
	public R update(@RequestBody SysRoleEntity role){
		if(StringUtils.isBlank(role.getName())){
			return R.error("角色名称不能为空");
		}

		BeanUtil.fillCCUUD(role, getUserId());
		sysRoleService.update(role);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete/{id}")
	@RequiresPermissions("sys:role:delete")
	public R delete(@PathVariable("id") Long id){
		/*if(PlatformContext.getGoalbalContext("adminId", String.class).equals(StringUtil.valueOf(getUserId()))){*/
			Long ids[] = new Long[1];
			ids[0] = id;
			sysRoleService.deleteBatch(ids);
		/*} else {
			SysRoleEntity role = new SysRoleEntity();
			role.setId(id);
			BeanUtil.fillCCUUD(role, getUserId());
			sysRoleService.update(role);
		}*/
		return R.ok();
	}
}
