package com.zhcs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import com.zhcs.dao.FleettDao;
import com.zhcs.entity.FleettEntity;
import com.zhcs.service.FleettService;
import com.zhcs.utils.DepartmentUtil;


//*****************************************************************************
/**
 * <p>Title:FleettServiceImpl</p>
 * <p>Description: 车队管理</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************

@Service("fleettService")
public class FleettServiceImpl implements FleettService {
	@Autowired
	private FleettDao fleettDao;
	@Autowired
	private DepartmentUtil departmentUtil;
	
	@Override
	public FleettEntity queryObject(Long id){
		return fleettDao.queryObject(id);
	}
	
	@Override
	public List<FleettEntity> queryList(Map<String, Object> map){
		return fleettDao.queryList(map);
	}
	
	@Override
	public List<FleettEntity> queryList1(Map<String, Object> map){
		String ids = departmentUtil.queryNodeDeptChildIds();
		map.put("ids", ids);
		return fleettDao.queryList1(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return fleettDao.queryTotal(map);
	}
	
	@Override
	@Transactional
	public void save(FleettEntity fleett){
		fleettDao.save(fleett);
	}
	
	@Override
	@Transactional
	public void update(FleettEntity fleett){
		fleettDao.update(fleett);
	}
	
	@Override
	@Transactional
	public void delete(Long id){
		fleettDao.delete(id);
	}
	
	@Override
	@Transactional
	public void deleteBatch(Long[] ids){
		fleettDao.deleteBatch(ids);
	}
	
}
