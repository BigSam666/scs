package com.zhcs.entity;

import java.io.Serializable;
import java.util.Date;

//*****************************************************************************
/**
 * <p>
 * Title:FleettEntity
 * </p>
 * <p>
 * Description: 车队管理
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
public class FleettEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	// 主键id
	private Long id;
	// 名称
	private String nm;
	// 所属部门
	private Long dept;
	// 所属部门名称
	private String deptName;
	// 队长
	private Long captain;
	// 队长姓名
	private String captainName;
	// 状态
	private String status;
	// 备注
	private String rmk;
	// 创建人员
	private Long crtid;
	// 创建时间
	private Date crttm;
	// 修改人员
	private Long updid;
	// 修改时间
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
	 * 设置：名称
	 */
	public void setNm(String nm) {
		this.nm = nm;
	}

	/**
	 * 获取：名称
	 */
	public String getNm() {
		return nm;
	}

	/**
	 * 设置：所属部门
	 */
	public void setDept(Long dept) {
		this.dept = dept;
	}

	/**
	 * 获取：所属部门
	 */
	public Long getDept() {
		return dept;
	}

	/**
	 * 设置：队长
	 */
	public void setCaptain(Long captain) {
		this.captain = captain;
	}

	/**
	 * 获取：队长
	 */
	public Long getCaptain() {
		return captain;
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
	 * 设置：备注
	 */
	public void setRmk(String rmk) {
		this.rmk = rmk;
	}

	/**
	 * 获取：备注
	 */
	public String getRmk() {
		return rmk;
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

	/**
	 * 获取：所属部门名称
	 */
	public String getDeptName() {
		return deptName;
	}

	/**
	 * 设置：修改所属部门名称
	 */
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	/**
	 * 获取：队长姓名
	 */
	public String getCaptainName() {
		return captainName;
	}

	/**
	 * 设置：队长姓名
	 */
	public void setCaptainName(String captainName) {
		this.captainName = captainName;
	}

}
