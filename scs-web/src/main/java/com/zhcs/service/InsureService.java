package com.zhcs.service;

import com.zhcs.entity.InsureEntity;

import java.util.List;
import java.util.Map;

//*****************************************************************************
/**
 * <p>Title:InsureService</p>
 * <p>Description: 保险管理</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public interface InsureService {
	
	InsureEntity queryObject(Long id);
	
	List<InsureEntity> queryList(Map<String, Object> map);
	
	List<InsureEntity> queryList1(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(InsureEntity insure);
	
	void update(InsureEntity insure);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
}
