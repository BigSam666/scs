package com.zhcs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import com.zhcs.dao.GridmngDao;
import com.zhcs.entity.GridmngEntity;
import com.zhcs.service.GridmngService;


//*****************************************************************************
/**
 * <p>Title:GridmngServiceImpl</p>
 * <p>Description: 网格管理</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************

@Service("gridmngService")
public class GridmngServiceImpl implements GridmngService {
	@Autowired
	private GridmngDao gridmngDao;
	
	@Override
	public Map<String, Object> queryObject(Long id){
		return gridmngDao.queryObject2(id);
	}
	
	@Override
	public List<Map<String, Object>> queryList(Map<String, Object> map){
		return gridmngDao.queryList2(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return gridmngDao.queryTotal(map);
	}
	
	@Override
	@Transactional
	public void save(GridmngEntity gridmng){
		gridmngDao.save(gridmng);
	}
	
	@Override
	@Transactional
	public void update(GridmngEntity gridmng){
		gridmngDao.update(gridmng);
	}
	
	@Override
	@Transactional
	public void delete(Long id){
		gridmngDao.delete(id);
	}
	
	@Override
	@Transactional
	public void deleteBatch(Long[] ids){
		gridmngDao.deleteBatch(ids);
	}
	
}
