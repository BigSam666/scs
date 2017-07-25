package com.zhcs.dao;

import java.util.List;

import com.zhcs.entity.BddetailEntity;

//*****************************************************************************
/**
 * <p>Title:BddetailDao</p>
 * <p>Description: 预算明细</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public interface BddetailDao extends BaseDao<BddetailEntity> {

	/**
	 * 查询指定budget相关的所有项
	 */
	List<BddetailEntity> queryListByBudget(Long budget);

	/**
	 * 删除指定budget相关的所有项
	 */
	void deleteNyBudget(Long budgetId);
	
}
