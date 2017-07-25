package com.zhcs.service;

import com.zhcs.entity.AccidentEntity;

import java.util.List;
import java.util.Map;

//*****************************************************************************
/**
 * <p>Title:AccidentService</p>
 * <p>Description: 事故管理</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public interface AccidentService {
	
	AccidentEntity queryObject(Long id);
	
	List<AccidentEntity> queryList(Map<String, Object> map);
	
	List<AccidentEntity> queryList1(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(AccidentEntity accident);
	
	void update(AccidentEntity accident);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
}
