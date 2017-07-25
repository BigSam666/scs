package com.zhcs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import com.zhcs.dao.MsglogDao;
import com.zhcs.entity.MsglogEntity;
import com.zhcs.service.MsglogService;


//*****************************************************************************
/**
 * <p>Title:MsglogServiceImpl</p>
 * <p>Description: 消息记录</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************

@Service("msglogService")
public class MsglogServiceImpl implements MsglogService {
	@Autowired
	private MsglogDao msglogDao;
	
	@Override
	public Map<String, Object> queryObject(Long id){
		return msglogDao.queryObject2(id);
	}
	
	@Override
	public List<Map<String, Object>> queryList(Map<String, Object> map){
		return msglogDao.queryList2(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return msglogDao.queryTotal(map);
	}
	
	@Override
	@Transactional
	public void save(MsglogEntity msglog){
		msglogDao.save(msglog);
	}
	
	@Override
	@Transactional
	public void update(MsglogEntity msglog){
		msglogDao.update(msglog);
	}
	
	@Override
	@Transactional
	public void delete(Long id){
		msglogDao.delete(id);
	}
	
	@Override
	@Transactional
	public void deleteBatch(Long[] ids){
		msglogDao.deleteBatch(ids);
	}
	
}
