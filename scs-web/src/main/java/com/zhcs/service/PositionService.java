package com.zhcs.service;

import com.zhcs.entity.PositionEntity;

import java.util.List;
import java.util.Map;

//*****************************************************************************
/**
 * <p>Title:PositionService</p>
 * <p>Description: 定位信息</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public interface PositionService {
	
	PositionEntity queryObject(Long id);
	
	List<PositionEntity> queryList(Map<String, Object> map);

	List<Map<String, Object>> getUserMsgByPosition(Map<String, Object> map);

	int getUserMsgByPositionTotal(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(PositionEntity position);
	
	void update(PositionEntity position);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
}
