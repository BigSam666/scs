package com.zhcs.entity;

import java.io.Serializable;
import java.util.Date;


//*****************************************************************************
/**
 * <p>Title:CentpointEntity</p>
 * <p>Description: 中心点设置</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public class CentpointEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键id
	private Long id;
	//项目部
	private Long proj;
	//负责业务
	private String bus;
	//项目描述
	private String des;
	//经度
	private String lng;
	//维度
	private String lat;
	//半径
	private Integer radius;
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
	 * 设置：项目部
	 */
	public void setProj(Long proj) {
		this.proj = proj;
	}
	/**
	 * 获取：项目部
	 */
	public Long getProj() {
		return proj;
	}
	/**
	 * 设置：负责业务
	 */
	public void setBus(String bus) {
		this.bus = bus;
	}
	/**
	 * 获取：负责业务
	 */
	public String getBus() {
		return bus;
	}
	/**
	 * 设置：项目描述
	 */
	public void setDes(String des) {
		this.des = des;
	}
	/**
	 * 获取：项目描述
	 */
	public String getDes() {
		return des;
	}
	/**
	 * 设置：经度
	 */
	public void setLng(String lng) {
		this.lng = lng;
	}
	/**
	 * 获取：经度
	 */
	public String getLng() {
		return lng;
	}
	/**
	 * 设置：维度
	 */
	public void setLat(String lat) {
		this.lat = lat;
	}
	/**
	 * 获取：维度
	 */
	public String getLat() {
		return lat;
	}
	/**
	 * 设置：半径
	 */
	public void setRadius(Integer radius) {
		this.radius = radius;
	}
	/**
	 * 获取：半径
	 */
	public Integer getRadius() {
		return radius;
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
