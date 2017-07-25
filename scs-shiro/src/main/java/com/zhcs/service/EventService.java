package com.zhcs.service;

import com.zhcs.entity.EventEntity;

import java.util.List;
import java.util.Map;

//*****************************************************************************
/**
 * <p>Title:EventService</p>
 * <p>Description: 案件</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public interface EventService {
	
	EventEntity queryObject(Long id);
	
	EventEntity queryByNo(String no);
	
	List<EventEntity> queryList(Map<String, Object> map);

	List<Map<String, Object>> queryList2(Map<String, Object> map);
	
	List<Map<String, Object>> queryList3(Map<String, Object> map);
	
	List<Map<String, Object>> historyList(Map<String, Object> map);

	List<Map<String, Object>> getHandleList(Map<String, Object> map);
	List<Map<String, Object>> getHandleList1(Map<String, Object> map);

	List<Map<String, Object>> fulfilList(Map<String, Object> map);
	
	List<Map<String, Object>> fulfilList1(Map<String, Object> map);

	Map<String, Object> getHandleSituation(int userid);
	
	int queryTotal(Map<String, Object> map);
	
	void save(EventEntity event);
	
	void update(EventEntity event);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
}
