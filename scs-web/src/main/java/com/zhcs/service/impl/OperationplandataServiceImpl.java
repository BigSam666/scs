package com.zhcs.service.impl;

import com.zhcs.dao.OperationplandataDao;
import com.zhcs.entity.OperationplandataEntity;
import com.zhcs.service.OperationplandataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;


//*****************************************************************************
/**
 * <p>Title:OperationplandataServiceImpl</p>
 * <p>Description: 作业计划数据</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************

@Service("operationplandataService")
public class OperationplandataServiceImpl implements OperationplandataService {
	@Autowired
	private OperationplandataDao operationplandataDao;
	
	@Override
	public OperationplandataEntity queryObject(Long id){
		return operationplandataDao.queryObject(id);
	}
	
	@Override
	public List<OperationplandataEntity> queryList(Map<String, Object> map){
		return operationplandataDao.queryList(map);
	}

	@Override
	public List<Map<String, Object>> queryList2(Map<String, Object> map){
		return operationplandataDao.queryList2(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return operationplandataDao.queryTotal(map);
	}
	
	@Override
	@Transactional
	public void save(OperationplandataEntity operationplandata){
		operationplandataDao.save(operationplandata);
	}
	
	@Override
	@Transactional
	public void update(OperationplandataEntity operationplandata){
		operationplandataDao.update(operationplandata);
	}
	
	@Override
	@Transactional
	public void delete(Long id){
		operationplandataDao.delete(id);
	}
	
	@Override
	@Transactional
	public void deleteBatch(Long[] ids){
		operationplandataDao.deleteBatch(ids);
	}

	@Override
	public int queryDay(String date, String[] banci) {
		return operationplandataDao.queryDay(date, banci);
	}

}
