package com.zhcs.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


//*****************************************************************************
/**
 * <p>Title:DepartmentEntity</p>
 * <p>Description: 部门</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public class SysDepartmentEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键id
	private Long id;
	//父部门ID，一级部门为0
	private Long pid;
	//父部门名称
	private String pnm;
	//部门名称
	private String name;
	//是否有效：0无效、1有效
	private String valid;
	//排序号
	private BigDecimal sort;
	//组织类型
	private String otype;
	// 负责人
	private Long pesponsible;
	//备注
	private String rmk;

	//创建人员
	private Long crtid;
	//创建时间
	private Date crttm;
	//修改人员
	private Long updid;
	//修改时间
	private Date updtm;
	
	//ztree属性
	private Boolean open;

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
	 * 设置：父部门ID，一级部门为0
	 */
	public void setPid(Long pid) {
		this.pid = pid;
	}
	/**
	 * 获取：父部门ID，一级部门为0
	 */
	public Long getPid() {
		return pid;
	}
	/**
	 * 设置：父级部门名称
	 */
	public void setPnm(String pnm) {
		this.pnm = pnm;
	}
	/**
	 * 获取：父级部门名称
	 */
	public String getPnm() {
		return pnm;
	}
	/**
	 * 设置：部门名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：部门名称
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：是否有效：0无效、1有效
	 */
	public void setValid(String valid) {
		this.valid = valid;
	}
	/**
	 * 获取：是否有效：0无效、1有效
	 */
	public String getValid() {
		return valid;
	}
	/**
	 * 设置：排序号
	 */
	public void setSort(BigDecimal sort) {
		this.sort = sort;
	}
	/**
	 * 获取：排序号
	 */
	public BigDecimal getSort() {
		return sort;
	}
	public String getOtype() {
		return otype;
	}
	public void setOtype(String otype) {
		this.otype = otype;
	}
	
	public Long getPesponsible() {
		return pesponsible;
	}
	public void setPesponsible(Long pesponsible) {
		this.pesponsible = pesponsible;
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
	
	public void setOpen(Boolean open) {
		this.open = open;
	}
	
	public Boolean getOpen() {
		return open;
	}
	
}
