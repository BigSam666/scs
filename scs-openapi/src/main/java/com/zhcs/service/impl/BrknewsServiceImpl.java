package com.zhcs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import com.zhcs.dao.BrknewsDao;
import com.zhcs.entity.BrknewsEntity;
import com.zhcs.service.BrknewsService;


//*****************************************************************************
/**
 * <p>Title:BrknewsServiceImpl</p>
 * <p>Description: 爆料事件</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************

@Service("brknewsService")
public class BrknewsServiceImpl implements BrknewsService {
	@Autowired
	private BrknewsDao brknewsDao;
	
	@Override
	public BrknewsEntity queryObject(Long id){
		return brknewsDao.queryObject(id);
	}

	@Override
	public BrknewsEntity queryObjectByNo(String no){
		return brknewsDao.queryObjectByNo(no);
	}
	
	@Override
	public List<BrknewsEntity> queryList(Map<String, Object> map){
		return brknewsDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return brknewsDao.queryTotal(map);
	}
	
	@Override
	@Transactional
	public void save(BrknewsEntity brknews){
		brknewsDao.save(brknews);
	}
	
	@Override
	@Transactional
	public void update(BrknewsEntity brknews){
		brknewsDao.update(brknews);
	}
	
	@Override
	@Transactional
	public void delete(Long id){
		brknewsDao.delete(id);
	}
	
	@Override
	@Transactional
	public void deleteBatch(Long[] ids){
		brknewsDao.deleteBatch(ids);
	}

	@Override
	public List<BrknewsEntity> queryListByPerson(Map<String, Object> map) {
		return brknewsDao.queryListByPerson(map);
	}

	@Override
	public int queryCountByPerson(Map<String, Object> map) {
		return brknewsDao.queryCountByPerson(map);
	}

	@Override
	public List<Map<String, Object>> queryList2(Map<String, Object> map) {
		return brknewsDao.queryList2(map);
	}
	
}
