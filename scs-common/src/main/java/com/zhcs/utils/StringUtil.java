package com.zhcs.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

//*****************************************************************************
/**
 * <p>Title:StringUtil</p>
 * <p>Description:字符串工具类</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司</p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public class StringUtil extends StringUtils {
	
	public static final SerializerFeature[] DEFAULT_FORMAT = {SerializerFeature.WriteDateUseDateFormat, SerializerFeature.WriteEnumUsingToString,
        SerializerFeature.WriteNonStringKeyAsString, SerializerFeature.QuoteFieldNames, SerializerFeature.SkipTransientField,
        SerializerFeature.SortField, SerializerFeature.PrettyFormat};

	//*************************************************************************
	/** 
	* 【轉換】將以，分割的字符串轉換成字符串數組
	* @param valStr
	* @return  
	*/
	//*************************************************************************
	public static String[] StrList(String valStr){
	    int i = 0;
	    String TempStr = valStr;
	    String[] returnStr = new String[valStr.length() + 1 - TempStr.replace(",", "").length()];
	    valStr = valStr + ",";
	    while (valStr.indexOf(',') > 0)
	    {
	        returnStr[i] = valStr.substring(0, valStr.indexOf(','));
	        valStr = valStr.substring(valStr.indexOf(',')+1 , valStr.length());
	        
	        i++;
	    }
	    return returnStr;
	}

	//*************************************************************************
	/** 
	* 【替換】字符串替換
	* @param s 待替换的字符串
	* @param s1 替换部分
	* @param s2 替换值
	* @return String 替换后的字符串
	*/
	//*************************************************************************
	public static String replace(String s, String s1, String s2) {

		StringBuffer stringbuffer = new StringBuffer();
		int i = s1.length();
		for (int j = 0; (j = s.indexOf(s1)) != -1;) {
			stringbuffer.append(s.substring(0, j) + s2);
			s = s.substring(j + i);
		}

		stringbuffer.append(s);
		return stringbuffer.toString();
	}

	//*************************************************************************
	/** 
	* 【判斷】判斷字符串是否有效
	* @param obj
	* @return  
	*/
	//*************************************************************************
	@SuppressWarnings("rawtypes")
	public static boolean isValid(Object obj) {
		if (obj == null) {
			return false;
		} else if ((obj instanceof String) && obj.toString().trim().length() == 0) {
			return false;
		} else if ((obj instanceof List) && ((List) obj).size() == 0) {
			return false;
		} else if ((obj instanceof Map) && ((Map) obj).keySet().size() == 0) {
			return false;
		}
		return true;
	}

	//*************************************************************************
	/** 
	* 【判斷】判斷字符串是否無效
	* @param obj
	* @return  
	*/
	//*************************************************************************
	public static boolean isBlank(Object obj) {

		return !isValid(obj);
	}

	//*************************************************************************
	/** 
	* 【判斷】判斷字符串是否是int類型
	* @param str
	* @return  
	*/
	//*************************************************************************
	public static boolean isInt(String str) {
		if (str == null)
			return false;
		Pattern pattern = Pattern.compile("[-+]{0,1}[0-9]+");
		return pattern.matcher(str).matches();
	}

	//*************************************************************************
	/** 
	* 【轉換】將字符串數字轉換成int
	* @param sInt
	* @return  
	*/
	//*************************************************************************
	public static Integer strToInteger(String sInt) {

		Integer iRtn = 0;
		if (sInt == null || "".equals(sInt))
			return 0;
		try {
			iRtn = Integer.valueOf(sInt);
		} catch (Exception e) {
		}
		return iRtn;
	}

	//*************************************************************************
	/** 
	* 【轉換】將object類型的數據轉換成string類型數據
	* @param obj
	* @return  
	*/
	//*************************************************************************
	public static String valueOf(Object obj) {

		if (obj == null) {
			return "";
		} else {
			return String.valueOf(obj);
		}
	}

	//*************************************************************************
	/** 
	* 【轉換】將object類型的數據轉換成string類型數據，無限噢時將返回默認值
	* @param obj 待轉換數據
	* @param defaultString 默認值
	* @return  
	*/
	//*************************************************************************
	public static String valueOf(Object obj, String defaultString) {

		if (obj == null) {
			return defaultString;
		} else {
			return String.valueOf(obj);
		}
	}

	//*************************************************************************
	/** 
	* 【過濾】過濾json類型字符串
	* @param content
	* @return  
	*/
	//*************************************************************************
	public static String filterJsonChar(String content) {

		StringBuilder sb = new StringBuilder(content.length() + 20);
		for (int i = 0; i < content.length(); i++) {
			char c = content.charAt(i);
			switch (c) {
				case '\"':
					sb.append("\\\"");
					break;
				case '\\':
					sb.append("\\\\");
					break;
				case '/':
					sb.append("\\/");
					break;
				case '\b':
					sb.append("\\b");
					break;
				case '\f':
					sb.append("\\f");
					break;
				case '\n':
					sb.append("\\n");
					break;
				case '\r':
					sb.append("\\r");
					break;
				case '\t':
					sb.append("\\t");
					break;
				default:
					sb.append(c);
			}
		}
		return sb.toString();
	}

	//*************************************************************************
	/** 
	* 【取得】四捨五入取指定數字
	* @param number 數字
	* @param sacle 小數位數
	* @return  
	*/
	//*************************************************************************
	public static double round(double number, int sacle) {

		BigDecimal b = new BigDecimal(number);
		double f1 = b.setScale(sacle, BigDecimal.ROUND_HALF_UP).doubleValue();
		return f1;
	}

	//*************************************************************************
	/** 
	* 【取得】捨去指定小數位數數字
	* @param number 數字
	* @param sacle 捨去小數位數
	* @return  
	*/
	//*************************************************************************
	public static double fix(double number, int sacle) {

		String num = StringUtil.valueOf(number);
		int idx = num.indexOf(".");
		if (idx == -1) {
			return number;
		} else {
			String s = num.substring(idx);
			num = num.substring(0, idx) + s.substring(0, sacle+1 < s.length() ? sacle+1 : s.length());
		}
		return Double.parseDouble(num);
	}
	
	//*************************************************************************
	/** 
	* 【取得】向上取整
	* @param number
	* @return  
	*/
	//*************************************************************************
	public static double ceil(double number) {
		
		return Math.ceil(number);
	}
	
	//*************************************************************************
	/** 
	* 【取得】向下取整
	* @param number
	* @return  
	*/
	//*************************************************************************
	public static double floor(double number) {
		
		return Math.floor(number);
	}
	
	//*************************************************************************
	/** 
	* 【取得】將手機號碼的關鍵數字進行*替換
	* @param number
	* @return  
	*/
	//*************************************************************************
	public static String ReplaceMobile(String number) {
		return number.replaceAll("(?<=\\d{3})\\d(?=\\d{4})", "*");
	}
	
	//*************************************************************************
	/** 
	* 【取得】補零方法
	* @param strSource 原字符串
	* @param iLen 所需長度
	* @param iDirection 0 表示左補零 其它表示右補零
	* @return  
	*/
	//*************************************************************************
	public static String fillZero(String strSource, int iLen, int iDirection) {
		if (strSource == null)
			return "";
		if (strSource.length() > iLen) {
			return strSource.substring(0, iLen);
		}
		if (iDirection == 0) {
			return StringUtil.leftPad(strSource, iLen, "0");
		} else {
			return StringUtil.rightPad(strSource, iLen, "0");
		}
	}
	
	//*************************************************************************
	/** 
	* 【轉換】將字符串數組轉換成為List
	* @param array
	* @return  
	*/
	//*************************************************************************
	public static List<String> array2list(String[] array){
		List<String> list = new ArrayList<String>();
		Collections.addAll(list, array);
		return list;
		
	}
	
	//*************************************************************************
	/** 
	* 【轉換】將string類型數據轉換成ASCII碼
	* @param value
	* @return  
	*/
	//*************************************************************************
	public static String stringToAscii(String value)  
	{  
	    StringBuffer sbu = new StringBuffer();  
	    char[] chars = value.toCharArray();   
	    for (int i = 0; i < chars.length; i++) {  
	        if(i != chars.length - 1)  
	        {  
	            sbu.append((int)chars[i]).append(",");  
	        }  
	        else {  
	            sbu.append((int)chars[i]);  
	        }  
	    }  
	    return StringUtil.valueOf(sbu);  
	}  
	
	//*************************************************************************
	/** 
	* 【轉換】將ASCII碼數據轉換成爲string類型數據
	* @param value
	* @return  
	*/
	//*************************************************************************
	public static String asciiToString(String value)  
	{  
	    StringBuffer sbu = new StringBuffer();  
	    String[] chars = value.split(",");  
	    for (int i = 0; i < chars.length; i++) {  
	        sbu.append((char) Integer.parseInt(chars[i]));  
	    }  
	    return StringUtil.valueOf(sbu);  
	}  
	
	//*************************************************************************
	/** 
	* 【取得】過濾html標簽
	* @param s
	* @return  
	*/
	//*************************************************************************
	public static String guoHtml(String s) {
		if (StringUtil.isValid(s)) {
			return s.replaceAll("<[.[^<]]*>", "").replaceAll("\"","").replaceAll("\'","");
		} else {
			return s;
		}
	}
	
	//*************************************************************************
	/** 
	* 【获取】使用正则获取到模板中的自定义参数
	* @param text
	* @return  
	*/
	//*************************************************************************
	public static List<String> extractMessageByRegular(String text, boolean ispro){  
        
        List<String> list=new ArrayList<String>();  
        //Pattern p = Pattern.compile("(\\[[^\\]]*\\])");  
        Pattern p = Pattern.compile("(\\{\\{|\\{)(.*?)(\\}\\}|\\})");  
        Matcher m = p.matcher(text);  
        
        while(m.find()){  
            if(ispro){
            	list.add(m.group(0)); 
            } else {
            	list.add(m.group(2)); 
            }
        }  
        return list;  
    }  
	
	/**
     * @param obj 需要转换的java bean
     * @param <T> 入参对象类型泛型
     * @return 对应的json字符串
     */
    public static <T> String toJson(T obj) {
        return JSON.toJSONString(obj/*, DEFAULT_FORMAT*/);
    }

    /**
     * 通过Map生成一个json字符串
     *
     * @param map 需要转换的map
     * @return json串
     */
    public static String toJson(Map<String, Object> map) {
        return JSON.toJSONString(map/*, DEFAULT_FORMAT*/);
    }
    
    /**
     * 将字符串转换成JSON字符串
     *
     * @param jsonString json字符串
     * @return 转换成的json对象
     */
    public static JSONObject getJSONFromString(final String jsonString) {
        if (isBlank(jsonString)) {
            return new JSONObject();
        }
        return JSON.parseObject(jsonString);
    }
    
    /**
     * 美化传入的json,使得该json字符串容易查看
     *
     * @param jsonString 需要处理的json串
     * @return 美化后的json串
     */
    public static String prettyFormatJson(String jsonString) {
        return JSON.toJSONString(getJSONFromString(jsonString), true);
    }
    
    //*************************************************************************
    /** 
    * 【获取】获取倒序list
    * @param list
    * @return  
    */
    //*************************************************************************
    public static <T> List<T> ListReverse(List<T> list) {
    	Collections.reverse(list);
    	return list;
    }

	/**
	 * list 转 string
	 * @param list
	 * @return
	 */
	public static String listToString(List<?> list) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < list.size(); i++) {
			sb.append(list.get(i)).append(",");
		}
		return sb.toString().substring(0, sb.toString().length() - 1);
	}
    
    //*************************************************************************
    /** 
    * 【获取】拷贝list到另一个list
    * @param list 源
    * @param list2 目的地
    * @return  
    */
    //*************************************************************************
    public static <T> List<T> ListCopy(List<T> list, List<T> list2) {
    	Collections.copy(list2, list);
    	return list2;
    }
	
	public static void main(String[] args) {
//		String a = "1";
//		System.out.println(fillZero(a, 5, 0));
//		System.out.println(Math.random());
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", 123123);
		map.put("data2", "123123");
		System.out.println(toJson(map));
	}
	
}
