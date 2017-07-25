package com.zhcs.entity;

import java.io.Serializable;
import java.util.Date;


//*****************************************************************************
/**
 * <p>Title:GriddesEntity</p>
 * <p>Description: 网格管理详情</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public class GriddesEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//id
	private Long id;
	//网格id
	private Long fid;
	//排序
	private Long sort;
	//经度
	private String lon;
	//维度
	private String lat;
	//创建人ID
	private Long crtid;
	//创建时间
	private Date crttm;
	//修改人ID
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
	 * 设置：网格id
	 */
	public void setFid(Long fid) {
		this.fid = fid;
	}
	/**
	 * 获取：网格id
	 */
	public Long getFid() {
		return fid;
	}
	/**
	 * 设置：排序
	 */
	public void setSort(Long sort) {
		this.sort = sort;
	}
	/**
	 * 获取：排序
	 */
	public Long getSort() {
		return sort;
	}
	/**
	 * 设置：经度
	 */
	public void setLon(String lon) {
		this.lon = lon;
	}
	/**
	 * 获取：经度
	 */
	public String getLon() {
		return lon;
	}
	/**
	 * 设置：维度
	 */
	public void setLat(String lat) {
		this.lat = lat;
	}
	/**
	 * 获取：维度
	 */
	public String getLat() {
		return lat;
	}
	/**
	 * 设置：创建人ID
	 */
	public void setCrtid(Long crtid) {
		this.crtid = crtid;
	}
	/**
	 * 获取：创建人ID
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
	 * 设置：修改人ID
	 */
	public void setUpdid(Long updid) {
		this.updid = updid;
	}
	/**
	 * 获取：修改人ID
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
