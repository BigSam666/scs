package com.zhcs.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhcs.dao.SysRoleDao;
import com.zhcs.entity.SysRoleEntity;
import com.zhcs.service.SysRoleMenuService;
import com.zhcs.service.SysRoleService;

/**
 * 角色
 * 
 * @author Alter
 * @email Alter@Alter.com
 * @date 2016年9月18日 上午9:45:12
 */
@Service("sysRoleService")
public class SysRoleServiceImpl implements SysRoleService {
	@Autowired
	private SysRoleDao sysRoleDao;
	@Autowired
	private SysRoleMenuService sysRoleMenuService;

	@Override
	public SysRoleEntity queryObject(Long id) {
		return sysRoleDao.queryObject(id);
	}

	@Override
	public List<Map<String, Object>> queryDept(Long id) {
		return sysRoleDao.queryDept(id);
	}

	@Override
	public List<Map<String, Object>> queryList(Map<String, Object> map) {
		return sysRoleDao.queryList2(map);
	}
	
	@Override
	public Map<String, Object> queryTree(String id) {
		return sysRoleDao.queryTree(id);
	}

	@Override
	public int queryTotal(Map<String, Object> map) {
		return sysRoleDao.queryTotal(map);
	}

	@Override
	@Transactional
	public void save(SysRoleEntity role) {
		sysRoleDao.save(role);
		
		//保存角色与菜单关系
		sysRoleMenuService.saveOrUpdate(role.getId(), role.getMenuIdList());
	}

	@Override
	@Transactional
	public void update(SysRoleEntity role) {
		sysRoleDao.update(role);
		
		//更新角色与菜单关系
		sysRoleMenuService.saveOrUpdate(role.getId(), role.getMenuIdList());
	}

	@Override
	@Transactional
	public void deleteBatch(Long[] ids) {
		sysRoleDao.deleteBatch(ids);
		
		//删除角色与菜单关系
		sysRoleMenuService.deleteBatch(ids);
	}

}
