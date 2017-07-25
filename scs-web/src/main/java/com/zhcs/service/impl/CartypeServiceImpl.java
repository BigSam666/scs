package com.zhcs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import com.zhcs.dao.CartypeDao;
import com.zhcs.entity.CartypeEntity;
import com.zhcs.service.CartypeService;


//*****************************************************************************
/**
 * <p>Title:CartypeServiceImpl</p>
 * <p>Description: 车辆类型</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************

@Service("cartypeService")
public class CartypeServiceImpl implements CartypeService {
	@Autowired
	private CartypeDao cartypeDao;
	
	@Override
	public CartypeEntity queryObject(Long id){
		return cartypeDao.queryObject(id);
	}
	
	@Override
	public List<CartypeEntity> queryList(Map<String, Object> map){
		return cartypeDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return cartypeDao.queryTotal(map);
	}
	
	@Override
	@Transactional
	public void save(CartypeEntity cartype){
		cartypeDao.save(cartype);
	}
	
	@Override
	@Transactional
	public void update(CartypeEntity cartype){
		cartypeDao.update(cartype);
	}
	
	@Override
	@Transactional
	public void delete(Long id){
		cartypeDao.delete(id);
	}
	
	@Override
	@Transactional
	public void deleteBatch(Long[] ids){
		cartypeDao.deleteBatch(ids);
	}
	
}
