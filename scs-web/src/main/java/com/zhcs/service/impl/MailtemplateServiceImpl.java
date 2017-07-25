package com.zhcs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import com.zhcs.dao.MailtemplateDao;
import com.zhcs.entity.MailtemplateEntity;
import com.zhcs.service.MailtemplateService;


//*****************************************************************************
/**
 * <p>Title:MailtemplateServiceImpl</p>
 * <p>Description: 邮件模板</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************

@Service("mailtemplateService")
public class MailtemplateServiceImpl implements MailtemplateService {
	@Autowired
	private MailtemplateDao mailtemplateDao;
	
	@Override
	public MailtemplateEntity queryObject(Long id){
		return mailtemplateDao.queryObject(id);
	}
	
	@Override
	public List<MailtemplateEntity> queryList(Map<String, Object> map){
		return mailtemplateDao.queryList(map);
	}
	
	@Override
	public List<Map<String, Object>> getSendTemplate(){
		return mailtemplateDao.getSendTemplate();
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return mailtemplateDao.queryTotal(map);
	}
	
	@Override
	@Transactional
	public void save(MailtemplateEntity mailtemplate){
		mailtemplateDao.save(mailtemplate);
	}
	
	@Override
	@Transactional
	public void update(MailtemplateEntity mailtemplate){
		mailtemplateDao.update(mailtemplate);
	}
	
	@Override
	@Transactional
	public void delete(Long id){
		mailtemplateDao.delete(id);
	}
	
	@Override
	@Transactional
	public void deleteBatch(Long[] ids){
		mailtemplateDao.deleteBatch(ids);
	}
	
}
