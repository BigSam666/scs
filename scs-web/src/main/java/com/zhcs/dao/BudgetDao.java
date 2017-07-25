package com.zhcs.dao;

import java.util.List;
import java.util.Map;

import com.zhcs.entity.BudgetEntity;

//*****************************************************************************
/**
 * <p>Title:BudgetDao</p>
 * <p>Description: 预算申报</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public interface BudgetDao extends BaseDao<BudgetEntity> {
	/**
	 * 根据指定条件查询出所有的BudgetEntity，不过不要封装成对象
	 */
	List<Map<String, Object>> queryListMap(Map<String, Object> map);

	/**
	 * 将指定预算申报的进度调整为指定值
	 */
	void updateSchedule(Long budget, int schedule);
	
}
