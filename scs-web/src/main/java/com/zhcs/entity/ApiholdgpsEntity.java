package com.zhcs.entity;

import java.io.Serializable;
import java.util.Date;

import java.math.BigDecimal;

//*****************************************************************************
/**
 * <p>Title:ApiholdgpsEntity</p>
 * <p>Description: 阿姨机-gps</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public class ApiholdgpsEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键id
	private Long id;
	//15位设备唯一序号
	private String imei;
	//发生时间
	private Date timeBegin;
	//是否为响应
	private Integer isReply;
	//是否追踪
	private Integer isTrack;
	//城市
	private String city;
	//地址
	private String address;
	//经度
	private BigDecimal lon;
	//纬度
	private BigDecimal lat;
	//类型
	private String type;
	//状态
	private String status;
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
	 * 设置：15位设备唯一序号
	 */
	public void setImei(String imei) {
		this.imei = imei;
	}
	/**
	 * 获取：15位设备唯一序号
	 */
	public String getImei() {
		return imei;
	}
	/**
	 * 设置：发生时间
	 */
	public void setTimeBegin(Date timeBegin) {
		this.timeBegin = timeBegin;
	}
	/**
	 * 获取：发生时间
	 */
	public Date getTimeBegin() {
		return timeBegin;
	}
	/**
	 * 设置：是否为响应
	 */
	public void setIsReply(Integer isReply) {
		this.isReply = isReply;
	}
	/**
	 * 获取：是否为响应
	 */
	public Integer getIsReply() {
		return isReply;
	}
	/**
	 * 设置：是否追踪
	 */
	public void setIsTrack(Integer isTrack) {
		this.isTrack = isTrack;
	}
	/**
	 * 获取：是否追踪
	 */
	public Integer getIsTrack() {
		return isTrack;
	}
	/**
	 * 设置：城市
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * 获取：城市
	 */
	public String getCity() {
		return city;
	}
	/**
	 * 设置：地址
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * 获取：地址
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * 设置：经度
	 */
	public void setLon(BigDecimal lon) {
		this.lon = lon;
	}
	/**
	 * 获取：经度
	 */
	public BigDecimal getLon() {
		return lon;
	}
	/**
	 * 设置：纬度
	 */
	public void setLat(BigDecimal lat) {
		this.lat = lat;
	}
	/**
	 * 获取：纬度
	 */
	public BigDecimal getLat() {
		return lat;
	}
	/**
	 * 设置：类型
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * 获取：类型
	 */
	public String getType() {
		return type;
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
