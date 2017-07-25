package com.zhcs.service;

import com.zhcs.entity.PartyEntity;

import java.util.List;
import java.util.Map;

//*****************************************************************************
/**
 * <p>Title:PartyService</p>
 * <p>Description: 甲方</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public interface PartyService {
	
	PartyEntity queryObject(Long id);
	
	List<PartyEntity> queryList(Map<String, Object> map);

	List<Map<String, Object>> getPartyTree();
	
	int queryTotal(Map<String, Object> map);
	
	void save(PartyEntity party);
	
	void update(PartyEntity party);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
}
