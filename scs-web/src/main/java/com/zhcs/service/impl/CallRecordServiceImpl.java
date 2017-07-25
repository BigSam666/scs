package com.zhcs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import com.zhcs.dao.CallRecordDao;
import com.zhcs.entity.CallRecordEntity;
import com.zhcs.service.CallRecordService;


//*****************************************************************************
/**
 * <p>Title:CallRecordServiceImpl</p>
 * <p>Description: 通话记录表</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************

@Service("callRecordService")
public class CallRecordServiceImpl implements CallRecordService {
	@Autowired
	private CallRecordDao callRecordDao;
	
	@Override
	public CallRecordEntity queryObject(Long id){
		return callRecordDao.queryObject(id);
	}
	
	@Override
	public List<CallRecordEntity> queryList(Map<String, Object> map){
		return callRecordDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return callRecordDao.queryTotal(map);
	}
	
	@Override
	@Transactional
	public void save(CallRecordEntity callRecord){
		callRecordDao.save(callRecord);
	}
	
	@Override
	@Transactional
	public void update(CallRecordEntity callRecord){
		callRecordDao.update(callRecord);
	}
	
	@Override
	@Transactional
	public void delete(Long id){
		callRecordDao.delete(id);
	}
	
	@Override
	@Transactional
	public void deleteBatch(Long[] ids){
		callRecordDao.deleteBatch(ids);
	}
	
}
