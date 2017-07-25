package com.zhcs.dao;

import java.util.Map;

import com.zhcs.entity.WeixintokenEntity;

//*****************************************************************************
/**
 * <p>Title:WeixintokenDao</p>
 * <p>Description: 微信token</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public interface WeixintokenDao extends BaseDao<WeixintokenEntity> {

	String queryAccessToken(Map<String, String> map);
	
}
