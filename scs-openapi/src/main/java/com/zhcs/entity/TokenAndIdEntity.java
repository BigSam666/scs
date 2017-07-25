package com.zhcs.entity;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

//*****************************************************************************
/**
 * <p>Title:TokenAndIdEntity</p>
 * <p>Description:很多时候就只需要一个token和id</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司</p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年5月26日
 */
//*****************************************************************************
public class TokenAndIdEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
	 * token
	 */
	@ApiModelProperty(value="token" ,required=true)
	private String token;

	/**
	 * 页码
	 */
	@ApiModelProperty(value="id" ,required=true)
	private Long id;
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	
}
