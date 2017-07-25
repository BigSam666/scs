package com.zhcs.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.zhcs.dao.AreaDao;
import com.zhcs.entity.AreaEntity;
import com.zhcs.service.AreaService;


//*****************************************************************************
/**
 * <p>Title:AreaServiceImpl</p>
 * <p>Description: 省市数据 (T_AREA)</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************

@Service("areaService")
public class AreaServiceImpl implements AreaService {
	@Autowired
	private AreaDao areaDao;
	
	@Override
	public AreaEntity queryObject(Long id){
		return areaDao.queryObject(id);
	}
	
	@Override
	public List<AreaEntity> queryList(Map<String, Object> map){
		return areaDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return areaDao.queryTotal(map);
	}
	
	@Override
	public void save(AreaEntity area){
		areaDao.save(area);
	}
	
	@Override
	public void update(AreaEntity area){
		areaDao.update(area);
	}
	
	@Override
	public void delete(Long id){
		areaDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		areaDao.deleteBatch(ids);
	}
	
	@Override
	@Cacheable(value="menuCache")  
	public List<Map<String, Object>> queryAreaListByNo(String no) {
		return areaDao.queryAreaListByNo(no);
	}
	
}
