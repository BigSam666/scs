package com.zhcs.entity;

import java.io.Serializable;
import java.util.Date;


//*****************************************************************************
/**
 * <p>Title:JobwarningEntity</p>
 * <p>Description: jobwarning工作预警</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public class JobwarningEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键id
	private Long id;
	//项目部
	private Long project;
	//业务板块
	private Long business;
	//业务类型
	private Long btype;
	//预警类型
	private Long wtype;
	//是否推送本人
	private String flag;
	//时间设置
	private Long intv;
	//接收人
	private Long receive;
	//创建人ID
	private Long crtid;
	//创建时间
	private Date crttm;
	//修改人ID
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
	 * 设置：项目部
	 */
	public void setProject(Long project) {
		this.project = project;
	}
	/**
	 * 获取：项目部
	 */
	public Long getProject() {
		return project;
	}
	/**
	 * 设置：业务板块
	 */
	public void setBusiness(Long business) {
		this.business = business;
	}
	/**
	 * 获取：业务板块
	 */
	public Long getBusiness() {
		return business;
	}
	/**
	 * 设置：业务类型
	 */
	public void setBtype(Long btype) {
		this.btype = btype;
	}
	/**
	 * 获取：业务类型
	 */
	public Long getBtype() {
		return btype;
	}
	/**
	 * 设置：预警类型
	 */
	public void setWtype(Long wtype) {
		this.wtype = wtype;
	}
	/**
	 * 获取：预警类型
	 */
	public Long getWtype() {
		return wtype;
	}
	/**
	 * 设置：是否推送本人
	 */
	public void setFlag(String flag) {
		this.flag = flag;
	}
	/**
	 * 获取：是否推送本人
	 */
	public String getFlag() {
		return flag;
	}
	/**
	 * 设置：时间设置
	 */
	public void setIntv(Long intv) {
		this.intv = intv;
	}
	/**
	 * 获取：时间设置
	 */
	public Long getIntv() {
		return intv;
	}
	/**
	 * 设置：接收人
	 */
	public void setReceive(Long receive) {
		this.receive = receive;
	}
	/**
	 * 获取：接收人
	 */
	public Long getReceive() {
		return receive;
	}
	/**
	 * 设置：创建人ID
	 */
	public void setCrtid(Long crtid) {
		this.crtid = crtid;
	}
	/**
	 * 获取：创建人ID
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
	 * 设置：修改人ID
	 */
	public void setUpdid(Long updid) {
		this.updid = updid;
	}
	/**
	 * 获取：修改人ID
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
