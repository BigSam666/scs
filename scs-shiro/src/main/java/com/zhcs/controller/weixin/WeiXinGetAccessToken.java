package com.zhcs.controller.weixin;

import java.util.List;

import org.jeewx.api.core.exception.WexinReqException;
import org.jeewx.api.wxbase.wxtoken.JwTokenAPI;
import org.jeewx.api.wxuser.user.JwUserAPI;
import org.jeewx.api.wxuser.user.model.Wxuser;

import com.alibaba.fastjson.JSON;
import com.zhcs.utils.LogUtil;

public class WeiXinGetAccessToken {
	public static void main(String[] args) throws WexinReqException {
		//System.out.println(JwTokenAPI.getAccessToken("wxffe43e9023f4e499","e1913befffa61e51baa5233540a03bf9"));
		List<Wxuser> wu = JwUserAPI.getAllWxuser("1x-eznuTJm3NNyEh5OaFsJoKYBHNVXGKNvjcnvDXEtcTV5eBrig9nYX5m3aX5Vft10UcyXW7Db6wQHdd-wxb7OZ0_4uLxwKER_NUZCGSq3K3m0uvG7glQClBD4NkIZtzPPDiACADDR", "");
		LogUtil.info("得到关注用户列表为：{}", JSON.toJSONString(wu));
	}
	public static String getToken() throws WexinReqException{
		String s = JwTokenAPI.getAccessToken("wxffe43e9023f4e499","e1913befffa61e51baa5233540a03bf9");
		System.out.println(s);
		return s;
	}
	
}
