package com.zhcs.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhcs.dao.CallTelDao;
import com.zhcs.entity.CallTelEntity;
import com.zhcs.service.CallTelService;


//*****************************************************************************
/**
 * <p>Title:CallSeatsServiceImpl</p>
 * <p>Description: 坐席管理</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************

@Service("callTelService")
public class CallTelServiceImpl implements CallTelService {
	@Autowired
	private CallTelDao callTelDao;
	
	@Override
	public List<CallTelEntity> queryList(Map<String, Object> map){
		return callTelDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return callTelDao.queryTotal(map);
	}
	
}
