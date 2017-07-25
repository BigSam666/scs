package com.zhcs.entity;

import java.io.Serializable;
import java.util.Date;


//*****************************************************************************
/**
 * <p>Title:CallJobselEntity</p>
 * <p>Description: 工单查询</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public class CallJobselEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键id
	private Long id;
	//来源
	private String source;
	//姓名
	private String name;
	//类型
	private String type;
	//电话号码
	private Long phone;
	//最近的地点
	private String adds;
	//内容
	private String content;
	//时间
	private Date date;
	//图片
	private String img;
	//创建人员
	private String crtid;
	//创建时间
	private Date crttm;
	//修改人员
	private String updid;
	//修改时间
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
	 * 设置：来源
	 */
	public void setSource(String source) {
		this.source = source;
	}
	/**
	 * 获取：来源
	 */
	public String getSource() {
		return source;
	}
	/**
	 * 设置：姓名
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：姓名
	 */
	public String getName() {
		return name;
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
	 * 设置：电话号码
	 */
	public void setPhone(Long phone) {
		this.phone = phone;
	}
	/**
	 * 获取：电话号码
	 */
	public Long getPhone() {
		return phone;
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
	 * 设置：时间
	 */
	public void setDate(Date date) {
		this.date = date;
	}
	/**
	 * 获取：时间
	 */
	public Date getDate() {
		return date;
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
	public void setCrtid(String crtid) {
		this.crtid = crtid;
	}
	/**
	 * 获取：创建人员
	 */
	public String getCrtid() {
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
	public void setUpdid(String updid) {
		this.updid = updid;
	}
	/**
	 * 获取：修改人员
	 */
	public String getUpdid() {
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
