package com.zhcs.dao;

import com.zhcs.entity.CallPopEntity;

//*****************************************************************************
/**
 * <p>Title:CallJobselDao</p>
 * <p>Description: 工单查询</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public interface CallPopDao extends BaseDao<CallPopEntity> {

	int queryCallNumber(Long id);

	String queryName(String phone);
	
}
