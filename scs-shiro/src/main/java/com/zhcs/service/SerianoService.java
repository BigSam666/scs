package com.zhcs.service;

import com.zhcs.entity.SysSerianoEntity;

import java.util.List;
import java.util.Map;

//*****************************************************************************
/**
 * <p>Title:SerianoService</p>
 * <p>Description: 序号</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public interface SerianoService {
	
	SysSerianoEntity queryObject(String type);
	
	List<SysSerianoEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(SysSerianoEntity seriano);
	
	void update(SysSerianoEntity seriano);
	
	void delete(String type);
	
	void deleteBatch(String[] types);
	
	SysSerianoEntity getno(SysSerianoEntity seriano);
	
	String getId(String type);

	String getId(String type, String year);
	
}
