package com.zhcs.entity;

import java.io.Serializable;
import java.util.Date;


//*****************************************************************************
/**
 * <p>Title:AuditscheduleEntity</p>
 * <p>Description: 审核进度</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public class AuditscheduleEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键id
	private Long id;
	//预算申报
	private Long budget;
	//审核人
	private Long person;
	//补充说明
	private String rmk;
	//当前进度
	private Integer schedule;
	//是否通过
	private Integer pass;
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
	 * 设置：预算申报
	 */
	public void setBudget(Long budget) {
		this.budget = budget;
	}
	/**
	 * 获取：预算申报
	 */
	public Long getBudget() {
		return budget;
	}
	/**
	 * 设置：审核人
	 */
	public void setPerson(Long person) {
		this.person = person;
	}
	/**
	 * 获取：审核人
	 */
	public Long getPerson() {
		return person;
	}
	/**
	 * 设置：补充说明
	 */
	public void setRmk(String rmk) {
		this.rmk = rmk;
	}
	/**
	 * 获取：补充说明
	 */
	public String getRmk() {
		return rmk;
	}
	/**
	 * 设置：当前进度
	 */
	public void setSchedule(Integer schedule) {
		this.schedule = schedule;
	}
	/**
	 * 获取：当前进度
	 */
	public Integer getSchedule() {
		return schedule;
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
	public Integer getPass() {
		return pass;
	}
	public void setPass(Integer pass) {
		this.pass = pass;
	}
	
}
