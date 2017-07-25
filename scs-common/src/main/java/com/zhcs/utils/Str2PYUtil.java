package com.zhcs.utils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
 
//*****************************************************************************
/**
* <p>Title:PingYinUtil</p>
* <p>Description:拼音工具类</p>
* <p>Copyright: Copyright (c) 2017</p>
* <p>Company: 深圳市智慧城市管家信息科技有限公司</p>
* @author 刘晓东 - Alter
* @version v1.0 2017年3月16日
*/
//*****************************************************************************
public class Str2PYUtil {
	
    //*************************************************************************
    /** 
    * 【获取】获取拼音，带音标
    * @param inputString
    * @return  
    */
    //*************************************************************************
    public static String getPinYin2(String chinese) {  
    	  
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();  
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);  
        format.setToneType(HanyuPinyinToneType.WITH_TONE_MARK);  
        format.setVCharType(HanyuPinyinVCharType.WITH_U_UNICODE);  
  
        char[] input = chinese.trim().toCharArray();  
        StringBuffer output = new StringBuffer("");  
  
        try {  
            for (int i = 0; i < input.length; i++) {  
                if (Character.toString(input[i]).matches("[\u4E00-\u9FA5]+")) {  
                    String[] temp = PinyinHelper.toHanyuPinyinStringArray(input[i], format);  
                    output.append(temp[0]);  
                    output.append(" ");  
                } else  
                    output.append(Character.toString(input[i]));  
            }  
        } catch (BadHanyuPinyinOutputFormatCombination e) {  
            e.printStackTrace();  
        }  
        return output.toString();  
    }  

	//*************************************************************************
    /** 
    * 【获取】将字符串中的中文转化为拼音,其他字符不变
    * @param chinese
    * @return  
    */
    //*************************************************************************
    public static String getPingYin(String chinese) {
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        format.setVCharType(HanyuPinyinVCharType.WITH_V);
 
        char[] input = chinese.trim().toCharArray();
        String output = "";
 
        try {
            for (int i = 0; i < input.length; i++) {
                if (java.lang.Character.toString(input[i]).matches("[\\u4E00-\\u9FA5]+")) {
                    String[] temp = PinyinHelper.toHanyuPinyinStringArray(input[i], format);
                    output += temp[0];
                } else
                    output += java.lang.Character.toString(input[i]);
            }
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            e.printStackTrace();
        }
        return output;
    }
    
    //*************************************************************************
    /** 
     * 【获取】获取汉字串拼音首字母，英文字符不变  
     * @param chinese 汉字串  
     * @return  汉语拼音首字母  
     */
    //*************************************************************************
    public static String getFirstSpell(String chinese) {   
    	return getFirstSpell(chinese, "");  
    }  
    
    //*************************************************************************
    /** 
    * 【获取】获取汉字串拼音首字母，英文字符不变  
    * @param chinese 汉字串  
    * @param lowercase 1 表示大写，其它表示小写
    * @return  汉语拼音首字母  
    */
    //*************************************************************************
    public static String getFirstSpell(String chinese, String lowercase) {   
            StringBuffer pybf = new StringBuffer();   
            char[] arr = chinese.toCharArray();   
            HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();   
           if("1".equals(lowercase)){
        	   defaultFormat.setCaseType(HanyuPinyinCaseType.UPPERCASE);   
           } else {
        	   defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);   
           }
            defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);   
            for (int i = 0; i < arr.length; i++) {   
                if (arr[i] > 128) {   
                    try {   
                        String[] temp = PinyinHelper.toHanyuPinyinStringArray(arr[i], defaultFormat);   
                        if (temp != null) {   
                                pybf.append(temp[0].charAt(0));   
                        }   
                    } catch (BadHanyuPinyinOutputFormatCombination e) {   
                            e.printStackTrace();   
                    }   
                } else {   
                    pybf.append(arr[i]);
                }   
            }   
            return pybf.toString().replaceAll("\\W", "").trim();   
    }   
    
    //*************************************************************************
    /** 
    * 【获取】获取汉字串拼音首字母，英文字符不变  
    * @param chinese 汉字串  
    * @return  汉语拼音首字母  
    */
    //*************************************************************************
    public static String spell(String chinese) {  
        if (chinese == null) {  
            return null;  
        }  
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();  
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);// 小写   UPPERCASE  大写
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);// 不标声调  
        format.setVCharType(HanyuPinyinVCharType.WITH_V);// u:的声母替换为v  
        try {  
            StringBuilder sb = new StringBuilder();  
            for (int i = 0; i < chinese.length(); i++) {  
                String[] array = PinyinHelper.toHanyuPinyinStringArray(chinese .charAt(i), format);  
                if (array == null || array.length == 0) {  
                    continue;  
                }  
                String s = array[0];// 不管多音字,只取第一个  
                char c = s.charAt(0);// 大写第一个字母  
                String pinyin = String.valueOf(c).toUpperCase().concat(s.substring(1));  
                sb.append(pinyin);  
            }  
            return sb.toString();  
        } catch (BadHanyuPinyinOutputFormatCombination e) {  
            e.printStackTrace();  
        }  
        return null;  
    }  
    
    //*************************************************************************
    /** 
    * 【获取】获取汉字串拼音，英文字符不变  
    * @param chinese 汉字串  
    * @return  汉语拼音  
    */
    //*************************************************************************
    public static String getFullSpell(String chinese) {   
            StringBuffer pybf = new StringBuffer();   
            char[] arr = chinese.toCharArray();   
            HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();   
            defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);   
            defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);   
            for (int i = 0; i < arr.length; i++) {   
                if (arr[i] > 128) {   
                    try {   
                            pybf.append(PinyinHelper.toHanyuPinyinStringArray(arr[i], defaultFormat)[0]);   
                    } catch (BadHanyuPinyinOutputFormatCombination e) {   
                            e.printStackTrace();   
                    }   
                } else {   
                        pybf.append(arr[i]);   
                }   
            }   
            return pybf.toString();   
    }  
    
    public static void main(String[] args) {
    	System.out.println(getFirstSpell("执法流程"));
	}
}  