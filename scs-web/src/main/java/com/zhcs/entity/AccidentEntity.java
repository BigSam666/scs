package com.zhcs.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


//*****************************************************************************
/**
 * <p>Title:AccidentEntity</p>
 * <p>Description: 事故管理</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public class AccidentEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键id
	private Long id;
	//车辆
	private Long car;
	//车牌号
	private String carNo;
	//所属车队
	private String fleettName;
	//肇事人
	private Long person;
	//肇事人
	private String personName;
	//肇事日期
	private Date adate;
	//肇事地点
	private String addr;
	//事故责任
	private Long ares;
	//事故责任
	private String aresName;
	//应赔金额
	private BigDecimal money;
	//实赔金额
	private BigDecimal rmoney;
	//事故经过
	private String content;
	//现场照片
	private String pics;
	//状态
	private String status;
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
	 * 设置：车辆
	 */
	public void setCar(Long car) {
		this.car = car;
	}
	/**
	 * 获取：车辆
	 */
	public Long getCar() {
		return car;
	}
	/**
	 * 设置：肇事人
	 */
	public void setPerson(Long person) {
		this.person = person;
	}
	/**
	 * 获取：肇事人
	 */
	public Long getPerson() {
		return person;
	}
	/**
	 * 设置：肇事日期
	 */
	public void setAdate(Date adate) {
		this.adate = adate;
	}
	/**
	 * 获取：肇事日期
	 */
	public Date getAdate() {
		return adate;
	}
	/**
	 * 设置：肇事地点
	 */
	public void setAddr(String addr) {
		this.addr = addr;
	}
	/**
	 * 获取：肇事地点
	 */
	public String getAddr() {
		return addr;
	}
	/**
	 * 设置：事故责任
	 */
	public void setAres(Long ares) {
		this.ares = ares;
	}
	/**
	 * 获取：事故责任
	 */
	public Long getAres() {
		return ares;
	}
	/**
	 * 设置：应赔金额
	 */
	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	/**
	 * 获取：应赔金额
	 */
	public BigDecimal getMoney() {
		return money;
	}
	/**
	 * 设置：实赔金额
	 */
	public void setRmoney(BigDecimal rmoney) {
		this.rmoney = rmoney;
	}
	/**
	 * 获取：实赔金额
	 */
	public BigDecimal getRmoney() {
		return rmoney;
	}
	/**
	 * 设置：事故经过
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 获取：事故经过
	 */
	public String getContent() {
		return content;
	}
	/**
	 * 设置：现场照片
	 */
	public void setPics(String pics) {
		this.pics = pics;
	}
	/**
	 * 获取：现场照片
	 */
	public String getPics() {
		return pics;
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
	public String getCarNo() {
		return carNo;
	}
	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}
	public String getFleettName() {
		return fleettName;
	}
	public void setFleettName(String fleettName) {
		this.fleettName = fleettName;
	}
	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	public String getAresName() {
		return aresName;
	}
	public void setAresName(String aresName) {
		this.aresName = aresName;
	}
	
	
}
