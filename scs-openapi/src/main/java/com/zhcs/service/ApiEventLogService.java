package com.zhcs.service;

import java.util.List;
import java.util.Map;

import com.zhcs.entity.EventLogEntity;

public interface ApiEventLogService {


	List<Map<String, Object>> getSpeed(String caseno);

	EventLogEntity queryObject(String id);
	
	void save(EventLogEntity eventLog);

	/**
	 * 获取已处理的事件（包括事件处理和复合处理）
	 */
	List<Map<String, Object>> getCompleteList(Map<String, Object> map);

	/**
	 * 获取已处理的事件的总条数
	 */
	Integer getCount(Map<String, Object> map);

}
