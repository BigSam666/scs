package com.zhcs.entity;

import java.io.Serializable;
import java.util.Date;


//*****************************************************************************
/**
 * <p>Title:CarEntity</p>
 * <p>Description: 车辆管理</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public class CarEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键id
	private Long id;
	//所属车队
	private Long fleett;
	//所属车队名称
	private String fleettName;
	//车辆类型
	private Long ctype;
	//车辆类型名称
	private String ctypeName;
	//车牌号
	private String cno;
	//图片（存放路径）
	private String pic;
	//车架号
	private String cin;
	//发动机号
	private String engno;
	//绑定设备(车载)
	private String binddev;
	//绑定设备(RFID)
	private String binddevrfid;
	//责任人
	private Long person;
	//责任人名称
	private String personName;
	//联系电话
	private String phone;
	//备注
	private String rmk;
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
	 * 设置：所属车队
	 */
	public void setFleett(Long fleett) {
		this.fleett = fleett;
	}
	/**
	 * 获取：所属车队
	 */
	public Long getFleett() {
		return fleett;
	}
	/**
	 * 设置：车辆类型
	 */
	public void setCtype(Long ctype) {
		this.ctype = ctype;
	}
	/**
	 * 获取：车辆类型
	 */
	public Long getCtype() {
		return ctype;
	}
	/**
	 * 设置：车牌号
	 */
	public void setCno(String cno) {
		this.cno = cno;
	}
	/**
	 * 获取：车牌号
	 */
	public String getCno() {
		return cno;
	}
	/**
	 * 设置：车架号
	 */
	public void setCin(String cin) {
		this.cin = cin;
	}
	/**
	 * 获取：车架号
	 */
	public String getCin() {
		return cin;
	}
	/**
	 * 设置：发动机号
	 */
	public void setEngno(String engno) {
		this.engno = engno;
	}
	/**
	 * 获取：发动机号
	 */
	public String getEngno() {
		return engno;
	}
	/**
	 * 设置：绑定设备
	 */
	public void setBinddev(String binddev) {
		this.binddev = binddev;
	}
	/**
	 * 获取：绑定设备
	 */
	public String getBinddev() {
		return binddev;
	}
	/**
	 * 设置：责任人
	 */
	public void setPerson(Long person) {
		this.person = person;
	}
	/**
	 * 获取：责任人
	 */
	public Long getPerson() {
		return person;
	}
	/**
	 * 设置：联系电话
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * 获取：联系电话
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * 设置：备注
	 */
	public void setRmk(String rmk) {
		this.rmk = rmk;
	}
	/**
	 * 获取：备注
	 */
	public String getRmk() {
		return rmk;
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
	/**
	 * 获取：所属车队名称
	 */
	public String getFleettName() {
		return fleettName;
	}
	/**
	 * 设置：所属车队名称
	 */
	public void setFleettName(String fleettName) {
		this.fleettName = fleettName;
	}
	/**
	 * 获取：车辆类型名称
	 */
	public String getCtypeName() {
		return ctypeName;
	}
	/**
	 * 设置：车辆类型名称
	 */
	public void setCtypeName(String ctypeName) {
		this.ctypeName = ctypeName;
	}
	/**
	 * 获取：责任人姓名
	 */
	public String getPersonName() {
		return personName;
	}
	/**
	 * 设置：责任人姓名
	 */
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	/**
	 * 获取：图片（存放路径）
	 */
	public String getPic() {
		return pic;
	}
	/**
	 * 设置：图片（存放路径）
	 */
	public void setPic(String pic) {
		this.pic = pic;
	}
	public String getBinddevrfid() {
		return binddevrfid;
	}
	public void setBinddevrfid(String binddevrfid) {
		this.binddevrfid = binddevrfid;
	}
	
}
