package com.zhcs.service;

import com.zhcs.entity.CallRecordEntity;

import java.util.List;
import java.util.Map;

//*****************************************************************************
/**
 * <p>Title:CallRecordService</p>
 * <p>Description: 通话记录表</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public interface CallRecordService {
	
	CallRecordEntity queryObject(Long id);
	
	List<CallRecordEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(CallRecordEntity callRecord);
	
	void update(CallRecordEntity callRecord);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
}
