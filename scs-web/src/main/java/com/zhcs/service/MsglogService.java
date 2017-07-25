package com.zhcs.service;

import com.zhcs.entity.MsglogEntity;

import java.util.List;
import java.util.Map;

//*****************************************************************************
/**
 * <p>Title:MsglogService</p>
 * <p>Description: 消息记录</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public interface MsglogService {
	
	Map<String, Object> queryObject(Long id);
	
	List<Map<String, Object>> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(MsglogEntity msglog);
	
	void update(MsglogEntity msglog);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
}
