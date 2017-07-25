package com.zhcs.entity;

import java.io.Serializable;
import java.util.Date;


//*****************************************************************************
/**
 * <p>Title:SysUserDeptEntity</p>
 * <p>Description: 用户与部门对应关系</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public class SysUserDeptEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键id
	private Long id;
	//用户ID
	private Long userid;
	//部门ID
	private Long deptid;
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
	 * 设置：用户ID
	 */
	public void setUserid(Long userid) {
		this.userid = userid;
	}
	/**
	 * 获取：用户ID
	 */
	public Long getUserid() {
		return userid;
	}
	/**
	 * 设置：部门ID
	 */
	public void setDeptid(Long deptid) {
		this.deptid = deptid;
	}
	/**
	 * 获取：部门ID
	 */
	public Long getDeptid() {
		return deptid;
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
