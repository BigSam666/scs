package com.zhcs.entity;

import java.io.Serializable;
import java.util.Date;


//*****************************************************************************
/**
 * <p>Title:BasissettingEntity</p>
 * <p>Description: 基础设置</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public class BasissettingEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//id
	private Long id;
	//车辆滞留米
	private Integer czm;
	//车辆滞留分钟
	private Integer czf;
	//车辆越界米
	private Integer cym;
	//车辆超速公里
	private Integer ccg;
	//考勤滞留米
	private Integer kzm;
	//考勤滞留分钟
	private Integer kzf;
	//考勤越界米
	private Integer kym;
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
	 * 设置：车辆滞留米
	 */
	public void setCzm(Integer czm) {
		this.czm = czm;
	}
	/**
	 * 获取：车辆滞留米
	 */
	public Integer getCzm() {
		return czm;
	}
	/**
	 * 设置：车辆滞留分钟
	 */
	public void setCzf(Integer czf) {
		this.czf = czf;
	}
	/**
	 * 获取：车辆滞留分钟
	 */
	public Integer getCzf() {
		return czf;
	}
	/**
	 * 设置：车辆越界米
	 */
	public void setCym(Integer cym) {
		this.cym = cym;
	}
	/**
	 * 获取：车辆越界米
	 */
	public Integer getCym() {
		return cym;
	}
	/**
	 * 设置：车辆超速公里
	 */
	public void setCcg(Integer ccg) {
		this.ccg = ccg;
	}
	/**
	 * 获取：车辆超速公里
	 */
	public Integer getCcg() {
		return ccg;
	}
	/**
	 * 设置：考勤滞留米
	 */
	public void setKzm(Integer kzm) {
		this.kzm = kzm;
	}
	/**
	 * 获取：考勤滞留米
	 */
	public Integer getKzm() {
		return kzm;
	}
	/**
	 * 设置：考勤滞留分钟
	 */
	public void setKzf(Integer kzf) {
		this.kzf = kzf;
	}
	/**
	 * 获取：考勤滞留分钟
	 */
	public Integer getKzf() {
		return kzf;
	}
	/**
	 * 设置：考勤越界米
	 */
	public void setKym(Integer kym) {
		this.kym = kym;
	}
	/**
	 * 获取：考勤越界米
	 */
	public Integer getKym() {
		return kym;
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
