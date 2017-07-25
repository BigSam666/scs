package com.zhcs.entity;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

//*****************************************************************************
/**
 * <p>Title:FeedBackEntity</p>
 * <p>Description:反馈</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司</p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public class FeedBackEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * token
	 */
	@ApiModelProperty(value="token" ,required=true)
	private String token;
	
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
	
	@ApiModelProperty(required=false, hidden=true)
	private Long person;

	@ApiModelProperty(required=false, hidden=true)
	private String infosource;
	
	//创建人员
	@ApiModelProperty(required=false, hidden=true)
	private Long crtid;
	//创建时间
	@ApiModelProperty(required=false, hidden=true)
	private Date crttm;
	//修改人员
	@ApiModelProperty(required=false, hidden=true)
	private Long updid;
	//修改时间
	@ApiModelProperty(required=false, hidden=true)
	private Date updtm;
	

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
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

	public String getProblemtype() {
		return problemtype;
	}

	public void setProblemtype(String problemtype) {
		this.problemtype = problemtype;
	}

	public Long getPerson() {
		return person;
	}

	public void setPerson(Long person) {
		this.person = person;
	}

	public Long getCrtid() {
		return crtid;
	}

	public void setCrtid(Long crtid) {
		this.crtid = crtid;
	}

	public Date getCrttm() {
		return crttm;
	}

	public void setCrttm(Date crttm) {
		this.crttm = crttm;
	}

	public Long getUpdid() {
		return updid;
	}

	public void setUpdid(Long updid) {
		this.updid = updid;
	}

	public Date getUpdtm() {
		return updtm;
	}

	public void setUpdtm(Date updtm) {
		this.updtm = updtm;
	}

	public String getInfosource() {
		return infosource;
	}

	public void setInfosource(String infosource) {
		this.infosource = infosource;
	}
	
}
