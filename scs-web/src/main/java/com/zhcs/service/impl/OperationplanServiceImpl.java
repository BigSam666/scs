package com.zhcs.service.impl;

import com.zhcs.dao.OperationplanDao;
import com.zhcs.entity.OperationplanEntity;
import com.zhcs.entity.OperationplandataEntity;
import com.zhcs.service.OperationplanService;
import com.zhcs.utils.DepartmentUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

//*****************************************************************************
/**
 * <p>Title:OperationplanServiceImpl</p>
 * <p>Description: operationplan(作业计划)</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************

@Service("operationplanService")
public class OperationplanServiceImpl implements OperationplanService {
	@Autowired
	private OperationplanDao operationplanDao;
	@Autowired
	private DepartmentUtil departmentUtil;
	
	@Override
	public List<Map<String, Object>> queryBanCiMap() {
		return operationplanDao.queryBanCiMap();
	}
	
	@Override
	public List<Map<String, Object>> queryLists(Map<String, Object> map) {
		return operationplanDao.queryLists(map);
	}
	
	@Override
	public List<Map<String, Object>> queryLists1(Map<String, Object> map) {
		String ids = departmentUtil.queryNodeDeptChildIds();
		if (ids != null && !"".equals(ids)) {
			map.put("ids", ids);
		}
		return operationplanDao.queryLists1(map);
	}
	
	@Override
	public List<Map<String, Object>> queryObjects(Object oid) {
		return operationplanDao.queryObjects(oid);
	}

	@Override
	public List<Map<String, Object>> queryDataList(Long oid) {
		return operationplanDao.queryDataList(oid);
	}
	
	@Override
	public List<Map<String, Object>> queryDataListConctOptm(Long oid) {
		return operationplanDao.queryDataListConctOptm(oid);
	}
	@Override
	public List<Map<String, Object>> queryDataListConctOptmByOids(String oids) {
		return operationplanDao.queryDataListConctOptmByOids(oids);
	}
	@Override
	public String queryMonth(String date) {
		return operationplanDao.queryMonth(date);
	}
	
	@Override
	public List<OperationplanEntity> querySEdate(Object[] sedate){
		return operationplanDao.querySEdate(sedate);
	}
	
	@Override
	public List<Map<String, Object>> queryDept(Map<String, Object> bumen){
		return operationplanDao.queryDept(bumen);
	}
	
	@Override
	public List<Map<String, Object>> queryDeptname(String dept){
		return operationplanDao.queryDeptname(dept);
	}
	
	@Override
	public String queryDrivername(String drivername){
		return operationplanDao.queryDrivername(drivername);
	}
	
	@Override
	public List<Map<String, Object>> queryCar(String dept){
		return operationplanDao.queryCar(dept);
	}
	
	@Override
	public List<String> queryCla(String name){
		return operationplanDao.queryCla(name);
	}
	
	@Override
	public String queryId(Long id) {
		return operationplanDao.queryId(id);
	}
	
	@Override
	public List<Map<String, Object>> queryOpList(Object id){
		return operationplanDao.queryOpList(id);
	}
		
	@Override
	public int queryTotal(Map<String, Object> map){
		return operationplanDao.queryTotal(map);
	}
	
	@Override
	@Transactional
	public void save(OperationplanEntity operationplanEntity) {
		operationplanDao.save(operationplanEntity);
	}
	
	@Override
	@Transactional
	public void saveData(OperationplandataEntity operationplandataEntity) {
		operationplanDao.saveData(operationplandataEntity);
	}
	
	@Override
	@Transactional
	public void delete(Long id){
		operationplanDao.delete(id);
	}
	
	@Override
	@Transactional
	public void deleteup(Long oid){
		operationplanDao.deleteup(oid);
	}
	
	@Override
	@Transactional
	public void update(OperationplanEntity operationplan){
		operationplanDao.update(operationplan);
	}
	
	@Override
	@Transactional
	public void copy(Map<String, Object> opmap){
		operationplanDao.copy(opmap);
	}
	
	@Override
	@Transactional
	public void updatedata(OperationplandataEntity operationplandataEntity){
		operationplanDao.updatedata(operationplandataEntity);
	}

	@Override
	public List<OperationplanEntity> queryByProject(List<Long> depts) {
		Object[] array = depts.toArray();
		return operationplanDao.queryByProject(array);
	}

	@Override
	public OperationplanEntity queryODB(Map<String, Object> oob) {
		return operationplanDao.queryODB(oob);
	}
}
