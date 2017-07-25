package com.zhcs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import com.zhcs.dao.CallSeatsDao;
import com.zhcs.entity.CallSeatsEntity;
import com.zhcs.service.CallSeatsService;


//*****************************************************************************
/**
 * <p>Title:CallSeatsServiceImpl</p>
 * <p>Description: 坐席管理</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************

@Service("callSeatsService")
public class CallSeatsServiceImpl implements CallSeatsService {
	@Autowired
	private CallSeatsDao callSeatsDao;
	
	@Override
	public CallSeatsEntity queryObject(Long id){
		return callSeatsDao.queryObject(id);
	}
	
	@Override
	public List<CallSeatsEntity> queryList(Map<String, Object> map){
		return callSeatsDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return callSeatsDao.queryTotal(map);
	}
	
	@Override
	@Transactional
	public void save(CallSeatsEntity callSeats){
		callSeatsDao.save(callSeats);
	}
	
	@Override
	@Transactional
	public void update(CallSeatsEntity callSeats){
		callSeatsDao.update(callSeats);
	}
	
	@Override
	@Transactional
	public void delete(Long id){
		callSeatsDao.delete(id);
	}
	
	@Override
	@Transactional
	public void deleteBatch(Long[] ids){
		callSeatsDao.deleteBatch(ids);
	}
	
}
