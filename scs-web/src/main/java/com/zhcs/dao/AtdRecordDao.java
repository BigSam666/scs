package com.zhcs.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zhcs.entity.AtdRecordEntity;

//*****************************************************************************
/**
 * <p>Title:AtdRecordDao</p>
 * <p>Description: 考勤记录</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public interface AtdRecordDao extends BaseDao<AtdRecordEntity> {
	
	
	List<Map<String, Object>> queryOperatorRecordDatalist(Map<String, Object> map);
	int queryOperatorRecordDatalistCount(Map<String, Object> map);
	
}
