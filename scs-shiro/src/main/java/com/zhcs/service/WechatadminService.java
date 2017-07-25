package com.zhcs.service;

import com.zhcs.entity.ReceiveData;
import com.zhcs.entity.WechatadminEntity;
import com.zhcs.utils.ServiceException;

import java.util.List;
import java.util.Map;

//*****************************************************************************
/**
 * <p>Title:WechatadminService</p>
 * <p>Description: 微信关注列表</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public interface WechatadminService {
	
	WechatadminEntity queryObject(Long id);
	
	WechatadminEntity queryObjectByOpenid(String openid);
	
	List<WechatadminEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(WechatadminEntity wechatadmin);
	
	void update(WechatadminEntity wechatadmin);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);

	/**
	 * 处理员工关注
	 */
	void doSubscribe(ReceiveData data) throws ServiceException;

	void doUnsubscribe(ReceiveData data);
}
