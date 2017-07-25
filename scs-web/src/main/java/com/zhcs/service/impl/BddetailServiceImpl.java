package com.zhcs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import com.zhcs.dao.BddetailDao;
import com.zhcs.entity.BddetailEntity;
import com.zhcs.service.BddetailService;


//*****************************************************************************
/**
 * <p>Title:BddetailServiceImpl</p>
 * <p>Description: 预算明细</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************

@Service("bddetailService")
public class BddetailServiceImpl implements BddetailService {
	@Autowired
	private BddetailDao bddetailDao;
	
	@Override
	public BddetailEntity queryObject(Long id){
		return bddetailDao.queryObject(id);
	}
	
	@Override
	public List<BddetailEntity> queryList(Map<String, Object> map){
		return bddetailDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return bddetailDao.queryTotal(map);
	}
	
	@Override
	@Transactional
	public void save(BddetailEntity bddetail){
		bddetailDao.save(bddetail);
	}
	
	@Override
	@Transactional
	public void update(BddetailEntity bddetail){
		bddetailDao.update(bddetail);
	}
	
	@Override
	@Transactional
	public void delete(Long id){
		bddetailDao.delete(id);
	}
	
	@Override
	@Transactional
	public void deleteBatch(Long[] ids){
		bddetailDao.deleteBatch(ids);
	}

	@Override
	public List<BddetailEntity> getAllItemsByBudget(Long budget) {
		return bddetailDao.queryListByBudget(budget);
	}

	
}
