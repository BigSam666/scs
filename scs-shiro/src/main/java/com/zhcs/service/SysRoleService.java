package com.zhcs.service;

import com.zhcs.entity.SysRoleEntity;

import java.util.List;
import java.util.Map;

//*****************************************************************************
/**
 * <p>Title:SysRoleService</p>
 * <p>Description:角色</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司</p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public interface SysRoleService {
	
	SysRoleEntity queryObject(Long id);
	
	List<Map<String, Object>> queryDept(Long id);
	
	List<Map<String, Object>> queryList(Map<String, Object> map);

	Map<String, Object> queryTree(String id);
	
	int queryTotal(Map<String, Object> map);
	
	void save(SysRoleEntity role);
	
	void update(SysRoleEntity role);
	
	void deleteBatch(Long[] ids);
}
