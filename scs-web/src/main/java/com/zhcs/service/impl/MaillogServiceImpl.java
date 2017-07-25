package com.zhcs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import com.zhcs.dao.MaillogDao;
import com.zhcs.entity.MaillogEntity;
import com.zhcs.service.MaillogService;


//*****************************************************************************
/**
 * <p>Title:MaillogServiceImpl</p>
 * <p>Description: 邮件记录</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************

@Service("maillogService")
public class MaillogServiceImpl implements MaillogService {
	@Autowired
	private MaillogDao maillogDao;
	
	@Override
	public Map<String, Object> queryObject(Long id){
		return maillogDao.queryObject2(id);
	}
	
	@Override
	public List<Map<String, Object>> queryList(Map<String, Object> map){
		return maillogDao.queryList2(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return maillogDao.queryTotal(map);
	}
	
	@Override
	@Transactional
	public void save(MaillogEntity maillog){
		maillogDao.save(maillog);
	}
	
	@Override
	@Transactional
	public void update(MaillogEntity maillog){
		maillogDao.update(maillog);
	}
	
	@Override
	@Transactional
	public void delete(Long id){
		maillogDao.delete(id);
	}
	
	@Override
	@Transactional
	public void deleteBatch(Long[] ids){
		maillogDao.deleteBatch(ids);
	}
	
}
