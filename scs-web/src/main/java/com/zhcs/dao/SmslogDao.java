package com.zhcs.dao;

import java.util.List;
import java.util.Map;

import com.zhcs.entity.SmslogEntity;

//*****************************************************************************
/**
 * <p>Title:SmslogDao</p>
 * <p>Description: 短信记录</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public interface SmslogDao extends BaseDao<SmslogEntity> {

	List<Map<String, Object>> queryList2(Map<String, Object> map);

	Map<String, Object> queryObject2(Long id);
	
}
