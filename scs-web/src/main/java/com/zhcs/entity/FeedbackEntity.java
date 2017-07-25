package com.zhcs.entity;

import java.io.Serializable;
import java.util.Date;


//*****************************************************************************
/**
 * <p>Title:FeedbackEntity</p>
 * <p>Description: 意见反馈</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public class FeedbackEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键id
	private Long id;
	//信息来源
	private String infosource;
	//问题类型
	private String problemtype;
	//图片
	private String img;
	//反馈内容
	private String content;
	//谁反馈的
	private Long person;
	//创建人员
	private Long crtid;
	//创建时间
	private Date crttm;
	//修改人员
	private Long updid;
	//修改时间
	private Date updtm;

	/**
	 * 设置：主键id
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：主键id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：信息来源
	 */
	public void setInfosource(String infosource) {
		this.infosource = infosource;
	}
	/**
	 * 获取：信息来源
	 */
	public String getInfosource() {
		return infosource;
	}
	/**
	 * 设置：问题类型
	 */
	public void setProblemtype(String problemtype) {
		this.problemtype = problemtype;
	}
	/**
	 * 获取：问题类型
	 */
	public String getProblemtype() {
		return problemtype;
	}
	/**
	 * 设置：图片
	 */
	public void setImg(String img) {
		this.img = img;
	}
	/**
	 * 获取：图片
	 */
	public String getImg() {
		return img;
	}
	/**
	 * 设置：反馈内容
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 获取：反馈内容
	 */
	public String getContent() {
		return content;
	}
	/**
	 * 设置：谁反馈的
	 */
	public void setPerson(Long person) {
		this.person = person;
	}
	/**
	 * 获取：谁反馈的
	 */
	public Long getPerson() {
		return person;
	}
	/**
	 * 设置：创建人员
	 */
	public void setCrtid(Long crtid) {
		this.crtid = crtid;
	}
	/**
	 * 获取：创建人员
	 */
	public Long getCrtid() {
		return crtid;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCrttm(Date crttm) {
		this.crttm = crttm;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCrttm() {
		return crttm;
	}
	/**
	 * 设置：修改人员
	 */
	public void setUpdid(Long updid) {
		this.updid = updid;
	}
	/**
	 * 获取：修改人员
	 */
	public Long getUpdid() {
		return updid;
	}
	/**
	 * 设置：修改时间
	 */
	public void setUpdtm(Date updtm) {
		this.updtm = updtm;
	}
	/**
	 * 获取：修改时间
	 */
	public Date getUpdtm() {
		return updtm;
	}
}
