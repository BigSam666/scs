package com.zhcs.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


//*****************************************************************************
/**
 * <p>Title:InsdetailEntity</p>
 * <p>Description: 投保明细</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public class InsdetailEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键id
	private Long id;
	//保险id
	private Long insid;
	//投保项类型
	private String itemtype;
	//投保项代码
	private String itemcode;
	//金额
	private BigDecimal money;
	//状态
	private String status = "1";
	//创建人员
	private Long crtid;
	//创建时间
	private Date crttm;
	//修改人员
	private Long updid;
	//修改时间
	private Date updtm;
	

	public InsdetailEntity() {
	}
	
	public InsdetailEntity(String itemtype, String itemcode, BigDecimal money) {
		this.itemtype = itemtype;
		this.itemcode = itemcode;
		this.money = money;
	}

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
	 * 设置：投保项类型
	 */
	public void setItemtype(String itemtype) {
		this.itemtype = itemtype;
	}
	/**
	 * 获取：投保项类型
	 */
	public String getItemtype() {
		return itemtype;
	}
	/**
	 * 设置：投保项代码
	 */
	public void setItemcode(String itemcode) {
		this.itemcode = itemcode;
	}
	/**
	 * 获取：投保项代码
	 */
	public String getItemcode() {
		return itemcode;
	}
	/**
	 * 设置：金额
	 */
	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	/**
	 * 获取：金额
	 */
	public BigDecimal getMoney() {
		return money;
	}
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
	public Long getInsid() {
		return insid;
	}
	public void setInsid(Long insid) {
		this.insid = insid;
	}
	
}
