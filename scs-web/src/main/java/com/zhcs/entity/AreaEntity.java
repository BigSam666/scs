package com.zhcs.entity;

import java.io.Serializable;
import java.util.Date;


//*****************************************************************************
/**
 * <p>Title:AreaEntity</p>
 * <p>Description: 省市数据 (T_AREA)</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public class AreaEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键id
	private Long id;
	//地址代码
	private String no;
	//地址类型：1省级、2市级、3县级
	private String type;
	//地址中文名
	private String cnm;
	//地址英文名
	private String enm;
	//地址简称
	private String abbrnm;
	//父级地址码
	private String pno;
	//邮编
	private String zno;
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
	 * 设置：地址代码
	 */
	public void setNo(String no) {
		this.no = no;
	}
	/**
	 * 获取：地址代码
	 */
	public String getNo() {
		return no;
	}
	/**
	 * 设置：地址类型：1省级、2市级、3县级
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * 获取：地址类型：1省级、2市级、3县级
	 */
	public String getType() {
		return type;
	}
	/**
	 * 设置：地址中文名
	 */
	public void setCnm(String cnm) {
		this.cnm = cnm;
	}
	/**
	 * 获取：地址中文名
	 */
	public String getCnm() {
		return cnm;
	}
	/**
	 * 设置：地址英文名
	 */
	public void setEnm(String enm) {
		this.enm = enm;
	}
	/**
	 * 获取：地址英文名
	 */
	public String getEnm() {
		return enm;
	}
	/**
	 * 设置：地址简称
	 */
	public void setAbbrnm(String abbrnm) {
		this.abbrnm = abbrnm;
	}
	/**
	 * 获取：地址简称
	 */
	public String getAbbrnm() {
		return abbrnm;
	}
	/**
	 * 设置：父级地址码
	 */
	public void setPno(String pno) {
		this.pno = pno;
	}
	/**
	 * 获取：父级地址码
	 */
	public String getPno() {
		return pno;
	}
	/**
	 * 设置：邮编
	 */
	public void setZno(String zno) {
		this.zno = zno;
	}
	/**
	 * 获取：邮编
	 */
	public String getZno() {
		return zno;
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
