package com.zhcs.service;

import com.zhcs.entity.GridmngEntity;

import java.util.List;
import java.util.Map;

//*****************************************************************************
/**
 * <p>Title:GridmngService</p>
 * <p>Description: 网格管理</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public interface GridmngService {
	
	Map<String, Object> queryObject(Long id);
	
	List<Map<String, Object>> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(GridmngEntity gridmng);
	
	void update(GridmngEntity gridmng);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
}
