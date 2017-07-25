package com.zhcs.service;

import com.zhcs.entity.CostrecordEntity;

import java.util.List;
import java.util.Map;

//*****************************************************************************
/**
 * <p>Title:CostrecordService</p>
 * <p>Description: 费用记录</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public interface CostrecordService {
	
	CostrecordEntity queryObject(Long id);
	
	List<CostrecordEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(CostrecordEntity costrecord);
	
	void update(CostrecordEntity costrecord);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);

	/**
	 * 同queryList，不过返回的结果不是实体类，而是Map
	 */
	List<Map<String, Object>> queryListMap(Map<String, Object> map);
}
