package com.zhcs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import com.zhcs.dao.MtdetailDao;
import com.zhcs.entity.MtdetailEntity;
import com.zhcs.service.MtdetailService;


//*****************************************************************************
/**
 * <p>Title:MtdetailServiceImpl</p>
 * <p>Description: 保养明细</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************

@Service("mtdetailService")
public class MtdetailServiceImpl implements MtdetailService {
	@Autowired
	private MtdetailDao mtdetailDao;
	
	@Override
	public MtdetailEntity queryObject(Long id){
		return mtdetailDao.queryObject(id);
	}
	
	@Override
	public List<MtdetailEntity> queryList(Map<String, Object> map){
		return mtdetailDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return mtdetailDao.queryTotal(map);
	}
	
	@Override
	@Transactional
	public void save(MtdetailEntity mtdetail){
		mtdetailDao.save(mtdetail);
	}
	
	@Override
	@Transactional
	public void update(MtdetailEntity mtdetail){
		mtdetailDao.update(mtdetail);
	}
	
	@Override
	@Transactional
	public void delete(Long id){
		mtdetailDao.delete(id);
	}
	
	@Override
	@Transactional
	public void deleteBatch(Long[] ids){
		mtdetailDao.deleteBatch(ids);
	}

	@Override
	public List<MtdetailEntity> queryListByMtid(Long mtid) {
		return mtdetailDao.queryListByMtid(mtid);
	}
	
}
