package com.zhcs.entity;

import java.io.Serializable;
import java.util.Date;


//*****************************************************************************
/**
 * <p>Title:CallTelEntity</p>
 * <p>Description: 坐席管理</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public class CallTelEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键id
	private Long id;
	//姓名
	private String name;
	//分机号码
	private Long fphone;
	//性别
	private String sex;
	//所属项目
	private String xmper;
	//坐席编码(自动生成)
	private String zxno;
	//部门
	private String depa;
	//手机号码
	private Long phone;
	//身份证号码
	private String sid;
	//出生日期
	private Date cdate;
	//居住地址
	private String jaddes;
	//户籍地址
	private String haddes;
	//人员类型
	private String rtype;
	//备注
	private String rem;
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
	 * 设置：姓名
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：姓名
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：分机号码
	 */
	public void setFphone(Long fphone) {
		this.fphone = fphone;
	}
	/**
	 * 获取：分机号码
	 */
	public Long getFphone() {
		return fphone;
	}
	/**
	 * 设置：性别
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}
	/**
	 * 获取：性别
	 */
	public String getSex() {
		return sex;
	}
	/**
	 * 设置：所属项目
	 */
	public void setXmper(String xmper) {
		this.xmper = xmper;
	}
	/**
	 * 获取：所属项目
	 */
	public String getXmper() {
		return xmper;
	}
	/**
	 * 设置：坐席编码(自动生成)
	 */
	public void setZxno(String zxno) {
		this.zxno = zxno;
	}
	/**
	 * 获取：坐席编码(自动生成)
	 */
	public String getZxno() {
		return zxno;
	}
	/**
	 * 设置：部门
	 */
	public void setDepa(String depa) {
		this.depa = depa;
	}
	/**
	 * 获取：部门
	 */
	public String getDepa() {
		return depa;
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
	 * 设置：身份证号码
	 */
	public void setSid(String sid) {
		this.sid = sid;
	}
	/**
	 * 获取：身份证号码
	 */
	public String getSid() {
		return sid;
	}
	/**
	 * 设置：出生日期
	 */
	public void setCdate(Date cdate) {
		this.cdate = cdate;
	}
	/**
	 * 获取：出生日期
	 */
	public Date getCdate() {
		return cdate;
	}
	/**
	 * 设置：居住地址
	 */
	public void setJaddes(String jaddes) {
		this.jaddes = jaddes;
	}
	/**
	 * 获取：居住地址
	 */
	public String getJaddes() {
		return jaddes;
	}
	/**
	 * 设置：户籍地址
	 */
	public void setHaddes(String haddes) {
		this.haddes = haddes;
	}
	/**
	 * 获取：户籍地址
	 */
	public String getHaddes() {
		return haddes;
	}
	/**
	 * 设置：人员类型
	 */
	public void setRtype(String rtype) {
		this.rtype = rtype;
	}
	/**
	 * 获取：人员类型
	 */
	public String getRtype() {
		return rtype;
	}
	/**
	 * 设置：备注
	 */
	public void setRem(String rem) {
		this.rem = rem;
	}
	/**
	 * 获取：备注
	 */
	public String getRem() {
		return rem;
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
