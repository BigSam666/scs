package com.zhcs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zhcs.dao.BaseCodeDao;
import com.zhcs.entity.BaseCodeEntity;
import com.zhcs.service.BaseCodeService;


//*****************************************************************************
/**
 * <p>Title:BaseCdeServiceImpl</p>
 * <p>Description: 基础代码 (T_BASE_CDE)</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************

@Service("baseCdeService")
public class BaseCodeServiceImpl implements BaseCodeService {
	@Autowired
	private BaseCodeDao BaseCodeDao;
	
	@Override
	public BaseCodeEntity queryObject(Long id){
		return BaseCodeDao.queryObject(id);
	}
	
	@Override
	public List<BaseCodeEntity> queryList(Map<String, Object> map){
		return BaseCodeDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return BaseCodeDao.queryTotal(map);
	}
	
	@Override
	public void save(BaseCodeEntity baseCde){
		BaseCodeDao.save(baseCde);
	}
	
	@Override
	public void update(BaseCodeEntity baseCde){
		BaseCodeDao.update(baseCde);
	}
	
	@Override
	public void delete(Long id){
		BaseCodeDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		BaseCodeDao.deleteBatch(ids);
	}

	@Override
	public List<BaseCodeEntity> selectByType(String type) {
		return BaseCodeDao.selectByType(type);
	}
	
	@Override
	public BaseCodeEntity selectByTypeValue(String type, String no) {
		Map<String, String> m = new HashMap<String, String>();
		m.put("type", type);
		m.put("no", no);
		return BaseCodeDao.selectByTypeValue(m);
	}


	public Map<String, List<String>> getCnmNoMapByType(String type) {
		List<BaseCodeEntity> scoretypes = selectByType(type);
		Map<String, List<String>> ret = new HashMap<>();
		ret.put("keys", new ArrayList<String>());
		ret.put("values", new ArrayList<String>());
		for (BaseCodeEntity scoretype : scoretypes) {
			ret.get("keys").add(scoretype.getCnm());
			ret.get("values").add(scoretype.getNo());
		}
		return ret;
	}
	
}
