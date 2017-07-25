package com.zhcs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import com.zhcs.dao.WorkrecordDao;
import com.zhcs.entity.WorkrecordEntity;
import com.zhcs.service.WorkrecordService;


//*****************************************************************************
/**
 * <p>Title:WorkrecordServiceImpl</p>
 * <p>Description: 工作记录</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************

@Service("workrecordService")
public class WorkrecordServiceImpl implements WorkrecordService {
	@Autowired
	private WorkrecordDao workrecordDao;
	
	@Override
	public WorkrecordEntity queryObject(Long id){
		return workrecordDao.queryObject(id);
	}
	
	@Override
	public List<Map<String, Object>> queryList(Map<String, Object> map){
		return workrecordDao.queryList2(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return workrecordDao.queryTotal(map);
	}
	
	@Override
	@Transactional
	public void save(WorkrecordEntity workrecord){
		workrecordDao.save(workrecord);
	}
	
	@Override
	@Transactional
	public void update(WorkrecordEntity workrecord){
		workrecordDao.update(workrecord);
	}
	
	@Override
	@Transactional
	public void delete(Long id){
		workrecordDao.delete(id);
	}
	
	@Override
	@Transactional
	public void deleteBatch(Long[] ids){
		workrecordDao.deleteBatch(ids);
	}
	
}
