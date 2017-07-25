package com.zhcs.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhcs.dao.InsdetailDao;
import com.zhcs.dao.InsureDao;
import com.zhcs.entity.InsdetailEntity;
import com.zhcs.entity.InsureEntity;
import com.zhcs.service.InsureService;
import com.zhcs.utils.BeanUtil;
import com.zhcs.utils.DepartmentUtil;

//*****************************************************************************
/**
 * <p>
 * Title:InsureServiceImpl
 * </p>
 * <p>
 * Description: 保险管理
 * </p>
 * <p>
 * Copyright: Copyright (c) 2017
 * </p>
 * <p>
 * Company: 深圳市智慧城市管家信息科技有限公司
 * </p>
 * 
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
// *****************************************************************************

@Service("insureService")
public class InsureServiceImpl implements InsureService {
	@Autowired
	private InsureDao insureDao;
	@Autowired
	private InsdetailDao insdetailDao;
	@Autowired
	private DepartmentUtil departmentUtil;

	@Override
	public InsureEntity queryObject(Long id) {
		return insureDao.queryObject(id);
	}

	@Override
	public List<InsureEntity> queryList(Map<String, Object> map) {
		return insureDao.queryList(map);
	}
	
	@Override
	public List<InsureEntity> queryList1(Map<String, Object> map) {
		String ids = departmentUtil.queryNodeDeptChildIds();
		if (ids != null && !"".equals(ids)) {
			map.put("ids", ids);
		}
		return insureDao.queryList1(map);
	}

	@Override
	public int queryTotal(Map<String, Object> map) {
		return insureDao.queryTotal(map);
	}

	@Override
	@Transactional
	public void save(InsureEntity insure) {
		insureDao.save(insure);
		for (InsdetailEntity i : insure.getInsdetails()) {
			i.setInsid(insure.getId());
			BeanUtil.fillCCUUD(i, insure.getCrtid(), insure.getCrtid());
		}
		insdetailDao.saveBatch(insure.getInsdetails());
	}

	@Override
	@Transactional
	public void update(InsureEntity insure) {
		insureDao.update(insure);
		
		// 先删除投保明细中的数据，然后重新添加
		for (InsdetailEntity i : insure.getInsdetails()) {
			i.setInsid(insure.getId());
			BeanUtil.fillCCUUD(i, insure.getUpdid(), insure.getUpdid());
		}
		insdetailDao.deleteByInsid(insure.getId());
		insdetailDao.saveBatch(insure.getInsdetails());
	}

	@Override
	@Transactional
	public void delete(Long id) {
		insureDao.delete(id);
		insdetailDao.deleteByInsid(id); // 删除投保明细中的数据
	}

	@Override
	@Transactional
	public void deleteBatch(Long[] ids) {
		insureDao.deleteBatch(ids);
	}

}
