package com.zhcs.service;

import com.zhcs.entity.IllegaEntity;

import java.util.List;
import java.util.Map;

//*****************************************************************************
/**
 * <p>Title:IllegaService</p>
 * <p>Description: 违章管理</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public interface IllegaService {
	
	IllegaEntity queryObject(Long id);
	
	List<IllegaEntity> queryList(Map<String, Object> map);
	
	List<IllegaEntity> queryList1(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(IllegaEntity illega);
	
	void update(IllegaEntity illega);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
}
