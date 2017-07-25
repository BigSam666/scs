package com.zhcs.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhcs.dao.MaintainDao;
import com.zhcs.dao.MtdetailDao;
import com.zhcs.entity.MaintainEntity;
import com.zhcs.entity.MtdetailEntity;
import com.zhcs.service.MaintainService;
import com.zhcs.utils.BeanUtil;
import com.zhcs.utils.DepartmentUtil;


//*****************************************************************************
/**
 * <p>Title:MaintainServiceImpl</p>
 * <p>Description: 保养管理</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************

@Service("maintainService")
public class MaintainServiceImpl implements MaintainService {
	@Autowired
	private MaintainDao maintainDao;
	@Autowired
	private MtdetailDao mtdetailDao;
	@Autowired
	private DepartmentUtil departmentUtil;
	
	@Override
	public MaintainEntity queryObject(Long id){
		return maintainDao.queryObject(id);
	}
	
	@Override
	public List<MaintainEntity> queryList(Map<String, Object> map){
		return maintainDao.queryList(map);
	}
	
	@Override
	public List<MaintainEntity> queryList1(Map<String, Object> map){
		String ids = departmentUtil.queryNodeDeptChildIds();
		if (ids != null && !"".equals(ids)) {
			map.put("ids", ids);
		}
		return maintainDao.queryList1(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return maintainDao.queryTotal(map);
	}
	
	@Override
	@Transactional
	public void save(MaintainEntity maintain){
		maintainDao.save(maintain);
		for (MtdetailEntity i : maintain.getMtdetails()) {
			i.setMtid(maintain.getId());
			BeanUtil.fillCCUUD(i, maintain.getCrtid(), maintain.getCrtid());
		}
		mtdetailDao.saveBatch(maintain.getMtdetails());
	}
	
	@Override
	@Transactional
	public void update(MaintainEntity maintain){
		maintainDao.update(maintain);
		// 先删除投保明细中的数据，然后重新添加
		for (MtdetailEntity i : maintain.getMtdetails()) {
			i.setMtid(maintain.getId());
			BeanUtil.fillCCUUD(i, maintain.getUpdid(), maintain.getUpdid());
		}
		mtdetailDao.deleteByMtid(maintain.getId());
		mtdetailDao.saveBatch(maintain.getMtdetails());
	}
	
	@Override
	@Transactional
	public void delete(Long id){
		maintainDao.delete(id);
	}
	
	@Override
	@Transactional
	public void deleteBatch(Long[] ids){
		maintainDao.deleteBatch(ids);
	}
	
}
