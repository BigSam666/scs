package com.zhcs.service;

import java.util.List;

//*****************************************************************************
/**
 * <p>Title:SysUserRoleService</p>
 * <p>Description:用户与角色对应关系</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司</p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public interface SysUserRoleService {
	
	void saveOrUpdate(Long id, List<Long> roleIdList);
	
	/**
	 * 根据用户ID，获取角色ID列表
	 */
	List<Long> queryRoleIdList(Long id);
	
	void deleteBatch(Object[] id);

	/**
	 * 根据用户ID，获取角色名字
	 */
	String queryRoleText(List<Long> roleIdList);

}
