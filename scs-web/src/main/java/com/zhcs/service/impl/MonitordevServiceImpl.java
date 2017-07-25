package com.zhcs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import com.zhcs.dao.MonitordevDao;
import com.zhcs.entity.MonitordevEntity;
import com.zhcs.service.MonitordevService;


//*****************************************************************************
/**
 * <p>Title:MonitordevServiceImpl</p>
 * <p>Description: 监控设备</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************

@Service("monitordevService")
public class MonitordevServiceImpl implements MonitordevService {
	@Autowired
	private MonitordevDao monitordevDao;
	
	@Override
	public MonitordevEntity queryObject(Long id){
		return monitordevDao.queryObject(id);
	}
	
	@Override
	public List<MonitordevEntity> queryList(Map<String, Object> map){
		return monitordevDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return monitordevDao.queryTotal(map);
	}
	
	@Override
	@Transactional
	public void save(MonitordevEntity monitordev){
		monitordevDao.save(monitordev);
	}
	
	@Override
	@Transactional
	public void update(MonitordevEntity monitordev){
		monitordevDao.update(monitordev);
	}
	
	@Override
	@Transactional
	public void delete(Long id){
		monitordevDao.delete(id);
	}
	
	@Override
	@Transactional
	public void deleteBatch(Long[] ids){
		monitordevDao.deleteBatch(ids);
	}
	
}
