package com.zhcs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import com.zhcs.dao.CallJobselDao;
import com.zhcs.entity.CallJobselEntity;
import com.zhcs.service.CallJobselService;


//*****************************************************************************
/**
 * <p>Title:CallJobselServiceImpl</p>
 * <p>Description: 工单查询</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************

@Service("callJobselService")
public class CallJobselServiceImpl implements CallJobselService {
	@Autowired
	private CallJobselDao callJobselDao;
	
	@Override
	public CallJobselEntity queryObject(Long id){
		return callJobselDao.queryObject(id);
	}
	
	@Override
	public List<CallJobselEntity> queryList(Map<String, Object> map){
		return callJobselDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return callJobselDao.queryTotal(map);
	}
	
	@Override
	@Transactional
	public void save(CallJobselEntity callJobsel){
		callJobselDao.save(callJobsel);
	}
	
	@Override
	@Transactional
	public void update(CallJobselEntity callJobsel){
		callJobselDao.update(callJobsel);
	}
	
	@Override
	@Transactional
	public void delete(Long id){
		callJobselDao.delete(id);
	}
	
	@Override
	@Transactional
	public void deleteBatch(Long[] ids){
		callJobselDao.deleteBatch(ids);
	}
	
}
