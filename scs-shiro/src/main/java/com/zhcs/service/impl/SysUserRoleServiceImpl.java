package com.zhcs.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhcs.dao.SysUserRoleDao;
import com.zhcs.service.SysUserRoleService;
import com.zhcs.utils.DateUtil;
import com.zhcs.utils.ShiroUtils;

/**
 * 用户与角色对应关系
 * 
 * @author Alter
 * @email Alter@Alter.com
 * @date 2016年9月18日 上午9:45:48
 */
@Service("sysUserRoleService")
public class SysUserRoleServiceImpl implements SysUserRoleService {
	@Autowired
	private SysUserRoleDao sysUserRoleDao;

	@Override
	@Transactional
	public void saveOrUpdate(Long userId, List<Long> roleIdList) {
		if(roleIdList.size() == 0){
			return ;
		}
		
		//先删除用户与角色关系
		sysUserRoleDao.delete(userId);
		
		//保存用户与角色关系
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userid", userId);
		map.put("roleIdList", roleIdList);
		map.put("crtid", ShiroUtils.getUserId());
		map.put("updid", ShiroUtils.getUserId());
		map.put("crttm", DateUtil.getSystemDate());
		map.put("updtm", DateUtil.getSystemDate());
		sysUserRoleDao.save(map);
	}

	@Override
	/*@Cacheable(value="menuCache", key="'UserRole'+#userId")*/
	public List<Long> queryRoleIdList(Long id) {
		return sysUserRoleDao.queryRoleIdList(id);
	}

	@Override
	public void deleteBatch(Object[] id) {
		sysUserRoleDao.deleteBatch(id);
	}

	@Override
	public String queryRoleText(List<Long> roleIds) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("roleIds", roleIds);
		return sysUserRoleDao.queryRoleText(map);
	}
}
