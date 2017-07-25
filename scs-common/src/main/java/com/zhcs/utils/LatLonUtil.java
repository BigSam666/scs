package com.zhcs.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LatLonUtil {

	private static final double PI = 3.14159265; // 圆周率
	private static final double EARTH_RADIUS = 6378137; // 地球半径
	private static final double RAD = Math.PI / 180.0; // 一百八十度角
	

	/**
	 * 计算地球上任意两点(经纬度)距离
	 *
	 * @param lon1
	 *            第一点经度
	 * @param lat1
	 *            第一点纬度
	 * @param lon2
	 *            第二点经度
	 * @param lat2
	 *            第二点纬度
	 * @return 返回距离单位：千米
	 */
	public static double getDistatce(double lon1, double lat1, double lon2, double lat2) {
		double R = 6371;
		double distance = 0.0;
		double dLat = (lat2 - lat1) * RAD;
		double dLon = (lon2 - lon1) * RAD;
		double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
				+ Math.cos(lat1 * RAD)
				* Math.cos(lat2 * RAD) * Math.sin(dLon / 2)
				* Math.sin(dLon / 2);
		distance = (2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a))) * R;
		return distance;
	}

	/**
	 * 计算地球上任意两点(经纬度)距离
	 *
	 * @param long1
	 *            第一点经度
	 * @param lat1
	 *            第一点纬度
	 * @param long2
	 *            第二点经度
	 * @param lat2
	 *            第二点纬度
	 * @return 返回距离单位：米
	 */
	public static double Distance(double long1, double lat1, double long2, double lat2) {
		double a, b, R;
		//R = 6378137; // 地球半径
		R = EARTH_RADIUS;
		lat1 = lat1 * RAD;
		lat2 = lat2 * RAD;
		a = lat1 - lat2;
		b = (long1 - long2) * RAD;
		double d;
		double sa2, sb2;
		sa2 = Math.sin(a / 2.0);
		sb2 = Math.sin(b / 2.0);
		d = 2 * R * Math.asin(Math.sqrt(sa2 * sa2 + Math.cos(lat1) * Math.cos(lat2) * sb2 * sb2));
		return d;
	}

	/**
	 * @param raidus
	 * 单位米 return minLat 
	 * 最小经度 minLng 
	 * 最小纬度 maxLat 
	 * 最大经度 maxLng 
	 * 最大纬度 minLat
	 */
	public static Map<String, Object> getAround(double lat, double lon, int raidus) {
		
		Map<String, Object> map = new HashMap<String, Object>();

		Double latitude = lat;// 传值给经度
		Double longitude = lon;// 传值给纬度

		Double degree = (24901 * 1609) / 360.0; // 获取每度
		double raidusMile = raidus;

		Double dpmLat = 1 / degree;
		Double radiusLat = dpmLat * raidusMile;
		// 获取最小纬度
		Double minLat = latitude - radiusLat;
		// 获取最大纬度
		Double maxLat = latitude + radiusLat;

		Double mpdLng = degree * Math.cos(latitude * (PI / 180));
		Double dpmLng = 1 / mpdLng;
		Double radiusLng = dpmLng * raidusMile;
		//获取最小经度
		Double minLng = longitude - radiusLng;
		// 获取最大经度
		Double maxLng = longitude + radiusLng;
		
		/*System.out.println("最小纬度：" + minLat);
		System.out.println("最小经度：" + minLng);
		System.out.println("最大纬度：" + maxLat);
		System.out.println("最大经度：" + maxLng);*/

		map.put("minLat", minLat);
		map.put("maxLat", maxLat);
		map.put("minLng", minLng);
		map.put("maxLng", maxLng);
		
		return map;
	}
	
	//测试方法
	public static void main(String [] src){
		//getAround(36.68027, 117.12744, 10000);
		//System.out.println(getDistatce(117.12744, 36.68027, 117.0154019924088, 36.590417602779226));
		List<String> a = new ArrayList<String>();
		a.add("1");
		a.add("3");
		a.add("2");
		a.add("4");
		System.out.println(a);
		System.out.println(StringUtil.ListReverse(a));
		System.out.println(Math.sqrt(1));
	}

}