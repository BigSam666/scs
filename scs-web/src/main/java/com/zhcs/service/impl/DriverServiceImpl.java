package com.zhcs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import com.zhcs.dao.DriverDao;
import com.zhcs.entity.DriverEntity;
import com.zhcs.service.DriverService;
import com.zhcs.utils.DepartmentUtil;


//*****************************************************************************
/**
 * <p>Title:DriverServiceImpl</p>
 * <p>Description: 司机管理</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************

@Service("driverService")
public class DriverServiceImpl implements DriverService {
	@Autowired
	private DriverDao driverDao;
	@Autowired
	private DepartmentUtil departmentUtil;
	
	@Override
	public DriverEntity queryObject(Long id){
		return driverDao.queryObject(id);
	}
	
	@Override
	public List<DriverEntity> queryList(Map<String, Object> map){
		return driverDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return driverDao.queryTotal(map);
	}
	
	@Override
	@Transactional
	public void save(DriverEntity driver){
		driverDao.save(driver);
	}
	
	@Override
	@Transactional
	public void update(DriverEntity driver){
		driverDao.update(driver);
	}
	
	@Override
	@Transactional
	public void delete(Long id){
		driverDao.delete(id);
	}
	
	@Override
	@Transactional
	public void deleteBatch(Long[] ids){
		driverDao.deleteBatch(ids);
	}

	@Override
	public List<Map<String, Object>> queryFullList(Map<String, Object> map) {
		return driverDao.queryFullList(map);
	}
	
	@Override
	public List<Map<String, Object>> queryFullList1(Map<String, Object> map) {
		String ids = departmentUtil.queryNodeDeptChildIds();
		map.put("ids", ids);
		return driverDao.queryFullList1(map);
	}
	
}
