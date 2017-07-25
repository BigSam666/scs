package com.zhcs.entity;

import java.io.Serializable;
import java.util.Date;


//*****************************************************************************
/**
 * <p>Title:CallRecordEntity</p>
 * <p>Description: 通话记录表</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public class CallRecordEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键id
	private Long id;
	//通话时间
	private Date calldate;
	//呼叫类型
	private String calltype;
	//手机号码
	private Long phone;
	//通话时长
	private Long arttime;
	//坐席编号
	private Long seatsid;
	//坐席姓名
	private String seatsname;
	//通话录音url地址
	private String calltapesurl;
	//创建人员
	private String crtid;
	//创建时间
	private Date crttm;
	//修改人员
	private String updid;
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
	 * 设置：通话时间
	 */
	public void setCalldate(Date calldate) {
		this.calldate = calldate;
	}
	/**
	 * 获取：通话时间
	 */
	public Date getCalldate() {
		return calldate;
	}
	/**
	 * 设置：呼叫类型
	 */
	public void setCalltype(String calltype) {
		this.calltype = calltype;
	}
	/**
	 * 获取：呼叫类型
	 */
	public String getCalltype() {
		return calltype;
	}
	/**
	 * 设置：手机号码
	 */
	public void setPhone(Long phone) {
		this.phone = phone;
	}
	/**
	 * 获取：手机号码
	 */
	public Long getPhone() {
		return phone;
	}
	/**
	 * 设置：通话时长
	 */
	public void setArttime(Long arttime) {
		this.arttime = arttime;
	}
	/**
	 * 获取：通话时长
	 */
	public Long getArttime() {
		return arttime;
	}
	/**
	 * 设置：坐席编号
	 */
	public void setSeatsid(Long seatsid) {
		this.seatsid = seatsid;
	}
	/**
	 * 获取：坐席编号
	 */
	public Long getSeatsid() {
		return seatsid;
	}
	/**
	 * 设置：坐席姓名
	 */
	public void setSeatsname(String seatsname) {
		this.seatsname = seatsname;
	}
	/**
	 * 获取：坐席姓名
	 */
	public String getSeatsname() {
		return seatsname;
	}
	/**
	 * 设置：通话录音url地址
	 */
	public void setCalltapesurl(String calltapesurl) {
		this.calltapesurl = calltapesurl;
	}
	/**
	 * 获取：通话录音url地址
	 */
	public String getCalltapesurl() {
		return calltapesurl;
	}
	/**
	 * 设置：创建人员
	 */
	public void setCrtid(String crtid) {
		this.crtid = crtid;
	}
	/**
	 * 获取：创建人员
	 */
	public String getCrtid() {
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
	public void setUpdid(String updid) {
		this.updid = updid;
	}
	/**
	 * 获取：修改人员
	 */
	public String getUpdid() {
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
