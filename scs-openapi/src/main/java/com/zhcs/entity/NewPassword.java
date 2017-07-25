package com.zhcs.entity;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

//*****************************************************************************
/**
 * <p>Title:NewPassword</p>
 * <p>Description:修改密码</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司</p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public class NewPassword implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
	 * 手机号
	 */
	@ApiModelProperty(value="手机号" ,required=true)
	private String mobile;
	
	/**
	 * 原密码
	 */
	@ApiModelProperty(value="原密码（Base64处理）" ,required=true)
	private String pwd;
	
	/**
	 * 新密码
	 */
	@ApiModelProperty(value="新密码（Base64处理）" ,required=true)
	private String newPwd;

	/**
	 * token
	 */
	@ApiModelProperty(value="token" ,required=true)
	private String token;
	

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getNewPwd() {
		return newPwd;
	}

	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	
	
}
