package com.zhcs.utils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.PropertyUtils;

import com.zhcs.context.PlatformConst;
import com.zhcs.context.PlatformContext;

//*****************************************************************************
/**
 * <p>
 * Title:BeanUtil
 * </p>
 * <p>
 * Description:提供bean的操作工具
 * </p>
 * <p>
 * Copyright: Copyright (c) 2017
 * </p>
 * <p>
 * Company: 深圳市智慧城市管家信息科技有限公司
 * </p>
 * 
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
// *****************************************************************************
public abstract class BeanUtil extends BeanUtils {

	/** 默认的查找父类时，类名称过滤字符串（类名称必须包含有此字符串） */
	public static String DEFAULT_FILTER = "com";

	static {
		DEFAULT_FILTER = PlatformContext.getGoalbalContext(
				PlatformConst.BEAN_FILTER, String.class);
	}

	public static Map<String, Object> transBean2Map(Object obj) {

		if (obj == null) {
			return null;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
			PropertyDescriptor[] propertyDescriptors = beanInfo
					.getPropertyDescriptors();
			for (PropertyDescriptor property : propertyDescriptors) {
				String key = property.getName();
				// 过滤class属性
				if (!key.equals("class")) {
					// 得到property对应的getter方法
					Method getter = property.getReadMethod();
					Object value = getter.invoke(obj);
					map.put(key, value);
				}
			}
		} catch (Exception e) {
			System.out.println("transBean2Map Error " + e);
		}
		return map;

	}

	/**
	 * 获取类的所有属性字段，包含父类的属性字段。
	 * 
	 * <pre>
	 * 
	 * Description：
	 * 
	 *     详细说明。
	 * 
	 * </pre>
	 * 
	 * @param cls
	 *            　类名
	 * @param filter
	 *            过滤字符串，在查找父类时，父类的类名称必须包含有指定的过滤字符串, filter 为 null，则不进行过滤
	 * @return List<Field> 属性集合或null
	 */
	@SuppressWarnings("rawtypes")
	public static List<Field> getDeclaredFields(Class cls, String filter) {

		Map<String, Field> map = new HashMap<String, Field>();
		Field[] fs = cls.getDeclaredFields();
		for (Field f : fs) {
			map.put(f.getName(), f);
		}

		while ((cls = cls.getSuperclass()) != null && cls != Object.class) {
			if (filter == null || cls.getName().indexOf(filter) != -1) {
				// 添加父类的属性字段
				fs = cls.getDeclaredFields();
				for (Field f : fs) {
					// 子类中己有的属性，不再添加父类的相同属性
					if (!map.containsKey(f.getName())) {
						map.put(f.getName(), f);
					}
				}
			}
		}

		List<Field> list = new ArrayList<Field>();
		list.addAll(map.values());
		return list;

	}

	/**
	 * 获取类的指定名称属性字段，包含父类的属性字段。
	 * 
	 * <pre>
	 * 
	 * Description：
	 * 
	 *     详细说明。
	 * 
	 * </pre>
	 * 
	 * @param cls
	 *            　类名
	 * @param name
	 *            属性名
	 * @return Field 找到的属性字段或null
	 */
	@SuppressWarnings("rawtypes")
	public static Field getDeclaredField(Class cls, String name) {

		return getDeclaredField(cls, name, BeanUtil.DEFAULT_FILTER);
	}

	/**
	 * 获取类的指定名称属性字段，包含父类的属性字段。
	 * 
	 * <pre>
	 * 
	 * Description：
	 * 
	 *     详细说明。
	 * 
	 * </pre>
	 * 
	 * @param cls
	 *            　类名
	 * @param name
	 *            属性名
	 * @param filter
	 *            过滤字符串，在查找父类时，父类的类名称必须包含有指定的过滤字符串, filter 为 null，则不进行过滤
	 * @return Field 找到的属性字段或null
	 */
	@SuppressWarnings("rawtypes")
	public static Field getDeclaredField(Class cls, String name, String filter) {

		Field field = null;
		try {

			field = cls.getDeclaredField(name);

		} catch (Exception e) {
			// 当前类查找不到属性字段，继续在父类中查找
			while ((cls = cls.getSuperclass()) != null && cls != Object.class) {
				if (filter == null || cls.getName().indexOf(filter) != -1) {
					try {
						field = cls.getDeclaredField(name);
					} catch (Exception e2) {
						// 父类中查找不到属性字段，继续在上一级父类查找
					}
					if (field != null) {
						return field;
					}
				} else {
					return null;
				}
			}
		}

		return field;

	}

	/**
	 * 设置对象的属性值(带默认过滤，并分折子属性)。
	 * 
	 * <pre>
	 * 
	 * Description：
	 * 
	 * class Test {
	 * 	private String address
	 * 	private PolicyVOId policyId;
	 * }
	 * 
	 * class PolicyVO {
	 * 	private String id;
	 * 	private String name;
	 * }
	 * 
	 * 则最终的属性集合为：
	 * {address, id, name}
	 * 而不是 {address, policyId}
	 * 此场景通常用于JavaBean中带复合主键的情形下
	 * 注意：为了提升性能，当divide为true时，必须提供 filter
	 * 
	 * </pre>
	 * 
	 * @param obj
	 *            要设置值的对象
	 * @param name
	 *            要设置值的属性名
	 * @param value
	 *            值
	 */
	public static boolean setFieldValue(Object obj, String name, Object value)
			throws Exception {

		return setFieldValue(obj, name, value, BeanUtil.DEFAULT_FILTER, true);
	}

	/**
	 * 设置对象的属性值。
	 * 
	 * <pre>
	 * 
	 * Description：
	 * 
	 * class Test {
	 * 	private String address
	 * 	private PolicyVOId policyId;
	 * }
	 * 
	 * class PolicyVO {
	 * 	private String id;
	 * 	private String name;
	 * }
	 * 则最终的属性集合为：
	 * {address, id, name}
	 * 而不是 {address, policyId}
	 * 此场景通常用于JavaBean中带复合主键的情形下
	 * 注意：为了提升性能，当divide为true时，必须提供 filter
	 * 
	 * </pre>
	 * 
	 * @param obj
	 *            要设置值的对象
	 * @param name
	 *            要设置值的属性名
	 * @param value
	 *            值
	 * @param filter
	 *            过滤字符串，在查找父类时，父类的类名称必须包含有指定的过滤字符串, filter 为 null，则不进行过滤
	 * @param divide
	 *            为true时， 对于属性类型是自定义对象时(类名称包含filter)，则进一步查找自定义对象中的属性
	 * 
	 */
	@SuppressWarnings("rawtypes")
	public static boolean setFieldValue(Object obj, String name, Object value,
			String filter, boolean divide) throws Exception {

		try {
			// 使用write方法设置值(setter)
			PropertyDescriptor pd = PropertyUtils.getPropertyDescriptor(obj,
					name);
			if (Date.class.isAssignableFrom(pd.getPropertyType())
					&& value.getClass() == String.class) {
				value = DateUtil.strToDate((String) value);
			} else {
				value = ConvertUtils.convert(value, pd.getPropertyType());
			}
			pd.getWriteMethod().invoke(obj, value);
			return true;
		} catch (Exception e) {
			// 没有对应的setter方法
			try {
				// 考虑属性是public的，直接set
				Field field = getDeclaredField(obj.getClass(), name, filter);
				Class ft = field.getType();
				if (Date.class.isAssignableFrom(ft)
						&& value.getClass() == String.class) {
					value = DateUtil.strToDate((String) value);
				} else {
					value = ConvertUtils.convert(value, ft);
				}
				field.set(obj, value);
				return true;
			} catch (Exception e1) {
				// 考虑嵌套属性
				if (divide && filter != null && !filter.equals("")) {
					List<Field> fs = BeanUtil.getDeclaredFields(obj.getClass(),
							filter);
					for (Field f : fs) {
						if (f.getType().getName().indexOf(filter) != -1) {
							try {
								Object o = BeanUtil.getFieldValue(obj,
										f.getName(), filter, divide);
								if (o == null) {
									o = f.getType().newInstance();
								}
								if (BeanUtil.setFieldValue(o, name, value,
										filter, divide)) {
									if (BeanUtil.setFieldValue(obj,
											f.getName(), o, filter, divide)) {
										return true;
									}
								}
							} catch (Exception e2) {
								LogUtil.error(e2.getMessage());
							}
						}
					}
				}
			}
		}
		LogUtil.warn(obj.getClass().getName() + "没有此属性：" + name);
		return false;
	}

	/**
	 * 获取对象的属性值(带默认过滤，并分折子属性)。
	 * 
	 * <pre>
	 * 
	 * Description：
	 * 
	 * class Test {
	 * 	private String address
	 * 	private PolicyVOId policyId;
	 * }
	 * 
	 * class PolicyVO {
	 * 	private String id;
	 * 	private String name;
	 * }
	 * 则最终的属性集合为：
	 * {address, id, name}
	 * 而不是 {address, policyId}
	 * 此场景通常用于JavaBean中带复合主键的情形下
	 * 注意：为了提升性能，当divide为true时，必须提供 filter
	 * 
	 * </pre>
	 * 
	 * @param obj
	 *            要获取值的对象
	 * @param name
	 *            要获取值的属性名
	 * 
	 */
	public static Object getFieldValue(Object obj, String name)
			throws Exception {

		return getFieldValue(obj, name, BeanUtil.DEFAULT_FILTER, true);
	}

	/**
	 * 获取对象的属性值。
	 * 
	 * <pre>
	 * 
	 * Description：
	 * 
	 * <pre>
	 * class Test {
	 * 	private String address
	 * 	private PolicyVOId policyId;
	 * }
	 * 
	 * class PolicyVO {
	 * 	private String id;
	 * 	private String name;
	 * }
	 * 则最终的属性集合为：
	 * {address, id, name}
	 * 而不是 {address, policyId}
	 * 此场景通常用于JavaBean中带复合主键的情形下
	 * 注意：为了提升性能，当divide为true时，必须提供 filter
	 * 
	 * </pre>
	 * 
	 * @param obj
	 *            要获取值的对象
	 * @param name
	 *            要获取值的属性名
	 * @param filter
	 *            过滤字符串，在查找父类时，父类的类名称必须包含有指定的过滤字符串, filter 为 null，则不进行过滤
	 * @param divide
	 *            为true时， 对于属性类型是自定义对象时(类名称包含filter)，则进一步查找自定义对象中的属性
	 */
	public static Object getFieldValue(Object obj, String name, String filter,
			boolean divide) throws Exception {

		StringBuffer error = new StringBuffer();
		try {
			// 使用read方法获取值(getter)
			PropertyDescriptor pd = PropertyUtils.getPropertyDescriptor(obj,
					name);
			return pd.getReadMethod().invoke(obj);
		} catch (Exception e) {
			error.append(e.getMessage() + "\r\n");
			// 没有对应的getter方法
			try {
				// 考虑属性是public的，直接get
				Field field = getDeclaredField(obj.getClass(), name, filter);
				return field.get(obj);
			} catch (Exception e1) {
				error.append(e1.getMessage() + "\r\n");
				// 考虑嵌套属性
				if (divide && filter != null && !filter.equals("")) {
					List<Field> fs = BeanUtil.getDeclaredFields(obj.getClass(),
							filter);
					for (Field f : fs) {
						if (f.getType().getName().indexOf(filter) != -1) {
							try {
								Object o = BeanUtil.getFieldValue(obj,
										f.getName(), filter, divide);
								return BeanUtil.getFieldValue(o, name, filter,
										divide);
							} catch (Exception e2) {
								error.append(e2.getMessage() + "\r\n");
							}
						}
					}
				}
			}
		}
		throw new Exception(error.toString());
	}

	/**
	 * 将JavaBean对象的属性值以字符串的格式保存在Map中。
	 * 
	 * <pre>
	 * 
	 * Description：
	 * 
	 *     详细说明。
	 * 
	 * </pre>
	 * 
	 * @param beanList
	 *            值对象
	 * @return List<Map>
	 */
	@SuppressWarnings("rawtypes")
	public static List<Map<String, String>> bean2MapList(List beanList) {

		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		for (Object bean : beanList) {
			list.add(bean2Map(bean));
		}
		return list;
	}

	/**
	 * 将JavaBean对象的属性值以字符串的格式保存在Map中。
	 * 
	 * <pre>
	 * 
	 * Description：
	 * 
	 *     详细说明。
	 * 
	 * </pre>
	 * 
	 * @param bean
	 *            值对象
	 * @return Map<String, String>
	 */
	public static Map<String, String> bean2Map(Object bean) {

		return BeanUtil.bean2Map(bean, BeanUtil.DEFAULT_FILTER, true);
	}

	/**
	 * 将JavaBean对象的属性值以字符串的格式保存在Map中。
	 * 
	 * <pre>
	 * 
	 * Description：
	 * 
	 * class Test {
	 * 	private String address
	 * 	private PolicyVOId policyId;
	 * }
	 * 
	 * class PolicyVO {
	 * 	private String id;
	 * 	private String name;
	 * }
	 * 则最终的属性集合为：
	 * {address, id, name}
	 * 而不是 {address, policyId}
	 * 此场景通常用于JavaBean中带复合主键的情形下
	 * 注意：为了提升性能，当divide为true时，必须提供 filter
	 * 
	 * </pre>
	 * 
	 * @param bean
	 *            值对象
	 * @param filter
	 *            过滤字符串，在查找父类时，父类的类名称必须包含有指定的过滤字符串, filter 为 null，则不进行过滤
	 * @param divide
	 *            为true时， 对于属性类型是自定义对象时(类名称包含filter)，则进一步查找自定义对象中的属性
	 * @return Map<String, String>
	 */
	public static Map<String, String> bean2Map(Object bean, String filter,
			boolean divide) {

		/*
		 * if (StringUtil.isValid(filter)) { if
		 * (bean.getClass().getName().indexOf(filter) == -1) { return null; } }
		 */
		Map<String, String> map = new HashMap<String, String>();
		List<Field> list = BeanUtil.getDeclaredFields(bean.getClass(), filter);
		for (Field f : list) {
			if (divide && StringUtil.isValid(filter)
					&& f.getType().getName().indexOf(filter) != -1) {
				Object obj = null;
				try {
					obj = BeanUtil.getFieldValue(bean, f.getName(), filter,
							divide);
				} catch (Exception e) {
					// 不能获取的属性(java机制没有访问权限)，则不put到map中
					continue;
				}
				if (obj != null) {
					map.putAll(BeanUtil.bean2Map(obj, filter, divide));
				}
			} else {
				Object value = null;
				try {
					value = BeanUtil.getFieldValue(bean, f.getName(), filter,
							divide);
					if (value != null) {
						if (Date.class.isAssignableFrom(value.getClass())) {
							value = DateUtil.dateToStr((Date) value);
						} else {
							value = ConvertUtils.convert(value, String.class);
						}
					}
					map.put(f.getName(),
							String.valueOf(value).equals("null") ? null
									: String.valueOf(value));
				} catch (Exception e) {
					// 不能获取的属性(java机制没有访问权限)，则不put到map中
					continue;
				}

			}
		}
		return map;
	}

	/**
	 * 将Map中的key/value转换成指定的JavaBean。
	 * 
	 * <pre>
	 * 
	 * Description：
	 * 
	 *     详细说明。
	 * 
	 * </pre>
	 * 
	 * @param mapList
	 *            转换源
	 * @param cls
	 *            要转换成的目标JavaBean
	 * @return List<T>
	 */
	@SuppressWarnings("rawtypes")
	public static <T> List<T> map2Bean(List<Map> mapList, Class<T> cls) {

		List<T> list = new ArrayList<T>();
		for (Map map : mapList) {
			list.add(map2Bean(map, cls));
		}
		return list;
	}

	/**
	 * 将Map中的key/value转换成指定的JavaBean。
	 * 
	 * <pre>
	 * 
	 * Description：
	 * 
	 *     详细说明。
	 * 
	 * </pre>
	 * 
	 * @param map
	 *            转换源
	 * @param cls
	 *            要转换成的目标JavaBean
	 * @return <T> 或 null
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static <T> T map2Bean(Map map, Class<T> cls) {

		Object bean = null;
		try {
			bean = cls.newInstance();
		} catch (InstantiationException e) {
			return null;
		} catch (IllegalAccessException e) {
			return null;
		}
		for (Object key : map.keySet()) {
			try {
				BeanUtil.setFieldValue(bean, (String) key, map.get(key),
						BeanUtil.DEFAULT_FILTER, true);
			} catch (Exception e) {
				// 不能转换的属性则忽略不计（java机制没有访问权限）
			}
		}
		return (T) bean;

	}

	/**
	 * 将Map中的key/value转换成指定的JavaBean。
	 * 
	 * <pre>
	 * 
	 * Description：
	 * 
	 * class Test {
	 * 	private String address
	 * 	private PolicyVOId policyId;
	 * }
	 * 
	 * class PolicyVO {
	 * 	private String id;
	 * 	private String name;
	 * }
	 * 则最终的属性集合为：
	 * {address, id, name}
	 * 而不是 {address, policyId}
	 * 此场景通常用于JavaBean中带复合主键的情形下
	 * 注意：为了提升性能，当divide为true时，必须提供 filter
	 * 
	 * </pre>
	 * 
	 * @param map
	 *            转换源
	 * @param cls
	 *            要转换成的目标JavaBean
	 * @param filter
	 *            过滤字符串，在查找父类时，父类的类名称必须包含有指定的过滤字符串, filter 为 null，则不进行过滤
	 * @param divide
	 *            为true时， 对于属性类型是自定义对象时(类名称包含filter)，则进一步查找自定义对象中的属性
	 * @return <T> 或 null
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static <T> T map2Bean(Map map, Class<T> cls, String filter,
			boolean divide) throws Exception {

		Object bean = cls.newInstance();
		for (Object key : map.keySet()) {
			BeanUtil.setFieldValue(bean, (String) key, map.get(key), filter,
					divide);
		}
		return (T) bean;

	}

	/**
	 * 获得Bean的所有属性名称。
	 * 
	 * <pre>
	 * 
	 * Description：
	 * 
	 *     详细说明。
	 * 
	 * </pre>
	 * 
	 * @param bean
	 *            Object : Bean实例
	 * @return String[] : 属性名称数组
	 * @throws Exception
	 */
	public static String[] getDeclaredFieldNames(Object bean) throws Exception {

		List<Field> list = getDeclaredFields(bean.getClass(),
				BeanUtil.DEFAULT_FILTER);
		String[] attrs = new String[list.size()];
		int i = 0;
		for (Field f : list) {
			attrs[i++] = f.getName();
		}
		return attrs;
	}

	/**
	 * 断言类之间的继承关系。
	 * 
	 * <pre>
	 * 
	 * Description：
	 * 
	 *     详细说明。
	 * 
	 * </pre>
	 * 
	 * @param base
	 * @param parent
	 * @return true 是子类
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static boolean isSubClass(Class base, Class parent) {

		return parent.isAssignableFrom(base);
	}

	/**
	 * 填充创建人、修改人、创建时间、修改时间。
	 * 
	 * <pre>
	 * 
	 * Description：
	 * 
	 *     创建/修改人设置为system，时间设置为系统时间。
	 * 
	 * </pre>
	 * 
	 * @param bean
	 *            需要填充的bean对象
	 * @return 说明
	 * @throws 异常类型
	 *             说明
	 */
	public static void fillCCUUD(Object bean) {

		// PlatformContext.getGoalbalContext(PlatformConst.SYSTEM_USERNAME,
		// "system", String.class);
		fillCCUUD(bean, 1L, 1L);
	}

	/**
	 * 填充修改人、修改时间。
	 * 
	 * <pre>
	 * 
	 * Description：
	 * 
	 *     时间设置为系统时间。
	 * 
	 * </pre>
	 * 
	 * @param bean
	 *            需要填充的bean对象
	 * @param CUpdCde
	 *            修改人
	 * @return 说明
	 * @throws 异常类型
	 *             说明
	 */
	public static void fillCCUUD(Object bean, Long updateCde) {
		LogUtil.debug("填充修改人，修改时间");
		Date d = DateUtil.getSystemDate();
		try {
			if (StringUtil.isBlank(updateCde)) {
				fillCCUUD(bean);
			} else {
				setFieldValue(bean, "updid", updateCde);
				setFieldValue(bean, "updtm", d);
			}
		} catch (Exception e) {
			LogUtil.warn(e);
		}
	}

	/**
	 * 填充创建人、修改人、修改时间。
	 * 
	 * <pre>
	 * 
	 * Description：
	 * 
	 *     时间设置为系统时间。
	 * 
	 * </pre>
	 * 
	 * @param bean
	 *            需要填充的bean对象
	 * @param CUpdCde
	 *            修改人
	 * @return 说明
	 * @throws 异常类型
	 *             说明
	 */
	public static void fillCCUUD(Object bean, Long createCde, Long updateCde) {
		LogUtil.debug("填充创建人，创建时间，修改人，修改时间");
		Date d = DateUtil.getSystemDate();
		try {
			setFieldValue(bean, "crtid", createCde);
			setFieldValue(bean, "updid", updateCde);
			setFieldValue(bean, "crttm", d);
			setFieldValue(bean, "updtm", d);
		} catch (Exception e) {
			LogUtil.warn(e);
		}
	}
	/**
	 * 获取指定位数的随机数
	 * @param strLength
	 * @return
	 */
	private static int getFixLenthString(int strLength) {  
	    Random rm = new Random();  
	    // 获得随机数  
	    double pross = (1 + rm.nextDouble()) * Math.pow(10, strLength);  
	    // 将获得的获得随机数转化为字符串  
	    String fixLenthString = String.valueOf(pross);  
	    // 返回固定的长度的随机数  
	    return Integer.parseInt(fixLenthString.substring(2, strLength + 2));  
	} 
	/**
	 * 填充坐席编码,坐席账号,坐席密码
	 * @param bean
	 */
	public static void randomNumber(Object bean) {
		LogUtil.debug("填充坐席编码,席账号,坐席密码");
		try {
			setFieldValue(bean, "seatsid", getFixLenthString(7));
			setFieldValue(bean, "seatsuser", getFixLenthString(7));
			setFieldValue(bean, "seatspassword", getFixLenthString(7));
		} catch (Exception e) {
			LogUtil.warn(e);
		}
	}
}
