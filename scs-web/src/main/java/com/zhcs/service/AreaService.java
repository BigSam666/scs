package com.zhcs.service;

import com.zhcs.entity.AreaEntity;

import java.util.List;
import java.util.Map;

//*****************************************************************************
/**
 * <p>Title:AreaService</p>
 * <p>Description: 省市数据 (T_AREA)</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public interface AreaService {
	
	AreaEntity queryObject(Long id);
	
	List<AreaEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(AreaEntity area);
	
	void update(AreaEntity area);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
	
	List<Map<String, Object>> queryAreaListByNo(String no);
}
