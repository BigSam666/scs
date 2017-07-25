package com.zhcs.dao;

import java.util.List;
import java.util.Map;

import com.zhcs.entity.FeedbackEntity;

//*****************************************************************************
/**
 * <p>Title:FeedbackDao</p>
 * <p>Description: 意见反馈</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public interface FeedbackDao extends BaseDao<FeedbackEntity> {

	List<Map<String, Object>> queryListMap(Map<String, Object> map);
	
}
