package com.zhcs.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhcs.dao.SysUserDeptDao;
import com.zhcs.service.SysUserDeptService;
import com.zhcs.utils.DateUtil;
import com.zhcs.utils.ShiroUtils;
import com.zhcs.utils.StringUtil;


//*****************************************************************************
/**
 * <p>Title:SysUserDeptServiceImpl</p>
 * <p>Description: 用户与部门对应关系</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************

@Service("sysUserDeptService")
public class SysUserDeptServiceImpl implements SysUserDeptService {
	@Autowired
	private SysUserDeptDao sysUserDeptDao;
	
	@Override
	@Transactional
	public void saveOrUpdate(Long userId, List<Long> deptIdList) {
		if(StringUtil.isBlank(deptIdList)){
			return ;
		}
		
		//先删除用户与部门关系
		sysUserDeptDao.delete(userId);
		
		//保存用户与部门关系
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userid", userId);
		map.put("deptIdList", deptIdList);
		map.put("crtid", ShiroUtils.getUserId());
		map.put("updid", ShiroUtils.getUserId());
		map.put("crttm", DateUtil.getSystemDate());
		map.put("updtm", DateUtil.getSystemDate());
		sysUserDeptDao.save(map);
	}

	@Override
	public List<Long> queryDeptIdList(Long id) {
		return sysUserDeptDao.queryDeptIdList(id);
	}
	
	@Override
	public String queryDeptText(List<Long> deptIds) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("deptIds", deptIds);
		return sysUserDeptDao.queryDeptText(map);
	}

	@Override
	@Transactional
	public void deleteBatch(Object[] id) {
		sysUserDeptDao.deleteBatch(id);
	}

	
	
}
