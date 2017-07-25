package com.zhcs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import com.zhcs.dao.InsdetailDao;
import com.zhcs.entity.InsdetailEntity;
import com.zhcs.service.InsdetailService;


//*****************************************************************************
/**
 * <p>Title:InsdetailServiceImpl</p>
 * <p>Description: 投保明细</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************

@Service("insdetailService")
public class InsdetailServiceImpl implements InsdetailService {
	@Autowired
	private InsdetailDao insdetailDao;
	
	@Override
	public InsdetailEntity queryObject(Long id){
		return insdetailDao.queryObject(id);
	}
	
	@Override
	public List<InsdetailEntity> queryList(Map<String, Object> map){
		return insdetailDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return insdetailDao.queryTotal(map);
	}
	
	@Override
	@Transactional
	public void save(InsdetailEntity insdetail){
		insdetailDao.save(insdetail);
	}
	
	@Override
	@Transactional
	public void update(InsdetailEntity insdetail){
		insdetailDao.update(insdetail);
	}
	
	@Override
	@Transactional
	public void delete(Long id){
		insdetailDao.delete(id);
	}
	
	@Override
	@Transactional
	public void deleteBatch(Long[] ids){
		insdetailDao.deleteBatch(ids);
	}

	@Override
	public List<InsdetailEntity> queryListByInsid(Long insid) {
		return insdetailDao.queryListByInsid(insid);
	}
	
}
