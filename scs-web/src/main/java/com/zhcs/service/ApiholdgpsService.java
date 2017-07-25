package com.zhcs.service;

import com.zhcs.entity.ApiholdgpsEntity;

import java.util.List;
import java.util.Map;

//*****************************************************************************
/**
 * <p>Title:ApiholdgpsService</p>
 * <p>Description: 阿姨机-gps</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public interface ApiholdgpsService {
	
	ApiholdgpsEntity queryObject(Long id);
	
	List<ApiholdgpsEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(ApiholdgpsEntity apiholdgps);
	
	void update(ApiholdgpsEntity apiholdgps);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);

	List<Map<String, Object>> getUserMsgByPosition(Map<String, Object> map);
}
