package com.zhcs.service;

import com.zhcs.entity.ReceiveData;
import com.zhcs.entity.WechatEntity;
import com.zhcs.utils.ServiceException;

import java.util.List;
import java.util.Map;

//*****************************************************************************
/**
 * <p>Title:WechatService</p>
 * <p>Description: 微信关注列表</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public interface WechatService {
	
	WechatEntity queryObject(Long id);
	
	WechatEntity queryObjectByOpenid(String openid);
	
	List<WechatEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(WechatEntity wechat);
	
	void update(WechatEntity wechat);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);

	void doSubscribe(ReceiveData data) throws ServiceException;

	void doUnsubscribe(ReceiveData data);

}
