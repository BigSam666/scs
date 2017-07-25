package com.zhcs.entity;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class BrknewsDetailEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value="openId" ,required=true)
	private String openId;
	
	@ApiModelProperty(value="事件id" ,required=true)
	private Long id;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
