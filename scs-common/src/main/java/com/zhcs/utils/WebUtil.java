package com.zhcs.utils;

import java.net.FileNameMap;
import java.net.URLConnection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.springframework.util.StringUtils;

//*****************************************************************************
/**
 * <p>Title:WebUtil</p>
 * <p>Description:web相应实用工具类</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司</p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public class WebUtil {

	/**
	 * 
	 * 判断客户端浏览器是否支持gzip流， 兼容ie6/7 gzip压缩<br>
	 * 
	 * <pre>
	 * 方法acceptsGzipEncoding的详细说明 <br>
	 * 编写者：
	 * 创建时间：2016-1-27 上午10:19:00
	 * </pre>
	 * 
	 * @param request 请求对象
	 * @return boolean true支持gzip压缩
	 * @throws 异常类型 说明
	 */
	public boolean acceptsGzipEncoding(HttpServletRequest request) {

		boolean ie6 = headerContains(request, "User-Agent", "MSIE 6.0");
		boolean ie7 = headerContains(request, "User-Agent", "MSIE 7.0");
		return acceptsEncoding(request, "gzip") || acceptsEncoding(request, "x-gzip") || ie6 || ie7;
	}

	/**
	 * 
	 * Checks if request accepts the named encoding。 <br>
	 * 
	 * <pre>
	 * 方法acceptsEncoding的详细说明 <br>
	 * 编写者：
	 * 创建时间：2016-1-27 上午10:19:14
	 * </pre>
	 * 
	 * @param request 请求对象
	 * @param name 编码类型名
	 * @return true 支持指定的编码
	 * @throws 异常类型 说明
	 */
	private boolean acceptsEncoding(final HttpServletRequest request, final String name) {

		final boolean accepts = headerContains(request, "Accept-Encoding", name);
		return accepts;
	}

	/**
	 * 
	 * Checks if request contains the header value。 <br>
	 * 
	 * <pre>
	 * 方法headerContains的详细说明 <br>
	 * 编写者：
	 * 创建时间：2016-1-27 上午10:19:45
	 * </pre>
	 * 
	 * @param request 请求对象
	 * @param header 请求头名称
	 * @param value 请求头值
	 * @return boolean true指定的请求头包含相应的值
	 * @throws 异常类型 说明
	 */
	@SuppressWarnings("rawtypes")
	private boolean headerContains(final HttpServletRequest request, final String header, final String value) {

		final Enumeration accepted = request.getHeaders(header);
		while (accepted.hasMoreElements()) {
			final String headerValue = (String) accepted.nextElement();
			if (headerValue.indexOf(value) != -1) {
				return true;
			}
		}
		return false;

	}

	/**
	 * 
	 * print the request headers <br>
	 * 
	 * <pre>
	 * 方法printRequestHeaders的详细说明 <br>
	 * 编写者：
	 * 创建时间：2016-1-27 上午10:23:14
	 * </pre>
	 * 
	 * @param request 请求对象
	 * @return String 说明
	 * @throws 异常类型 说明
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String printRequestHeaders(final HttpServletRequest request) {

		Map headers = new HashMap();
		Enumeration enumeration = request.getHeaderNames();
		StringBuffer headerLine = new StringBuffer();
		headerLine.append("Request Headers");
		while (enumeration.hasMoreElements()) {
			String name = (String) enumeration.nextElement();
			String headerValue = request.getHeader(name);
			headers.put(name, headerValue);
			headerLine.append(": ").append(name).append(" -> ").append(headerValue);
		}
		return headerLine.toString();
	}

	/**
	 * 
	 * 将请求拆分成两个字符串内容。 url[0]：原始的请求路径,不带参数。 url[1]：原始的请求路径+query查询字符串。<br>
	 * 
	 * <pre>
	 * 方法generateAllPattenURL的详细说明 <br>
	 * 编写者：
	 * 创建时间：2016-1-27 上午10:26:26
	 * </pre>
	 * 
	 * @param request
	 * @return String[] url
	 * @throws 异常类型 说明
	 */
	public static String[] generateAllPattenURL(ServletRequest request) {

		HttpServletRequest req = (HttpServletRequest) request;
		String[] url = new String[2];

		StringBuffer reqPath = new StringBuffer();
		reqPath.append("");

		String servletPath = req.getServletPath();
		if (servletPath != null && servletPath.trim().length() > 0) {
			reqPath.append(servletPath);
		}
		String pathInfo = req.getPathInfo();
		if (pathInfo != null && pathInfo.trim().length() > 0) {
			reqPath.append(pathInfo);
		}
		url[0] = reqPath.toString();

		String queryString = req.getQueryString();
		if (queryString != null && queryString.trim().length() > 0) {
			reqPath.append("?" + queryString);
		}
		url[1] = reqPath.toString();

		return url;

	}

	/*
	 * Copyright 2004, 2005, 2006 Acegi Technology Pty Limited
	 * 
	 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain
	 * a copy of the License at
	 * 
	 * http://www.apache.org/licenses/LICENSE-2.0
	 * 
	 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
	 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and
	 * limitations under the License.
	 */

	/**
	 * Provides static methods for composing URLs.
	 * <p>
	 * Placed into a separate class for visibility, so that changes to URL formatting conventions will affect all users.
	 * </p>
	 * 
	 * @author Ben Alex
	 * @version $Id: UrlUtils.java 3070 2008-05-15 17:58:15Z luke_t $
	 */
	public static class UrlUtils {

		// ~ Methods
		// ========================================================================================================

		/**
		 * Obtains the full URL the client used to make the request.
		 * <p>
		 * Note that the server port will not be shown if it is the default server port for HTTP or HTTPS (ie 80 and 443
		 * respectively).
		 * </p>
		 * 
		 * @return the full URL
		 */
		private String buildFullRequestUrl(String scheme, String serverName, int serverPort, String contextPath,
				String requestUrl, String servletPath, String requestURI, String pathInfo, String queryString) {

			boolean includePort = true;

			if ("http".equals(scheme.toLowerCase()) && (serverPort == 80)) {
				includePort = false;
			}

			if ("https".equals(scheme.toLowerCase()) && (serverPort == 443)) {
				includePort = false;
			}

			return scheme + "://" + serverName + ((includePort) ? (":" + serverPort) : "") + contextPath
					+ buildRequestUrl(servletPath, requestURI, contextPath, pathInfo, queryString);
		}

		/**
		 * Obtains the web application-specific fragment of the URL.
		 * 
		 * @return the URL, excluding any server name, context path or servlet path
		 */
		private String buildRequestUrl(String servletPath, String requestURI, String contextPath, String pathInfo,
				String queryString) {

			String uri = servletPath;

			if (uri == null) {
				uri = requestURI;
				uri = uri.substring(contextPath.length());
			}

			return uri + ((pathInfo == null) ? "" : pathInfo) + ((queryString == null) ? "" : ("?" + queryString));
		}

		public String getFullRequestUrl(HttpServletRequest r) {

			return buildFullRequestUrl(r.getScheme(), r.getServerName(), r.getServerPort(), r.getContextPath(), r
					.getRequestURL().toString(), r.getServletPath(), r.getRequestURI(), r.getPathInfo(),
					r.getQueryString());
		}

		public String getRequestUrl(HttpServletRequest r) {

			return buildRequestUrl(r.getServletPath(), r.getRequestURI(), r.getContextPath(), r.getPathInfo(),
					r.getQueryString());
		}

		public boolean isValidRedirectUrl(String url) {

			return !StringUtils.hasText(url) || url.startsWith("/") || url.toLowerCase().startsWith("http");
		}
	}

	// **************************************************************************
	/**
	 * 获取客户端真实IP地址的方法 <br>
	 * 
	 * @param request HttpServletRequest
	 * @return String 说明
	 */
	// **************************************************************************
	public static String getClientIP(ServletRequest req) {
		HttpServletRequest request = (HttpServletRequest)req;
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		if("0:0:0:0:0:0:0:1".equals(ip)){
			ip = "本地";
		}
		return ip;
	}

	/**
	 * 获取文件mimeType。
	 * 
	 * <pre>
	 * 
	 * Description：
	 * 
	 *     详细说明。
	 * 
	 * </pre>
	 * 
	 * @param 参数名 说明
	 * @return 说明
	 * @throws 异常类型 说明
	 */
	public static String getMimeType(String fileName) throws java.io.IOException {

		try {
			FileNameMap fileNameMap = URLConnection.getFileNameMap();
			String type = fileNameMap.getContentTypeFor(fileName);
			return type == null ? "" : type;
		} catch (Exception e) {
			return "";
		}
	}
}
