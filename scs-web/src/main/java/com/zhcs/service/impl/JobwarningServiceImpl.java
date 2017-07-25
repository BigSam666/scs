package com.zhcs.service.impl;

import com.zhcs.dao.JobwarningDao;
import com.zhcs.entity.JobwarningEntity;
import com.zhcs.service.JobwarningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;


//*****************************************************************************
/**
 * <p>Title:JobwarningServiceImpl</p>
 * <p>Description: jobwarning工作预警</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************

@Service("jobwarningService")
public class JobwarningServiceImpl implements JobwarningService {
	@Autowired
	private JobwarningDao jobwarningDao;

	@Override
	public List<Map<String, Object>> queryListMap(Map<String, Object> map) {
		return jobwarningDao.queryListMap(map);
	}

	@Override
	public JobwarningEntity queryObject(Long id){
		return jobwarningDao.queryObject(id);
	}
	
	@Override
	public List<JobwarningEntity> queryList(Map<String, Object> map){
		return jobwarningDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return jobwarningDao.queryTotal(map);
	}
	
	@Override
	@Transactional
	public void save(JobwarningEntity jobwarning){
		jobwarningDao.save(jobwarning);
	}
	
	@Override
	@Transactional
	public void update(JobwarningEntity jobwarning){
		jobwarningDao.update(jobwarning);
	}
	
	@Override
	@Transactional
	public void delete(Long id){
		jobwarningDao.delete(id);
	}
	
	@Override
	@Transactional
	public void deleteBatch(Long[] ids){
		jobwarningDao.deleteBatch(ids);
	}
	
}
