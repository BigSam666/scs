package com.zhcs.service;

import com.zhcs.entity.OperationplandataEntity;

import java.util.List;
import java.util.Map;

//*****************************************************************************
/**
 * <p>Title:OperationplandataService</p>
 * <p>Description: 作业计划数据</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public interface OperationplandataService {
	
	OperationplandataEntity queryObject(Long id);
	
	List<OperationplandataEntity> queryList(Map<String, Object> map);

	List<Map<String, Object>> queryList2(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(OperationplandataEntity operationplandata);
	
	void update(OperationplandataEntity operationplandata);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);

	int queryDay(String date, String[] banci);
}
