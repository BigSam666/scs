package com.zhcs.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhcs.dao.EventDao;
import com.zhcs.entity.EventEntity;
import com.zhcs.service.EventService;
import com.zhcs.utils.DepartmentUtil;


//*****************************************************************************
/**
 * <p>Title:EventServiceImpl</p>
 * <p>Description: 案件</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************

@Service("eventService")
public class EventServiceImpl implements EventService {
	@Autowired
	private EventDao eventDao;
	@Autowired
	private DepartmentUtil departmentUtil;
	
	@Override
	public EventEntity queryObject(Long id){
		return eventDao.queryObject(id);
	}

	@Override
	public EventEntity queryByNo(String no){
		return eventDao.queryByNo(no);
	}
	
	@Override
	public List<EventEntity> queryList(Map<String, Object> map){
		return eventDao.queryList(map);
	}
	
	@Override
	public List<Map<String, Object>> queryList2(Map<String, Object> map){
		return eventDao.queryList2(map);
	}
	
	@Override
	public List<Map<String, Object>> queryList3(Map<String, Object> map){
		String ids = departmentUtil.queryNodeDeptChildIds();
		if (ids != null && !"".equals(ids)) {
			map.put("ids", ids);
		}
		return eventDao.queryList3(map);
	}
	
	@Override
	public List<Map<String, Object>> historyList(Map<String, Object> map){
		return eventDao.historyList(map);
	}
	
	@Override
	public List<Map<String, Object>> getHandleList(Map<String, Object> map){
		return eventDao.getHandleList(map);
	}
	
	@Override
	public List<Map<String, Object>> getHandleList1(Map<String, Object> map){
		String ids = departmentUtil.queryNodeDeptChildIds();
		if (ids != null && !"".equals(ids)) {
			map.put("ids", ids);
		}
		return eventDao.getHandleList1(map);
	}
	
	@Override
	public List<Map<String, Object>> fulfilList(Map<String, Object> map){
		return eventDao.fulfilList(map);
	}
	
	@Override
	public List<Map<String, Object>> fulfilList1(Map<String, Object> map){
		String ids = departmentUtil.queryNodeDeptChildIds();
		if (ids != null && !"".equals(ids)) {
			map.put("ids", ids);
		}
		return eventDao.fulfilList1(map);
	}
	
	@Override
	public Map<String, Object> getHandleSituation(int userid){
		return eventDao.getHandleSituation(userid);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return eventDao.queryTotal(map);
	}
	
	@Override
	@Transactional
	public void save(EventEntity event){
		eventDao.save(event);
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
	@Transactional
	public void deleteBatch(Long[] ids){
		eventDao.deleteBatch(ids);
	}
	
}
