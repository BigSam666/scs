package com.zhcs.entity;

import java.io.Serializable;
import java.util.Date;


//*****************************************************************************
/**
 * <p>Title:CmngroupEntity</p>
 * <p>Description: 通讯组</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public class CmngroupEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//id
	private Long id;
	//组名
	private String nm;
	//电话
	private String userid;
	//状态：1:有效0:无效
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
	 * 设置：组名
	 */
	public void setNm(String nm) {
		this.nm = nm;
	}
	/**
	 * 获取：组名
	 */
	public String getNm() {
		return nm;
	}
	/**
	 * 设置：电话
	 */
	public void setUserid(String userid) {
		this.userid = userid;
	}
	/**
	 * 获取：电话
	 */
	public String getUserid() {
		return userid;
	}
	/**
	 * 设置：状态：1:有效0:无效
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * 获取：状态：1:有效0:无效
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
}
