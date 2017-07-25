package com.zhcs.dao;

import java.util.List;
import java.util.Map;

import com.zhcs.entity.AuditscheduleEntity;

//*****************************************************************************
/**
 * <p>Title:AuditscheduleDao</p>
 * <p>Description: 审核进度</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public interface AuditscheduleDao extends BaseDao<AuditscheduleEntity> {

	/**
	 * 查询出指定budget的所有审核意见
	 */
	List<Map<String,Object>> queryByBudget(Long budgetId);

	/**
	 * 删除指定预算申报的所有审核意见
	 */
	void deleteByBudget(Long id);
	
}
