package com.zhcs.entity;

public class MessageType {
	//text,image,voice,video,shortvideo,link,location,event,subscribe,unsubscribe,click,view,music,news,scancode_push,scancode_waitmsg,location_select
	//文本、图片、语言、视频、小视频、链接、地理位置、事件、关注、取消关注、菜单点击(click/view)、音乐、图文、扫码、扫码推事件且弹出“消息接收中”、弹出地理位置选择器
	public static final String TEXT="text";
	public static final String IMAGE="image";
	public static final String VOICE="voice";
	public static final String VIDEO="video";
	public static final String SHORTVIDEO="shortvideo";
	public static final String LINK="link";
	public static final String LOCATION="location";
	public static final String EVENT="event";
	public static final String SUBSCRIBE="subscribe";
	public static final String UNSUBSCRIBE="unsubscribe";
	public static final String CLICK="CLICK";
	public static final String VIEW="VIEW";
	public static final String MUSIC="music";
	public static final String NEWS="news";
	public static final String SCANCODE_PUSH="scancode_push";
	public static final String SCANCODE_WAITMSG="scancode_waitmsg";
	public static final String LOCATION_SELECT="location_select";//应该是上面的location、文档貌似有问题
	
}
