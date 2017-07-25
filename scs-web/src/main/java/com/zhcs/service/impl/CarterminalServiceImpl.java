package com.zhcs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import com.zhcs.dao.CarterminalDao;
import com.zhcs.entity.CarterminalEntity;
import com.zhcs.service.CarterminalService;


//*****************************************************************************
/**
 * <p>Title:CarterminalServiceImpl</p>
 * <p>Description: 车载终端</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************

@Service("carterminalService")
public class CarterminalServiceImpl implements CarterminalService {
	@Autowired
	private CarterminalDao carterminalDao;
	
	@Override
	public CarterminalEntity queryObject(Long id){
		return carterminalDao.queryObject(id);
	}
	
	@Override
	public List<CarterminalEntity> queryList(Map<String, Object> map){
		return carterminalDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return carterminalDao.queryTotal(map);
	}
	
	@Override
	@Transactional
	public void save(CarterminalEntity carterminal){
		carterminalDao.save(carterminal);
	}
	
	@Override
	@Transactional
	public void update(CarterminalEntity carterminal){
		carterminalDao.update(carterminal);
	}
	
	@Override
	@Transactional
	public void delete(Long id){
		carterminalDao.delete(id);
	}
	
	@Override
	@Transactional
	public void deleteBatch(Long[] ids){
		carterminalDao.deleteBatch(ids);
	}
	
}
