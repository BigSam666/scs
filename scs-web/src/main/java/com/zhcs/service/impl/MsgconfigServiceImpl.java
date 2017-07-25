package com.zhcs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import com.zhcs.dao.MsgconfigDao;
import com.zhcs.entity.MsgconfigEntity;
import com.zhcs.service.MsgconfigService;


//*****************************************************************************
/**
 * <p>Title:MsgconfigServiceImpl</p>
 * <p>Description: 信息基础设置</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************

@Service("msgconfigService")
public class MsgconfigServiceImpl implements MsgconfigService {
	@Autowired
	private MsgconfigDao msgconfigDao;
	
	@Override
	public MsgconfigEntity queryObject(Long id){
		return msgconfigDao.queryObject(id);
	}
	
	@Override
	public List<MsgconfigEntity> queryList(Map<String, Object> map){
		return msgconfigDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return msgconfigDao.queryTotal(map);
	}
	
	@Override
	@Transactional
	public void save(MsgconfigEntity msgconfig){
		msgconfigDao.save(msgconfig);
	}
	
	@Override
	@Transactional
	public void update(MsgconfigEntity msgconfig){
		msgconfigDao.update(msgconfig);
	}
	
	@Override
	@Transactional
	public void delete(Long id){
		msgconfigDao.delete(id);
	}
	
	@Override
	@Transactional
	public void deleteBatch(Long[] ids){
		msgconfigDao.deleteBatch(ids);
	}
	
}
