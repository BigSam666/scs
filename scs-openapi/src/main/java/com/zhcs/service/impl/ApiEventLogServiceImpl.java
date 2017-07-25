package com.zhcs.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhcs.dao.EventLogDao;
import com.zhcs.entity.EventLogEntity;
import com.zhcs.service.ApiEventLogService;

@Service("apiEventLogService")
public class ApiEventLogServiceImpl implements ApiEventLogService {
	
	@Autowired
	private EventLogDao eventLogDao;
	

	@Override
	public List<Map<String, Object>> getSpeed(String caseno) {
		return eventLogDao.getSpeed(caseno);
	}


	@Override
	public EventLogEntity queryObject(String id) {
		return eventLogDao.queryObject(id);
	}
	
	@Override
	@Transactional
	public void save(EventLogEntity eventLog){
		eventLogDao.save(eventLog);
	}


	@Override
	public List<Map<String, Object>> getCompleteList(Map<String, Object> map) {
		return eventLogDao.getCompleteList(map);
	}


	@Override
	public Integer getCount(Map<String, Object> map) {
		return eventLogDao.getCount(map);
	}

}
