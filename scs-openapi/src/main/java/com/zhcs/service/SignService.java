package com.zhcs.service;

import com.zhcs.entity.SignEntity;

import java.util.List;
import java.util.Map;

//*****************************************************************************
/**
 * <p>Title:SignService</p>
 * <p>Description: 招牌申请</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public interface SignService {
	
	SignEntity queryObject(Long id);
	
	List<SignEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(SignEntity sign);
	
	void update(SignEntity sign);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
}
