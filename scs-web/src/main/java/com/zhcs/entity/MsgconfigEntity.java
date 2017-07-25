package com.zhcs.entity;

import java.io.Serializable;
import java.util.Date;


//*****************************************************************************
/**
 * <p>Title:MsgconfigEntity</p>
 * <p>Description: 信息基础设置</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public class MsgconfigEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//id
	private Long id;
	//每天发送上限
	private Integer dup;
	//每人发送上限
	private Integer pup;
	//类型：1:短信 2:消息 3:邮件
	private String type;
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
	 * 设置：每天发送上限
	 */
	public void setDup(Integer dup) {
		this.dup = dup;
	}
	/**
	 * 获取：每天发送上限
	 */
	public Integer getDup() {
		return dup;
	}
	/**
	 * 设置：每人发送上限
	 */
	public void setPup(Integer pup) {
		this.pup = pup;
	}
	/**
	 * 获取：每人发送上限
	 */
	public Integer getPup() {
		return pup;
	}
	/**
	 * 设置：类型：1:短信 2:消息 3:邮件
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * 获取：类型：1:短信 2:消息 3:邮件
	 */
	public String getType() {
		return type;
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
