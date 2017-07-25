package com.zhcs.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhcs.dao.SysRoleMenuDao;
import com.zhcs.service.SysRoleMenuService;
import com.zhcs.utils.DateUtil;
import com.zhcs.utils.ShiroUtils;



/**
 * 角色与菜单对应关系
 * 
 * @author Alter
 * @email Alter@Alter.com
 * @date 2016年9月18日 上午9:44:35
 */
@Service("sysRoleMenuService")
public class SysRoleMenuServiceImpl implements SysRoleMenuService {
	@Autowired
	private SysRoleMenuDao sysRoleMenuDao;

	@Override
	@Transactional
	public void saveOrUpdate(Long roleId, List<Long> menuIdList) {
		if(menuIdList.size() == 0){
			return ;
		}
		//先删除角色与菜单关系
		sysRoleMenuDao.delete(roleId);
		
		//保存角色与菜单关系
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("roleid", roleId);
		map.put("menuIdList", menuIdList);
		map.put("crtid", ShiroUtils.getUserId());
		map.put("updid", ShiroUtils.getUserId());
		map.put("crttm", DateUtil.getSystemDate());
		map.put("updtm", DateUtil.getSystemDate());
		sysRoleMenuDao.save(map);
	}

	@Override
	public List<Long> queryMenuIdList(Long roleId) {
		return sysRoleMenuDao.queryMenuIdList(roleId);
	}
	
	@Override
	@Transactional
	public void deleteBatch(Object[] id) {
		sysRoleMenuDao.deleteBatch(id);
	}

}
