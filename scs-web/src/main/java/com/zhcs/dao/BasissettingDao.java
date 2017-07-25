package com.zhcs.dao;

import com.zhcs.entity.BasissettingEntity;

//*****************************************************************************
/**
 * <p>Title:BasissettingDao</p>
 * <p>Description: 基础设置</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public interface BasissettingDao extends BaseDao<BasissettingEntity> {
	String queryId();
}
