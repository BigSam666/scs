package com.zhcs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import com.zhcs.dao.SmslogDao;
import com.zhcs.entity.SmslogEntity;
import com.zhcs.service.SmslogService;


//*****************************************************************************
/**
 * <p>Title:SmslogServiceImpl</p>
 * <p>Description: 短信记录</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************

@Service("smslogService")
public class SmslogServiceImpl implements SmslogService {
	@Autowired
	private SmslogDao smslogDao;
	
	@Override
	public Map<String, Object> queryObject(Long id){
		return smslogDao.queryObject2(id);
	}
	
	@Override
	public List<Map<String, Object>> queryList(Map<String, Object> map){
		return smslogDao.queryList2(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return smslogDao.queryTotal(map);
	}
	
	@Override
	@Transactional
	public void save(SmslogEntity smslog){
		smslogDao.save(smslog);
	}
	
	@Override
	@Transactional
	public void update(SmslogEntity smslog){
		smslogDao.update(smslog);
	}
	
	@Override
	@Transactional
	public void delete(Long id){
		smslogDao.delete(id);
	}
	
	@Override
	@Transactional
	public void deleteBatch(Long[] ids){
		smslogDao.deleteBatch(ids);
	}
	
}
