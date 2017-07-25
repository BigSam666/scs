package com.zhcs.dao;

import java.util.List;
import java.util.Map;

import com.zhcs.entity.BrknewsEntity;

//*****************************************************************************
/**
 * <p>Title:BrknewsDao</p>
 * <p>Description: 爆料事件</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public interface BrknewsDao extends BaseDao<BrknewsEntity> {
	
	BrknewsEntity queryObjectByNo(String no);

	/**
	 * 根据爆料人查询
	 */
	List<BrknewsEntity> queryListByPerson(Map<String, Object> map);

	/**
	 * 根据爆料人查询爆料数量
	 */
	int queryCountByPerson(Map<String, Object> map);

	List<Map<String, Object>> queryList2(Map<String, Object> map);
	
}
