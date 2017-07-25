package com.zhcs.utils;

import java.util.Arrays;


//*****************************************************************************
/**
 * <p>Title:CheckSignatureUtils</p>
 * <p>Description: 检查微信SIGNATUREN是否正确</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司</p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年5月26日
 */
//*****************************************************************************
public class CheckSignatureUtils {
	public static boolean checkSignature(String token, String signature, String timestamp, String nonce) {
		/** 
		 * 开发者通过检验signature对请求进行校验（下面有校验方式）。
		 * 若确认此次GET请求来自微信服务器，请原样返回echostr参数内容，则接入生效，成为开发者成功，否则接入失败。
		 * 加密/校验流程如下：
		 * 1. 将token、timestamp、nonce三个参数进行字典序排序
		 * 2. 将三个参数字符串拼接成一个字符串进行sha1加密
		 * 3. 开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
		 */
		boolean result = false;
		String [] arrone = new String [] {token, timestamp, nonce};
		Arrays.sort(arrone);													//升序进行排序
		StringBuilder arrstr = new StringBuilder();
		for (int i = 0 ; i < arrone.length; i++) {
			arrstr.append(arrone[i]);											//拼接成一个字符串
		}
		String tmpstr = SHA1.SHA1Digest(StringUtil.valueOf(arrstr));
		// jdk自身办法
		/*MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-1");							//获得sha1加密对象
			byte [] digest = md.digest(arrstr.toString().getBytes());			//进行加密
			tmpstr = HelpCommon.bytesToHex(digest);								//字节数组转换为十六进制字符串
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}*/
		
		if (null != tmpstr && tmpstr.equalsIgnoreCase(signature)) {
			result = true;
		}
		return result;
	}


}
