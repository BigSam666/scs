package com.zhcs.dao;

import java.util.List;
import java.util.Map;

import com.zhcs.entity.CmngroupEntity;

//*****************************************************************************
/**
 * <p>Title:CmngroupDao</p>
 * <p>Description: 通讯组</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public interface CmngroupDao extends BaseDao<CmngroupEntity> {
	
	
	List<Map<String, Object>> findList(Map<String, Object> map);
	
	int findListCount(Map<String, Object> map);
	
}
