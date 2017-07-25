package com.zhcs.entity;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class ScheduleEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * token
	 */
	@ApiModelProperty(value="token" ,required=true)
	private String token;
	
	/**
	 * 员工姓名
	 */
	@ApiModelProperty(value="员工姓名" ,required=true)
	private String personName;
	
	/**
	 * 所属部门
	 */
	@ApiModelProperty(value="所属部门" ,required=true)
	private Long dept;
	
	/**
	 * 班次
	 */
	@ApiModelProperty(value="班次" ,required=true)
	private Long classes;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public Long getDept() {
		return dept;
	}

	public void setDept(Long dept) {
		this.dept = dept;
	}

	public Long getClasses() {
		return classes;
	}

	public void setClasses(Long classes) {
		this.classes = classes;
	}

	
}
