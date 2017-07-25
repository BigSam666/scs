package com.zhcs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import com.zhcs.dao.SignDao;
import com.zhcs.entity.SignEntity;
import com.zhcs.service.SignService;


//*****************************************************************************
/**
 * <p>Title:SignServiceImpl</p>
 * <p>Description: 招牌申请</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************

@Service("signService")
public class SignServiceImpl implements SignService {
	@Autowired
	private SignDao signDao;
	
	@Override
	public SignEntity queryObject(Long id){
		return signDao.queryObject(id);
	}
	
	@Override
	public List<SignEntity> queryList(Map<String, Object> map){
		return signDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return signDao.queryTotal(map);
	}
	
	@Override
	@Transactional
	public void save(SignEntity sign){
		signDao.save(sign);
	}
	
	@Override
	@Transactional
	public void update(SignEntity sign){
		signDao.update(sign);
	}
	
	@Override
	@Transactional
	public void delete(Long id){
		signDao.delete(id);
	}
	
	@Override
	@Transactional
	public void deleteBatch(Long[] ids){
		signDao.deleteBatch(ids);
	}
	
}
