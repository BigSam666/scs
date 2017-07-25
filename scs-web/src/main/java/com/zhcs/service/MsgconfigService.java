package com.zhcs.service;

import com.zhcs.entity.MsgconfigEntity;

import java.util.List;
import java.util.Map;

//*****************************************************************************
/**
 * <p>Title:MsgconfigService</p>
 * <p>Description: 信息基础设置</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public interface MsgconfigService {
	
	MsgconfigEntity queryObject(Long id);
	
	List<MsgconfigEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(MsgconfigEntity msgconfig);
	
	void update(MsgconfigEntity msgconfig);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
}
