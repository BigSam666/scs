package com.zhcs.controller.weixin;

import org.jeewx.api.core.exception.WexinReqException;
import org.jeewx.api.core.req.model.message.IndustryTemplateMessageSend;
import org.jeewx.api.core.req.model.message.TemplateData;
import org.jeewx.api.core.req.model.message.TemplateMessage;
import org.jeewx.api.wxsendmsg.JwTemplateMessageAPI;

import com.zhcs.utils.DateUtil;

public class WeiXinController {
	public static void main(String[] args) {
		try {
			String s = "f1gaVOtcaCma1oSxyOS8Bb0i4z73QEV9mUD8k2q5YE-5_IYYhtoCdgM5AA4o2ZLosjGt6gHsylZKxRt-qRJ0sqwp3IyKa2OYMC8DJhtFTu6tCPpVFoGfoN8FGzdxHqVwBTCcAEAGYH";
			// 获取token
			//JwTokenAPI.getAccessToken("wxffe43e9023f4e499","e1913befffa61e51baa5233540a03bf9");
			System.out.println(s);
			IndustryTemplateMessageSend industryTemplateMessageSend = new IndustryTemplateMessageSend();
			industryTemplateMessageSend.setAccess_token(s);
			industryTemplateMessageSend.setTemplate_id("-4HsOMFpfWYPtTe_s-HY6_Lta9nSCd0Jk6wbPF85riU");
			industryTemplateMessageSend.setTouser("oY9cUvxHwM5IgU1MvVAQwNZDnkwU");
			// 没有设置url就不会有详情显示
			// industryTemplateMessageSend.setUrl("www.baidu.com");
			industryTemplateMessageSend.setTopcolor("#ffAADD");
			TemplateMessage data = new TemplateMessage();
			TemplateData first = new TemplateData();
			first.setColor("#173177");
			first.setValue("您提交的服务申请已收到，请保持手机畅通，稍后会有服务工程师与您联系服务时间。");
			
			TemplateData keynote1= new TemplateData();
			keynote1.setColor("#ff0000");
			keynote1.setValue("已申请");
			TemplateData keynote2= new TemplateData();
			keynote2.setColor("#000000");
			keynote2.setValue(DateUtil.getSystemDateStr());
			
			TemplateData remark= new TemplateData();
			remark.setValue("感谢您选择我们的服务");
			data.setFirst(first);
			data.setKeynote1(keynote1);
			data.setKeynote2(keynote2);
			data.setRemark(remark);
			industryTemplateMessageSend.setData(data);
			
			s  = JwTemplateMessageAPI.sendTemplateMsg(industryTemplateMessageSend);
			
			System.out.println(s);
			
		} catch (WexinReqException e) {
			e.printStackTrace();
		}

	}

}