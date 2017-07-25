package com.zhcs.entity;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

//*****************************************************************************

/**
 * <p>Title:CostrecordEntity</p>
 * <p>Description: 费用记录</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 *
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public class CostrecordEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键id
    @ApiModelProperty(hidden = true)
    private Long id;
    @ApiModelProperty(value = "token", required = true)
    private String token;
    //申报日期
    @ApiModelProperty(value = "申报日期", required = true)
    private Date ddate;
    //费用类型
    @ApiModelProperty(value = "费用类型中的code", required = true)
    private String dtype;
    //金额
    @ApiModelProperty(value = "金额", required = true)
    private BigDecimal money;
    //申报人员
    @ApiModelProperty(value = "申报人员", required = true)
    private Long person;
    //费用说明
    @ApiModelProperty(value = "费用说明", required = true)
    private String rmk;
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
     * 设置：申报日期
     */
    public void setDdate(Date ddate) {
        this.ddate = ddate;
    }

    /**
     * 获取：申报日期
     */
    public Date getDdate() {
        return ddate;
    }

    /**
     * 设置：费用类型（预算明细项bditem中的代码）
     */
    public void setDtype(String dtype) {
        this.dtype = dtype;
    }

    /**
     * 获取：费用类型（预算明细项bditem中的代码）
     */
    public String getDtype() {
        return dtype;
    }

    /**
     * 设置：金额
     */
    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    /**
     * 获取：金额
     */
    public BigDecimal getMoney() {
        return money;
    }

    /**
     * 设置：申报人员
     */
    public void setPerson(Long person) {
        this.person = person;
    }

    /**
     * 获取：申报人员
     */
    public Long getPerson() {
        return person;
    }

    /**
     * 设置：费用说明
     */
    public void setRmk(String rmk) {
        this.rmk = rmk;
    }

    /**
     * 获取：费用说明
     */
    public String getRmk() {
        return rmk;
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
