package com.zhcs.entity;

import java.io.Serializable;
import java.util.Date;


//*****************************************************************************
/**
 * <p>Title:WorkflowEntity</p>
 * <p>Description: 工作流</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public class WorkflowEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键id
	private Long id;
	//节点名称
	private String nodename;
	//节点编号
	private String nodeno;
	//流程名称
	private String nm;
	//流程编号
	private String number;
	//上一节点编号
	private String prevno;
	//下一节点编号
	private String nextno;
	//回退节点编号
	private String returnno;
	//角色集合
	private String roleids;
	//角色集合
	private String[] roleIds;
	//节点类型
	private String nodetype;
	//版本序号
	private Integer vseq;
	//版本名称
	private String vname;
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
	 * 设置：节点名称
	 */
	public void setNodename(String nodename) {
		this.nodename = nodename;
	}
	/**
	 * 获取：节点名称
	 */
	public String getNodename() {
		return nodename;
	}
	/**
	 * 设置：节点编号
	 */
	public void setNodeno(String nodeno) {
		this.nodeno = nodeno;
	}
	/**
	 * 获取：节点编号
	 */
	public String getNodeno() {
		return nodeno;
	}
	/**
	 * 设置：流程名称
	 */
	public void setNm(String nm) {
		this.nm = nm;
	}
	/**
	 * 获取：流程名称
	 */
	public String getNm() {
		return nm;
	}
	/**
	 * 设置：流程编号
	 */
	public void setNumber(String number) {
		this.number = number;
	}
	/**
	 * 获取：流程编号
	 */
	public String getNumber() {
		return number;
	}
	/**
	 * 设置：上一节点编号
	 */
	public void setPrevno(String prevno) {
		this.prevno = prevno;
	}
	/**
	 * 获取：上一节点编号
	 */
	public String getPrevno() {
		return prevno;
	}
	/**
	 * 设置：下一节点编号
	 */
	public void setNextno(String nextno) {
		this.nextno = nextno;
	}
	/**
	 * 获取：下一节点编号
	 */
	public String getNextno() {
		return nextno;
	}
	/**
	 * 设置：回退节点编号
	 */
	public void setReturnno(String returnno) {
		this.returnno = returnno;
	}
	/**
	 * 获取：回退节点编号
	 */
	public String getReturnno() {
		return returnno;
	}
	/**
	 * 设置：角色集合
	 */
	public void setRoleids(String roleids) {
		this.roleids = roleids;
	}
	
	/**
	 * 获取：角色集合
	 */
	public String getRoleids() {
		return roleids;
	}
	public void setRoleIds(String[] roleIds) {
		this.roleIds = roleIds;
	}
	public String[] getRoleIds() {
		return roleIds;
	}
	/**
	 * 设置：节点类型
	 */
	public void setNodetype(String nodetype) {
		this.nodetype = nodetype;
	}
	/**
	 * 获取：节点类型
	 */
	public String getNodetype() {
		return nodetype;
	}
	/**
	 * 设置：版本序号
	 */
	public void setVseq(Integer vseq) {
		this.vseq = vseq;
	}
	/**
	 * 获取：版本序号
	 */
	public Integer getVseq() {
		return vseq;
	}
	/**
	 * 设置：版本名称
	 */
	public void setVname(String vname) {
		this.vname = vname;
	}
	/**
	 * 获取：版本名称
	 */
	public String getVname() {
		return vname;
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
