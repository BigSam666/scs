package com.zhcs.entity;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

//*****************************************************************************
/**
 * <p>
 * Title:ClassesEntity
 * </p>
 * <p>
 * Description: 班次管理
 * </p>
 * <p>
 * Copyright: Copyright (c) 2017
 * </p>
 * <p>
 * Company: 深圳市智慧城市管家信息科技有限公司
 * </p>
 * 
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
// *****************************************************************************
public class ClassesEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	// id
	@ApiModelProperty(hidden=true)
	private Long id;
	/**
	 * token
	 */
	@ApiModelProperty(value="token" ,required=true)
	private String token;
	// 业务板块
	@ApiModelProperty(hidden=true)
	private Long business;
	// 班次名称
	@ApiModelProperty(value="班次名称" ,required=true)
	private String classes;
	// 开始时间
	@ApiModelProperty(value="开始时间" ,required=true)
	private String starttime;
	// 结束时间
	@ApiModelProperty(value="结束时间" ,required=true)
	private String emdtime;
	// 创建项目部
	@ApiModelProperty(value="项目部" ,required=true)
	private String pjdept;
	// 备注
	@ApiModelProperty(hidden=true)
	private String remark;
	// 创建人
	@ApiModelProperty(hidden=true)
	private Long crtid;
	// 创建时间
	@ApiModelProperty(hidden=true)
	private Date crttm;
	// 修改人
	@ApiModelProperty(hidden=true)
	private Long updid;
	// 修改时间
	@ApiModelProperty(hidden=true)
	private Date updtm;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getBusiness() {
		return business;
	}
	public void setBusiness(Long business) {
		this.business = business;
	}
	public String getClasses() {
		return classes;
	}
	public void setClasses(String classes) {
		this.classes = classes;
	}
	public String getStarttime() {
		return starttime;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	public String getEmdtime() {
		return emdtime;
	}
	public void setEmdtime(String emdtime) {
		this.emdtime = emdtime;
	}
	public String getPjdept() {
		return pjdept;
	}
	public void setPjdept(String pjdept) {
		this.pjdept = pjdept;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}


}
