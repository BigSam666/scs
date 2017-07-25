package com.zhcs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import com.zhcs.dao.IllegaDao;
import com.zhcs.entity.IllegaEntity;
import com.zhcs.service.IllegaService;
import com.zhcs.utils.DepartmentUtil;


//*****************************************************************************
/**
 * <p>Title:IllegaServiceImpl</p>
 * <p>Description: 违章管理</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************

@Service("illegaService")
public class IllegaServiceImpl implements IllegaService {
	@Autowired
	private IllegaDao illegaDao;
	@Autowired
	private DepartmentUtil departmentUtil;
	
	@Override
	public IllegaEntity queryObject(Long id){
		return illegaDao.queryObject(id);
	}
	
	@Override
	public List<IllegaEntity> queryList(Map<String, Object> map){
		return illegaDao.queryList(map);
	}
	
	@Override
	public List<IllegaEntity> queryList1(Map<String, Object> map){
		String ids = departmentUtil.queryNodeDeptChildIds();
		if (ids != null && !"".equals(ids)) {
			map.put("ids", ids);
		}
		return illegaDao.queryList1(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return illegaDao.queryTotal(map);
	}
	
	@Override
	@Transactional
	public void save(IllegaEntity illega){
		illegaDao.save(illega);
	}
	
	@Override
	@Transactional
	public void update(IllegaEntity illega){
		illegaDao.update(illega);
	}
	
	@Override
	@Transactional
	public void delete(Long id){
		illegaDao.delete(id);
	}
	
	@Override
	@Transactional
	public void deleteBatch(Long[] ids){
		illegaDao.deleteBatch(ids);
	}
	
}
