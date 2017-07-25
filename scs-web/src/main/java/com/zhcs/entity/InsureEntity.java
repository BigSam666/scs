package com.zhcs.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


//*****************************************************************************
/**
 * <p>Title:InsureEntity</p>
 * <p>Description: 保险管理</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public class InsureEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键id
	private Long id;
	//车牌号(车的id，关联到车辆表)
	private Long cno;
	//车牌号(真正的车牌号)
	private String cnoText;
	//保单号码
	private String insno;
	//保险日期
	private Date insuretm;
	//到期日期
	private Date ninstm;
	//投保明细(表单字段)
	private String insdetail;
	//投保明细
	private List<InsdetailEntity> insdetails;
	//办理人员
	private Long person;
	//办理人员姓名
	private String personName;
	//办理费用
	private BigDecimal cost;
	//投保地点
	private String addr;
	//投保单位
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
	 * 设置：保险日期
	 */
	public void setInsuretm(Date insuretm) {
		this.insuretm = insuretm;
	}
	/**
	 * 获取：保险日期
	 */
	public Date getInsuretm() {
		return insuretm;
	}
	/**
	 * 设置：下次日期
	 */
	public void setNinstm(Date ninstm) {
		this.ninstm = ninstm;
	}
	/**
	 * 获取：下次日期
	 */
	public Date getNinstm() {
		return ninstm;
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
	 * 设置：办理费用
	 */
	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}
	/**
	 * 获取：办理费用
	 */
	public BigDecimal getCost() {
		return cost;
	}
	/**
	 * 设置：保险地点
	 */
	public void setAddr(String addr) {
		this.addr = addr;
	}
	/**
	 * 获取：保险地点
	 */
	public String getAddr() {
		return addr;
	}
	/**
	 * 设置：保险单位
	 */
	public void setUnit(String unit) {
		this.unit = unit;
	}
	/**
	 * 获取：保险单位
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
	public String getInsno() {
		return insno;
	}
	public void setInsno(String insno) {
		this.insno = insno;
	}
	public List<InsdetailEntity> getInsdetails() {
		return insdetails;
	}
	public void setInsdetails(List<InsdetailEntity> insdetails) {
		this.insdetails = insdetails;
	}
	public String getInsdetail() {
		return insdetail;
	}
	public void setInsdetail(String insdetail) {
		this.insdetail = insdetail;
		this.insdetails = new ArrayList<InsdetailEntity>();
		String[] items = insdetail.split("#"); // [finsure_0=1,finsure_1=2]
		for(String item:items){
			String[] foos = item.split("\\="); //     [finsure_0,1]
			if(foos.length > 1) {
				String[] bars = foos[0].split("\\_"); // [finsure,0]
				insdetails.add(new InsdetailEntity(bars[0],bars[1],new BigDecimal(foos[1])));
			}
		}
	}
	
	
	
	
}
