package com.zhcs.entity;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

//*****************************************************************************
/**
 * <p>Title:NewsListEntity</p>
 * <p>Description:消息列表</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司</p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年5月26日
 */
//*****************************************************************************
public class NewsListEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
	 * token
	 */
	@ApiModelProperty(value="token" ,required=true)
	private String token;
	
	/**
	 * btime
	 */
	@ApiModelProperty(value="最早的消息时间" ,required=false)
	private Date btime;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getBtime() {
		return btime;
	}

	public void setBtime(Date btime) {
		this.btime = btime;
	}
	
	

	
}
