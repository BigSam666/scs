package com.zhcs.entity;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

//*****************************************************************************
/**
 * <p>Title:SysUserEntity</p>
 * <p>Description:绑定用户使用</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司</p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public class BindUser implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
	 * 微信ID（OPENID）
	 */
	@ApiModelProperty(value="微信ID" ,required=true)
	private String openId;

	/**
	 * 微信ID（OPENID）
	 */
	@ApiModelProperty(value="token" ,required=true)
	private String token;

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
}
