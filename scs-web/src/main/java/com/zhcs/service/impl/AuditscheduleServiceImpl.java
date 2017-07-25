package com.zhcs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import com.zhcs.dao.AuditscheduleDao;
import com.zhcs.entity.AuditscheduleEntity;
import com.zhcs.service.AuditscheduleService;


//*****************************************************************************
/**
 * <p>Title:AuditscheduleServiceImpl</p>
 * <p>Description: 审核进度</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************

@Service("auditscheduleService")
public class AuditscheduleServiceImpl implements AuditscheduleService {
	@Autowired
	private AuditscheduleDao auditscheduleDao;
	
	@Override
	public AuditscheduleEntity queryObject(Long id){
		return auditscheduleDao.queryObject(id);
	}
	
	@Override
	public List<AuditscheduleEntity> queryList(Map<String, Object> map){
		return auditscheduleDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return auditscheduleDao.queryTotal(map);
	}
	
	@Override
	@Transactional
	public void save(AuditscheduleEntity auditschedule){
		auditscheduleDao.save(auditschedule);
	}
	
	@Override
	@Transactional
	public void update(AuditscheduleEntity auditschedule){
		auditscheduleDao.update(auditschedule);
	}
	
	@Override
	@Transactional
	public void delete(Long id){
		auditscheduleDao.delete(id);
	}
	
	@Override
	@Transactional
	public void deleteBatch(Long[] ids){
		auditscheduleDao.deleteBatch(ids);
	}

	@Override
	public List<Map<String,Object>> queryByBudget(Long budgetId) {
		return auditscheduleDao.queryByBudget(budgetId);
	}
	
}
