package com.zhcs.entity;

import java.io.Serializable;
import java.util.Date;

import com.zhcs.utils.DateUtil;


//*****************************************************************************
/**
 * <p>Title:AtdManageAuditeeEntity</p>
 * <p>Description: 考勤管理审核</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public class AtdManageAuditeeEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键id
	private Long id;
	//职员
	private Long person;
	//职员姓名
	private String personName;
	//所属部门
	private String deptName;
	//申报类型
	private Integer atdtype;
	//申报类型
	private String atdtypeName;
	//请假类型
	private String ltype;
	//时间1
	private Date ftime;
	//时间2
	private Date stime;
	//时间3
	private Date ttime;
	//时间4
	private Date fotime;
	//起始时间
	private String startTime;
	//结束时间
	private String endTime;
	//时长
	private String duration;
	//备注
	private String rmk;
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
	 * 设置：职员
	 */
	public void setPerson(Long person) {
		this.person = person;
	}
	/**
	 * 获取：职员
	 */
	public Long getPerson() {
		return person;
	}
	/**
	 * 设置：申报类型
	 */
	public void setAtdtype(Integer atdtype) {
		this.atdtype = atdtype;
		switch (atdtype) {
		case 1:
			this.atdtypeName = "请假";
			break;
		case 2:
			this.atdtypeName = "加班";
			break;
		case 3:
			this.atdtypeName = "调班";			
			break;
		case 4:
			this.atdtypeName = "调休";			
			break;
		default:
			this.atdtypeName = "";
			break;
		}
	}
	/**
	 * 获取：申报类型
	 */
	public Integer getAtdtype() {
		return atdtype;
	}
	/**
	 * 设置：请假类型
	 */
	public void setLtype(String ltype) {
		this.ltype = ltype;
	}
	/**
	 * 获取：请假类型
	 */
	public String getLtype() {
		return ltype;
	}
	/**
	 * 设置：时间1
	 */
	public void setFtime(Date ftime) {
		this.ftime = ftime;
	}
	/**
	 * 获取：时间1
	 */
	public Date getFtime() {
		return ftime;
	}
	/**
	 * 设置：时间2
	 */
	public void setStime(Date stime) {
		this.stime = stime;
		if (atdtype <3 ) {
			this.startTime = DateUtil.dateToStr(ftime, "yyyy-MM-dd HH:mm");	
			this.endTime = DateUtil.dateToStr(stime, "yyyy-MM-dd HH:mm");
		}
	}
	/**
	 * 获取：时间2
	 */
	public Date getStime() {
		return stime;
	}
	/**
	 * 设置：时间3
	 */
	public void setTtime(Date ttime) {
		this.ttime = ttime;
	}
	/**
	 * 获取：时间3
	 */
	public Date getTtime() {
		return ttime;
	}
	/**
	 * 设置：时间4
	 */
	public void setFotime(Date fotime) {
		this.fotime = fotime;
		this.startTime = DateUtil.dateToStr(ftime, "yyyy-MM-dd HH:mm") + "至" + DateUtil.dateToStr(stime, "yyyy-MM-dd HH:mm");	
		this.endTime = DateUtil.dateToStr(ttime, "yyyy-MM-dd HH:mm") + "至" + DateUtil.dateToStr(fotime, "yyyy-MM-dd HH:mm");	
	}
	/**
	 * 获取：时间4
	 */
	public Date getFotime() {
		return fotime;
	}
	/**
	 * 设置：时长
	 */
	public void setDuration(String duration) {
		this.duration = duration;
	}
	/**
	 * 获取：时长
	 */
	public String getDuration() {
		return duration;
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
	public String getAtdtypeName() {
		return atdtypeName;
	}
	public void setAtdtypeName(String atdtypeName) {
		this.atdtypeName = atdtypeName;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
}
