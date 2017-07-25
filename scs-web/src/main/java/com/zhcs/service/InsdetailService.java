package com.zhcs.service;

import com.zhcs.entity.InsdetailEntity;

import java.util.List;
import java.util.Map;

//*****************************************************************************
/**
 * <p>Title:InsdetailService</p>
 * <p>Description: 投保明细</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public interface InsdetailService {
	
	InsdetailEntity queryObject(Long id);
	
	List<InsdetailEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(InsdetailEntity insdetail);
	
	void update(InsdetailEntity insdetail);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);

	/**
	 * 根据保险id查询保险明细
	 */
	List<InsdetailEntity> queryListByInsid(Long insid);
}
