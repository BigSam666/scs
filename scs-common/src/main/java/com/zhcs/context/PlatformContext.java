package com.zhcs.context;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

import org.springframework.context.ApplicationContext;


//*****************************************************************************
/**
 * <p>Title:PlatformContext</p>
 * <p>Description:上下文</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司</p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public class PlatformContext {

	/** 全局上下文 */
	private static ConcurrentHashMap<Object, Object> goalbalContext = new ConcurrentHashMap<Object, Object>();

	/** 线程上下文 */
	private static ThreadLocal<HashMap<Object, Object>> requestContext = new ThreadLocal<HashMap<Object, Object>>();

	//*************************************************************************
	/** 
	* 【保存】保存数据到全局上下文。
	* @param k
	* @param v  
	*/
	//*************************************************************************
	public static void putGoalbalContext(Object k, Object v) {

		goalbalContext.put(k, v);

	}

	//*************************************************************************
	/** 
	* 【获取】在全局上下文获取指定键名对应的键值
	* @param k
	* @return  
	*/
	//*************************************************************************
	public static Object getGoalbalContext(Object k) {

		return goalbalContext.get(k);

	}

	//*************************************************************************
	/** 
	* 【获取】在全局上下文获取指定键名对应的键值。返回的键值具有指定的数据类型。注意，需要与put时的键值类型对应，此方法并不会对键值作强制转换。
	* @param k
	* @param cls
	* @return  
	*/
	//*************************************************************************
	@SuppressWarnings("unchecked")
	public static <T> T getGoalbalContext(Object k, Class<T> cls) {

		return (T) getGoalbalContext(k);
	}

	//*************************************************************************
	/** 
	* 【获取】在全局上下文获取指定键名对应的键值。如果没有相应的键名存在，则返回给定的默认值
	* @param k
	* @param defaultValue
	* @param cls
	* @return  
	*/
	//*************************************************************************
	@SuppressWarnings("unchecked")
	public static <T> T getGoalbalContext(Object k, Object defaultValue,
			Class<T> cls) {

		Object v = getGoalbalContext(k);
		if (v == null) {
			v = defaultValue;
		}
		return (T) v;
	}

	//*************************************************************************
	/** 
	* 【删除】 删除全局上下文中指定的键名
	* @param k  
	*/
	//*************************************************************************
	public static void removeGoalbalContext(Object k) {

		goalbalContext.remove(k);

	}

	//*************************************************************************
	/** 
	* 【删除】清空全局上下文对象。
	*/
	//*************************************************************************
	public static void clearGoalbalContext() {

		goalbalContext.clear();

	}

	//*************************************************************************
	/** 
	* 【保存】保存数据到线程上下文
	* @param k
	* @param v  
	*/
	//*************************************************************************
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void putRequestContext(Object k, Object v) {

		HashMap context = requestContext.get();
		if (context == null) {
			context = new HashMap();
			requestContext.set(context);
		}
		context.put(k, v);

	}

	//*************************************************************************
	/** 
	* 【获取】在线程上下文获取指定键名对应的键值。
	* @param k
	* @return  
	*/
	//*************************************************************************
	@SuppressWarnings("rawtypes")
	public static Object getRequestContext(Object k) {

		HashMap context = requestContext.get();
		if (context == null) {
			return null;
		} else {
			return context.get(k);
		}

	}

	//*************************************************************************
	/** 
	* 【获取】在线程上下文获取指定键名对应的键值。 返回的键值具有指定的数据类型。注意，需要与put时的键值类型对应，此方法并不会对键值作强制转换。
	* @param k
	* @param cls
	* @return  
	*/
	//*************************************************************************
	@SuppressWarnings("unchecked")
	public static <T> T getRequestContext(Object k, Class<T> cls) {

		return (T) getRequestContext(k);
	}

	//*************************************************************************
	/** 
	* 【获取】在线程上下文获取指定键名对应的键值， 如果没有相应的键名存在，则返回给定的默认值。
	* @param k
	* @param defaultValue
	* @param cls
	* @return  
	*/
	//*************************************************************************
	@SuppressWarnings("unchecked")
	public static <T> T getRequestContext(Object k, Object defaultValue,
			Class<T> cls) {

		Object v = getRequestContext(k);
		if (v == null) {
			v = defaultValue;
		}
		return (T) v;
	}

	/**
	 * 删除线程上下文中指定的键名。
	 * 
	 * <pre>
	 * 
	 * Description：
	 * 
	 *     详细说明。
	 * 
	 * </pre>
	 * 
	 * @param k
	 *            键名
	 */
	@SuppressWarnings("rawtypes")
	public static void removeRequestContext(Object k) {

		HashMap context = requestContext.get();
		if (context != null) {
			context.remove(k);
		}

	}

	/**
	 * 清空线程上下文对象。
	 * 
	 * <pre>
	 * 
	 * Description：
	 * 
	 *     删除线程上下文中的所有键名。
	 * 
	 * </pre>
	 * 
	 */
	public static void clearRequestContext() {

		requestContext.remove();

	}

	/**
	 * 获取请求封装对象。
	 * 
	 * <pre>
	 * 
	 * Description：
	 * 
	 *     详细说明。
	 * 
	 * </pre>
	 * 
	 * @return 返回RequestWrapper或null
	 */
	public static RequestWrapper getRequestWrapper() {

		return PlatformContext.getRequestContext(RequestWrapper.class,
				RequestWrapper.class);
	}

	/**
	 * 获取响应封装对象。
	 * 
	 * <pre>
	 * 
	 * Description：
	 * 
	 *     详细说明。
	 * 
	 * </pre>
	 * 
	 * @return 返回ResponseWrapper或null
	 */
	public static ResponseWrapper getResponseWrapper() {

		return PlatformContext.getRequestContext(ResponseWrapper.class,
				ResponseWrapper.class);
	}

	/**
	 * 获取系统根目录对应的绝对路径。
	 * 
	 * <pre>
	 * 
	 * Description：
	 * 
	 *     WebRoot绝对路径(以斜杠/结尾)，如：D:/test/demo/WebRoot/ (以斜杠/结尾)。
	 * 
	 * </pre>
	 * 
	 * @return 返回根目录对应的绝对路径或null
	 */
	public static String getRootRealPath() {

		return PlatformContext.getGoalbalContext(PlatformConst.ROOT_REAL_PATH,
				String.class);
	}

	/**
	 * 获取系统根目录对应的上下文路径。
	 * 
	 * <pre>
	 * 
	 * Description：
	 * 
	 *     WebRoot上下文路径(端口号后面部分的 /demo,不以斜杠/结尾)，如：http://localhost:8080/demo。
	 * 
	 * </pre>
	 * 
	 * @return 返回根目录对应的上下文路径或null
	 */
	public static String getRootCxtPath() {

		return PlatformContext.getGoalbalContext(PlatformConst.ROOT_CXT_PATH,
				String.class);
	}

	/**
	 * 获取 spring 上下文对象。
	 * 
	 * <pre>
	 * 
	 * Description：
	 * 
	 *     详细说明。
	 * 
	 * </pre>
	 * 
	 * @return 返回spring上下文对象或null
	 */
	public static ApplicationContext getSpringAppCxt() {

		return getGoalbalContext(ApplicationContext.class,
				ApplicationContext.class);
	}

	/**
	 * 获取spring 上下文中管理的bean对象。
	 * 
	 * <pre>
	 * 
	 * Description：
	 * 
	 *     详细说明。
	 * 
	 * </pre>
	 * 
	 * @param name
	 *            bean定义名称
	 * @return 返回指定的bean或null
	 */
	public static Object getBean(String name) {

		return PlatformContext.getSpringAppCxt().getBean(name);
	}

	/**
	 * 获取spring 上下文中管理的bean对象。
	 * 
	 * <pre>
	 * 
	 * Description：
	 * 
	 *     详细说明。
	 * 
	 * </pre>
	 * 
	 * @param name
	 *            bean定义名称
	 * @param cls
	 *            指定bena类型
	 * @return 返回指定的bean或null
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String name, Class<T> cls) {

		return (T) PlatformContext.getSpringAppCxt().getBean(name);
	}

	/**
	 * 获取app_key。
	 * 
	 * <pre>
	 * 
	 * Description：
	 * 
	 *     详细说明。
	 * 
	 * </pre>
	 * 
	 * @return 返回app_key
	 */
	public static String getAppKey() {

		return PlatformContext.getGoalbalContext("app_key", String.class);

	}

	/**
	 * 获取app_secret。
	 * 
	 * <pre>
	 * 
	 * Description：
	 * 
	 *     详细说明。
	 * 
	 * </pre>
	 * 
	 * @return 返回app_secret
	 */
	public static String getAppSecret() {

		return PlatformContext.getGoalbalContext("app_secret", String.class);

	}
}
