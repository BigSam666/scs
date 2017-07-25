package com.zhcs.service;

import com.zhcs.entity.RepairEntity;

import java.util.List;
import java.util.Map;

//*****************************************************************************
/**
 * <p>Title:RepairService</p>
 * <p>Description: 维修管理</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public interface RepairService {
	
	RepairEntity queryObject(Long id);
	
	List<RepairEntity> queryList(Map<String, Object> map);
	
	List<RepairEntity> queryList1(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(RepairEntity repair);
	
	void update(RepairEntity repair);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
}
