package com.zhcs.controller.weixin;

import com.zhcs.entity.MessageType;
import com.zhcs.entity.ReceiveData;
import com.zhcs.service.WechatadminService;
import com.zhcs.utils.CheckSignatureUtils;
import com.zhcs.utils.LogUtil;
import com.zhcs.utils.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
@RequestMapping("/weixin")
public class WeiXinCenterController {
	
	@Autowired
	private WechatadminService wechatadminService;
	
	private static final String token = "zhcsygd";
	
	/**
	 * 仅用于微信对接
	 */
	@ResponseBody
	@RequestMapping(value="/",method=RequestMethod.GET)
	public void docking(HttpServletRequest request, HttpServletResponse response, String signature, String timestamp, String nonce, String echostr){
		LogUtil.info("signature：{}", signature);
		LogUtil.info("timestamp：{}", timestamp);
		LogUtil.info("nonce：{}", nonce);
		LogUtil.info("echostr：{}", echostr);

		boolean flag = CheckSignatureUtils.checkSignature(token, signature, timestamp, nonce);
		PrintWriter out = null;
		try {
			out = response.getWriter();
			if (flag) {
				out.print(echostr);
			} else {
				out.print("签名不一致");
				LogUtil.error("签名不一致");
			}
		} catch (IOException e) {
			LogUtil.error(e.getMessage());
		} finally {
			if (null != out) {
				out.close();
				out = null;
			}
		}
	}
	
	/**
	 * 微信的入口
	 */
	@ResponseBody
	@RequestMapping(value="/", method=RequestMethod.POST, produces="application/xml;charset=UTF-8")
	public ReceiveData entrance(@RequestBody ReceiveData data) {
		
		//根据消息类型进行回复
		switch (data.getMsgType()) {
			case MessageType.EVENT:      //对事件进行回复（关注、取消关注）
				String eventType=data.getEvent();
				if (MessageType.SUBSCRIBE.equals(eventType)) {
					LogUtil.info(data.getFromUserName()+"：关注了");//====================
					try {
						wechatadminService.doSubscribe(data);
						data.setContent("感谢您关注鑫梓润");
						data.setMsgType(MessageType.TEXT);
					} catch (ServiceException e) {
						return null;
					}
				}
				if (MessageType.UNSUBSCRIBE.equals(eventType)) {
					LogUtil.info(data.getFromUserName()+"：取消关注了");//=====================
					wechatadminService.doUnsubscribe(data);
				}
				if (MessageType.CLICK.equals(eventType)) {
					LogUtil.info(data.getFromUserName()+"：Click菜单点击");//==================
				}
				if (MessageType.VIEW.equals(eventType)) {
					LogUtil.info(data.getFromUserName()+"：View菜单点击");//==================
				}
				if (MessageType.SCANCODE_PUSH.equals(eventType)) {
					LogUtil.info(data.getFromUserName()+"：扫码事件");//==================
				}
				break;
			case MessageType.TEXT:       //对文字信息进行回复
				if (data.getContent().contains("来一首")) {//对音乐进行回复
					break;
				}else if (data.getContent().contains("图战")) {//对图战进行回复
					LogUtil.info(data.getFromUserName()+"：引发了图战");
					break;
				}
				LogUtil.info(data.getFromUserName()+"："+data.getContent());//===================
				/*String message = "您发送的消息是：" + data.getContent();*/
				break;
			case MessageType.IMAGE:      //对图片进行回复
				LogUtil.info(data.getFromUserName()+"：发了一张图片");//=========================
				break;
			case MessageType.LOCATION:      //对地理位置进行回复
				LogUtil.info(data.getFromUserName()+"：发了地理位置");//=========================
				break;
			default:
				LogUtil.info(data.getFromUserName()+"：发了未知内容");//==========================
				break;
		}
		//将FromUserName和ToUserName交换
		swapName(data);
		return data;
	}
	
	
	
	/**
	 * 将FromUserName和ToUserName交换
	 * @param data
	 */
	private void swapName(ReceiveData data){
		String FromUserName=data.getFromUserName();
		data.setFromUserName(data.getToUserName());
		data.setToUserName(FromUserName);
	}
}