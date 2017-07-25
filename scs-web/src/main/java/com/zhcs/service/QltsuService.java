package com.zhcs.service;

import com.zhcs.entity.QltsuEntity;

import java.util.List;
import java.util.Map;

//*****************************************************************************
/**
 * <p>Title:QltsuService</p>
 * <p>Description: 质量督导</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public interface QltsuService {
	
	QltsuEntity queryObject(Long id);
	
	List<QltsuEntity> queryList(Map<String, Object> map);
	
	List<QltsuEntity> queryList1(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(QltsuEntity qltsu);
	
	void update(QltsuEntity qltsu);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
}
