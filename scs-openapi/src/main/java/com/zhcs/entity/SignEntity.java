package com.zhcs.entity;

import com.zhcs.validator.group.AddGroup;
import com.zhcs.validator.group.UpdateGroup;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.util.Date;


//*****************************************************************************
/**
 * <p>Title:SignEntity</p>
 * <p>Description: 招牌申请</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public class SignEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键id
	private Long id;
	//招牌照片
	private String signimg;
	//招牌内容
	private String content;
	//招牌长度
	private Integer length;
	//规格宽度
	private Integer width;
	//离地高度
	private Integer height;
	//使用性质
	private String nature;
	//招牌类型
	private String type;
	//照明方式
	private String light;
	//申请期限
	private String term;
	//省
	private String province;
	//市
	private String city;
	//县
	private String county;
	//详细地址
	private String dtladdress;
	//姓名
	private String unm;
	//电话
	private String mobile;
	//所在单位
	private String unit;
	//营业执照
	private String blcs;
	//微信帐号
	private String openid;
	//创建人ID
	private Long crtid;
	//创建时间
	private Date crttm;
	//修改人ID
	private Long updid;
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
	 * 设置：招牌照片
	 */
	public void setSignimg(String signimg) {
		this.signimg = signimg;
	}
	/**
	 * 获取：招牌照片
	 */
	public String getSignimg() {
		return signimg;
	}
	/**
	 * 设置：招牌内容
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 获取：招牌内容
	 */
	public String getContent() {
		return content;
	}
	/**
	 * 设置：招牌长度
	 */
	public void setLength(Integer length) {
		this.length = length;
	}
	/**
	 * 获取：招牌长度
	 */
	public Integer getLength() {
		return length;
	}
	/**
	 * 设置：规格宽度
	 */
	public void setWidth(Integer width) {
		this.width = width;
	}
	/**
	 * 获取：规格宽度
	 */
	public Integer getWidth() {
		return width;
	}
	/**
	 * 设置：离地高度
	 */
	public void setHeight(Integer height) {
		this.height = height;
	}
	/**
	 * 获取：离地高度
	 */
	public Integer getHeight() {
		return height;
	}
	/**
	 * 设置：使用性质
	 */
	public void setNature(String nature) {
		this.nature = nature;
	}
	/**
	 * 获取：使用性质
	 */
	public String getNature() {
		return nature;
	}
	/**
	 * 设置：招牌类型
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * 获取：招牌类型
	 */
	public String getType() {
		return type;
	}
	/**
	 * 设置：照明方式
	 */
	public void setLight(String light) {
		this.light = light;
	}
	/**
	 * 获取：照明方式
	 */
	public String getLight() {
		return light;
	}
	/**
	 * 设置：申请期限
	 */
	public void setTerm(String term) {
		this.term = term;
	}
	/**
	 * 获取：申请期限
	 */
	public String getTerm() {
		return term;
	}
	/**
	 * 设置：省
	 */
	public void setProvince(String province) {
		this.province = province;
	}
	/**
	 * 获取：省
	 */
	public String getProvince() {
		return province;
	}
	/**
	 * 设置：市
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * 获取：市
	 */
	public String getCity() {
		return city;
	}
	/**
	 * 设置：县
	 */
	public void setCounty(String county) {
		this.county = county;
	}
	/**
	 * 获取：县
	 */
	public String getCounty() {
		return county;
	}
	/**
	 * 设置：详细地址
	 */
	public void setDtladdress(String dtladdress) {
		this.dtladdress = dtladdress;
	}
	/**
	 * 获取：详细地址
	 */
	public String getDtladdress() {
		return dtladdress;
	}
	/**
	 * 设置：姓名
	 */
	public void setUnm(String unm) {
		this.unm = unm;
	}
	/**
	 * 获取：姓名
	 */
	public String getUnm() {
		return unm;
	}
	/**
	 * 设置：电话
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	/**
	 * 获取：电话
	 */
	public String getMobile() {
		return mobile;
	}
	/**
	 * 设置：所在单位
	 */
	public void setUnit(String unit) {
		this.unit = unit;
	}
	/**
	 * 获取：所在单位
	 */
	public String getUnit() {
		return unit;
	}
	/**
	 * 设置：营业执照
	 */
	public void setBlcs(String blcs) {
		this.blcs = blcs;
	}
	/**
	 * 获取：营业执照
	 */
	public String getBlcs() {
		return blcs;
	}
	/**
	 * 设置：微信帐号
	 */
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	/**
	 * 获取：微信帐号
	 */
	public String getOpenid() {
		return openid;
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
