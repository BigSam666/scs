package com.zhcs.entity;

import java.io.Serializable;
import java.util.Date;


//*****************************************************************************
/**
 * <p>Title:BasicssetupEntity</p>
 * <p>Description: 预警管理----基础设置
</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public class BasicssetupEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//id
	private Long id;
	//设置项
	private String setup;
	//滞留时间
	private Integer zdate;
	//滞留范围
	private Integer zrange;
	//越界范围
	private Integer yrange;
	//超速公里
	private Integer sdkm;
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
	 * 设置：设置项
	 */
	public void setSetup(String setup) {
		this.setup = setup;
	}
	/**
	 * 获取：设置项
	 */
	public String getSetup() {
		return setup;
	}
	/**
	 * 设置：滞留时间
	 */
	public void setZdate(Integer zdate) {
		this.zdate = zdate;
	}
	/**
	 * 获取：滞留时间
	 */
	public Integer getZdate() {
		return zdate;
	}
	/**
	 * 设置：滞留范围
	 */
	public void setZrange(Integer zrange) {
		this.zrange = zrange;
	}
	/**
	 * 获取：滞留范围
	 */
	public Integer getZrange() {
		return zrange;
	}
	/**
	 * 设置：越界范围
	 */
	public void setYrange(Integer yrange) {
		this.yrange = yrange;
	}
	/**
	 * 获取：越界范围
	 */
	public Integer getYrange() {
		return yrange;
	}
	/**
	 * 设置：超速公里
	 */
	public void setSdkm(Integer sdkm) {
		this.sdkm = sdkm;
	}
	/**
	 * 获取：超速公里
	 */
	public Integer getSdkm() {
		return sdkm;
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
}
