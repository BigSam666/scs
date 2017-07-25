package com.zhcs.service;

import com.zhcs.entity.MonitordevEntity;

import java.util.List;
import java.util.Map;

//*****************************************************************************
/**
 * <p>Title:MonitordevService</p>
 * <p>Description: 监控设备</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public interface MonitordevService {
	
	MonitordevEntity queryObject(Long id);
	
	List<MonitordevEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(MonitordevEntity monitordev);
	
	void update(MonitordevEntity monitordev);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
}
