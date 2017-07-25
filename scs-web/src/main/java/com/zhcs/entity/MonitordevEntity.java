package com.zhcs.entity;

import java.io.Serializable;
import java.util.Date;


//*****************************************************************************
/**
 * <p>Title:MonitordevEntity</p>
 * <p>Description: 监控设备</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public class MonitordevEntity implements Serializable {
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
	//设备编号
	private String devid;
	//监控地点
	private String addr;
	//纬度
	private String lat;
	//经度
	private String lng;
	//现场照片
	private String pic;
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
	 * 设置：设备编号
	 */
	public void setDevid(String devid) {
		this.devid = devid;
	}
	/**
	 * 获取：设备编号
	 */
	public String getDevid() {
		return devid;
	}
	/**
	 * 设置：监控地点
	 */
	public void setAddr(String addr) {
		this.addr = addr;
	}
	/**
	 * 获取：监控地点
	 */
	public String getAddr() {
		return addr;
	}
	/**
	 * 设置：纬度
	 */
	public void setLat(String lat) {
		this.lat = lat;
	}
	/**
	 * 获取：纬度
	 */
	public String getLat() {
		return lat;
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
	 * 设置：现场照片
	 */
	public void setPic(String pic) {
		this.pic = pic;
	}
	/**
	 * 获取：现场照片
	 */
	public String getPic() {
		return pic;
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
