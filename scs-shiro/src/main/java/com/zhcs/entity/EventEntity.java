package com.zhcs.entity;

import java.io.Serializable;
import java.util.Date;


//*****************************************************************************
/**
 * <p>Title:EventEntity</p>
 * <p>Description: 案件</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public class EventEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键id
	private Long id;
	//业务板块
	private String busseg;
	//事件编号
	private String no;
	//事件来源
	private String sour;
	//问题描述
	private String qdescribe;
	//所属经度
	private String lng;
	//所属纬度
	private String lat;
	//详细地址
	private String addr;
	//问题图片
	private String qimg;
	//处理人
	private Long handle;
	//处理时间
	private Date htm;
	//处理图片
	private String img;
	//预计完成时间
	private Date estimatetm;
	//辅办人
	private Long auxiliary;
	//工作反馈
	private String des;
	//派单时间
	private Date dltm;
	//当前环节
	private String current;
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
	 * 设置：事件编号
	 */
	public void setNo(String no) {
		this.no = no;
	}
	/**
	 * 获取：事件编号
	 */
	public String getNo() {
		return no;
	}
	/**
	 * 设置：事件来源
	 */
	public void setSour(String sour) {
		this.sour = sour;
	}
	/**
	 * 获取：事件来源
	 */
	public String getSour() {
		return sour;
	}
	/**
	 * 设置：问题描述
	 */
	public void setQdescribe(String qdescribe) {
		this.qdescribe = qdescribe;
	}
	/**
	 * 获取：问题描述
	 */
	public String getQdescribe() {
		return qdescribe;
	}
	/**
	 * 设置：所属经度
	 */
	public void setLng(String lng) {
		this.lng = lng;
	}
	/**
	 * 获取：所属经度
	 */
	public String getLng() {
		return lng;
	}
	/**
	 * 设置：所属纬度
	 */
	public void setLat(String lat) {
		this.lat = lat;
	}
	/**
	 * 获取：所属纬度
	 */
	public String getLat() {
		return lat;
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
	/**
	 * 设置：问题图片
	 */
	public void setQimg(String qimg) {
		this.qimg = qimg;
	}
	/**
	 * 获取：问题图片
	 */
	public String getQimg() {
		return qimg;
	}
	/**
	 * 设置：处理人
	 */
	public void setHandle(Long handle) {
		this.handle = handle;
	}
	/**
	 * 获取：处理人
	 */
	public Long getHandle() {
		return handle;
	}
	/**
	 * 设置：处理事件
	 */
	public void setHtm(Date htm) {
		this.htm = htm;
	}
	/**
	 * 获取：处理事件
	 */
	public Date getHtm() {
		return htm;
	}
	/**
	 * 设置：处理图片
	 */
	public void setImg(String img) {
		this.img = img;
	}
	/**
	 * 获取：处理图片
	 */
	public String getImg() {
		return img;
	}
	public Date getEstimatetm() {
		return estimatetm;
	}
	public void setEstimatetm(Date estimatetm) {
		this.estimatetm = estimatetm;
	}
	public Long getAuxiliary() {
		return auxiliary;
	}
	public void setAuxiliary(Long auxiliary) {
		this.auxiliary = auxiliary;
	}
	/**
	 * 设置：工作反馈
	 */
	public void setDes(String des) {
		this.des = des;
	}
	/**
	 * 获取：工作反馈
	 */
	public String getDes() {
		return des;
	}
	/**
	 * 设置：派单时间
	 */
	public void setDltm(Date dltm) {
		this.dltm = dltm;
	}
	/**
	 * 获取：派单时间
	 */
	public Date getDltm() {
		return dltm;
	}
	/**
	 * 设置：当前环节
	 */
	public void setCurrent(String current) {
		this.current = current;
	}
	/**
	 * 获取：当前环节
	 */
	public String getCurrent() {
		return current;
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
