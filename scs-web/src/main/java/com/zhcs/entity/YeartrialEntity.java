package com.zhcs.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


//*****************************************************************************
/**
 * <p>Title:YeartrialEntity</p>
 * <p>Description: 年审管理</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public class YeartrialEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键id
	private Long id;
	//车牌号(车的id，关联到车辆表)
	private Long cno;
	//车牌号(真正的车牌号)
	private String cnoText;
	//年审日期
	private Date yttm;
	//到期日期
	private Date nyttm;
	//年检号
	private String ytno;
	//办理人员
	private Long person;
	//办理人员姓名
	private String personName;
	//年审费用
	private BigDecimal cost;
	//年审地点
	private String addr;
	//年审单位
	private String unit;
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
	 * 设置：车牌号(关联到车辆表)
	 */
	public void setCno(Long cno) {
		this.cno = cno;
	}
	/**
	 * 获取：车牌号(关联到车辆表)
	 */
	public Long getCno() {
		return cno;
	}
	/**
	 * 设置：年审日期
	 */
	public void setYttm(Date yttm) {
		this.yttm = yttm;
	}
	/**
	 * 获取：年审日期
	 */
	public Date getYttm() {
		return yttm;
	}
	/**
	 * 设置：到期日期
	 */
	public void setNyttm(Date nyttm) {
		this.nyttm = nyttm;
	}
	/**
	 * 获取：到期日期
	 */
	public Date getNyttm() {
		return nyttm;
	}
	/**
	 * 设置：办理人员
	 */
	public void setPerson(Long person) {
		this.person = person;
	}
	/**
	 * 获取：办理人员
	 */
	public Long getPerson() {
		return person;
	}
	/**
	 * 设置：年审费用
	 */
	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}
	/**
	 * 获取：年审费用
	 */
	public BigDecimal getCost() {
		return cost;
	}
	/**
	 * 设置：年审地点
	 */
	public void setAddr(String addr) {
		this.addr = addr;
	}
	/**
	 * 获取：年审地点
	 */
	public String getAddr() {
		return addr;
	}
	/**
	 * 设置：年审单位
	 */
	public void setUnit(String unit) {
		this.unit = unit;
	}
	/**
	 * 获取：年审单位
	 */
	public String getUnit() {
		return unit;
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
	/**
	 * 获取：车牌号
	 */
	public String getCnoText() {
		return cnoText;
	}
	/**
	 * 设置：办理人员姓名
	 */
	public void setCnoText(String cnoText) {
		this.cnoText = cnoText;
	}
	/**
	 * 获取：车牌号
	 */
	public String getPersonName() {
		return personName;
	}
	/**
	 * 设置：办理人员姓名
	 */
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	public String getYtno() {
		return ytno;
	}
	public void setYtno(String ytno) {
		this.ytno = ytno;
	}
	
	
}
