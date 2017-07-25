package com.zhcs.service;

import com.zhcs.entity.ClassesEntity;

import java.util.List;
import java.util.Map;

//*****************************************************************************
/**
 * <p>Title:ClassesService</p>
 * <p>Description: 班次管理</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public interface ClassesService {
	
	ClassesEntity queryObject(Long id);
	
	List<ClassesEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(ClassesEntity classes);
	
	void update(ClassesEntity classes);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);

	List<Map<String, Object>> queryListMap(Map<String, Object> map);
	
	List<Map<String, Object>> queryListMap1(Map<String, Object> map);

	List<Map<String, Object>> queryObjectMap(Object id);

}
