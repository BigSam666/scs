package com.zhcs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import com.zhcs.dao.PositionDao;
import com.zhcs.entity.PositionEntity;
import com.zhcs.service.PositionService;


//*****************************************************************************
/**
 * <p>Title:PositionServiceImpl</p>
 * <p>Description: 定位信息</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************

@Service("positionService")
public class PositionServiceImpl implements PositionService {
	@Autowired
	private PositionDao positionDao;
	
	@Override
	public PositionEntity queryObject(Long id){
		return positionDao.queryObject(id);
	}
	
	@Override
	public List<PositionEntity> queryList(Map<String, Object> map){
		return positionDao.queryList(map);
	}
	
	@Override
	public List<Map<String, Object>> getUserMsgByPosition(Map<String, Object> map){
		return positionDao.getUserMsgByPosition(map);
	}
	
	@Override
	public int getUserMsgByPositionTotal(Map<String, Object> map){
		return positionDao.getUserMsgByPositionTotal(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return positionDao.queryTotal(map);
	}
	
	@Override
	@Transactional
	public void save(PositionEntity position){
		positionDao.save(position);
	}
	
	@Override
	@Transactional
	public void update(PositionEntity position){
		positionDao.update(position);
	}
	
	@Override
	@Transactional
	public void delete(Long id){
		positionDao.delete(id);
	}
	
	@Override
	@Transactional
	public void deleteBatch(Long[] ids){
		positionDao.deleteBatch(ids);
	}
	
}
