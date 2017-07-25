package com.zhcs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import com.zhcs.dao.AccidentDao;
import com.zhcs.entity.AccidentEntity;
import com.zhcs.service.AccidentService;
import com.zhcs.utils.DepartmentUtil;


//*****************************************************************************
/**
 * <p>Title:AccidentServiceImpl</p>
 * <p>Description: 事故管理</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************

@Service("accidentService")
public class AccidentServiceImpl implements AccidentService {
	@Autowired
	private AccidentDao accidentDao;
	@Autowired
	private DepartmentUtil departmentUtil;

	@Override
	public AccidentEntity queryObject(Long id){
		return accidentDao.queryObject(id);
	}

	@Override
	public List<AccidentEntity> queryList(Map<String, Object> map){
		return accidentDao.queryList(map);
	}
	
	@Override
	public List<AccidentEntity> queryList1(Map<String, Object> map){
		String ids = departmentUtil.queryNodeDeptChildIds();
		if (ids != null && !"".equals(ids)) {
			map.put("ids", ids);
		}
		return accidentDao.queryList1(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return accidentDao.queryTotal(map);
	}
	
	@Override
	@Transactional
	public void save(AccidentEntity accident){
		accidentDao.save(accident);
	}
	
	@Override
	@Transactional
	public void update(AccidentEntity accident){
		accidentDao.update(accident);
	}
	
	@Override
	@Transactional
	public void delete(Long id){
		accidentDao.delete(id);
	}
	
	@Override
	@Transactional
	public void deleteBatch(Long[] ids){
		accidentDao.deleteBatch(ids);
	}
	
}
