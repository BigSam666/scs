package com.zhcs.service;

import java.util.List;
import java.util.Map;

import com.zhcs.entity.SysDepartmentEntity;
import com.zhcs.entity.SysUserEntity;

//*****************************************************************************
/**
 * <p>Title:DepartmentService</p>
 * <p>Description: 部门</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public interface SysDepartmentService {
	
	SysDepartmentEntity queryObject(Long id);
	
	List<SysDepartmentEntity> queryObjectByPid(Long pid);

	/**
	 * 获取部门
	 */
	List<SysDepartmentEntity> queryListTree();
	
	/**
	 * 获取部门2
	 */
	List<Map<String, Object>> queryListTree2();
	
	List<Map<String, Object>> queryListTree3();
	
	/**
	 * 获取部门关于通讯
	 */
	List<Map<String, Object>> queryListTreeForCmn();
	
	List<Map<String, Object>> queryListTreeForCmn2();
	
	List<Map<String, Object>> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(SysDepartmentEntity department);
	
	void update(SysDepartmentEntity department);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);

	/**
	 * 返回当前部门和下面的所有子部门
	 */
	List<SysDepartmentEntity> querySubDepartments(Long id);

	/**
	 * 根据组织类型查询部门
	 */
	List<SysDepartmentEntity> queryListByOtype(String projectOtype);
	
	/**
	 * 根据事业部获取项目部
	 * @param id 事业部的id
	 * @param projectOtype 项目部的组织类型
	 */
	List<SysDepartmentEntity> queryProjectByInstitution(Long id, String projectOtype);
	
	/**
	 * 根据项目部获取项目
	 * @param id 项目部的id
	 * @param projectOtype 项目的组织类型
	 */
	List<SysDepartmentEntity> queryProjectByProjectDept(Long id, String projectOtype);

	/**
	 * 根据项目获取班级
	 * @param id 项目的id
	 * @param classOtype 班级的组织类型
	 */
	List<SysDepartmentEntity> queryClassByProject(Long id, String classOtype);

	/**
	 * 根据班级获取人
	 * 
	 * @param id 班级的id
	 */
	List<SysUserEntity> queryPersonByClass(Long id);
	
}
