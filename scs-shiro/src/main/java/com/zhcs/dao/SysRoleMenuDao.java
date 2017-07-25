package com.zhcs.dao;

import com.zhcs.entity.SysRoleMenuEntity;

import java.util.List;

//*****************************************************************************
/**
 * <p>Title:SysRoleMenuDao</p>
 * <p>Description:角色与菜单对应关系</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司</p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public interface SysRoleMenuDao extends BaseDao<SysRoleMenuEntity> {
	
	/**
	 * 根据角色ID，获取菜单ID列表
	 */
	List<Long> queryMenuIdList(Long roleId);
}
