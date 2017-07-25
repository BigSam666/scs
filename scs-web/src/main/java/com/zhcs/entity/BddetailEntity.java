package com.zhcs.entity;

import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

//*****************************************************************************
/**
 * <p>Title:BddetailEntity</p>
 * <p>Description: 预算明细</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public class BddetailEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键id
	private Long id;
	//预算申报
	private Long budget;
	//代码
	private String code;
	//父节点代码
	private String pcode;
	//金额
	private BigDecimal money;
	//当前项来源
//	0：初始预算
//	1：第一次新增预算
//	2：第二次新增预算
//	3：第三次新增预算
	private Integer origin = 0;
	//创建人员
	private Long crtid;
	//创建时间
	private Date crttm;
	//修改人员
	private Long updid;
	//修改时间
	private Date updtm;
	

	public BddetailEntity() {
	}
	
	public BddetailEntity(String code, String pcode, BigDecimal money) {
		this.code = code;
		this.pcode = pcode;
		this.money = money;
	}
	

	public BddetailEntity(String code, String pcode, Integer origin, BigDecimal money) {
		this.code = code;
		this.pcode = pcode;
		this.money = money;
		this.origin = origin;
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
	 * 设置：预算申报
	 */
	public void setBudget(Long budget) {
		this.budget = budget;
	}
	/**
	 * 获取：预算申报
	 */
	public Long getBudget() {
		return budget;
	}
	/**
	 * 设置：代码
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * 获取：代码
	 */
	public String getCode() {
		return code;
	}
	/**
	 * 设置：父节点代码
	 */
	public void setPcode(String pcode) {
		this.pcode = pcode;
	}
	/**
	 * 获取：父节点代码
	 */
	public String getPcode() {
		return pcode;
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

	public Integer getOrigin() {
		return origin;
	}

	public void setOrigin(Integer origin) {
		this.origin = origin;
	}
	
}
