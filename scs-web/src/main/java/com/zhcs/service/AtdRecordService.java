package com.zhcs.service;

import com.zhcs.entity.AtdRecordEntity;

import java.util.List;
import java.util.Map;

//*****************************************************************************
/**
 * <p>Title:AtdRecordService</p>
 * <p>Description: 考勤记录</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public interface AtdRecordService {
	
	AtdRecordEntity queryObject(Long id);
	
	List<AtdRecordEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(AtdRecordEntity atdRecord);
	
	void update(AtdRecordEntity atdRecord);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
	/**
	 * 考勤查询列表
	 * @param paramMap
	 * @return
	 */
	Map<String, Object> queryOperatorRecordDatalist(Map<String, Object> paramMap);
	
	/**
	 * 根据时间查询调班 或调休 信息
	 * @param paramMap
	 * @return
	 */
	List<Map<String, Object>> findTiaoxiuOrBan(Map<String, Object> paramMap);
	
}
