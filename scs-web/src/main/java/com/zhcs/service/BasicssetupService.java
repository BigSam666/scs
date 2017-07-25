package com.zhcs.service;

import com.zhcs.entity.BasicssetupEntity;

import java.util.List;
import java.util.Map;

//*****************************************************************************
/**
 * <p>Title:BasicssetupService</p>
 * <p>Description: 预警管理----基础设置
</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public interface BasicssetupService {
	
	BasicssetupEntity queryObject(Long id);
	
	List<BasicssetupEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(BasicssetupEntity basicssetup);
	
	void update(BasicssetupEntity basicssetup);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
}
