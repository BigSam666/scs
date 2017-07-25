package com.zhcs.entity;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

//*****************************************************************************
/**
 * <p>Title:StaffTipOffEntity</p>
 * <p>Description:员工爆料</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司</p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public class StaffTipOffEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * token
	 */
	@ApiModelProperty(value="token" ,required=true)
	private String token;
	
	/**
	 * 业务板块
	 */
	@ApiModelProperty(value="类型" ,required=true)
	private String busseg;
	
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
	@ApiModelProperty(value="原因说明" ,required=true)
	private String content;
	
	/**
	 * 图片
	 */
	@ApiModelProperty(value="图片" ,required=true)
	private String img;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getBusseg() {
		return busseg;
	}

	public void setBusseg(String busseg) {
		this.busseg = busseg;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}



	
	
	
}
