package com.zhcs.entity;

import java.io.Serializable;
import java.util.Date;


//*****************************************************************************
/**
 * <p>Title:DriverEntity</p>
 * <p>Description: 司机管理</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public class DriverEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键id
	private Long id;
	//驾照编号
	private String dno;
	//准驾车型
	private String dclass;
	//准驾车型名称(C1..，多个用,分割)
	private String dclassName;
	//领证日期
	private Date bdate;
	//到期日期
	private Date edate;
	//司机(id)
	private Long driverId;
	//司机
	private SysUserEntity driverEntity;
//	//司机名
//	private String dnm;
//	//身份证号
//	private String card;
//	//手机号
//	private String phone;
//	//邮箱
//	private String email;
//	//所属部门名称(多个用,分割)
//	private String deptName;
//	//所属角色名称(多个用,分隔)
//	private String roleName;
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
//	/**
//	 * 设置：司机名
//	 */
//	public void setDnm(String dnm) {
//		this.dnm = dnm;
//	}
//	/**
//	 * 获取：司机名
//	 */
//	public String getDnm() {
//		return dnm;
//	}
//	/**
//	 * 设置：身份证号
//	 */
//	public void setCard(String card) {
//		this.card = card;
//	}
//	/**
//	 * 获取：身份证号
//	 */
//	public String getCard() {
//		return card;
//	}
//	/**
//	 * 设置：手机号
//	 */
//	public void setPhone(String phone) {
//		this.phone = phone;
//	}
//	/**
//	 * 获取：手机号
//	 */
//	public String getPhone() {
//		return phone;
//	}
//	/**
//	 * 设置：邮箱
//	 */
//	public void setEmail(String email) {
//		this.email = email;
//	}
//	/**
//	 * 获取：邮箱
//	 */
//	public String getEmail() {
//		return email;
//	}
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
	 * 获取：准驾车型名称
	 */
	public String getDclassName() {
		return dclassName;
	}
	/**
	 * 设置：准驾车型名称
	 */
	public void setDclassName(String dclassName) {
		this.dclassName = dclassName;
	}
//	/**
//	 * 获取：所属部门名称(多个用,分割)
//	 */
//	public String getDeptName() {
//		return deptName;
//	}
//	/**
//	 * 设置：所属部门名称(多个用,分割)
//	 */
//	public void setDeptName(String deptName) {
//		this.deptName = deptName;
//	}
//	/**
//	 * 获取：所属角色名称(多个用,分隔)
//	 */
//	public String getRoleName() {
//		return roleName;
//	}
//	/**
//	 * 设置：所属角色名称(多个用,分隔)
//	 */
//	public void setRoleName(String roleName) {
//		this.roleName = roleName;
//	}
	/**
	 * 获取：驾照编号
	 */
	public String getDno() {
		return dno;
	}
	/**
	 * 设置：驾照编号
	 */
	public void setDno(String dno) {
		this.dno = dno;
	}
	/**
	 * 获取：准驾车型
	 */
	public String getDclass() {
		return dclass;
	}
	/**
	 * 设置：准驾车型
	 */
	public void setDclass(String dclass) {
		this.dclass = dclass;
	}
	/**
	 * 获取：司机(id)
	 */
	public Long getDriverId() {
		return driverId;
	}
	/**
	 * 设置：司机(id)
	 */
	public void setDriverId(Long driverId) {
		this.driverId = driverId;
	}
	/**
	 * 获取：领证日期
	 */
	public Date getBdate() {
		return bdate;
	}
	/**
	 * 设置：领证日期
	 */
	public void setBdate(Date bdate) {
		this.bdate = bdate;
	}
	/**
	 * 获取：到期日期
	 */
	public Date getEdate() {
		return edate;
	}
	/**
	 * 设置：到期日期
	 */
	public void setEdate(Date edate) {
		this.edate = edate;
	}
	public SysUserEntity getDriverEntity() {
		return driverEntity;
	}
	public void setDriverEntity(SysUserEntity driverEntity) {
		this.driverEntity = driverEntity;
	}
	
	
	
}
