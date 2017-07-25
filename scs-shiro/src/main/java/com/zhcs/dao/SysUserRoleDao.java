package com.zhcs.dao;

import com.zhcs.entity.SysUserRoleEntity;

import java.util.List;
import java.util.Map;

//*****************************************************************************
/**
 * <p>Title:SysUserRoleDao</p>
 * <p>Description:用户与角色对应关系</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司</p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public interface SysUserRoleDao extends BaseDao<SysUserRoleEntity> {
	
	/**
	 * 根据用户ID，获取角色ID列表
	 */
	List<Long> queryRoleIdList(Long id);

	String queryRoleText(Map<String, Object> map);
}
