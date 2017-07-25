package com.zhcs.dao;

import java.util.List;
import java.util.Map;

import com.zhcs.entity.EventLogEntity;

public interface EventLogDao extends BaseDao<EventLogEntity> {

	List<Map<String, Object>> getSpeed(String caseno);

	EventLogEntity queryObject(String id);

	List<Map<String, Object>> getCompleteList(Map<String, Object> map);

	Integer getCount(Map<String, Object> map);


}
