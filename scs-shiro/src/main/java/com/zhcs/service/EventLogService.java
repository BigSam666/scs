package com.zhcs.service;

import com.zhcs.entity.EventLogEntity;

import java.util.List;
import java.util.Map;

//*****************************************************************************
/**
 * <p>Title:EventLogService</p>
 * <p>Description: 案件日志</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public interface EventLogService {
	
	EventLogEntity queryObject(Long id);
	
	List<EventLogEntity> queryList(Map<String, Object> map);

	List<Map<String, Object>> getSpeed(String caseno);
	
	int queryTotal(Map<String, Object> map);
	
	void save(EventLogEntity eventLog);
	
	void update(EventLogEntity eventLog);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
}
