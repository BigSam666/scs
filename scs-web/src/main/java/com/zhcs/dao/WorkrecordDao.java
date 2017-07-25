package com.zhcs.dao;

import java.util.List;
import java.util.Map;

import com.zhcs.entity.WorkrecordEntity;

//*****************************************************************************
/**
 * <p>Title:WorkrecordDao</p>
 * <p>Description: 工作记录</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public interface WorkrecordDao extends BaseDao<WorkrecordEntity> {
	
	List<Map<String, Object>> queryList2(Map<String, Object> map);
	
}
