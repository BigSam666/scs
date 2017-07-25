package com.zhcs.entity;

import java.io.Serializable;
import java.util.Date;


//*****************************************************************************
/**
 * <p>Title:PersonterminalEntity</p>
 * <p>Description: 人员终端</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public class PersonterminalEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键id
	private Long id;
	//设备类型
	private Long devtype;
	//设备类型
	private String devtypeName;
	//手机号码
	private String phone;
	//设备编号
	private String devno;
	//使用人
	private Long person;
	//使用人
	private String personName;
	//创建人员
	private Long crtid;
	//创建时间
	private Date crttm;
	//修改人员
	private Long updid;
	//修改人
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
	 * 设置：设备类型
	 */
	public void setDevtype(Long devtype) {
		this.devtype = devtype;
	}
	/**
	 * 获取：设备类型
	 */
	public Long getDevtype() {
		return devtype;
	}
	/**
	 * 设置：手机号码
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * 获取：手机号码
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * 设置：设备编号
	 */
	public void setDevno(String devno) {
		this.devno = devno;
	}
	/**
	 * 获取：设备编号
	 */
	public String getDevno() {
		return devno;
	}
	/**
	 * 设置：使用人
	 */
	public void setPerson(Long person) {
		this.person = person;
	}
	/**
	 * 获取：使用人
	 */
	public Long getPerson() {
		return person;
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
	public String getDevtypeName() {
		return devtypeName;
	}
	public void setDevtypeName(String devtypeName) {
		this.devtypeName = devtypeName;
	}
	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	public String getUpdName() {
		return updName;
	}
	public void setUpdName(String updName) {
		this.updName = updName;
	}
	
	
}
