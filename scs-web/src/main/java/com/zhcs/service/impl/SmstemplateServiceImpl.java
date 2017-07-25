package com.zhcs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zhcs.dao.SmstemplateDao;
import com.zhcs.entity.SmstemplateEntity;
import com.zhcs.service.SmstemplateService;


//*****************************************************************************
/**
 * <p>Title:SmstemplateServiceImpl</p>
 * <p>Description: 短信模板</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************

@Service("smstemplateService")
public class SmstemplateServiceImpl implements SmstemplateService {
	@Autowired
	private SmstemplateDao smstemplateDao;
	
	@Override
	public SmstemplateEntity queryObject(Long id){
		return smstemplateDao.queryObject(id);
	}
	
	@Override
	public List<SmstemplateEntity> queryList(Map<String, Object> map){
		return smstemplateDao.queryList(map);
	}
	
	@Override
	public List<Map<String, Object>> getSendTemplate(){
		return smstemplateDao.getSendTemplate();
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return smstemplateDao.queryTotal(map);
	}
	
	@Override
	@Transactional
	public void save(SmstemplateEntity smstemplate){
		smstemplateDao.save(smstemplate);
	}
	
	@Override
	@Transactional
	public void update(SmstemplateEntity smstemplate){
		smstemplateDao.update(smstemplate);
	}
	
	@Override
	@Transactional
	public void delete(Long id){
		smstemplateDao.delete(id);
	}
	
	@Override
	@Transactional
	public void deleteBatch(Long[] ids){
		smstemplateDao.deleteBatch(ids);
	}

	@Override
	public Map<String, Object> queryPageList(Map<String, Object> map) {
		
		Map<String, Object> resultMap = new HashMap<>();
		List<Map<String, Object>> datalist = smstemplateDao.findList(map);
		int total = smstemplateDao.findListCount(map);
		resultMap.put("datalist", datalist);
		resultMap.put("total", total);
		return resultMap;
	}
	
}
