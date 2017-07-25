package com.zhcs.dao;

import java.util.List;
import java.util.Map;

import com.zhcs.entity.ClassesEntity;

//*****************************************************************************
/**
 * <p>Title:ClassesDao</p>
 * <p>Description: 班次管理</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public interface ClassesDao extends BaseDao<ClassesEntity> {
	
	List<Map<String, Object>> queryListMap(Map<String, Object> map);
	
	List<Map<String, Object>> queryObjectMap(Object id);
	
}
