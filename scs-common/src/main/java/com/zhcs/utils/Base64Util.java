package com.zhcs.utils;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Base64;

//*****************************************************************************
/**
 * <p>Title:Base64Util</p>
 * <p>Description:将String进行base64编码解码，使用utf-8</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司</p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年5月25日
 */
//*****************************************************************************
public class Base64Util {

    //*************************************************************************
    /** 
    * 【解码】对给定的字符串进行base64解码操作
    * @param inputData
    * @return  
    */
    //*************************************************************************
    public static String decodeData(String inputData) {
        try {
            if (null == inputData) {
                return null;
            }
            return new String(Base64.decodeBase64(inputData.getBytes("utf-8")), "utf-8");
        } catch (UnsupportedEncodingException e) {
            LogUtil.error(e);
        }

        return null;
    }

    //*************************************************************************
    /** 
    * 【加密】对给定的字符串进行base64加密操作
    * @param inputData
    * @return  
    */
    //*************************************************************************
    public static String encodeData(String inputData) {
        try {
            if (null == inputData) {
                return null;
            }
            return new String(Base64.encodeBase64(inputData.getBytes("utf-8")), "utf-8");
        } catch (UnsupportedEncodingException e) {
            LogUtil.error(e);
        }

        return null;
    }

    public static void main(String[] args) {
		System.out.println(Base64Util.encodeData("刘晓东123"));
		System.out.println(Base64Util.decodeData("5YiY5pmT5LicMTIz"));
	}
    
}