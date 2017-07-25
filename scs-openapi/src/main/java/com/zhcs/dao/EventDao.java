package com.zhcs.dao;

import java.util.List;
import java.util.Map;

import com.zhcs.entity.EventEntity;

public interface EventDao extends BaseDao<EventEntity> {

	/**
	 * 查询待处理事件
	 */
	List<Map<String, Object>> queryPendingEvents(Map<String, Object> map);

	/**
	 * 根据事件id查询事件详情
	 */
	Map<String, Object> queryObject(String eventId);

	EventEntity queryObjectById(String id);

	List<Map<String, Object>> getHandleList(Long id);

	/**
	 * 获取条数
	 */
	Integer getCount(Map<String, Object> map);

	/**
	 * 获取位于指定时间段指派给指定人的事件
	 */
	List<EventEntity> queryListBetweenTime(Map<String, Object> map);

	/**
	 * 查询待派单事件
	 */
	List<Map<String, Object>> queryNeedDispEvents(Map<String, Object> map);
	
	

}
