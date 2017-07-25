package com.zhcs.controller.weixin;

import java.util.List;

import org.jeewx.api.core.exception.WexinReqException;
import org.jeewx.api.core.req.model.message.IndustryTemplateMessageSend;
import org.jeewx.api.core.req.model.message.TemplateData;
import org.jeewx.api.core.req.model.message.TemplateMessage;
import org.jeewx.api.wxsendmsg.JwTemplateMessageAPI;
import org.jeewx.api.wxuser.user.JwUserAPI;
import org.jeewx.api.wxuser.user.model.Wxuser;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weixintmsg")
public class WeiXinTMSGController {
	public static void main(String[] args) {
		try {
			String s = "K3gKEKPKoR_31-o0KNHHcEIG2VJZglUBpwbFM7f_8XYBLEwzHl3PnaAIR3P7RtNAqmi3KP9CBJ4OikbDM-iIWw-Ebc3hQt2av3BGr5KfFACJzbu0r3tOlVdg7AfoDdXYPXTaAFASAW";
			// 获取token
			//JwTokenAPI.getAccessToken("wxffe43e9023f4e499","e1913befffa61e51baa5233540a03bf9");
			//System.out.println(s);
			IndustryTemplateMessageSend industryTemplateMessageSend = new IndustryTemplateMessageSend();
			industryTemplateMessageSend.setAccess_token(s);
			industryTemplateMessageSend.setTemplate_id("HxTtyeGid4dsVxZR-YtSjl_pPacuChA97NW-pFiZ3sU");
			List<Wxuser> wu = JwUserAPI.getAllWxuser(s, "");
			for (Wxuser wxuser : wu) {
				industryTemplateMessageSend.setTouser(/*"oY9cUvxHwM5IgU1MvVAQwNZDnkwU"*/ wxuser.getOpenid());
				industryTemplateMessageSend.setTopcolor("#ffAADD");
				TemplateMessage data = new TemplateMessage();
				TemplateData first = new TemplateData();
				
				first.setColor("#173177");
				first.setValue(wxuser.getNickname() + "您有一条会议提醒：");
				
				TemplateData Topic = new TemplateData();
				Topic.setColor("#ff0000");
				Topic.setValue("智慧城市3.0平台项目启动会");
				
				TemplateData Time = new TemplateData();
				Time.setColor("#000000");
				Time.setValue("2017/03/31 17:30:00");
				
				TemplateData Address = new TemplateData();
				Address.setColor("#000000");
				Address.setValue("602会议室");
				
				TemplateData remark= new TemplateData();
				remark.setValue("请及时到达！");
				data.setFirst(first);
				data.setTopic(Topic);
				data.setTime(Time);
				data.setAddress(Address);
				data.setRemark(remark);
				industryTemplateMessageSend.setData(data);
				
				s  = JwTemplateMessageAPI.sendTemplateMsg(industryTemplateMessageSend);
				
				System.out.println(s);
			}
		} catch (WexinReqException e) {
			e.printStackTrace();
		}
	}

}