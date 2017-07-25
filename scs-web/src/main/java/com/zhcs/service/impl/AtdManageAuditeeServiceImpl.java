package com.zhcs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import com.zhcs.dao.AtdManageAuditeeDao;
import com.zhcs.entity.AtdManageAuditeeEntity;
import com.zhcs.service.AtdManageAuditeeService;
import com.zhcs.utils.DepartmentUtil;


//*****************************************************************************
/**
 * <p>Title:AtdManageAuditeeServiceImpl</p>
 * <p>Description: 考勤管理审核</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************

@Service("atdManageAuditeeService")
public class AtdManageAuditeeServiceImpl implements AtdManageAuditeeService {
	@Autowired
	private AtdManageAuditeeDao atdManageAuditeeDao;
	@Autowired
	private DepartmentUtil departmentUtil;
	
	@Override
	public AtdManageAuditeeEntity queryObject(Long id){
		return atdManageAuditeeDao.queryObject(id);
	}
	
	@Override
	public List<AtdManageAuditeeEntity> queryList(Map<String, Object> map){
		return atdManageAuditeeDao.queryList(map);
	}
	
	@Override
	public List<AtdManageAuditeeEntity> queryList1(Map<String, Object> map){
		String ids = departmentUtil.queryNodeDeptChildIds1();
		if (ids != null && !"".equals(ids)) {
			map.put("ids", ids);
		}
		return atdManageAuditeeDao.queryList1(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return atdManageAuditeeDao.queryTotal(map);
	}
	
	@Override
	@Transactional
	public void save(AtdManageAuditeeEntity atdManageAuditee){
		atdManageAuditeeDao.save(atdManageAuditee);
	}
	
	@Override
	@Transactional
	public void update(AtdManageAuditeeEntity atdManageAuditee){
		atdManageAuditeeDao.update(atdManageAuditee);
	}
	
	@Override
	@Transactional
	public void delete(Long id){
		atdManageAuditeeDao.delete(id);
	}
	
	@Override
	@Transactional
	public void deleteBatch(Long[] ids){
		atdManageAuditeeDao.deleteBatch(ids);
	}
	
}
