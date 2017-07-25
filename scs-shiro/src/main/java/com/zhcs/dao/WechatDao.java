package com.zhcs.dao;

import com.zhcs.entity.WechatEntity;

//*****************************************************************************
/**
 * <p>Title:WechatDao</p>
 * <p>Description: 微信关注列表</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public interface WechatDao extends BaseDao<WechatEntity> {
	
	WechatEntity queryObjectByOpenid(String openid);

    /**
     * 根据openId更改订阅状态
     */
    void updateSubscribeState(String openId, String subscribe);
}
