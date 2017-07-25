package com.zhcs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import com.zhcs.dao.FeedbackDao;
import com.zhcs.entity.FeedbackEntity;
import com.zhcs.service.FeedbackService;


//*****************************************************************************
/**
 * <p>Title:FeedbackServiceImpl</p>
 * <p>Description: 意见反馈</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************

@Service("feedbackService")
public class FeedbackServiceImpl implements FeedbackService {
	@Autowired
	private FeedbackDao feedbackDao;
	
	@Override
	public FeedbackEntity queryObject(Long id){
		return feedbackDao.queryObject(id);
	}
	
	@Override
	public List<FeedbackEntity> queryList(Map<String, Object> map){
		return feedbackDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return feedbackDao.queryTotal(map);
	}
	
	@Override
	@Transactional
	public void save(FeedbackEntity feedback){
		feedbackDao.save(feedback);
	}
	
	@Override
	@Transactional
	public void update(FeedbackEntity feedback){
		feedbackDao.update(feedback);
	}
	
	@Override
	@Transactional
	public void delete(Long id){
		feedbackDao.delete(id);
	}
	
	@Override
	@Transactional
	public void deleteBatch(Long[] ids){
		feedbackDao.deleteBatch(ids);
	}

	@Override
	public List<Map<String, Object>> queryListMap(Map<String, Object> map) {
		return feedbackDao.queryListMap(map);
	}
	
}
