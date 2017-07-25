package com.zhcs.service;

import com.zhcs.entity.BrknewsEntity;

import java.util.List;
import java.util.Map;

//*****************************************************************************
/**
 * <p>Title:BrknewsService</p>
 * <p>Description: 爆料事件</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public interface BrknewsService {
	
	BrknewsEntity queryObject(Long id);

	BrknewsEntity queryObjectByNo(String no);
	
	List<BrknewsEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(BrknewsEntity brknews);
	
	void update(BrknewsEntity brknews);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);

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
