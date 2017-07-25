package com.zhcs.service;

import com.zhcs.entity.SmstemplateEntity;

import java.util.List;
import java.util.Map;

//*****************************************************************************
/**
 * <p>Title:SmstemplateService</p>
 * <p>Description: 短信模板</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public interface SmstemplateService {
	
	SmstemplateEntity queryObject(Long id);
	
	List<SmstemplateEntity> queryList(Map<String, Object> map);

	List<Map<String, Object>> getSendTemplate();
	
	int queryTotal(Map<String, Object> map);
	
	void save(SmstemplateEntity smstemplate);
	
	void update(SmstemplateEntity smstemplate);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);

	Map<String, Object> queryPageList(Map<String, Object> map);
}
