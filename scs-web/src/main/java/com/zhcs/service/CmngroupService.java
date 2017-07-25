package com.zhcs.service;

import com.zhcs.entity.CmngroupEntity;

import java.util.List;
import java.util.Map;

//*****************************************************************************
/**
 * <p>Title:CmngroupService</p>
 * <p>Description: 通讯组</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public interface CmngroupService {
	
	CmngroupEntity queryObject(Long id);
	
	List<CmngroupEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(CmngroupEntity cmngroup);
	
	void update(CmngroupEntity cmngroup);
	 
	void delete(Long id);
	
	void deleteBatch(Long[] ids);

	Map<String, Object> queryPageList(Map<String, Object> map);
}
