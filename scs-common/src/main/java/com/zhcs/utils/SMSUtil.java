package com.zhcs.utils;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.aliyuncs.sms.model.v20160927.SingleSendSmsRequest;
import com.aliyuncs.sms.model.v20160927.SingleSendSmsResponse;
import com.zhcs.context.PlatformContext;
import com.zhcs.utils.strRandom.RandomStr;

//*****************************************************************************
/**
 * <p>Title:SMSUtil</p>
 * <p>Description:短信util</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司</p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年3月16日
 */
//*****************************************************************************
public class SMSUtil {
	
	public static void main(String[] args) {
		try {
			IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAIvkfHbkTbS8HX", "BydEtdPnj4ApsZ1KIqtJTdhBFm2gYD");
			DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", "Sms", "sms.aliyuncs.com");
			IAcsClient client = new DefaultAcsClient(profile);
			SingleSendSmsRequest request = new SingleSendSmsRequest();
			request.setSignName("智慧城市管家");// 控制台创建的签名名称
			request.setTemplateCode("SMS_55705042");// 控制台创建的模板CODE
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("product", "智慧城市3.0");
			map.put("code", RandomStr.randomNum(4));
			// 短信模板中的变量；数字需要转换为字符串；个人用户每个变量长度必须小于15个字符。"
			//String s = "{\"product\":\"智慧城市3.0\",\"code\":\"" + RandomStr.randomNum(4) + "\"}";
			request.setParamString(getParamString(map));
			//request.setParamString(StringUtil.valueOf(StringUtil.toJson(map)));
			request.setRecNum("13027991217");// 接收号码
			SingleSendSmsResponse httpResponse = client.getAcsResponse(request);
			LogUtil.info("返回结果:{}", JSON.toJSON(httpResponse));
		} catch (ServerException e) {
			e.printStackTrace();
		} catch (ClientException e) {
			e.printStackTrace();
		}
	}
	
	//*************************************************************************
	/** 
	* 【发送】发送短信
	* @param recNum 13027991217  发送给谁 接收号码
	* @param templateCode SMS_55705045  短信模板  控制台创建的模板CODE
	* @param paramString "{\"customer\":\"Alter\"}" 短信类容，短信模板中的变，数字需要转换为字符串；个人用户每个变量长度必须小于15个字符
	* @return
	* @throws ClientException  
	*/
	//*************************************************************************
	public static SingleSendSmsResponse SendSMS(String recNum, String templateCode, String paramString) throws ClientException{
		IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", 
				PlatformContext.getGoalbalContext("accessKey", String.class), 
				PlatformContext.getGoalbalContext("accessSecret", String.class));
		DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", "Sms", "sms.aliyuncs.com");
		IAcsClient client = new DefaultAcsClient(profile);
		SingleSendSmsRequest request = new SingleSendSmsRequest();
		request.setSignName(PlatformContext.getGoalbalContext("signname", String.class));// 控制台创建的签名名称
		request.setTemplateCode(templateCode);// 控制台创建的模板CODE
		request.setParamString(paramString);// 短信模板中的变量；数字需要转换为字符串；个人用户每个变量长度必须小于15个字符。"
		request.setRecNum(recNum);// 接收号码
		SingleSendSmsResponse httpResponse = client.getAcsResponse(request);
		LogUtil.info("返回结果:{}, requestid:{}", JSON.toJSON(httpResponse), httpResponse.getRequestId());
		return httpResponse;
	}
	
	//*************************************************************************
	/** 
	* 【转换】将map对象转换为可以发送到阿里大鱼的参数字符串
	* @param map 需要转换的map对象
	* @return  
	*/
	//*************************************************************************
	public static String getParamString(Map<String, Object> map){
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		int i = 0;
		for (String key : map.keySet()) {
			sb.append("\"");
			sb.append(key);
			sb.append("\":");
			sb.append("\"");
			sb.append(map.get(key));
			sb.append("\"");
			i++;
			if(i<map.size()){
				sb.append(",");
			}
		}
		sb.append("}");
		LogUtil.debug("转换之后为：" + sb);
		return StringUtil.valueOf(sb);
	}
	
}
