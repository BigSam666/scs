package com.zhcs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import com.zhcs.dao.CarDao;
import com.zhcs.entity.CarEntity;
import com.zhcs.service.CarService;
import com.zhcs.utils.DepartmentUtil;


//*****************************************************************************
/**
 * <p>Title:CarServiceImpl</p>
 * <p>Description: 车辆管理</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************

@Service("carService")
public class CarServiceImpl implements CarService {
	@Autowired
	private CarDao carDao;
	@Autowired
	private DepartmentUtil departmentUtil;
	
	@Override
	public CarEntity queryObject(Long id){
		return carDao.queryObject(id);
	}
	
	@Override
	public List<CarEntity> queryList(Map<String, Object> map){
		return carDao.queryList(map);
	}
	
	@Override
	public List<CarEntity> queryList1(Map<String, Object> map)
	{
		String ids = departmentUtil.queryNodeDeptChildIds();
		map.put("ids", ids);
		return carDao.queryList1(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return carDao.queryTotal(map);
	}
	
	@Override
	@Transactional
	public void save(CarEntity car){
		carDao.save(car);
	}
	
	@Override
	@Transactional
	public void update(CarEntity car){
		carDao.update(car);
	}
	
	@Override
	@Transactional
	public void delete(Long id){
		carDao.delete(id);
	}
	
	@Override
	@Transactional
	public void deleteBatch(Long[] ids){
		carDao.deleteBatch(ids);
	}
	
}
