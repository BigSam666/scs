package com.zhcs.dao;

import java.util.List;
import java.util.Map;

import com.zhcs.entity.PositionEntity;

//*****************************************************************************
/**
 * <p>Title:PositionDao</p>
 * <p>Description: 定位信息</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public interface PositionDao extends BaseDao<PositionEntity> {
	

	List<Map<String, Object>> getUserMsgByPosition(Map<String, Object> map);

	int getUserMsgByPositionTotal(Map<String, Object> map);
	
}
