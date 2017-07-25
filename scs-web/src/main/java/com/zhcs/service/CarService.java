package com.zhcs.service;

import com.zhcs.entity.CarEntity;

import java.util.List;
import java.util.Map;

//*****************************************************************************
/**
 * <p>Title:CarService</p>
 * <p>Description: 车辆管理</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public interface CarService {
	
	CarEntity queryObject(Long id);
	
	List<CarEntity> queryList(Map<String, Object> map);
	
	List<CarEntity> queryList1(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(CarEntity car);
	
	void update(CarEntity car);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
}
