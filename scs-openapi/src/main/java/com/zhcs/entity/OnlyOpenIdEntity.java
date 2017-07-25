package com.zhcs.entity;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class OnlyOpenIdEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="openId" ,required=true)
	private String openId;

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}
}
