package com.zhcs.service;

import com.zhcs.entity.MsgtemplateEntity;

import java.util.List;
import java.util.Map;

//*****************************************************************************
/**
 * <p>Title:MsgtemplateService</p>
 * <p>Description: 消息模板</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public interface MsgtemplateService {
	
	MsgtemplateEntity queryObject(Long id);
	
	List<MsgtemplateEntity> queryList(Map<String, Object> map);

	List<Map<String, Object>> getSendTemplate();
	
	int queryTotal(Map<String, Object> map);
	
	void save(MsgtemplateEntity msgtemplate);
	
	void update(MsgtemplateEntity msgtemplate);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
}
