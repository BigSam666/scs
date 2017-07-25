package com.zhcs.entity;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

//*****************************************************************************
/**
 * <p>Title:SysUserEntity</p>
 * <p>Description:登录使用</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司</p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public class LoginEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
	 * 密码
	 */
	@ApiModelProperty(value="密码（Base64处理）" ,required=true)
	private String pwd;

	/**
	 * 手机号
	 */
	@ApiModelProperty(value="手机号" ,required=true)
	private String mobile;
	
	/**
	 * Code
	 */
	@ApiModelProperty(value="code" ,required=true)
	private String code;

	/**
	 * 设置：密码
	 * @param password 密码
	 */
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	/**
	 * 获取：密码
	 * @return String
	 */
	public String getPwd() {
		return pwd;
	}
	
	/**
	 * 设置：手机号
	 * @param mobile 手机号
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * 获取：手机号
	 * @return String
	 */
	public String getMobile() {
		return mobile;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	
	
	
}
