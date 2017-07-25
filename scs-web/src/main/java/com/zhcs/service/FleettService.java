package com.zhcs.service;

import com.zhcs.entity.FleettEntity;

import java.util.List;
import java.util.Map;

//*****************************************************************************
/**
 * <p>Title:FleettService</p>
 * <p>Description: 车队管理</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public interface FleettService {
	
	FleettEntity queryObject(Long id);
	
	List<FleettEntity> queryList(Map<String, Object> map);
	
	List<FleettEntity> queryList1(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(FleettEntity fleett);
	
	void update(FleettEntity fleett);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
}
