package com.zhcs.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhcs.dao.SysDepartmentDao;
import com.zhcs.entity.SysDepartmentEntity;
import com.zhcs.entity.SysUserEntity;
import com.zhcs.service.SysDepartmentService;
import com.zhcs.utils.DepartmentUtil;


//*****************************************************************************
/**
 * <p>Title:DepartmentServiceImpl</p>
 * <p>Description: 部门</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************

@Service("departmentService")
public class DepartmentServiceImpl implements SysDepartmentService {
	@Autowired
	private SysDepartmentDao sysdepartmentDao;
	@Autowired
	private DepartmentUtil departmentUtil;
	
	@Override
	public SysDepartmentEntity queryObject(Long id){
		return sysdepartmentDao.queryObject(id);
	}

	@Override
	public List<SysDepartmentEntity> queryObjectByPid(Long pid) {
		return sysdepartmentDao.queryObjectByPid(pid);
	}
	
	@Override
	public List<SysDepartmentEntity> queryListTree(){
		return sysdepartmentDao.queryListTree();
	}
	
	@Override
	public List<Map<String, Object>> queryListTree2(){
		return sysdepartmentDao.queryListTree2();
	}
	
	@Override
	public List<Map<String, Object>> queryListTree3() {
		// TODO Auto-generated method stub
		String ids = departmentUtil.queryNodeDeptLinkIds();
		return sysdepartmentDao.queryDepartmentListTree(ids);
	}
	
	@Override
	public List<Map<String, Object>> queryListTreeForCmn(){
		return sysdepartmentDao.queryListTreeForCmn();
	}
	
	@Override
	public List<Map<String, Object>> queryListTreeForCmn2()
	{
		String ids = departmentUtil.queryNodeDeptLinkIds();
		return sysdepartmentDao.queryListTreeForCmn2(ids);
	}
	
	@Override
	public List<Map<String, Object>> queryList(Map<String, Object> map){
		return sysdepartmentDao.queryList2(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return sysdepartmentDao.queryTotal(map);
	}
	
	@Override
	@Transactional
	public void save(SysDepartmentEntity department){
		sysdepartmentDao.save(department);
	}
	
	@Override
	@Transactional
	public void update(SysDepartmentEntity department){
		sysdepartmentDao.update(department);
	}
	
	@Override
	@Transactional
	public void delete(Long id){
		sysdepartmentDao.delete(id);
	}
	
	@Override
	@Transactional
	public void deleteBatch(Long[] ids){
		sysdepartmentDao.deleteBatch(ids);
	}

	@Override
	public List<SysDepartmentEntity> querySubDepartments(Long id) {
		List<List<SysDepartmentEntity>> levelDepts = new ArrayList<>();
		List<SysDepartmentEntity> levelDept = new ArrayList<>();
		levelDept.add(queryObject(id));
		levelDepts.add(levelDept);
		int currentSize = 0;
		while (levelDepts.size() > currentSize) {
			currentSize = levelDepts.size();
			levelDept = new ArrayList<>();
			for (SysDepartmentEntity tmp: levelDepts.get(currentSize-1)) { // 循环最后一层
				List<SysDepartmentEntity> list = queryObjectByPid(tmp.getId());
				for (SysDepartmentEntity _tmp : list) {
					levelDept.add(_tmp);
				}
			}
			if (levelDept.size()>0) {
				levelDepts.add(levelDept);
			}
		}
		List<SysDepartmentEntity> ret = new ArrayList<>();
		for (List<SysDepartmentEntity> tmp:levelDepts) {
			ret.addAll(tmp);
		}
		return ret;
	}

	@Override
	public List<SysDepartmentEntity> queryListByOtype(String projectOtype) {
		return sysdepartmentDao.queryListByOtype(projectOtype);
	}

	@Override
	public List<SysDepartmentEntity> queryProjectByProjectDept(Long id, String projectOtype) {
		return sysdepartmentDao.queryListByPidAndOtype(id,projectOtype);
	}
	
	@Override
	public List<SysDepartmentEntity> queryProjectByInstitution(Long id,
			String institutionOtype) {
		return sysdepartmentDao.queryListByPidAndOtype(id, institutionOtype);
	}

	@Override
	public List<SysDepartmentEntity> queryClassByProject(Long id,
			String classOtype) {
		return sysdepartmentDao.queryListByPidAndOtype(id,classOtype);
	}

	@Override
	public List<SysUserEntity> queryPersonByClass(Long id) {
		return sysdepartmentDao.queryPersonByClass(id);
	}


	
}
