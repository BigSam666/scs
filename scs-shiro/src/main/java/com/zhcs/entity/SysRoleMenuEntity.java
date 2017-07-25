package com.zhcs.entity;

import java.io.Serializable;
import java.util.Date;

//*****************************************************************************
/**
 * <p>Title:SysRoleMenuEntity</p>
 * <p>Description:角色与菜单对应关系</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司</p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public class SysRoleMenuEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;

	/**
	 * 角色ID
	 */
	private Long roleid;

	/**
	 * 菜单ID
	 */
	private Long menuid;

	//创建人员
	private Long crtid;
	//创建时间
	private Date crttm;
	//修改人员
	private Long updid;
	//修改时间
	private Date updtm;	

	/**
	 * 设置：
	 * @param id 
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 获取：
	 * @return Long
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * 设置：角色ID
	 * @param roleId 角色ID
	 */
	public void setRoleid(Long roleid) {
		this.roleid = roleid;
	}

	/**
	 * 获取：角色ID
	 * @return Long
	 */
	public Long getRoleid() {
		return roleid;
	}
	
	/**
	 * 设置：菜单ID
	 * @param menuId 菜单ID
	 */
	public void setMenuid(Long menuid) {
		this.menuid = menuid;
	}

	/**
	 * 获取：菜单ID
	 * @return Long
	 */
	public Long getMenuid() {
		return menuid;
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
