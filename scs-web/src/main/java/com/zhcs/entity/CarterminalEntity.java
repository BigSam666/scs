package com.zhcs.entity;

import java.io.Serializable;
import java.util.Date;


//*****************************************************************************
/**
 * <p>Title:CarterminalEntity</p>
 * <p>Description: 车载终端</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public class CarterminalEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键id
	private Long id;
	//设备厂商
	private Long devfrim;
	//设备厂商
	private String devfrimName;
	//设备型号
	private Long devmodel;
	//设备型号
	private String devmodelName;
	//设备ID
	private String devid;
	//2G/SIM卡号
	private String g2no;
	//3G/SIM卡号
	private String g3no;
	//创建人员
	private Long crtid;
	//创建时间
	private Date crttm;
	//修改人员
	private Long updid;
	//修改人员
	private String updName;
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
	 * 设置：设备厂商
	 */
	public void setDevfrim(Long devfrim) {
		this.devfrim = devfrim;
	}
	/**
	 * 获取：设备厂商
	 */
	public Long getDevfrim() {
		return devfrim;
	}
	/**
	 * 设置：设备型号
	 */
	public void setDevmodel(Long devmodel) {
		this.devmodel = devmodel;
	}
	/**
	 * 获取：设备型号
	 */
	public Long getDevmodel() {
		return devmodel;
	}
	/**
	 * 设置：设备ID
	 */
	public void setDevid(String devid) {
		this.devid = devid;
	}
	/**
	 * 获取：设备ID
	 */
	public String getDevid() {
		return devid;
	}
	/**
	 * 设置：2G/SIM卡号
	 */
	public void setG2no(String g2no) {
		this.g2no = g2no;
	}
	/**
	 * 获取：2G/SIM卡号
	 */
	public String getG2no() {
		return g2no;
	}
	/**
	 * 设置：3G/SIM卡号
	 */
	public void setG3no(String g3no) {
		this.g3no = g3no;
	}
	/**
	 * 获取：3G/SIM卡号
	 */
	public String getG3no() {
		return g3no;
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
	public String getDevfrimName() {
		return devfrimName;
	}
	public void setDevfrimName(String devfrimName) {
		this.devfrimName = devfrimName;
	}
	public String getDevmodelName() {
		return devmodelName;
	}
	public void setDevmodelName(String devmodelName) {
		this.devmodelName = devmodelName;
	}
	public String getUpdName() {
		return updName;
	}
	public void setUpdName(String updName) {
		this.updName = updName;
	}

	
}
