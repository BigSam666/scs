package com.zhcs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import com.zhcs.dao.MsgtemplateDao;
import com.zhcs.entity.MsgtemplateEntity;
import com.zhcs.service.MsgtemplateService;


//*****************************************************************************
/**
 * <p>Title:MsgtemplateServiceImpl</p>
 * <p>Description: 消息模板</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************

@Service("msgtemplateService")
public class MsgtemplateServiceImpl implements MsgtemplateService {
	@Autowired
	private MsgtemplateDao msgtemplateDao;
	
	@Override
	public MsgtemplateEntity queryObject(Long id){
		return msgtemplateDao.queryObject(id);
	}
	
	@Override
	public List<MsgtemplateEntity> queryList(Map<String, Object> map){
		return msgtemplateDao.queryList(map);
	}

	@Override
	public List<Map<String, Object>> getSendTemplate(){
		return msgtemplateDao.getSendTemplate();
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return msgtemplateDao.queryTotal(map);
	}
	
	@Override
	@Transactional
	public void save(MsgtemplateEntity msgtemplate){
		msgtemplateDao.save(msgtemplate);
	}
	
	@Override
	@Transactional
	public void update(MsgtemplateEntity msgtemplate){
		msgtemplateDao.update(msgtemplate);
	}
	
	@Override
	@Transactional
	public void delete(Long id){
		msgtemplateDao.delete(id);
	}
	
	@Override
	@Transactional
	public void deleteBatch(Long[] ids){
		msgtemplateDao.deleteBatch(ids);
	}
	
}
