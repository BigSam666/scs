package com.zhcs.entity;

import java.io.Serializable;
import java.util.Date;

//*****************************************************************************
/**
 * <p>
 * Title:OperationplandataEntity
 * </p>
 * <p>
 * Description: 作业计划数据
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
public class OperationplandataEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	// id
	private Long id;
	// oid
	private Long oid;
	// 日期
	private Date optime;
	// 班次/时段
	private Long shift;
	// 班级
	private Long cla;
	// 成员
	private Long member;
	// 司机
	private String driver;
	// 车辆
	private String car;
	// 状态
	private String status;
	// 创建人
	private Long crtid;
	// 创建日期
	private Date crttm;
	// 修改人
	private Long updid;
	// 修改日期
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
	 * 设置：oid
	 */
	public void setOid(Long oid) {
		this.oid = oid;
	}

	/**
	 * 获取：oid
	 */
	public Long getOid() {
		return oid;
	}

	public Date getOptime() {
		return optime;
	}

	public void setOptime(Date optime) {
		this.optime = optime;
	}

	public Long getShift() {
		return shift;
	}

	public void setShift(Long shift) {
		this.shift = shift;
	}

	public Long getCla() {
		return cla;
	}

	public void setCla(Long cla) {
		this.cla = cla;
	}

	public Long getMember() {
		return member;
	}

	public void setMember(Long member) {
		this.member = member;
	}

	/**
	 * 设置：司机
	 */
	public void setDriver(String driver) {
		this.driver = driver;
	}

	/**
	 * 获取：司机
	 */
	public String getDriver() {
		return driver;
	}

	/**
	 * 设置：车辆
	 */
	public void setCar(String car) {
		this.car = car;
	}

	/**
	 * 获取：车辆
	 */
	public String getCar() {
		return car;
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
