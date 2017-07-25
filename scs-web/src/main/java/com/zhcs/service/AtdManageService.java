package com.zhcs.service;

import com.zhcs.entity.AtdManageEntity;
import com.zhcs.utils.ServiceException;

import java.util.List;
import java.util.Map;

//*****************************************************************************
/**
 * <p>Title:AtdManageService</p>
 * <p>Description: 考勤管理申报</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public interface AtdManageService {
	
	AtdManageEntity queryObject(Long id);
	
	List<AtdManageEntity> queryList(Map<String, Object> map);
	
	List<AtdManageEntity> queryList1(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(AtdManageEntity atdManage) throws ServiceException;
	
	void update(AtdManageEntity atdManage);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
}
