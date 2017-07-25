package com.zhcs.xss;

import org.apache.commons.lang.StringUtils;

import com.zhcs.utils.LogUtil;
import com.zhcs.utils.StringUtil;
import com.zhcs.utils.ZHCSException;

//*****************************************************************************
/**
 * <p>Title:SQLFilter</p>
 * <p>Description:SQL过滤</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司</p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年6月2日
 */
//*****************************************************************************
public class SQLFilter {

    /**
     * SQL注入过滤
     * @param str  待验证的字符串
     */
    public static String sqlInject(String str){
        if(StringUtil.isBlank(str)){
            return null;
        }
        //去掉'|"|;|\字符
        str = StringUtils.replace(str, "'", "");
        str = StringUtils.replace(str, "\"", "");
        str = StringUtils.replace(str, ";", "");
        str = StringUtils.replace(str, "\\", "");

        //转换成小写
        str = str.toLowerCase();

        //非法字符
        //String[] keywords = {"master", "truncate", "insert", "select", "delete", "update", "declare", "alert", "create", "drop"};
        String[] keywords = {"master", "truncate", "insert", "select", "delete", "update", "declare", "alert", "create", "drop"};
        //判断是否包含非法字符
        for(String keyword : keywords){
            if(str.equals(keyword)){
            	LogUtil.error("当前非法字符为：{}", str);
                throw new ZHCSException("包含非法字符");
            }
        }

        return str;
    }
}
