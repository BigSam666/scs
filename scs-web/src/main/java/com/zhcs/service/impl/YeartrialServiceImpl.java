package com.zhcs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import com.zhcs.dao.YeartrialDao;
import com.zhcs.entity.YeartrialEntity;
import com.zhcs.service.YeartrialService;
import com.zhcs.utils.DepartmentUtil;


//*****************************************************************************
/**
 * <p>Title:YeartrialServiceImpl</p>
 * <p>Description: 年审管理</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************

@Service("yeartrialService")
public class YeartrialServiceImpl implements YeartrialService {
	@Autowired
	private YeartrialDao yeartrialDao;
	@Autowired
	private DepartmentUtil departmentUtil;
	
	@Override
	public YeartrialEntity queryObject(Long id){
		return yeartrialDao.queryObject(id);
	}
	
	@Override
	public List<YeartrialEntity> queryList(Map<String, Object> map){
		return yeartrialDao.queryList(map);
	}
	
	@Override
	public List<YeartrialEntity> queryList1(Map<String, Object> map){
		String ids = departmentUtil.queryNodeDeptChildIds();
		if (ids != null && !"".equals(ids)) {
			map.put("ids", ids);
		}
		return yeartrialDao.queryList1(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return yeartrialDao.queryTotal(map);
	}
	
	@Override
	@Transactional
	public void save(YeartrialEntity yeartrial){
		yeartrialDao.save(yeartrial);
	}
	
	@Override
	@Transactional
	public void update(YeartrialEntity yeartrial){
		yeartrialDao.update(yeartrial);
	}
	
	@Override
	@Transactional
	public void delete(Long id){
		yeartrialDao.delete(id);
	}
	
	@Override
	@Transactional
	public void deleteBatch(Long[] ids){
		yeartrialDao.deleteBatch(ids);
	}
	
}
