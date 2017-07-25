package com.zhcs.service;

import com.zhcs.entity.MtdetailEntity;

import java.util.List;
import java.util.Map;

//*****************************************************************************
/**
 * <p>Title:MtdetailService</p>
 * <p>Description: 保养明细</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public interface MtdetailService {
	
	MtdetailEntity queryObject(Long id);
	
	List<MtdetailEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(MtdetailEntity mtdetail);
	
	void update(MtdetailEntity mtdetail);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);

	/**
	 * 根据保养id查询保养明细
	 */
	List<MtdetailEntity> queryListByMtid(Long mtid);
}
