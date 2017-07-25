package com.zhcs.entity;

import java.io.Serializable;
import java.util.Date;


//*****************************************************************************
/**
 * <p>Title:MaillogEntity</p>
 * <p>Description: 邮件记录</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public class MaillogEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//id
	private Long id;
	//接收人
	private String receiver;
	//发送类型:1系统2人工
	private String type;
	//内容
	private String content;
	//发送状态：1:成功0:失败
	private String status;
	//发送日志
	private String log;
	//创建人员
	private Long crtid;
	//创建时间
	private Date crttm;
	//修改人员
	private Long updid;
	//修改时间
	private Date updtm;

	/**
	 * 设置：id
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：接收人
	 */
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	/**
	 * 获取：接收人
	 */
	public String getReceiver() {
		return receiver;
	}
	/**
	 * 设置：发送类型:1系统2人工
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * 获取：发送类型:1系统2人工
	 */
	public String getType() {
		return type;
	}
	/**
	 * 设置：内容
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 获取：内容
	 */
	public String getContent() {
		return content;
	}
	/**
	 * 设置：发送状态：1:成功0:失败
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * 获取：发送状态：1:成功0:失败
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * 设置：发送日志
	 */
	public void setLog(String log) {
		this.log = log;
	}
	/**
	 * 获取：发送日志
	 */
	public String getLog() {
		return log;
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
}
