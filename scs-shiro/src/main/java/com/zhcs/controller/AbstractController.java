package com.zhcs.controller;

import com.zhcs.entity.SysUserEntity;
import com.zhcs.utils.ShiroUtils;

//*****************************************************************************
/**
 * <p>Title:AbstractController</p>
 * <p>Description:Controller公共组件</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司</p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年5月26日
 */
//*****************************************************************************
public abstract class AbstractController {
	
	protected SysUserEntity getUser() {
		return ShiroUtils.getUserEntity();
	}

	protected Long getUserId() {
		return getUser().getId();
	}
}
