package com.zhcs.service;

import com.zhcs.entity.YeartrialEntity;

import java.util.List;
import java.util.Map;

//*****************************************************************************
/**
 * <p>Title:YeartrialService</p>
 * <p>Description: 年审管理</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public interface YeartrialService {
	
	YeartrialEntity queryObject(Long id);
	
	List<YeartrialEntity> queryList(Map<String, Object> map);
	
	List<YeartrialEntity> queryList1(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(YeartrialEntity yeartrial);
	
	void update(YeartrialEntity yeartrial);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
}
