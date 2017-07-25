package com.zhcs.service;

import com.zhcs.entity.TrashtabEntity;

import java.util.List;
import java.util.Map;

//*****************************************************************************
/**
 * <p>Title:TrashtabService</p>
 * <p>Description: 垃圾桶标记</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public interface TrashtabService {
	
	TrashtabEntity queryObject(Long id);
	
	List<TrashtabEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(TrashtabEntity trashtab);
	
	void update(TrashtabEntity trashtab);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
}
