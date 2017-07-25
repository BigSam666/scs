package com.zhcs.entity;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;


//*****************************************************************************
/**
 * <p>Title:TrashtabEntity</p>
 * <p>Description: 垃圾桶标记</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public class TrashtabList implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
	 * token
	 */
	@ApiModelProperty(value="token" ,required=true)
	private String token;
	
	/**
	 * 页码
	 */
	@ApiModelProperty(value="页码" ,required=true)
	private Integer page;
	
	/**
	 * 每页的条数
	 */
	@ApiModelProperty(value="每页的条数" ,required=true)
	private Integer pagesize;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getPagesize() {
		return pagesize;
	}

	public void setPagesize(Integer pagesize) {
		this.pagesize = pagesize;
	}
	
	
}
