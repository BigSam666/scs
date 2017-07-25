package com.zhcs.task;

import java.util.ArrayList;
import java.util.List;

import org.jeewx.api.core.exception.WexinReqException;
import org.jeewx.api.wxuser.user.JwUserAPI;
import org.jeewx.api.wxuser.user.model.Wxuser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.zhcs.context.PlatformContext;
import com.zhcs.entity.WechatEntity;
import com.zhcs.entity.WechatadminEntity;
import com.zhcs.entity.WeixintokenEntity;
import com.zhcs.service.WechatService;
import com.zhcs.service.WechatadminService;
import com.zhcs.service.WeixintokenService;
import com.zhcs.utils.BeanUtil;
import com.zhcs.utils.Constant;
import com.zhcs.utils.GetAccesstoken;
import com.zhcs.utils.LogUtil;
import com.zhcs.utils.StringUtil;

//*****************************************************************************
/**
 * <p>Title:TestTask</p>
 * <p>Description:测试定时任务</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司</p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
@Component("systemTask")
public class SystemTask {

	@Autowired
	private WechatService wechatService;
	
	@Autowired
	private WechatadminService wechatadminService;
	
	@Autowired
	private WeixintokenService weixintokenService;
	
	//*************************************************************************
	/** 
	* 【获取】定时任务获取微信服务号token 
	*/
	//*************************************************************************
	public void getAccessToken(){
		GetAccesstoken.getAccessToken();
		// 市民
		String appid = PlatformContext.getGoalbalContext("appid", String.class);
		String appscret = PlatformContext.getGoalbalContext("appscret", String.class);
		String token1 = Constant.access_token;
		// 员工
		String appidadmin = PlatformContext.getGoalbalContext("appidadmin", String.class);
		String appscretadmin = PlatformContext.getGoalbalContext("appscretadmin", String.class);
		String token2 = Constant.access_token2;
		
		WeixintokenEntity weixintokenEntity = new WeixintokenEntity(appid,appscret,token1,System.currentTimeMillis());
		WeixintokenEntity weixintokenEntity2 = new WeixintokenEntity(appidadmin,appscretadmin,token2,System.currentTimeMillis());
		BeanUtil.fillCCUUD(weixintokenEntity);
		BeanUtil.fillCCUUD(weixintokenEntity2);
		
		weixintokenService.update(weixintokenEntity);
		weixintokenService.update(weixintokenEntity2);
		
	}
	
	//*************************************************************************
	/** 
	* 【同步】同步市民微信服务号用户列表到数据库
	* @throws Exception  
	*/
	//*************************************************************************
	public void syncUserList() throws Exception{
		LogUtil.info("开始同步市民微信服务号用户列表...");
		List<Wxuser> wu = new ArrayList<Wxuser>();
		try {
			wu = JwUserAPI.getAllWxuser(Constant.access_token, "");
		} catch (WexinReqException e) {
			LogUtil.info("获取市民微信服务号用户列表失败正在重试...");
			LogUtil.error(e.getMessage());
			getAccessToken();
		}
		LogUtil.debug("获取到同步市民微信服务号用户列表为:{}", JSON.toJSONString(wu));
		LogUtil.info("开始插入到数据库表...");
		WechatEntity wechat = null;
		for (Wxuser wxuser : wu) {
			// 应该先判断是否有数据，没有数据进行插入，有数据进行修改。
			wechat = wechatService.queryObjectByOpenid(wxuser.getOpenid());
			if(StringUtil.isValid(wechat)){
				wechat.setCity(wxuser.getCity());
				wechat.setCountry(wxuser.getCountry());
				wechat.setHeadimgurl(wxuser.getHeadimgurl());
				wechat.setLanguage(wxuser.getLanguage());
				wechat.setNickname(wxuser.getNickname());
				wechat.setOpenid(wxuser.getOpenid());
				wechat.setProvince(wxuser.getProvince());
				wechat.setSex(wxuser.getSex());
				wechat.setSubscribe(StringUtil.valueOf(wxuser.getSubscribe()));
				wechat.setSubscribeTime(wxuser.getSubscribe_time());
				BeanUtil.fillCCUUD(wechat, 1L);
				wechatService.update(wechat);
			} else {
				wechat = new WechatEntity();
				wechat.setCity(wxuser.getCity());
				wechat.setCountry(wxuser.getCountry());
				wechat.setHeadimgurl(wxuser.getHeadimgurl());
				wechat.setLanguage(wxuser.getLanguage());
				wechat.setNickname(wxuser.getNickname());
				wechat.setOpenid(wxuser.getOpenid());
				wechat.setProvince(wxuser.getProvince());
				wechat.setSex(wxuser.getSex());
				wechat.setSubscribe(StringUtil.valueOf(wxuser.getSubscribe()));
				wechat.setSubscribeTime(wxuser.getSubscribe_time());
				BeanUtil.fillCCUUD(wechat);
				wechatService.save(wechat);
				/*citizen = new CitizenEntity();
				citizen.setNm(wxuser.getNickname());
				citizen.setFaceimg(wxuser.getHeadimgurl());
				citizen.setWechatid(wxuser.getOpenid());
				citizen.setGender(wxuser.getSex());
				citizen.setAddr(wxuser.getCountry() + wxuser.getProvince() + wxuser.getCity());
				BeanUtil.fillCCUUD(citizen);
				citizenService.save(citizen);*/
			}
		}
		LogUtil.info("结束同步市民微信服务号用户列表...");
	}
	
	//*************************************************************************
	/** 
	* 【同步】同步员工微信服务号用户列表到数据库
	* @throws Exception  
	*/
	//*************************************************************************
	public void syncUserListByAdmin() throws Exception {
		LogUtil.info("开始同步员工微信服务号用户列表...");
		List<Wxuser> wu = new ArrayList<Wxuser>();
		try {
			wu = JwUserAPI.getAllWxuser(Constant.access_token2, "");
		} catch (WexinReqException e) {
			LogUtil.info("获取员工微信服务号用户列表失败正在重试...");
			LogUtil.error(e.getMessage());
			getAccessToken();
		}
		LogUtil.debug("获取到同步员工微信服务号用户列表为:{}", JSON.toJSONString(wu));
		LogUtil.info("开始插入到数据库表...");
		WechatadminEntity wechat = null;
		for (Wxuser wxuser : wu) {
			// 应该先判断是否有数据，没有数据进行插入，有数据进行修改。
			wechat = wechatadminService.queryObjectByOpenid(wxuser.getOpenid());
			if(StringUtil.isValid(wechat)){
				wechat.setCity(wxuser.getCity());
				wechat.setCountry(wxuser.getCountry());
				wechat.setHeadimgurl(wxuser.getHeadimgurl());
				wechat.setLanguage(wxuser.getLanguage());
				wechat.setNickname(wxuser.getNickname());
				wechat.setOpenid(wxuser.getOpenid());
				wechat.setProvince(wxuser.getProvince());
				wechat.setSex(wxuser.getSex());
				wechat.setSubscribe(StringUtil.valueOf(wxuser.getSubscribe()));
				wechat.setSubscribeTime(wxuser.getSubscribe_time());
				BeanUtil.fillCCUUD(wechat, 1L);
				wechatadminService.update(wechat);
			} else {
				wechat = new WechatadminEntity();
				wechat.setCity(wxuser.getCity());
				wechat.setCountry(wxuser.getCountry());
				wechat.setHeadimgurl(wxuser.getHeadimgurl());
				wechat.setLanguage(wxuser.getLanguage());
				wechat.setNickname(wxuser.getNickname());
				wechat.setOpenid(wxuser.getOpenid());
				wechat.setProvince(wxuser.getProvince());
				wechat.setSex(wxuser.getSex());
				wechat.setSubscribe(StringUtil.valueOf(wxuser.getSubscribe()));
				wechat.setSubscribeTime(wxuser.getSubscribe_time());
				BeanUtil.fillCCUUD(wechat);
				wechatadminService.save(wechat);
			}
		}
		LogUtil.info("结束同步员工微信服务号用户列表...");
	}
}
