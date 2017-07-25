package com.zhcs.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


//*****************************************************************************
/**
 * <p>Title:IllegaEntity</p>
 * <p>Description: 违章管理</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public class IllegaEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键id
	private Long id;
	//车牌号(关联到车辆表)
	private Long cno;
	//车牌号(真正的车牌号)
	private String cnoText;
	//违章日期
	private Date illtm;
	//违章原因
	private Long cause;
	//违章原因
	private String causeName;
	
	//违章人员
	private Long person;
	//违章人员姓名
	private String personName;
	//处罚金额
	private BigDecimal amount;
	//扣分
	private int score;
	//违章地点
	private String addr;
	//处罚单位
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
	 * 设置：违章日期
	 */
	public void setIlltm(Date illtm) {
		this.illtm = illtm;
	}
	/**
	 * 获取：违章日期
	 */
	public Date getIlltm() {
		return illtm;
	}
	/**
	 * 设置：违章原因
	 */
	public void setCause(Long cause) {
		this.cause = cause;
	}
	/**
	 * 获取：违章原因
	 */
	public Long getCause() {
		return cause;
	}
	/**
	 * 设置：违章人员
	 */
	public void setPerson(Long person) {
		this.person = person;
	}
	/**
	 * 获取：违章人员
	 */
	public Long getPerson() {
		return person;
	}
	/**
	 * 设置：处罚金额
	 */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	/**
	 * 获取：处罚金额
	 */
	public BigDecimal getAmount() {
		return amount;
	}
	/**
	 * 设置：违章地点
	 */
	public void setAddr(String addr) {
		this.addr = addr;
	}
	/**
	 * 获取：违章地点
	 */
	public String getAddr() {
		return addr;
	}
	/**
	 * 设置：处罚单位
	 */
	public void setUnit(String unit) {
		this.unit = unit;
	}
	/**
	 * 获取：处罚单位
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
	 * 获取：车牌号(真正的车牌号)
	 */
	public String getCnoText() {
		return cnoText;
	}
	/**
	 * 设置：车牌号(真正的车牌号)
	 */
	public void setCnoText(String cnoText) {
		this.cnoText = cnoText;
	}
	/**
	 * 获取：违章人员姓名
	 */
	public String getPersonName() {
		return personName;
	}
	/**
	 * 设置：违章人员姓名
	 */
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	public String getCauseName() {
		return causeName;
	}
	public void setCauseName(String causeName) {
		this.causeName = causeName;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	
	
}
