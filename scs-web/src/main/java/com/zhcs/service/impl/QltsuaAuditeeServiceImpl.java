package com.zhcs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import com.zhcs.dao.QltsuaAuditeeDao;
import com.zhcs.entity.QltsuaAuditeeEntity;
import com.zhcs.service.QltsuaAuditeeService;
import com.zhcs.utils.DepartmentUtil;


//*****************************************************************************
/**
 * <p>Title:QltsuaAuditeeServiceImpl</p>
 * <p>Description: 质量督导审核方</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************

@Service("qltsuaAuditeeService")
public class QltsuaAuditeeServiceImpl implements QltsuaAuditeeService {
	@Autowired
	private QltsuaAuditeeDao qltsuaAuditeeDao;
	@Autowired
	private DepartmentUtil departmentUtil;
	
	@Override
	public QltsuaAuditeeEntity queryObject(Long id){
		return qltsuaAuditeeDao.queryObject(id);
	}
	
	@Override
	public List<QltsuaAuditeeEntity> queryList(Map<String, Object> map){
		return qltsuaAuditeeDao.queryList(map);
	}
	
	@Override
	public List<QltsuaAuditeeEntity> queryList1(Map<String, Object> map){
		String ids = departmentUtil.queryNodeDeptChildIds();
		if (ids != null && !"".equals(ids)) {
			map.put("ids", ids);
		}
		return qltsuaAuditeeDao.queryList1(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return qltsuaAuditeeDao.queryTotal(map);
	}
	
	@Override
	@Transactional
	public void save(QltsuaAuditeeEntity qltsuaAuditee){
		qltsuaAuditeeDao.save(qltsuaAuditee);
	}
	
	@Override
	@Transactional
	public void update(QltsuaAuditeeEntity qltsuaAuditee){
		qltsuaAuditeeDao.update(qltsuaAuditee);
	}
	
	@Override
	@Transactional
	public void delete(Long id){
		qltsuaAuditeeDao.delete(id);
	}
	
	@Override
	@Transactional
	public void deleteBatch(Long[] ids){
		qltsuaAuditeeDao.deleteBatch(ids);
	}
	
}
