package com.zhcs.util;

import com.zhcs.context.PlatformContext;
import com.zhcs.utils.ActionException;
import com.zhcs.utils.LogUtil;
import com.zhcs.utils.R;
import com.zhcs.utils.ServiceException;
import net.sf.json.JSONObject;

import java.io.IOException;

public class WeChatUtil {
    public static String getOpenId(String appidadmin, String appscretadmin, String code) throws ActionException {
        String getOpenidURL = PlatformContext.getGoalbalContext("GETOPENIDURL", String.class);
        // 根据code获取openId
        String targetUrl = getOpenidURL.replace("APPID", appidadmin);
        targetUrl = targetUrl.replace("SECRET", appscretadmin);
        targetUrl = targetUrl.replace("CODE", code);

        JSONObject jsonObject = null;
        try {
            jsonObject = HttpUtil.doGetStr(targetUrl);
        } catch (Exception e) {
            LogUtil.error(jsonObject == null ? "根据code获取openId时微信服务器没有响应" : "根据code获取openId时微信服务器返回数据：" + jsonObject.toString());
            throw new ActionException("根据code获取openId时微信服务器没有响应");
            //51008
        }

        String openId = String.valueOf(jsonObject.get("openid"));   // 拿到openId
        LogUtil.error("根据code获取openId时的请求链接为：" + targetUrl);
        LogUtil.info("拿到微信返回openId为：{}", openId);
        if (openId == null || openId.length() < 1 || "null".equals(openId)) {
            LogUtil.error("根据code获取openId时微信服务器返回的数据：" + jsonObject.toString());
            throw new ActionException("无效的code");
            //51005
        } else {
            return openId;
        }
    }

}
