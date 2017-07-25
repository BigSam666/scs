package com.zhcs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhcs.dao.FeedBackDao;
import com.zhcs.entity.FeedBackEntity;
import com.zhcs.service.FeedBackService;


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

@Service("feedBackService")
public class FeedBackServiceImpl implements FeedBackService {
	@Autowired
	private FeedBackDao feedBackDao;

	@Override
	public void save(FeedBackEntity feedback) {
		feedBackDao.save(feedback);
	}
	
	
}
