package com.zhcs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import com.zhcs.dao.EventLogDao;
import com.zhcs.entity.EventLogEntity;
import com.zhcs.service.EventLogService;


//*****************************************************************************
/**
 * <p>Title:EventLogServiceImpl</p>
 * <p>Description: 案件日志</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************

@Service("eventLogService")
public class EventLogServiceImpl implements EventLogService {
	@Autowired
	private EventLogDao eventLogDao;
	
	@Override
	public EventLogEntity queryObject(Long id){
		return eventLogDao.queryObject(id);
	}
	
	@Override
	public List<EventLogEntity> queryList(Map<String, Object> map){
		return eventLogDao.queryList(map);
	}
	
	@Override
	public List<Map<String, Object>> getSpeed(String caseno){
		return eventLogDao.getSpeed(caseno);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return eventLogDao.queryTotal(map);
	}
	
	@Override
	@Transactional
	public void save(EventLogEntity eventLog){
		eventLogDao.save(eventLog);
	}
	
	@Override
	@Transactional
	public void update(EventLogEntity eventLog){
		eventLogDao.update(eventLog);
	}
	
	@Override
	@Transactional
	public void delete(Long id){
		eventLogDao.delete(id);
	}
	
	@Override
	@Transactional
	public void deleteBatch(Long[] ids){
		eventLogDao.deleteBatch(ids);
	}
	
}
