package com.zhcs.context;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.zhcs.utils.GetAccesstoken;
import com.zhcs.utils.LogUtil;


public class StartFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
	}
	
	@Override
	public void destroy() {

		// 清除全局上下文
		PlatformContext.clearGoalbalContext();
	}
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		LogUtil.info("CORE 初始化...");

		try {
			// 设置系统参数
			PlatformLauncher.initSettingProperties();
		} catch (RuntimeException e) {
			throw new ServletException(e.getMessage());
		}
		// 设置系统路径
		PlatformLauncher.initRootPath(filterConfig);
		if("1".equals(PlatformContext.getGoalbalContext("prod_weixin", String.class))){
			LogUtil.info("----正式环境");
			GetAccesstoken.getAccessToken();
		} else {
			LogUtil.info("----测试环境");
		}
		// 设置spring context
		PlatformLauncher.initSpringAppCxt(filterConfig);
		LogUtil.info("CORE 初始化...完成");
	}
}
