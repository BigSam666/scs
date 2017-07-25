package com.zhcs.service;

import com.zhcs.entity.CartypeEntity;

import java.util.List;
import java.util.Map;

//*****************************************************************************
/**
 * <p>Title:CartypeService</p>
 * <p>Description: 车辆类型</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public interface CartypeService {
	
	CartypeEntity queryObject(Long id);
	
	List<CartypeEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(CartypeEntity cartype);
	
	void update(CartypeEntity cartype);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
}
