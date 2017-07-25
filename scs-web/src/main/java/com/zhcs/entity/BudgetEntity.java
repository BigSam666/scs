package com.zhcs.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


//*****************************************************************************
/**
 * <p>Title:BudgetEntity</p>
 * <p>Description: 预算申报</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public class BudgetEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键id
	private Long id;
	//申报人员
	private Long person;
	//申报人员
	private String personName;
	//月份
	private String month;
	//所属项目
	private String project;
	//所属项目
	private String projectName;
	//联系方式
	private String mobile;
	//进度
	private Integer schedule = 1;
	// 维修明细（表单）
	private String budgetDetail;
	// 维修明细
	private List<BddetailEntity> budgetDetailList;
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
	 * 设置：申报人员
	 */
	public void setPerson(Long person) {
		this.person = person;
	}
	/**
	 * 获取：申报人员
	 */
	public Long getPerson() {
		return person;
	}


	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	/**
	 * 设置：所属项目
	 */
	public void setProject(String project) {
		this.project = project;
	}
	/**
	 * 获取：所属项目
	 */
	public String getProject() {
		return project;
	}
	/**
	 * 设置：进度
	 */
	public void setSchedule(Integer schedule) {
		this.schedule = schedule;
	}
	/**
	 * 获取：进度
	 */
	public Integer getSchedule() {
		return schedule;
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
	public String getBudgetDetail() {
		return budgetDetail;
	}
	public void setBudgetDetail(String budgetDetail) {
		this.budgetDetail = budgetDetail;  //   7-0-0=1#7-0-1=#8-0-0=2
		this.budgetDetailList = new ArrayList<>();
		String[] items = budgetDetail.split("#"); // 7-0-0=1,7-0-1=
		for (String item : items) {
			String[] foo = item.split("\\="); // 7-0-0,1
			if (foo.length < 2) continue;
			String[] bar = foo[0].split("\\-"); // 7,0,0
			budgetDetailList.add(new BddetailEntity(bar[0].trim(), bar[1].trim(), Integer.parseInt(bar[2].trim()), new BigDecimal(foo[1].trim())));
		}
	}
	public List<BddetailEntity> getBudgetDetailList() {
		return budgetDetailList;
	}
	public void setBudgetDetailList(List<BddetailEntity> budgetDetailList) {
		this.budgetDetailList = budgetDetailList;
	}
	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

}
