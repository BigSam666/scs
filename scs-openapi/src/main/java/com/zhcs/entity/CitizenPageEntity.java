package com.zhcs.entity;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
public class CitizenPageEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
	 * openApi
	 */
	@ApiModelProperty(value="openApi：唯一标识微信用户" ,required=true)
	private String openId;

	/**
	 * 字段标识
	 */
	@ApiModelProperty(value="字段标识：根据不同的业务逻辑有不同的值" ,required=true)
	private String flag;

	/**
	 * 关键字
	 */
	@ApiModelProperty(value="用于检索：前期可能用不到不用理会" ,required=true)
	private String keyword;


	/**
	 * 页码
	 */
	@ApiModelProperty(value="页码:当前取第几页" ,required=true)
	private Integer page;
	
	/**
	 * 每页的条数
	 */
	@ApiModelProperty(value="页面大小：每页的条数" ,required=true)
	private Integer pageSize;

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
}
