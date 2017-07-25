package com.zhcs.service;

import com.zhcs.entity.CallJobselEntity;

import java.util.List;
import java.util.Map;

//*****************************************************************************
/**
 * <p>Title:CallJobselService</p>
 * <p>Description: 工单查询</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public interface CallJobselService {
	
	CallJobselEntity queryObject(Long id);
	
	List<CallJobselEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(CallJobselEntity callJobsel);
	
	void update(CallJobselEntity callJobsel);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
}
