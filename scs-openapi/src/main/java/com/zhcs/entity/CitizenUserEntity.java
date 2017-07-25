package com.zhcs.entity;

import io.swagger.annotations.ApiModelProperty;

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
public class CitizenUserEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value="code" ,required=true)
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
