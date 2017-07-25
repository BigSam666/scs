package com.zhcs.service;

import com.zhcs.entity.CitizenEntity;

import java.util.List;
import java.util.Map;

//*****************************************************************************
/**
 * <p>Title:CitizenService</p>
 * <p>Description: 市民</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public interface CitizenService {
	
	CitizenEntity queryObject(Long id);

	CitizenEntity queryObjectByMobile(String mobile);
	
	List<CitizenEntity> queryList(Map<String, Object> map);

	List<Map<String, Object>> getUserList();

	List<Map<String, Object>> getUserTree();
	
	int queryTotal(Map<String, Object> map);
	
	void save(CitizenEntity citizen);
	
	void update(CitizenEntity citizen);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
}
