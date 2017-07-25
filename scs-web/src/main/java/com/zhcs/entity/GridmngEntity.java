package com.zhcs.entity;

import java.io.Serializable;
import java.util.Date;


//*****************************************************************************
/**
 * <p>Title:GridmngEntity</p>
 * <p>Description: 网格管理</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public class GridmngEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//网格ID
	private Long id;
	//网格名称
	private String name;
	//用户id
	private String uid;
	//所属项目部
	private Long dept;
	//网格业务类型
	private String type;
	//创建人ID
	private Long crtid;
	//创建时间
	private Date crttm;
	//修改人ID
	private Long updid;
	//修改时间
	private Date updtm;

	/**
	 * 设置：网格ID
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：网格ID
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：网格名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：网格名称
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：用户id
	 */
	public void setUid(String uid) {
		this.uid = uid;
	}
	/**
	 * 获取：用户id
	 */
	public String getUid() {
		return uid;
	}
	/**
	 * 设置：所属项目部
	 */
	public void setDept(Long dept) {
		this.dept = dept;
	}
	/**
	 * 获取：所属项目部
	 */
	public Long getDept() {
		return dept;
	}
	/**
	 * 设置：网格业务类型
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * 获取：网格业务类型
	 */
	public String getType() {
		return type;
	}
	/**
	 * 设置：创建人ID
	 */
	public void setCrtid(Long crtid) {
		this.crtid = crtid;
	}
	/**
	 * 获取：创建人ID
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
	 * 设置：修改人ID
	 */
	public void setUpdid(Long updid) {
		this.updid = updid;
	}
	/**
	 * 获取：修改人ID
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
