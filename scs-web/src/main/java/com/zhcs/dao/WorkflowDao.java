package com.zhcs.dao;

import java.util.Map;

import com.zhcs.entity.WorkflowEntity;

//*****************************************************************************
/**
 * <p>Title:WorkflowDao</p>
 * <p>Description: 工作流</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public interface WorkflowDao extends BaseDao<WorkflowEntity> {

	WorkflowEntity queryOtherNodeObject(Map<String, Object> map);
	
}
