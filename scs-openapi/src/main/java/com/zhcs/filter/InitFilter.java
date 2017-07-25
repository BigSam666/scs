package com.zhcs.filter;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.zhcs.context.PlatformContext;
import com.zhcs.context.StartFilter;
import com.zhcs.utils.LogUtil;

public class InitFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
	}
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		LogUtil.info("加载系统初始化参数...");
		Properties p = new Properties();
		try {
			p.load(StartFilter.class.getResourceAsStream("/weixin.properties"));
			@SuppressWarnings("rawtypes")
			Enumeration k = p.keys();
			while (k.hasMoreElements()) {
				String key = (String) k.nextElement();
				PlatformContext.putGoalbalContext(key, p.get(key));
			}
			LogUtil.info("CORE 初始化...完成");
		} catch (IOException e) {
			LogUtil.error("读取 weixin.properites 出错，读取微信appid和scret失败。", e);
			throw new ServletException(e);
		}
	}

	@Override
	public void destroy() {
		PlatformContext.clearGoalbalContext();
	}
}
