package com.zhcs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import com.zhcs.dao.CitizenDao;
import com.zhcs.entity.CitizenEntity;
import com.zhcs.service.CitizenService;


//*****************************************************************************
/**
 * <p>Title:CitizenServiceImpl</p>
 * <p>Description: 市民</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************

@Service("citizenService")
public class CitizenServiceImpl implements CitizenService {
	@Autowired
	private CitizenDao citizenDao;
	
	@Override
	public CitizenEntity queryObject(Long id){
		return citizenDao.queryObject(id);
	}
	
	@Override
	public CitizenEntity queryObjectByMobile(String mobile){
		return citizenDao.queryObjectByMobile(mobile);
	}
	
	@Override
	public List<CitizenEntity> queryList(Map<String, Object> map){
		return citizenDao.queryList(map);
	}
	
	@Override
	public List<Map<String, Object>> getUserList(){
		return citizenDao.getUserList();
	}
	
	@Override
	public List<Map<String, Object>> getUserTree(){
		return citizenDao.getUserTree();
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return citizenDao.queryTotal(map);
	}
	
	@Override
	@Transactional
	public void save(CitizenEntity citizen){
		citizenDao.save(citizen);
	}
	
	@Override
	@Transactional
	public void update(CitizenEntity citizen){
		citizenDao.update(citizen);
	}
	
	@Override
	@Transactional
	public void delete(Long id){
		citizenDao.delete(id);
	}
	
	@Override
	@Transactional
	public void deleteBatch(Long[] ids){
		citizenDao.deleteBatch(ids);
	}
	
}
