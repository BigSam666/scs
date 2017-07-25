package com.zhcs.entity;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;


//*****************************************************************************

/**
 * <p>Title:BrknewsEntity</p>
 * <p>Description: 爆料事件</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public class BrknewsReportEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	// 类型
	@ApiModelProperty(value="openId" ,required=true)
	private String openId;
	// 类型
	@ApiModelProperty(value="类型" ,required=true)
	private String type;
	// 纬度
	@ApiModelProperty(value="纬度" ,required=true)
	private String lat;
	// 经度
	@ApiModelProperty(value="经度" ,required=true)
	private String lng;
	// 最近的地点
	@ApiModelProperty(value="最近的地点" ,required=true)
	private String adds;
	// 内容
	@ApiModelProperty(value="内容" ,required=true)
	private String content;
	// 图片
	@ApiModelProperty(value="图片" ,required=true)
	private String img;


	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getOpenId() {
		return openId;
	}

	/**
	 * 设置：类型
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * 获取：类型
	 */
	public String getType() {
		return type;
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
	 * 设置：最近的地点
	 */
	public void setAdds(String adds) {
		this.adds = adds;
	}
	/**
	 * 获取：最近的地点
	 */
	public String getAdds() {
		return adds;
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

}
