package com.zhcs.service;

import com.zhcs.entity.WeixintokenEntity;

import java.util.List;
import java.util.Map;

//*****************************************************************************
/**
 * <p>Title:WeixintokenService</p>
 * <p>Description: 微信token</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public interface WeixintokenService {
	
	WeixintokenEntity queryObject(Long id);
	
	List<WeixintokenEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(WeixintokenEntity weixintoken);
	
	void update(WeixintokenEntity weixintoken);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
	
	/**
	 * 获取 access_token
	 */
	String getAccessTokenAdmin();
	/**
	 * 获取 access_token
	 */
	String getAccessToken();
	
	
}
