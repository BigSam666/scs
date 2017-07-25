package com.zhcs.service;

import com.zhcs.entity.FeedbackEntity;

import java.util.List;
import java.util.Map;

//*****************************************************************************
/**
 * <p>Title:FeedbackService</p>
 * <p>Description: 意见反馈</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public interface FeedbackService {
	
	FeedbackEntity queryObject(Long id);
	
	List<FeedbackEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(FeedbackEntity feedback);
	
	void update(FeedbackEntity feedback);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);

	List<Map<String, Object>> queryListMap(Map<String, Object> map);
}
