package com.zhcs.service;

import com.zhcs.entity.CentpointEntity;

import java.util.List;
import java.util.Map;

//*****************************************************************************
/**
 * <p>Title:CentpointService</p>
 * <p>Description: 中心点设置</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public interface CentpointService {
	
	CentpointEntity queryObject(Long id);

	CentpointEntity queryObjectByProject(Long project);
	
	List<Map<String, Object>> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(CentpointEntity centpoint);
	
	void update(CentpointEntity centpoint);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);

}
