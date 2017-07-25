package com.zhcs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import com.zhcs.dao.ApiholdgpsDao;
import com.zhcs.entity.ApiholdgpsEntity;
import com.zhcs.service.ApiholdgpsService;


//*****************************************************************************
/**
 * <p>Title:ApiholdgpsServiceImpl</p>
 * <p>Description: 阿姨机-gps</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************

@Service("apiholdgpsService")
public class ApiholdgpsServiceImpl implements ApiholdgpsService {
	@Autowired
	private ApiholdgpsDao apiholdgpsDao;
	
	@Override
	public ApiholdgpsEntity queryObject(Long id){
		return apiholdgpsDao.queryObject(id);
	}
	
	@Override
	public List<ApiholdgpsEntity> queryList(Map<String, Object> map){
		return apiholdgpsDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return apiholdgpsDao.queryTotal(map);
	}
	
	@Override
	@Transactional
	public void save(ApiholdgpsEntity apiholdgps){
		apiholdgpsDao.save(apiholdgps);
	}
	
	@Override
	@Transactional
	public void update(ApiholdgpsEntity apiholdgps){
		apiholdgpsDao.update(apiholdgps);
	}
	
	@Override
	@Transactional
	public void delete(Long id){
		apiholdgpsDao.delete(id);
	}
	
	@Override
	@Transactional
	public void deleteBatch(Long[] ids){
		apiholdgpsDao.deleteBatch(ids);
	}

	@Override
	public List<Map<String, Object>> getUserMsgByPosition(
			Map<String, Object> map) {
		return apiholdgpsDao.getUserMsgByPosition(map);
	}
	
}
