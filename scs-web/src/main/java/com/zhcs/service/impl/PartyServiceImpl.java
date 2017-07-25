package com.zhcs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import com.zhcs.dao.PartyDao;
import com.zhcs.entity.PartyEntity;
import com.zhcs.service.PartyService;


//*****************************************************************************
/**
 * <p>Title:PartyServiceImpl</p>
 * <p>Description: 甲方</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************

@Service("partyService")
public class PartyServiceImpl implements PartyService {
	@Autowired
	private PartyDao partyDao;
	
	@Override
	public PartyEntity queryObject(Long id){
		return partyDao.queryObject(id);
	}
	
	@Override
	public List<PartyEntity> queryList(Map<String, Object> map){
		return partyDao.queryList(map);
	}
	
	@Override
	public List<Map<String, Object>> getPartyTree(){
		return partyDao.getPartyTree();
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return partyDao.queryTotal(map);
	}
	
	@Override
	@Transactional
	public void save(PartyEntity party){
		partyDao.save(party);
	}
	
	@Override
	@Transactional
	public void update(PartyEntity party){
		partyDao.update(party);
	}
	
	@Override
	@Transactional
	public void delete(Long id){
		partyDao.delete(id);
	}
	
	@Override
	@Transactional
	public void deleteBatch(Long[] ids){
		partyDao.deleteBatch(ids);
	}
	
}
