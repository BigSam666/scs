package com.zhcs.entity;

import java.io.Serializable;
import java.util.Date;


//*****************************************************************************
/**
 * <p>Title:WorkrecordEntity</p>
 * <p>Description: 工作记录</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public class WorkrecordEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键id
	private Long id;
	//业务板块
	private String busseg;
	//现场照片
	private String img;
	//详细地址
	private String addr;
	//精确地址
	private String accaddr;
	//纬度
	private String lat;
	//经度
	private String lng;
	//说明
	private String expl;
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
	 * 设置：业务板块
	 */
	public void setBusseg(String busseg) {
		this.busseg = busseg;
	}
	/**
	 * 获取：业务板块
	 */
	public String getBusseg() {
		return busseg;
	}
	/**
	 * 设置：现场照片
	 */
	public void setImg(String img) {
		this.img = img;
	}
	/**
	 * 获取：现场照片
	 */
	public String getImg() {
		return img;
	}
	/**
	 * 设置：详细地址
	 */
	public void setAddr(String addr) {
		this.addr = addr;
	}
	/**
	 * 获取：详细地址
	 */
	public String getAddr() {
		return addr;
	}
	public String getAccaddr() {
		return accaddr;
	}
	public void setAccaddr(String accaddr) {
		this.accaddr = accaddr;
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
	 * 设置：说明
	 */
	public void setExpl(String expl) {
		this.expl = expl;
	}
	/**
	 * 获取：说明
	 */
	public String getExpl() {
		return expl;
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
