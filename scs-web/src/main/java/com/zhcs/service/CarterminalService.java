package com.zhcs.service;

import com.zhcs.entity.CarterminalEntity;

import java.util.List;
import java.util.Map;

//*****************************************************************************
/**
 * <p>Title:CarterminalService</p>
 * <p>Description: 车载终端</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public interface CarterminalService {
	
	CarterminalEntity queryObject(Long id);
	
	List<CarterminalEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(CarterminalEntity carterminal);
	
	void update(CarterminalEntity carterminal);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
}
