package com.zhcs.entity;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;


//*****************************************************************************
/**
 * <p>Title:TrashtabEntity</p>
 * <p>Description: 垃圾桶标记</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public class TrashtabEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键id
	@ApiModelProperty(hidden = true)
	private Long id;
	/**
	 * token
	 */
	@ApiModelProperty(value="token" ,required=true)
	private String token;
	/**
	 * 路段名
	 */
	@ApiModelProperty(value="路段名" ,required=true)
	private String street;
	/**
	 * 标签纸编号
	 */
	@ApiModelProperty(value="标签纸编号" ,required=true)
	private String no;
	/**
	 * 经度
	 */
	@ApiModelProperty(value="经度" ,required=true)
	private String lng;
	/**
	 * 纬度
	 */
	@ApiModelProperty(value="纬度" ,required=true)
	private String lat;
	/**
	 * 地址
	 */
	@ApiModelProperty(value="地址" ,required=true)
	private String addr;
	/**
	 * 备注
	 */
	@ApiModelProperty(value="备注" ,required=true)
	private String rmk;
	/**
	 * 图片
	 */
	@ApiModelProperty(value="图片" ,required=true)
	private String img;
	//创建人员
	@ApiModelProperty(hidden = true)
	private Long crtid;
	//创建时间
	@ApiModelProperty(hidden = true)
	private Date crttm;
	//修改人员
	@ApiModelProperty(hidden = true)
	private Long updid;
	//修改时间
	@ApiModelProperty(hidden = true)
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
	 * 设置：路段名
	 */
	public void setStreet(String street) {
		this.street = street;
	}
	/**
	 * 获取：路段名
	 */
	public String getStreet() {
		return street;
	}
	/**
	 * 设置：标签纸编号
	 */
	public void setNo(String no) {
		this.no = no;
	}
	/**
	 * 获取：标签纸编号
	 */
	public String getNo() {
		return no;
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
	 * 设置：图片
	 */
	public void setImg(String img) {
		this.img = img;
	}
	/**
	 * 获取：图片
	 */
	public String getImg() {
		return img;
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
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
}
