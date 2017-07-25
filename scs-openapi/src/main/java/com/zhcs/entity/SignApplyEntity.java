package com.zhcs.entity;

import com.zhcs.validator.group.AddGroup;
import com.zhcs.validator.group.UpdateGroup;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * @version V1.0
 * 版权所有：深圳市智慧城市管家信息科技有限公司
 * @Title: scs
 * @Package com.zhcs.entity
 * @Description: 用一句话描述该文件做什么
 * @author: 404221903@qq.com
 * @date: 2017/7/21  17:22
 * Create By: 刘晓东 - Alter
 * Modify By: XXX
 */
public class SignApplyEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value="code" ,required=true)
    private String code;

    @ApiModelProperty(value="招牌照片" ,required=true)
    private String signImg;

    @ApiModelProperty(value="招牌内容" ,required=true)
    private String content;

    @ApiModelProperty(value="规格长度" ,required=true)
    private int length;

    @ApiModelProperty(value="规格宽度" ,required=true)
    private int width;

    @ApiModelProperty(value="离地高度" ,required=true)
    private int height;

    @ApiModelProperty(value="使用性质" ,required=true)
    private String nature;

    @ApiModelProperty(value="招牌类型" ,required=true)
    private String type;

    @ApiModelProperty(value="照明方式" ,required=true)
    private String light;

    @ApiModelProperty(value="申请期限" ,required=true)
    private String term;

    @ApiModelProperty(value="省" ,required=true)
    private String province;

    @ApiModelProperty(value="市" ,required=true)
    private String city;

    @ApiModelProperty(value="县" ,required=true)
    private String county;

    @ApiModelProperty(value="详细地址" ,required=true)
    private String dtlAddress;

    @ApiModelProperty(value="姓名" ,required=true)
    private String unm;

    @ApiModelProperty(value="电话" ,required=true)
    private String mobile;

    @ApiModelProperty(value="所在单位" ,required=true)
    private String unit;

    @ApiModelProperty(value="营业执照" ,required=true)
    private String blcs;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSignImg() {
        return signImg;
    }

    public void setSignImg(String signImg) {
        this.signImg = signImg;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLight() {
        return light;
    }

    public void setLight(String light) {
        this.light = light;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getDtlAddress() {
        return dtlAddress;
    }

    public void setDtlAddress(String dtlAddress) {
        this.dtlAddress = dtlAddress;
    }

    public String getUnm() {
        return unm;
    }

    public void setUnm(String unm) {
        this.unm = unm;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getBlcs() {
        return blcs;
    }

    public void setBlcs(String blcs) {
        this.blcs = blcs;
    }
}
