package com.zhcs.service;

import com.zhcs.entity.SysDepartmentEntity;
import com.zhcs.entity.SysRoleEntity;
import com.zhcs.entity.SysUserEntity;

import java.util.List;
import java.util.Map;

//*****************************************************************************
/**
 * <p>Title:SysUserService</p>
 * <p>Description:系统用户</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司</p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public interface SysUserService {
	
	/**
	 * 查询用户的所有权限
	 * @param userId  用户ID
	 */
	List<String> queryAllPerms(Long id);
	
	/**
	 * 查询用户的所有菜单ID
	 */
	List<Long> queryAllMenuId(Long id);
	
	/**
	 * 根据用户名，查询系统用户
	 */
	SysUserEntity queryByUserName(String username);
	
	SysUserEntity queryByOpenId(String openId);
	
	/**
	 * 根据用户ID，查询用户
	 * @param userId
	 * @return
	 */
	SysUserEntity queryObject(Long id);
	
	/**
	 * 查询用户列表
	 */
	List<SysUserEntity> queryList(Map<String, Object> map);

	List<Map<String, Object>> getUserListByMsg();
	
	/**
	 * 查询总数
	 */
	int queryTotal(Map<String, Object> map);
	
	/**
	 * 保存用户
	 */
	void save(SysUserEntity user);
	
	/**
	 * 修改用户
	 */
	void update(SysUserEntity user);
	
	/**
	 * 修改用户
	 */
	void updateUser(SysUserEntity user);
	
	/**
	 * 删除用户
	 */
	void deleteBatch(Long[] ids);
	
	/**
	 * 修改密码
	 * @param userId       用户ID
	 * @param password     原密码
	 * @param newPassword  新密码
	 */
	int updatePassword(Long id, String password, String newPassword, String salt);
	
	/**
	 * ztree需要，根据部门一级一级展开到人
	 */
	List<Map<String, Object>> queryListTree();

	/**
	 * 查询指定用户所属的部门
	 */
	List<SysDepartmentEntity> queryDepartmentByUser(Long userId);

	SysUserEntity queryByMobile(String mobile);
	
	/**
	 * 查询用户所属的项目部
	 */
	List<SysDepartmentEntity> getUserProject(Long userId);
	
	/**
	 * 查询用户所属的事业部
	 */
	List<SysDepartmentEntity> getUserInstitution(Long userId);

	/**
	 * 查询指定部门下的所有用户（注意不会递归）
	 */
	List<SysUserEntity> queryUsersBydepts(List<Long> depts);

	/**
	 * 查询指定用户的角色
	 */
	List<SysRoleEntity> queryRoleByUser(Long id);
}
