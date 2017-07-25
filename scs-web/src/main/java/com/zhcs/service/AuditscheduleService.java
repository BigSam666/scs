package com.zhcs.service;

import com.zhcs.entity.AuditscheduleEntity;

import java.util.List;
import java.util.Map;

//*****************************************************************************
/**
 * <p>Title:AuditscheduleService</p>
 * <p>Description: 审核进度</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public interface AuditscheduleService {
	
	AuditscheduleEntity queryObject(Long id);
	
	List<AuditscheduleEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(AuditscheduleEntity auditschedule);
	
	void update(AuditscheduleEntity auditschedule);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);

	/**
	 * 查询出指定budget的所有审核意见
	 */
	List<Map<String,Object>> queryByBudget(Long budgetId);
}
