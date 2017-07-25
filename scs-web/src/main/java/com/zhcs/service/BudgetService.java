package com.zhcs.service;

import com.zhcs.entity.BudgetEntity;

import java.util.List;
import java.util.Map;

//*****************************************************************************
/**
 * <p>Title:BudgetService</p>
 * <p>Description: 预算申报</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public interface BudgetService {
	
	BudgetEntity queryObject(Long id);
	
	List<BudgetEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(BudgetEntity budget);
	
	void update(BudgetEntity budget);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);

	/**
	 * 根据指定条件查询出所有的BudgetEntity，不过不要封装成对象
	 */
	List<Map<String, Object>> queryListMap(Map<String, Object> map);

	/**
	 * 修改预算明细
	 */
	void adjust(BudgetEntity budget);

	/**
	 * 将指定预算申报的进度调整为指定值
	 */
	void updateSchedule(Long budget, int schedule);
}
