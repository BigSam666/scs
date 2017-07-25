package com.zhcs.service;

import com.zhcs.entity.RedetailEntity;

import java.util.List;
import java.util.Map;

//*****************************************************************************
/**
 * <p>Title:RedetailService</p>
 * <p>Description: 维修明细</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public interface RedetailService {
	
	RedetailEntity queryObject(Long id);
	
	List<RedetailEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(RedetailEntity redetail);
	
	void update(RedetailEntity redetail);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);

	/**
	 * 根据维修id查询维修明细
	 */
	List<RedetailEntity> queryListByReid(Long reid);
}
