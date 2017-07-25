package com.zhcs.dao;

import java.util.Map;

import com.zhcs.entity.ScheduleJobEntity;

//*****************************************************************************
/**
 * <p>Title:ScheduleJobDao</p>
 * <p>Description:定时任务</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司</p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年5月26日
 */
//*****************************************************************************
public interface ScheduleJobDao extends BaseDao<ScheduleJobEntity> {
	
	/**
	 * 批量更新状态
	 */
	int updateBatch(Map<String, Object> map);
}
