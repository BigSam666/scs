package com.zhcs.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhcs.dao.EventDao;
import com.zhcs.entity.EventEntity;
import com.zhcs.service.ApiEventService;

@Service("apiEventService")
public class ApiEventServiceImpl implements ApiEventService {
	
	@Autowired
	private EventDao eventDao;

	@Override
	public List<Map<String, Object>> queryPendingEvents(Map<String, Object> map) {
		return eventDao.queryPendingEvents(map);
	}

	@Override
	public Map<String, Object> queryObject(String eventId) {
		return eventDao.queryObject(eventId);
	}

	@Override
	public EventEntity queryObjectById(String id) {
		return eventDao.queryObjectById(id);
	}

	@Override
	public List<Map<String, Object>> getHandleList(Long id) {
		return eventDao.getHandleList(id);
	}
	
	@Override
	@Transactional
	public void update(EventEntity event){
		eventDao.update(event);
	}
	
	@Override
	@Transactional
	public void delete(Long id){
		eventDao.delete(id);
	}

	@Override
	public Integer getCount(Map<String, Object> map) {
		return eventDao.getCount(map);
	}
	
	@Override
	@Transactional
	public void save(EventEntity event){
		eventDao.save(event);
	}

	@Override
	public List<EventEntity> queryListBetweenTime(Map<String, Object> map) {
		return eventDao.queryListBetweenTime(map);
	}

	@Override
	public List<Map<String, Object>> queryNeedDispEvents(Map<String, Object> map) {
		return eventDao.queryNeedDispEvents(map);
	}


}
