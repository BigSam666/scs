package com.zhcs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import com.zhcs.dao.GriddesDao;
import com.zhcs.entity.GriddesEntity;
import com.zhcs.service.GriddesService;


//*****************************************************************************
/**
 * <p>Title:GriddesServiceImpl</p>
 * <p>Description: 网格管理详情</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************

@Service("griddesService")
public class GriddesServiceImpl implements GriddesService {
	@Autowired
	private GriddesDao griddesDao;
	
	@Override
	public GriddesEntity queryObject(Long id){
		return griddesDao.queryObject(id);
	}
	
	@Override
	public List<GriddesEntity> queryList(Map<String, Object> map){
		return griddesDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return griddesDao.queryTotal(map);
	}
	
	@Override
	@Transactional
	public void save(GriddesEntity griddes){
		griddesDao.save(griddes);
	}
	
	@Override
	@Transactional
	public void update(GriddesEntity griddes){
		griddesDao.update(griddes);
	}
	
	@Override
	@Transactional
	public void deleteByFid(Long id){
		griddesDao.deleteByFid(id);
	}
	
	@Override
	@Transactional
	public void delete(Long id){
		griddesDao.delete(id);
	}
	
	@Override
	@Transactional
	public void deleteBatch(Long[] ids){
		griddesDao.deleteBatch(ids);
	}

	@Override
	@Transactional
	public void saveBatch(List<GriddesEntity> griddes) {
		griddesDao.saveBatch(griddes);
	}
	
}
