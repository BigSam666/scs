package com.zhcs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zhcs.dao.CmngroupDao;
import com.zhcs.entity.CmngroupEntity;
import com.zhcs.service.CmngroupService;


//*****************************************************************************
/**
 * <p>Title:CmngroupServiceImpl</p>
 * <p>Description: 通讯组</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************

@Service("cmngroupService")
public class CmngroupServiceImpl implements CmngroupService {
	@Autowired
	private CmngroupDao cmngroupDao;
	
	@Override
	public CmngroupEntity queryObject(Long id){
		return cmngroupDao.queryObject(id);
	}
	
	@Override
	public List<CmngroupEntity> queryList(Map<String, Object> map){
		return cmngroupDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return cmngroupDao.queryTotal(map);
	}
	
	@Override
	@Transactional
	public void save(CmngroupEntity cmngroup){
		cmngroupDao.save(cmngroup);
	}
	
	@Override
	@Transactional
	public void update(CmngroupEntity cmngroup){
		cmngroupDao.update(cmngroup);
	}
	
	@Override
	@Transactional
	public void delete(Long id){
		cmngroupDao.delete(id);
	}
	
	@Override
	@Transactional
	public void deleteBatch(Long[] ids){
		cmngroupDao.deleteBatch(ids);
	}

	@Override
	public Map<String, Object> queryPageList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		Map<String, Object> resultMap = new HashMap<>();
		
		List<Map<String, Object>> datalist =  cmngroupDao.findList(map);
		int total = cmngroupDao.findListCount(map);
		
		resultMap.put("datalist", datalist);
		resultMap.put("total", total);
		return resultMap;
	}
	
}
