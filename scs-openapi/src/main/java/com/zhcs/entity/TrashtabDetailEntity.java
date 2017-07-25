package com.zhcs.entity;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;


//*****************************************************************************
/**
 * <p>Title:TrashtabDetailEntity</p>
 * <p>Description: 垃圾桶标记详情</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public class TrashtabDetailEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
	 * token
	 */
	@ApiModelProperty(value="token" ,required=true)
	private String token;
	
	/**
	 * 垃圾桶标记id
	 */
	@ApiModelProperty(value="垃圾桶标记id" ,required=true)
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
