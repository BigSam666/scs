package com.zhcs.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


//*****************************************************************************
/**
 * <p>Title:GasEntity</p>
 * <p>Description: 油耗管理</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public class GasEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键id
	private Long id;
	//车牌号(关联到车辆表)
	private Long cno;
	//车牌号(真正的车牌号)
	private String cnoText;
	//加油日期
	private Date gastm;
	//办理人员
	private Long person;
	//办理人员姓名
	private String personName;
	//加油单价
	private BigDecimal price;
	//加油单位
	private Long unit;
	//加油单位(升..)
	private String unitName;
	//加油类型
	private Long type;
	//加油类型(名称)
	private String typeName;
	//数量
	private float num;
	//加油费用
	private BigDecimal cost;
	//加油地点
	private String addr;
	//起里程数
	private Integer bmile;
	//止里程数
	private Integer emile;
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
	 * 设置：加油日期
	 */
	public void setGastm(Date gastm) {
		this.gastm = gastm;
	}
	/**
	 * 获取：加油日期
	 */
	public Date getGastm() {
		return gastm;
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
	 * 设置：加油单价
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	/**
	 * 获取：加油单价
	 */
	public BigDecimal getPrice() {
		return price;
	}
	/**
	 * 设置：加油单位
	 */
	public void setUnit(Long unit) {
		this.unit = unit;
	}
	/**
	 * 获取：加油单位
	 */
	public Long getUnit() {
		return unit;
	}
	/**
	 * 设置：加油费用
	 */
	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}
	/**
	 * 获取：加油费用
	 */
	public BigDecimal getCost() {
		return cost;
	}
	/**
	 * 设置：加油地点
	 */
	public void setAddr(String addr) {
		this.addr = addr;
	}
	/**
	 * 获取：加油地点
	 */
	public String getAddr() {
		return addr;
	}
	/**
	 * 设置：起里程数
	 */
	public void setBmile(Integer bmile) {
		this.bmile = bmile;
	}
	/**
	 * 获取：起里程数
	 */
	public Integer getBmile() {
		return bmile;
	}
	/**
	 * 设置：止里程数
	 */
	public void setEmile(Integer emile) {
		this.emile = emile;
	}
	/**
	 * 获取：止里程数
	 */
	public Integer getEmile() {
		return emile;
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
	 * 设置：车牌号
	 */
	public void setCnoText(String cnoText) {
		this.cnoText = cnoText;
	}
	/**
	 * 获取：办理人员姓名
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
	/**
	 * 获取：加油单位(升..)
	 */
	public String getUnitName() {
		return unitName;
	}
	/**
	 * 设置：加油单位(升..)
	 */
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public Long getType() {
		return type;
	}
	public void setType(Long type) {
		this.type = type;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public float getNum() {
		return num;
	}
	public void setNum(float num) {
		this.num = num;
	}
	
	
}
