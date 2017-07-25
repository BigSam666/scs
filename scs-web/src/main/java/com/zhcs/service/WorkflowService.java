package com.zhcs.service;

import com.zhcs.entity.WorkflowEntity;

import java.util.List;
import java.util.Map;

//*****************************************************************************
/**
 * <p>Title:WorkflowService</p>
 * <p>Description: 工作流</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public interface WorkflowService {
	
	WorkflowEntity queryObject(Long id);
	
	WorkflowEntity queryOtherNodeObject(String nodeno, String flag);
	
	List<WorkflowEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(WorkflowEntity workflow);
	
	void update(WorkflowEntity workflow);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
}
