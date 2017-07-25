package com.zhcs.dao;

import java.util.List;
import java.util.Map;

import com.zhcs.entity.EventEntity;

//*****************************************************************************
/**
 * <p>Title:EventDao</p>
 * <p>Description: 案件</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public interface EventDao extends BaseDao<EventEntity> {

	List<Map<String, Object>> queryList2(Map<String, Object> map);
	
	List<Map<String, Object>> queryList3(Map<String, Object> map);

	EventEntity queryByNo(String no);
	
	List<Map<String, Object>> historyList(Map<String, Object> map);

	List<Map<String, Object>> getHandleList(Map<String, Object> map);
	
	List<Map<String, Object>> getHandleList1(Map<String, Object> map);

	List<Map<String, Object>> fulfilList(Map<String, Object> map);
	
	List<Map<String, Object>> fulfilList1(Map<String, Object> map);

	Map<String, Object> getHandleSituation(int userid);
	
}
