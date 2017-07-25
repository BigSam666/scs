package com.zhcs.entity;

import java.io.Serializable;
import java.util.Date;


//*****************************************************************************
/**
 * <p>Title:WeixintokenEntity</p>
 * <p>Description: 微信token</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public class WeixintokenEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键id
	private Long id;
	//appid
	private String appid;
	//secret
	private String secret;
	//access_token
	private String token;
	//最后更新时间
	private Long lutime;
	//创建人员
	private Long crtid;
	//创建时间
	private Date crttm;
	//修改人员
	private Long updid;
	//修改时间
	private Date updtm;
	

	public WeixintokenEntity() {
		super();
	}
	
	public WeixintokenEntity(String appid, String secret, String token,
			Long lutime) {
		super();
		this.appid = appid;
		this.secret = secret;
		this.token = token;
		this.lutime = lutime;
	}

	/**
	 * 设置：主键id
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：主键id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：appid
	 */
	public void setAppid(String appid) {
		this.appid = appid;
	}
	/**
	 * 获取：appid
	 */
	public String getAppid() {
		return appid;
	}
	/**
	 * 设置：secret
	 */
	public void setSecret(String secret) {
		this.secret = secret;
	}
	/**
	 * 获取：secret
	 */
	public String getSecret() {
		return secret;
	}
	/**
	 * 设置：access_token
	 */
	public void setToken(String token) {
		this.token = token;
	}
	/**
	 * 获取：access_token
	 */
	public String getToken() {
		return token;
	}
	/**
	 * 设置：最后更新时间
	 */
	public void setLutime(Long lutime) {
		this.lutime = lutime;
	}
	/**
	 * 获取：最后更新时间
	 */
	public Long getLutime() {
		return lutime;
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
