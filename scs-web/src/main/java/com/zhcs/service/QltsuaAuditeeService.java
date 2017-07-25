package com.zhcs.service;

import com.zhcs.entity.QltsuaAuditeeEntity;

import java.util.List;
import java.util.Map;

//*****************************************************************************
/**
 * <p>Title:QltsuaAuditeeService</p>
 * <p>Description: 质量督导审核方</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public interface QltsuaAuditeeService {
	
	QltsuaAuditeeEntity queryObject(Long id);
	
	List<QltsuaAuditeeEntity> queryList(Map<String, Object> map);
	
	List<QltsuaAuditeeEntity> queryList1(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(QltsuaAuditeeEntity qltsuaAuditee);
	
	void update(QltsuaAuditeeEntity qltsuaAuditee);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
}
