package com.zhcs.dao;

import java.util.List;
import java.util.Map;

import com.zhcs.entity.SysDepartmentEntity;
import com.zhcs.entity.SysUserEntity;

//*****************************************************************************
/**
 * <p>Title:DepartmentDao</p>
 * <p>Description: 部门</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public interface SysDepartmentDao extends BaseDao<SysDepartmentEntity> {
	
	List<SysDepartmentEntity> queryListTree();

	List<Map<String, Object>> queryListTree2();
	
	/**
	 * 通过部门id查询部门树结构
	 * @Title: queryDepartmentListTree
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param id
	 * @return
	 * @throws
	 */
	List<Map<String, Object>> queryDepartmentListTree(String ids);
	
	String queryNodeDepartmentIds(Long id);
	
	String queryChildNodeDeptIds(Long id);
	
	List<Map<String, Object>> queryListTreeForCmn();
	
	List<Map<String, Object>> queryListTreeForCmn2(String ids);

	List<Map<String, Object>> queryList2(Map<String, Object> map);

	List<SysDepartmentEntity> queryObjectByPid(Long pid);

	List<SysDepartmentEntity> queryListByOtype(String projectOtype);

	/**
	 * 根据pid和Otype查询列表
	 */
	List<SysDepartmentEntity> queryListByPidAndOtype(Long id, String projectOtype);

	/**
	 * 根据班级获取人
	 */
	List<SysUserEntity> queryPersonByClass(Long id);
}
