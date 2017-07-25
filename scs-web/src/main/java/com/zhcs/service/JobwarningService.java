package com.zhcs.service;

import com.zhcs.entity.JobwarningEntity;

import java.util.List;
import java.util.Map;

//*****************************************************************************
/**
 * <p>Title:JobwarningService</p>
 * <p>Description: jobwarning工作预警</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public interface JobwarningService {

	List<Map<String, Object>> queryListMap(Map<String, Object> map);

	JobwarningEntity queryObject(Long id);
	
	List<JobwarningEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(JobwarningEntity jobwarning);
	
	void update(JobwarningEntity jobwarning);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
}
