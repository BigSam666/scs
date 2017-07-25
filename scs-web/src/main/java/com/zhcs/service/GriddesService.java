package com.zhcs.service;

import java.util.List;
import java.util.Map;

import com.zhcs.entity.GriddesEntity;

//*****************************************************************************
/**
 * <p>Title:GriddesService</p>
 * <p>Description: 网格管理详情</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public interface GriddesService {
	
	GriddesEntity queryObject(Long id);
	
	List<GriddesEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(GriddesEntity griddes);

	void saveBatch(List<GriddesEntity> griddes);
	
	void update(GriddesEntity griddes);
	
	void deleteByFid(Long fid);

	void delete(Long id);
	
	void deleteBatch(Long[] ids);
}
