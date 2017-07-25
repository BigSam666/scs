package com.zhcs.service;

import com.zhcs.entity.BasissettingEntity;

import java.util.List;
import java.util.Map;

//*****************************************************************************
/**
 * <p>Title:BasissettingService</p>
 * <p>Description: 基础设置</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public interface BasissettingService {
	
	BasissettingEntity queryObject(Long id);
	
	List<BasissettingEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(BasissettingEntity basissetting);
	
	void update(BasissettingEntity basissetting);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);

	String queryId();
}
