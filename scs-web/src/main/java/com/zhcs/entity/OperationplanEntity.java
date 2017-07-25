package com.zhcs.entity;

import java.io.Serializable;
import java.util.Date;


//*****************************************************************************
/**
 * <p>Title:OperationplanEntity</p>
 * <p>Description: 作业计划</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public class OperationplanEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//id
	private Long id;
	//日期
	private String optime;
	//所属部门
	private String dept;
	//业务板块
	private String bsment;
	//班次
	private String banci;
	//班级
	private String cla;
	//状态
	private String status;
	//创建人
	private Long crtid;
	//创建日期
	private Date crttm;
	//修改人
	private Long updid;
	//修改日期
	private Date updtm;

	/**
	 * 设置：id
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：日期
	 */
	public void setOptime(String optime) {
		this.optime = optime;
	}
	/**
	 * 获取：日期
	 */
	public String getOptime() {
		return optime;
	}
	/**
	 * 设置：所属部门
	 */
	public void setDept(String dept) {
		this.dept = dept;
	}
	/**
	 * 获取：所属部门
	 */
	public String getDept() {
		return dept;
	}
	/**
	 * 设置：业务板块
	 */
	public void setBsment(String bsment) {
		this.bsment = bsment;
	}
	/**
	 * 获取：业务板块
	 */
	public String getBsment() {
		return bsment;
	}
	
	public String getBanci() {
		return banci;
	}
	public void setBanci(String banci) {
		this.banci = banci;
	}
	/**
	 * 设置：班级
	 */
	public void setCla(String cla) {
		this.cla = cla;
	}
	/**
	 * 获取：班级
	 */
	public String getCla() {
		return cla;
	}
	/**
	 * 设置：状态
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * 获取：状态
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * 设置：创建人
	 */
	public void setCrtid(Long crtid) {
		this.crtid = crtid;
	}
	/**
	 * 获取：创建人
	 */
	public Long getCrtid() {
		return crtid;
	}
	/**
	 * 设置：创建日期
	 */
	public void setCrttm(Date crttm) {
		this.crttm = crttm;
	}
	/**
	 * 获取：创建日期
	 */
	public Date getCrttm() {
		return crttm;
	}
	/**
	 * 设置：修改人
	 */
	public void setUpdid(Long updid) {
		this.updid = updid;
	}
	/**
	 * 获取：修改人
	 */
	public Long getUpdid() {
		return updid;
	}
	/**
	 * 设置：修改日期
	 */
	public void setUpdtm(Date updtm) {
		this.updtm = updtm;
	}
	/**
	 * 获取：修改日期
	 */
	public Date getUpdtm() {
		return updtm;
	}
}
