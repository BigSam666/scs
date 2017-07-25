package com.zhcs.service;

import com.zhcs.entity.MailtemplateEntity;

import java.util.List;
import java.util.Map;

//*****************************************************************************
/**
 * <p>Title:MailtemplateService</p>
 * <p>Description: 邮件模板</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public interface MailtemplateService {
	
	MailtemplateEntity queryObject(Long id);
	
	List<MailtemplateEntity> queryList(Map<String, Object> map);

	List<Map<String, Object>> getSendTemplate();
	
	int queryTotal(Map<String, Object> map);
	
	void save(MailtemplateEntity mailtemplate);
	
	void update(MailtemplateEntity mailtemplate);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
}
