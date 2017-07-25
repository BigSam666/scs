package com.zhcs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zhcs.dao.WorkflowDao;
import com.zhcs.entity.WorkflowEntity;
import com.zhcs.service.WorkflowService;


//*****************************************************************************
/**
 * <p>Title:WorkflowServiceImpl</p>
 * <p>Description: 工作流</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************

@Service("workflowService")
public class WorkflowServiceImpl implements WorkflowService {
	@Autowired
	private WorkflowDao workflowDao;
	
	@Override
	public WorkflowEntity queryObject(Long id){
		return workflowDao.queryObject(id);
	}
	
	@Override
	public WorkflowEntity queryOtherNodeObject(String nodeno, String flag){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("nodeno", nodeno);
		map.put("flag", flag);
		return workflowDao.queryOtherNodeObject(map);
	}
	
	@Override
	public List<WorkflowEntity> queryList(Map<String, Object> map){
		return workflowDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return workflowDao.queryTotal(map);
	}
	
	@Override
	@Transactional
	public void save(WorkflowEntity workflow){
		workflowDao.save(workflow);
	}
	
	@Override
	@Transactional
	public void update(WorkflowEntity workflow){
		workflowDao.update(workflow);
	}
	
	@Override
	@Transactional
	public void delete(Long id){
		workflowDao.delete(id);
	}
	
	@Override
	@Transactional
	public void deleteBatch(Long[] ids){
		workflowDao.deleteBatch(ids);
	}

	@Override
	public WorkflowEntity queryByNodeNo(String nodeno) {
		return workflowDao.queryByNodeNo(nodeno);
	}
	
}
