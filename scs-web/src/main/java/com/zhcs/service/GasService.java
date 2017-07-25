package com.zhcs.service;

import com.zhcs.entity.GasEntity;

import java.util.List;
import java.util.Map;

//*****************************************************************************
/**
 * <p>Title:GasService</p>
 * <p>Description: 油耗管理</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public interface GasService {
	
	GasEntity queryObject(Long id);
	
	List<GasEntity> queryList(Map<String, Object> map);
	
	List<GasEntity> queryList1(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(GasEntity gas);
	
	void update(GasEntity gas);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
}
