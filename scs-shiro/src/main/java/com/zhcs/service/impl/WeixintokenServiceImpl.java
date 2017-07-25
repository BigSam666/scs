package com.zhcs.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhcs.context.PlatformContext;
import com.zhcs.dao.WeixintokenDao;
import com.zhcs.entity.WeixintokenEntity;
import com.zhcs.service.WeixintokenService;

//*****************************************************************************
/**
 * <p>Title:WeixintokenServiceImpl</p>
 * <p>Description: 微信token</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************

@Service("weixintokenService")
public class WeixintokenServiceImpl implements WeixintokenService {
	@Autowired
	private WeixintokenDao weixintokenDao;
	
	@Override
	public WeixintokenEntity queryObject(Long id){
		return weixintokenDao.queryObject(id);
	}
	
	@Override
	public List<WeixintokenEntity> queryList(Map<String, Object> map){
		return weixintokenDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return weixintokenDao.queryTotal(map);
	}
	
	@Override
	@Transactional
	public void save(WeixintokenEntity weixintoken){
		weixintokenDao.save(weixintoken);
	}
	
	@Override
	@Transactional
	public void update(WeixintokenEntity weixintoken){
		weixintokenDao.update(weixintoken);
	}
	
	@Override
	@Transactional
	public void delete(Long id){
		weixintokenDao.delete(id);
	}
	
	@Override
	@Transactional
	public void deleteBatch(Long[] ids){
		weixintokenDao.deleteBatch(ids);
	}

	private String getAccessToken(String appid, String secret) {
		Map<String,String> map = new HashMap<String, String>();
		map.put("appid", appid);
		map.put("secret", secret);
		return weixintokenDao.queryAccessToken(map);
	}

	@Override
	public String getAccessToken() {
		String appid = PlatformContext.getGoalbalContext("appid", String.class);
		String secret = PlatformContext.getGoalbalContext("appscret", String.class);
		return getAccessToken(appid, secret);
	}

	@Override
	public String getAccessTokenAdmin() {
		String appid = PlatformContext.getGoalbalContext("appidadmin", String.class);
		String secret = PlatformContext.getGoalbalContext("appscretadmin", String.class);
		return getAccessToken(appid, secret);
	}

	
}
