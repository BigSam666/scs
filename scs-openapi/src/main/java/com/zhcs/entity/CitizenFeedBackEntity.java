package com.zhcs.entity;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

public class CitizenFeedBackEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * token
	 */
	@ApiModelProperty(value="openId" ,required=true)
	private String openId;
	
	/**
	 * 意见类型
	 */
	@ApiModelProperty(value="意见类型" ,required=true)
	private String problemtype;
	
	/**
	 * 反馈内容
	 */
	@ApiModelProperty(value="反馈内容" ,required=true)
	private String content;
	
	/**
	 * 图片
	 */
	@ApiModelProperty(value="图片（将图片的key以\",\"隔开）" ,required=true)
	private String img;

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getProblemtype() {
		return problemtype;
	}

	public void setProblemtype(String problemtype) {
		this.problemtype = problemtype;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}
}
