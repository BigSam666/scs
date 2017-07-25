package com.zhcs.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhcs.dao.AuditscheduleDao;
import com.zhcs.dao.BaseCodeDao;
import com.zhcs.dao.BddetailDao;
import com.zhcs.dao.BudgetDao;
import com.zhcs.entity.BaseCodeEntity;
import com.zhcs.entity.BddetailEntity;
import com.zhcs.entity.BudgetEntity;
import com.zhcs.service.BudgetService;
import com.zhcs.utils.BeanUtil;

//*****************************************************************************
/**
 * <p>Title:BudgetServiceImpl</p>
 * <p>Description: 预算申报</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************

@Service("budgetService")
public class BudgetServiceImpl implements BudgetService {
	@Autowired
	private BudgetDao budgetDao;
	@Autowired
	private BddetailDao bddetailDao;
	@Autowired
	private BaseCodeDao baseCodeDao;
	@Autowired
	private AuditscheduleDao auditscheduleDao;
	
	@Override
	public BudgetEntity queryObject(Long id){
		BudgetEntity budget = budgetDao.queryObject(id);
		// 项目名
		Map<String,String> param = new HashMap<>();
		param.put("type", "project");
		param.put("no", budget.getProject());
		BaseCodeEntity project = baseCodeDao.selectByTypeValue(param);
		budget.setProjectName(project.getCnm());
		
		return budget;
	}
	
	@Override
	public List<BudgetEntity> queryList(Map<String, Object> map){
		return budgetDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return budgetDao.queryTotal(map);
	}
	
	@Override
	@Transactional
	public void save(BudgetEntity budget){
		budgetDao.save(budget);
		for (BddetailEntity i : budget.getBudgetDetailList()) {
			i.setBudget(budget.getId());
			BeanUtil.fillCCUUD(i, budget.getCrtid(), budget.getCrtid());
		}
		bddetailDao.saveBatch(budget.getBudgetDetailList());
	}
	
	@Override
	@Transactional
	public void update(BudgetEntity budget){
		budgetDao.update(budget);
	}
	
	@Override
	@Transactional
	public void delete(Long id){
		budgetDao.delete(id);
	}
	
	@Override
	@Transactional
	public void deleteBatch(Long[] ids){
		budgetDao.deleteBatch(ids);
	}

	@Override
	public List<Map<String, Object>> queryListMap(Map<String, Object> map) {
		return budgetDao.queryListMap(map);
	}

	@Override
	@Transactional
	public void adjust(BudgetEntity budget) {
		// 先删除所有的预算明细，然后重新添加进去
		bddetailDao.deleteNyBudget(budget.getId());
		for (BddetailEntity i : budget.getBudgetDetailList()) {
			i.setBudget(budget.getId());
			BeanUtil.fillCCUUD(i, budget.getCrtid(), budget.getCrtid());
		}
		bddetailDao.saveBatch(budget.getBudgetDetailList());
		// 设置进度为待审核
		budgetDao.updateSchedule(budget.getId(), 1);
		// 删除所有之前的审核记录
		auditscheduleDao.deleteByBudget(budget.getId());
	}

	@Override
	@Transactional
	public void updateSchedule(Long budget, int schedule) {
		budgetDao.updateSchedule(budget, schedule);
	}
	
}
