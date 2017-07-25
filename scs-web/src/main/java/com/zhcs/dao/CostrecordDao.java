package com.zhcs.dao;

import java.util.List;
import java.util.Map;

import com.zhcs.entity.CostrecordEntity;

//*****************************************************************************
/**
 * <p>Title:CostrecordDao</p>
 * <p>Description: 费用记录</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public interface CostrecordDao extends BaseDao<CostrecordEntity> {

	/**
	 * 同queryList，不过返回的结果不是实体类，而是Map
	 */
	List<Map<String, Object>> queryListMap(Map<String, Object> map);
	
}
