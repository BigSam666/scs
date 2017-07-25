package com.zhcs.utils;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zhcs.dao.SysDepartmentDao;
import com.zhcs.dao.SysUserDeptDao;

@Component
public class DepartmentUtil {

	@Autowired
	private SysUserDeptDao sysUserDeptDao;
	@Autowired
	private SysDepartmentDao sysdepartmentDao;
	
	/**
	 * 查询结点目录下的所有子节点(包括当前用户的目录结点)
	 * @Title: queryNodeDeptChildIds
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @return
	 * @throws
	 */
	public String queryNodeDeptChildIds()
	{
		long uid = ShiroUtils.getUserId();
		//通过uid查询 所属部门
		List<Long> deptIdList = sysUserDeptDao.queryDeptIdList(uid);
		//通过所属部门查处列表
		StringBuilder sb = new StringBuilder();
		for (Long deptId : deptIdList) {
			sb.append(",").append(deptId);
			String deptIds = sysdepartmentDao.queryChildNodeDeptIds(deptId);
			if (deptIds != null && !"".equals(deptIds)) {
				sb.append(",").append(deptIds);
			}
		}
		if (sb.length() > 0) {
			sb.deleteCharAt(0);
		}
		return sb.toString();
	}
	
	/**
	 * 查询结点目录下的所有子节点(不包括当前用户的目录结点)
	 * @Title: queryNodeDeptChildIds1
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @return
	 * @throws
	 */
	public String queryNodeDeptChildIds1()
	{
		long uid = ShiroUtils.getUserId();
		//通过uid查询 所属部门
		List<Long> deptIdList = sysUserDeptDao.queryDeptIdList(uid);
		//通过所属部门查处列表
		StringBuilder sb = new StringBuilder();
		for (Long deptId : deptIdList) {
			String deptIds = sysdepartmentDao.queryChildNodeDeptIds(deptId);
			if (deptIds != null && !"".equals(deptIds)) {
				sb.append(",").append(deptIds);
			}
		}
		if (sb.length() > 0) {
			sb.deleteCharAt(0);
		}
		return sb.toString();
	}
	
	
	/**
	 * 查询某个节点目录的链(所有父节点  和  所有子节点)
	 * @Title: queryNodeDeptLinkIds
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @return
	 * @throws
	 */
	public String queryNodeDeptLinkIds()
	{
		long uid = ShiroUtils.getUserId();
		//通过uid查询 所属部门
		List<Long> deptIdList = sysUserDeptDao.queryDeptIdList(uid);
		//通过所属部门查处列表
		StringBuilder sb = new StringBuilder();
		for (Long deptId : deptIdList) {
			String deptIds = sysdepartmentDao.queryNodeDepartmentIds(deptId);
			if (deptIds != null && !"".equals(deptIds)) {
				sb.append(",").append(deptIds);
			}
		}
		if (sb.length() > 0) {
			sb.deleteCharAt(0);
		}
		return sb.toString();
	}
	
}