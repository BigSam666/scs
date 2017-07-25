package com.zhcs.entity;

import java.io.Serializable;
import java.util.Date;


//*****************************************************************************
/**
 * <p>Title:AtdRecordEntity</p>
 * <p>Description: 考勤记录</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public class AtdRecordEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键id
	private Long id;
	//日期
	private Date date;
	//职员
	private Long person;
	//班次
	private Long shift;
	//上班时间
	private Date btime;
	//下班时间
	private Date etime;
	//状态
	private Integer status;
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
	 * 设置：日期
	 */
	public void setDate(Date date) {
		this.date = date;
	}
	/**
	 * 获取：日期
	 */
	public Date getDate() {
		return date;
	}
	/**
	 * 设置：职员
	 */
	public void setPerson(Long person) {
		this.person = person;
	}
	/**
	 * 获取：职员
	 */
	public Long getPerson() {
		return person;
	}
	/**
	 * 设置：班次
	 */
	public void setShift(Long shift) {
		this.shift = shift;
	}
	/**
	 * 获取：班次
	 */
	public Long getShift() {
		return shift;
	}
	/**
	 * 设置：上班时间
	 */
	public void setBtime(Date btime) {
		this.btime = btime;
	}
	/**
	 * 获取：上班时间
	 */
	public Date getBtime() {
		return btime;
	}
	/**
	 * 设置：下班时间
	 */
	public void setEtime(Date etime) {
		this.etime = etime;
	}
	/**
	 * 获取：下班时间
	 */
	public Date getEtime() {
		return etime;
	}
	/**
	 * 设置：状态
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：状态
	 */
	public Integer getStatus() {
		return status;
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
