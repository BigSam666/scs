package com.zhcs.entity;

import java.io.Serializable;
import java.math.BigDecimal;


//*****************************************************************************
/**
 * <p>Title:SysSerianoEntity</p>
 * <p>Description: 序号</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public class SysSerianoEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private String type;
	//
	private String year;
	//
	private String keyone;
	//
	private BigDecimal no;

	/**
	 * 设置：
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * 获取：
	 */
	public String getType() {
		return type;
	}
	/**
	 * 设置：
	 */
	public void setYear(String year) {
		this.year = year;
	}
	/**
	 * 获取：
	 */
	public String getYear() {
		return year;
	}
	/**
	 * 设置：
	 */
	public void setKeyone(String keyone) {
		this.keyone = keyone;
	}
	/**
	 * 获取：
	 */
	public String getKeyone() {
		return keyone;
	}
	/**
	 * 设置：
	 */
	public void setNo(BigDecimal no) {
		this.no = no;
	}
	/**
	 * 获取：
	 */
	public BigDecimal getNo() {
		return no;
	}
}
