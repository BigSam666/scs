package com.zhcs.dao;

import java.util.List;
import java.util.Map;

import com.zhcs.entity.GridmngEntity;

//*****************************************************************************
/**
 * <p>Title:GridmngDao</p>
 * <p>Description: 网格管理</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public interface GridmngDao extends BaseDao<GridmngEntity> {

	Map<String, Object> queryObject2(Long id);

	List<Map<String, Object>> queryList2(Map<String, Object> map);
	
}
