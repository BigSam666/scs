package com.zhcs.entity;

import java.io.Serializable;
import java.util.Date;


//*****************************************************************************
/**
 * <p>Title:WechatadminEntity</p>
 * <p>Description: 微信管理员关注列表</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public class WechatadminEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//id
	private Long id;
	//用户标识
	private String openid;
	//是否订阅(0表示没有关注)
	private String subscribe;
	//用户昵称
	private String nickname;
	//性别(0未知,1男,2,女)
	private String sex;
	//所在城市
	private String city;
	//所在国家
	private String country;
	//所在省份
	private String province;
	//语言
	private String language;
	//头像
	private String headimgurl;
	//关注时间
	private String subscribeTime;
	//创建人员
	private Long crtid;
	//创建时间
	private Date crttm;
	//修改人员
	private Long updid;
	//修改时间
	private Date updtm;

	/**
	 * 设置：id
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：用户标识
	 */
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	/**
	 * 获取：用户标识
	 */
	public String getOpenid() {
		return openid;
	}
	/**
	 * 设置：是否订阅(0表示没有关注)
	 */
	public void setSubscribe(String subscribe) {
		this.subscribe = subscribe;
	}
	/**
	 * 获取：是否订阅(0表示没有关注)
	 */
	public String getSubscribe() {
		return subscribe;
	}
	/**
	 * 设置：用户昵称
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	/**
	 * 获取：用户昵称
	 */
	public String getNickname() {
		return nickname;
	}
	/**
	 * 设置：性别(0未知,1男,2,女)
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}
	/**
	 * 获取：性别(0未知,1男,2,女)
	 */
	public String getSex() {
		return sex;
	}
	/**
	 * 设置：所在城市
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * 获取：所在城市
	 */
	public String getCity() {
		return city;
	}
	/**
	 * 设置：所在国家
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	/**
	 * 获取：所在国家
	 */
	public String getCountry() {
		return country;
	}
	/**
	 * 设置：所在省份
	 */
	public void setProvince(String province) {
		this.province = province;
	}
	/**
	 * 获取：所在省份
	 */
	public String getProvince() {
		return province;
	}
	/**
	 * 设置：语言
	 */
	public void setLanguage(String language) {
		this.language = language;
	}
	/**
	 * 获取：语言
	 */
	public String getLanguage() {
		return language;
	}
	/**
	 * 设置：头像
	 */
	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}
	/**
	 * 获取：头像
	 */
	public String getHeadimgurl() {
		return headimgurl;
	}
	/**
	 * 设置：关注时间
	 */
	public void setSubscribeTime(String subscribeTime) {
		this.subscribeTime = subscribeTime;
	}
	/**
	 * 获取：关注时间
	 */
	public String getSubscribeTime() {
		return subscribeTime;
	}
	/**
	 * 设置：创建人员
	 */
	public void setCrtid(Long crtid) {
		this.crtid = crtid;
	}
	/**
	 * 获取：创建人员
	 */
	public Long getCrtid() {
		return crtid;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCrttm(Date crttm) {
		this.crttm = crttm;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCrttm() {
		return crttm;
	}
	/**
	 * 设置：修改人员
	 */
	public void setUpdid(Long updid) {
		this.updid = updid;
	}
	/**
	 * 获取：修改人员
	 */
	public Long getUpdid() {
		return updid;
	}
	/**
	 * 设置：修改时间
	 */
	public void setUpdtm(Date updtm) {
		this.updtm = updtm;
	}
	/**
	 * 获取：修改时间
	 */
	public Date getUpdtm() {
		return updtm;
	}
}
