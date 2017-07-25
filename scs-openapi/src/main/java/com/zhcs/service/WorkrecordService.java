package com.zhcs.service;

import com.zhcs.entity.WorkrecordEntity;

import java.util.List;
import java.util.Map;

//*****************************************************************************
/**
 * <p>Title:WorkrecordService</p>
 * <p>Description: 工作记录</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public interface WorkrecordService {
	
	WorkrecordEntity queryObject(Long id);
	
	List<WorkrecordEntity> queryList(Map<String, Object> map);
	
	List<Map<String, Object>> queryListMap(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(WorkrecordEntity workrecord);
	
	void update(WorkrecordEntity workrecord);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
}
