package com.zhcs.dao;

import java.util.List;
import java.util.Map;

import com.zhcs.entity.CitizenEntity;

//*****************************************************************************
/**
 * <p>Title:CitizenDao</p>
 * <p>Description: 市民</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public interface CitizenDao extends BaseDao<CitizenEntity> {
	
	List<Map<String, Object>> getUserList();

	List<Map<String, Object>> getUserTree();

	CitizenEntity queryObjectByMobile(String mobile);
	
}
