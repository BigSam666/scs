package com.zhcs.dao;

import java.util.List;

import com.zhcs.entity.RedetailEntity;

//*****************************************************************************
/**
 * <p>Title:RedetailDao</p>
 * <p>Description: 维修明细</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public interface RedetailDao extends BaseDao<RedetailEntity> {
	/**
	 * 根据维修id删除维修明细中的记录
	 */
	void deleteByReid(Long id);
	
	
	/**
	 * 根据维修id查询维修明细
	 */
	List<RedetailEntity> queryListByReid(Long reid);

}
