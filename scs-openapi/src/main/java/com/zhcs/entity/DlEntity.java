package com.zhcs.entity;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

//*****************************************************************************
/**
 * <p>Title:DlEntity</p>
 * <p>Description:事件派单</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司</p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public class DlEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
	 * token
	 */
	@ApiModelProperty(value="token" ,required=true)
	private String token;
	
	/**
	 * 处理方式（回复还是指派人员？）
	 */
	@ApiModelProperty(value="处理方式（回复值为\"0\"，派单值为\"1\"）" ,required=true)
	private String btype;
	
	/**
	 * 事件id
	 */
	@ApiModelProperty(value="事件id" ,required=true)
	private Long eventId;
	
	/**
	 * 当前环节
	 */
	@ApiModelProperty(value="当前环节（'sjpd' or 'fhpd'）" ,required=true)
	private String current;
	
	/**
	 * 预计完成时间
	 */
	@ApiModelProperty(value="预计完成时间")
	private Date estimatetm;
	
	/**
	 * 备注说明
	 */
	@ApiModelProperty(value="备注说明")
	private String content;	
	
	/**
	 * 主要负责人
	 */
	@ApiModelProperty(value="主要负责人")
	private Long handle;
	
	/**
	 * 协作人员
	 */
	@ApiModelProperty(value="协作人员")
	private Long auxiliary;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Long getHandle() {
		return handle;
	}

	public Long getEventId() {
		return eventId;
	}

	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}

	public void setHandle(Long handle) {
		this.handle = handle;
	}

	public Long getAuxiliary() {
		return auxiliary;
	}

	public void setAuxiliary(Long auxiliary) {
		this.auxiliary = auxiliary;
	}

	public String getCurrent() {
		return current;
	}

	public void setCurrent(String current) {
		this.current = current;
	}

	public Date getEstimatetm() {
		return estimatetm;
	}

	public void setEstimatetm(Date estimatetm) {
		this.estimatetm = estimatetm;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getBtype() {
		return btype;
	}

	public void setBtype(String btype) {
		this.btype = btype;
	}
	
	
	
}
