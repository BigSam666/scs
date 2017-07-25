package com.zhcs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import com.zhcs.dao.RedetailDao;
import com.zhcs.dao.RepairDao;
import com.zhcs.entity.RedetailEntity;
import com.zhcs.entity.RepairEntity;
import com.zhcs.service.RepairService;
import com.zhcs.utils.BeanUtil;
import com.zhcs.utils.DepartmentUtil;


//*****************************************************************************
/**
 * <p>Title:RepairServiceImpl</p>
 * <p>Description: 维修管理</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************

@Service("repairService")
public class RepairServiceImpl implements RepairService {
	@Autowired
	private RepairDao repairDao;
	@Autowired
	private RedetailDao redetailDao;
	@Autowired
	private DepartmentUtil departmentUtil;
	
	@Override
	public RepairEntity queryObject(Long id){
		return repairDao.queryObject(id);
	}
	
	@Override
	public List<RepairEntity> queryList(Map<String, Object> map){
		return repairDao.queryList(map);
	}
	
	@Override
	public List<RepairEntity> queryList1(Map<String, Object> map){
		String ids = departmentUtil.queryNodeDeptChildIds();
		if (ids != null && !"".equals(ids)) {
			map.put("ids", ids);
		}
		return repairDao.queryList1(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return repairDao.queryTotal(map);
	}
	
	@Override
	@Transactional
	public void save(RepairEntity repair){
		repairDao.save(repair);
		for (RedetailEntity i : repair.getRedetails()) {
			i.setReid(repair.getId());
			BeanUtil.fillCCUUD(i, repair.getCrtid(), repair.getCrtid());
		}
		redetailDao.saveBatch(repair.getRedetails());
		System.out.println();
	}
	
	@Override
	@Transactional
	public void update(RepairEntity repair){
		repairDao.update(repair);
		// 先删除维修明细中的数据，然后重新添加
		for (RedetailEntity i : repair.getRedetails()) {
			i.setReid(repair.getId());
			BeanUtil.fillCCUUD(i, repair.getUpdid(), repair.getUpdid());
		}
		redetailDao.deleteByReid(repair.getId());
		redetailDao.saveBatch(repair.getRedetails());
		
	}
	
	@Override
	@Transactional
	public void delete(Long id){
		repairDao.delete(id);
		redetailDao.deleteByReid(id); // 删除维修明细中的数据
	}
	
	@Override
	@Transactional
	public void deleteBatch(Long[] ids){
		repairDao.deleteBatch(ids);
	}
	
}
