package com.zhcs.utils;

import org.jeewx.api.core.exception.WexinReqException;
import org.jeewx.api.wxbase.wxtoken.JwTokenAPI;

import com.zhcs.context.PlatformContext;

public class GetAccesstoken {
	
	public static void getAccessToken(){
		LogUtil.info("开始获取微信服务号token...");
		try {
			if(StringUtil.isBlank(Constant.access_token)){
				Constant.access_token = JwTokenAPI.getAccessToken(PlatformContext.getGoalbalContext("appid", String.class),PlatformContext.getGoalbalContext("appscret", String.class));
			}
			if(StringUtil.isBlank(Constant.access_token2)) {
				Constant.access_token2 = JwTokenAPI.getAccessToken(PlatformContext.getGoalbalContext("appidadmin", String.class),PlatformContext.getGoalbalContext("appscretadmin", String.class));
			}
			// save
		} catch (WexinReqException e) {
			LogUtil.info("获取微信服务号token失败正在重试...");
			LogUtil.error(e.getMessage());
			getAccessToken();
		}
		LogUtil.debug("获取到市民微信服务号accessToken为:{}，员工微信服务号accessToken为:{}", Constant.access_token, Constant.access_token2);
		LogUtil.info("结束获取微信服务号token...");
	}
}
