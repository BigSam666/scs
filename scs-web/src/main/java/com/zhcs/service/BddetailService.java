package com.zhcs.service;

import com.zhcs.entity.BddetailEntity;

import java.util.List;
import java.util.Map;

//*****************************************************************************
/**
 * <p>Title:BddetailService</p>
 * <p>Description: 预算明细</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public interface BddetailService {
	
	BddetailEntity queryObject(Long id);
	
	List<BddetailEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(BddetailEntity bddetail);
	
	void update(BddetailEntity bddetail);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);


	/**
	 * 查询指定budget相关的所有预算明细
	 */
	List<BddetailEntity> getAllItemsByBudget(Long budget);
	
	
}
