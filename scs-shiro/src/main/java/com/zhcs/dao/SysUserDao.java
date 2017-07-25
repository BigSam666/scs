package com.zhcs.dao;

import com.zhcs.entity.SysDepartmentEntity;
import com.zhcs.entity.SysRoleEntity;
import com.zhcs.entity.SysUserEntity;

import java.util.List;
import java.util.Map;

//*****************************************************************************
/**
 * <p>Title:SysUserDao</p>
 * <p>Description:系统用户</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司</p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public interface SysUserDao extends BaseDao<SysUserEntity> {
	
	/**
	 * 查询用户的所有权限
	 * @param userId  用户ID
	 */
	List<String> queryAllPerms(Long userId);
	
	/**
	 * 查询用户的所有菜单ID
	 */
	List<Long> queryAllMenuId(Long userId);
	
	/**
	 * 根据用户名，查询系统用户
	 */
	SysUserEntity queryByUserName(String username);

	SysUserEntity queryByOpenId(String openId);

	SysUserEntity queryByMobile(String mobile);
	
	/**
	 * 修改密码
	 */
	int updatePassword(Map<String, Object> map);
	
	/**
	 * ztree需要，根据部门一级一级展开到人
	 */
	List<Map<String, Object>> queryListTree();

	List<Map<String, Object>> getUserListByMsg();

	/**
	 * 查询指定用户所属的部门
	 */
	List<SysDepartmentEntity> queryDepartmentByUserId(Long userId);

	/**
	 * 查询指定部门下的所有用户（注意不会递归）
	 */
	List<SysUserEntity> queryUsersBydepts(List<Long> depts);

	/**
	 * 查询指定用户所属的角色
	 */
	List<SysRoleEntity> queryRoleByUser(Long id);

}
