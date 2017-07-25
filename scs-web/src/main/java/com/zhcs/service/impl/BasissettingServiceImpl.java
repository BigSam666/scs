package com.zhcs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import com.zhcs.dao.BasissettingDao;
import com.zhcs.entity.BasissettingEntity;
import com.zhcs.service.BasissettingService;


//*****************************************************************************
/**
 * <p>Title:BasissettingServiceImpl</p>
 * <p>Description: 基础设置</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************

@Service("basissettingService")
public class BasissettingServiceImpl implements BasissettingService {
	@Autowired
	private BasissettingDao basissettingDao;
	
	@Override
	public BasissettingEntity queryObject(Long id){
		return basissettingDao.queryObject(id);
	}
	
	@Override
	public List<BasissettingEntity> queryList(Map<String, Object> map){
		return basissettingDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return basissettingDao.queryTotal(map);
	}
	
	@Override
	public String queryId(){
		return basissettingDao.queryId();
	}
	
	@Override
	@Transactional
	public void save(BasissettingEntity basissetting){
		basissettingDao.save(basissetting);
	}
	
	@Override
	@Transactional
	public void update(BasissettingEntity basissetting){
		basissettingDao.update(basissetting);
	}
	
	@Override
	@Transactional
	public void delete(Long id){
		basissettingDao.delete(id);
	}
	
	@Override
	@Transactional
	public void deleteBatch(Long[] ids){
		basissettingDao.deleteBatch(ids);
	}
	
}
