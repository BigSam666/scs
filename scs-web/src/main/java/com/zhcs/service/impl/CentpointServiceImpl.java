package com.zhcs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import com.zhcs.dao.CentpointDao;
import com.zhcs.entity.CentpointEntity;
import com.zhcs.service.CentpointService;


//*****************************************************************************
/**
 * <p>Title:CentpointServiceImpl</p>
 * <p>Description: 中心点设置</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************

@Service("centpointService")
public class CentpointServiceImpl implements CentpointService {
	@Autowired
	private CentpointDao centpointDao;
	
	@Override
	public CentpointEntity queryObject(Long id){
		return centpointDao.queryObject(id);
	}
	
	@Override
	public List<Map<String, Object>> queryList(Map<String, Object> map){
		return centpointDao.queryList2(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return centpointDao.queryTotal(map);
	}
	
	@Override
	@Transactional
	public void save(CentpointEntity centpoint){
		centpointDao.save(centpoint);
	}
	
	@Override
	@Transactional
	public void update(CentpointEntity centpoint){
		centpointDao.update(centpoint);
	}
	
	@Override
	@Transactional
	public void delete(Long id){
		centpointDao.delete(id);
	}
	
	@Override
	@Transactional
	public void deleteBatch(Long[] ids){
		centpointDao.deleteBatch(ids);
	}

	@Override
	public CentpointEntity queryObjectByProject(Long project) {
		return centpointDao.queryObjectByProject(project);
	}
	
}
