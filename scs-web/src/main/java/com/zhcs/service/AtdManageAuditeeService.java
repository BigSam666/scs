package com.zhcs.service;

import com.zhcs.entity.AtdManageAuditeeEntity;

import java.util.List;
import java.util.Map;

//*****************************************************************************
/**
 * <p>Title:AtdManageAuditeeService</p>
 * <p>Description: 考勤管理审核</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public interface AtdManageAuditeeService {
	
	AtdManageAuditeeEntity queryObject(Long id);
	
	List<AtdManageAuditeeEntity> queryList(Map<String, Object> map);
	
	List<AtdManageAuditeeEntity> queryList1(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(AtdManageAuditeeEntity atdManageAuditee);
	
	void update(AtdManageAuditeeEntity atdManageAuditee);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
}
