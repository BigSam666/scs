package com.zhcs.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


//*****************************************************************************
/**
 * <p>Title:BaseCdeEntity</p>
 * <p>Description: 基础代码 (T_BASE_CDE)</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public class BaseCodeEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键id
	private Long id;
	//代码
	private String no;
	//类型
	private String type;
	//中文名
	private String cnm;
	//附加值
	private String attv;
	//是否有效：0无效、1有效
	private String valid;
	//排序号
	private BigDecimal sort;
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
	 * 设置：代码
	 */
	public void setNo(String no) {
		this.no = no;
	}
	/**
	 * 获取：代码
	 */
	public String getNo() {
		return no;
	}
	/**
	 * 设置：类型
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * 获取：类型
	 */
	public String getType() {
		return type;
	}
	/**
	 * 设置：中文名
	 */
	public void setCnm(String cnm) {
		this.cnm = cnm;
	}
	/**
	 * 获取：中文名
	 */
	public String getCnm() {
		return cnm;
	}
	/**
	 * 设置：附加值
	 */
	public void setAttv(String attv) {
		this.attv = attv;
	}
	/**
	 * 获取：附加值
	 */
	public String getAttv() {
		return attv;
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
}
