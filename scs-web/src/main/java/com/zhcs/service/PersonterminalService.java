package com.zhcs.service;

import com.zhcs.entity.PersonterminalEntity;

import java.util.List;
import java.util.Map;

//*****************************************************************************
/**
 * <p>Title:PersonterminalService</p>
 * <p>Description: 人员终端</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public interface PersonterminalService {
	
	PersonterminalEntity queryObject(Long id);
	
	List<PersonterminalEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(PersonterminalEntity personterminal);
	
	void update(PersonterminalEntity personterminal);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
}
