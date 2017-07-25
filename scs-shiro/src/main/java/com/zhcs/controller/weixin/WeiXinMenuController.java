package com.zhcs.controller.weixin;

import java.util.ArrayList;
import java.util.List;

import org.jeewx.api.core.exception.WexinReqException;
import org.jeewx.api.core.req.model.menu.WeixinButton;
import org.jeewx.api.wxbase.wxtoken.JwTokenAPI;
import org.jeewx.api.wxmenu.JwMenuAPI;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zhcs.utils.LogUtil;

@RestController
@RequestMapping("/weixinmenu")
public class WeiXinMenuController {
	public static void main(String[] args) {
		try {
			String s = JwTokenAPI.getAccessToken("wxffe43e9023f4e499","e1913befffa61e51baa5233540a03bf9");
			LogUtil.info("获取到token为:{}", s);
			
			List<WeixinButton> sub_button = new ArrayList<WeixinButton>();
			// 微信所有菜单
			List<WeixinButton> testsUb = new ArrayList<WeixinButton>();
			
			WeixinButton w = new WeixinButton();
			w.setType("view");
			w.setName("智慧市容");
			w.setUrl("http://wx.szscsinfo.com");
			
			WeixinButton w2 = new WeixinButton();
			w2.setName("测试菜单");
			
			WeixinButton w1 = new WeixinButton();
			w1.setName("扫码带提示");
			w1.setKey("rselfmenu_0_0");
			w1.setType("scancode_waitmsg");

			WeixinButton w1_1 = new WeixinButton();
			w1_1.setName("扫码推事件");
			w1_1.setKey("rselfmenu_0_1");
			w1_1.setType("scancode_push");
			
			sub_button.add(w1);
			sub_button.add(w1_1);
			
			w2.setSub_button(sub_button);

			testsUb.add(w);
			testsUb.add(w2);
			
			s= JwMenuAPI.createMenu(s,testsUb);
			System.out.println(s);
		} catch (WexinReqException e) {
			e.printStackTrace();
		}

	}

}