package com.zhcs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import com.zhcs.dao.ClassesDao;
import com.zhcs.entity.ClassesEntity;
import com.zhcs.service.ClassesService;


//*****************************************************************************
/**
 * <p>Title:ClassesServiceImpl</p>
 * <p>Description: 班次管理</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************

@Service("classesService")
public class ClassesServiceImpl implements ClassesService {
	@Autowired
	private ClassesDao classesDao;
	
	@Override
	public ClassesEntity queryObject(Long id){
		return classesDao.queryObject(id);
	}
	
	@Override
	public List<ClassesEntity> queryList(Map<String, Object> map){
		return classesDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return classesDao.queryTotal(map);
	}
	
	@Override
	public List<Map<String, Object>> queryListMap(Map<String, Object> map){
		return classesDao.queryListMap(map);
	}
	
	@Override
	public List<Map<String, Object>> queryObjectMap(Object id){
		return classesDao.queryObjectMap(id);
	}
	
	@Override
	@Transactional
	public void save(ClassesEntity classes){
		classesDao.save(classes);
	}
	
	@Override
	@Transactional
	public void update(ClassesEntity classes){
		classesDao.update(classes);
	}
	
	@Override
	@Transactional
	public void delete(Long id){
		classesDao.delete(id);
	}
	
	@Override
	@Transactional
	public void deleteBatch(Long[] ids){
		classesDao.deleteBatch(ids);
	}
	
}
