package com.zhcs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import com.zhcs.dao.GasDao;
import com.zhcs.entity.GasEntity;
import com.zhcs.service.GasService;
import com.zhcs.utils.DepartmentUtil;


//*****************************************************************************
/**
 * <p>Title:GasServiceImpl</p>
 * <p>Description: 油耗管理</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************

@Service("gasService")
public class GasServiceImpl implements GasService {
	@Autowired
	private GasDao gasDao;
	@Autowired
	private DepartmentUtil departmentUtil;
	
	@Override
	public GasEntity queryObject(Long id){
		return gasDao.queryObject(id);
	}
	
	@Override
	public List<GasEntity> queryList(Map<String, Object> map){
		return gasDao.queryList(map);
	}
	
	@Override
	public List<GasEntity> queryList1(Map<String, Object> map){
		String ids = departmentUtil.queryNodeDeptChildIds();
		if (ids != null && !"".equals(ids)) {
			map.put("ids", ids);
		}
		return gasDao.queryList1(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return gasDao.queryTotal(map);
	}
	
	@Override
	@Transactional
	public void save(GasEntity gas){
		gasDao.save(gas);
	}
	
	@Override
	@Transactional
	public void update(GasEntity gas){
		gasDao.update(gas);
	}
	
	@Override
	@Transactional
	public void delete(Long id){
		gasDao.delete(id);
	}
	
	@Override
	@Transactional
	public void deleteBatch(Long[] ids){
		gasDao.deleteBatch(ids);
	}
	
}
