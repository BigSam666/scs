package com.zhcs.context;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.zhcs.utils.LogUtil;

//*****************************************************************************
/**
 * <p>Title:PlatformLauncher</p>
 * <p>Description:启动器，系统初始化，读取配置文件，进行系统的初始化。设置系统根路径。</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司</p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public abstract class PlatformLauncher {

	/**
	 * 加载系统参数，存储到全局上下文中。
	 * 
	 * <pre>
	 * 
	 * Description：
	 * 
	 *     从文件setting.properties中读取配置参数，存储到全局上下文中。
	 * 
	 * </pre>
	 * 
	 */
	@SuppressWarnings("rawtypes")
	public static void initSettingProperties() {

		LogUtil.info("加载系统初始化参数...");

		Properties p = new Properties();

		try {

			p.load(StartFilter.class.getResourceAsStream("/setting.properties"));

			Enumeration k = p.keys();
			while (k.hasMoreElements()) {
				String key = (String) k.nextElement();
				PlatformContext.putGoalbalContext(key, p.get(key));
			}

		} catch (IOException e) {

			LogUtil.error("读取 setting.properites 出错，系统初始化参数设置失败。", e);

		}

	}

	/**
	 * 设置系统根目录对应的路径。
	 * 
	 * <pre>
	 * 
	 * Description：
	 * 
	 *     设置系统根目录对应的路径：绝对路径、上下文路径。
	 *     
	 *     1、存储WebRoot绝对路径(以斜杠/结尾)，如：D:/test/demo/(以斜杠/结尾)。
	 *     2、存储WebRoot上下文路径(端口号后面部分的 /demo,不以斜杠/结尾)，如：http://localhost:8080/demo。
	 * 
	 * </pre>
	 * 
	 * @param filterConfig 过滤器对象
	 */
	public static void initRootPath(FilterConfig filterConfig) {

		ServletContext sc = filterConfig.getServletContext();
		String realPath = sc.getRealPath("/");
		realPath = realPath.replaceAll("\\\\", "/");
		if (realPath.charAt(realPath.length() - 1) != '/') {
			realPath += "/";
		}
		// 存储WebRoot绝对路径(以斜杠/结尾)，如：D:/test/demo/WebRoot/ (以斜杠/结尾)
		PlatformContext.putGoalbalContext(PlatformConst.ROOT_REAL_PATH, realPath);
		LogUtil.info("设置系统绝对路径：" + realPath);

		String cxtPath = sc.getContextPath();
		cxtPath = cxtPath.replaceAll("\\\\", "/");
		if (!cxtPath.startsWith("/")) {
			cxtPath = "/" + cxtPath;
		}
		if (cxtPath.endsWith("/")) {
			cxtPath = cxtPath.substring(0, (cxtPath.length() - 1));
		}
		// 存储WebRoot上下文路径(端口号后面部分的 /demo,不以斜杠/结尾)，如：http://localhost:8080/demo
		PlatformContext.putGoalbalContext(PlatformConst.ROOT_CXT_PATH, cxtPath);
		LogUtil.info("设置系统上下文路径：" + cxtPath);

	}

	/**
	 * 设置spring app cxt。
	 * 
	 * <pre>
	 * 
	 * Description：
	 * 
	 *     详细说明。
	 * 
	 * </pre>
	 * 
	 * @param filterConfig
	 */
	public static void initSpringAppCxt(FilterConfig filterConfig) {
		
		LogUtil.info("设置spring context...");
		ServletContext sc = filterConfig.getServletContext();
		PlatformContext.putGoalbalContext(ApplicationContext.class, WebApplicationContextUtils.getWebApplicationContext(sc));
		
	}
	
}
