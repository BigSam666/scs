package com.zhcs.entity;

import java.io.Serializable;
import java.util.Date;


//*****************************************************************************
/**
 * <p>Title:QltsuaAuditeeEntity</p>
 * <p>Description: 质量督导审核方</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public class QltsuaAuditeeEntity implements Serializable {
private static final long serialVersionUID = 1L;
	
	//主键id
	private Long id;
	//扣分类型
	private Long scoretype;
	//扣分类型
	private String scoretypeName;
	//地址
	private String addr;
	//纬度
	private String lat;
	//经度
	private String lng;
	//扣分
	private Integer score;
	//扣分
	private String scoreName;
	//扣分时间
	private Date stime;
	//选人
	private Long person;
	//选人
	private String personName;
	//所属部门
	private String deptName;
	//精确地址
	private String accaddr;
	//现场照片
	private String img;
	//扣分说明
	private String expl;
	//状态
	private String status;
	//创建人员
	private Long crtid;
	//创建人员
	private String crtName;
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
	 * 设置：扣分类型
	 */
	public void setScoretype(Long scoretype) {
		this.scoretype = scoretype;
	}
	/**
	 * 获取：扣分类型
	 */
	public Long getScoretype() {
		return scoretype;
	}
	/**
	 * 设置：地址
	 */
	public void setAddr(String addr) {
		this.addr = addr;
	}
	/**
	 * 获取：地址
	 */
	public String getAddr() {
		return addr;
	}
	/**
	 * 设置：纬度
	 */
	public void setLat(String lat) {
		this.lat = lat;
	}
	/**
	 * 获取：纬度
	 */
	public String getLat() {
		return lat;
	}
	/**
	 * 设置：经度
	 */
	public void setLng(String lng) {
		this.lng = lng;
	}
	/**
	 * 获取：经度
	 */
	public String getLng() {
		return lng;
	}
	/**
	 * 设置：扣分
	 */
	public void setScore(Integer score) {
		this.score = score;
	}
	/**
	 * 获取：扣分
	 */
	public Integer getScore() {
		return score;
	}
	/**
	 * 设置：扣分时间
	 */
	public void setStime(Date stime) {
		this.stime = stime;
	}
	/**
	 * 获取：扣分时间
	 */
	public Date getStime() {
		return stime;
	}
	/**
	 * 设置：选人
	 */
	public void setPerson(Long person) {
		this.person = person;
	}
	/**
	 * 获取：选人
	 */
	public Long getPerson() {
		return person;
	}
	/**
	 * 设置：精确地址
	 */
	public void setAccaddr(String accaddr) {
		this.accaddr = accaddr;
	}
	/**
	 * 获取：精确地址
	 */
	public String getAccaddr() {
		return accaddr;
	}
	/**
	 * 设置：现场照片
	 */
	public void setImg(String img) {
		this.img = img;
	}
	/**
	 * 获取：现场照片
	 */
	public String getImg() {
		return img;
	}
	/**
	 * 设置：扣分说明
	 */
	public void setExpl(String expl) {
		this.expl = expl;
	}
	/**
	 * 获取：扣分说明
	 */
	public String getExpl() {
		return expl;
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
	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getCrtName() {
		return crtName;
	}
	public void setCrtName(String crtName) {
		this.crtName = crtName;
	}
	public String getScoreName() {
		return scoreName;
	}
	public void setScoreName(String scoreName) {
		this.scoreName = scoreName;
	}
	public String getScoretypeName() {
		return scoretypeName;
	}
	public void setScoretypeName(String scoretypeName) {
		this.scoretypeName = scoretypeName;
	}
	
}
