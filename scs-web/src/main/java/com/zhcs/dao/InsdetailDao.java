package com.zhcs.dao;

import java.util.List;

import com.zhcs.entity.InsdetailEntity;

//*****************************************************************************
/**
 * <p>Title:InsdetailDao</p>
 * <p>Description: 投保明细</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public interface InsdetailDao extends BaseDao<InsdetailEntity> {

	/**
	 * 根据保险id查询保险明细
	 */
	List<InsdetailEntity> queryListByInsid(Long insid);

	/**
	 * 根据保险id删除投保明细中的记录
	 */
	void deleteByInsid(Long id);
	
}
