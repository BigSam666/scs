package com.zhcs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import com.zhcs.dao.TrashtabDao;
import com.zhcs.entity.TrashtabEntity;
import com.zhcs.service.TrashtabService;


//*****************************************************************************
/**
 * <p>Title:TrashtabServiceImpl</p>
 * <p>Description: 垃圾桶标记</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************

@Service("trashtabService")
public class TrashtabServiceImpl implements TrashtabService {
	@Autowired
	private TrashtabDao trashtabDao;
	
	@Override
	public TrashtabEntity queryObject(Long id){
		return trashtabDao.queryObject(id);
	}
	
	@Override
	public List<TrashtabEntity> queryList(Map<String, Object> map){
		return trashtabDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return trashtabDao.queryTotal(map);
	}
	
	@Override
	@Transactional
	public void save(TrashtabEntity trashtab){
		trashtabDao.save(trashtab);
	}
	
	@Override
	@Transactional
	public void update(TrashtabEntity trashtab){
		trashtabDao.update(trashtab);
	}
	
	@Override
	@Transactional
	public void delete(Long id){
		trashtabDao.delete(id);
	}
	
	@Override
	@Transactional
	public void deleteBatch(Long[] ids){
		trashtabDao.deleteBatch(ids);
	}
	
}
