package com.zhcs.service;

import com.zhcs.entity.OperationplanEntity;
import com.zhcs.entity.OperationplandataEntity;

import java.util.List;
import java.util.Map;

//*****************************************************************************
/**
 * <p>Title:OperationplanService</p>
 * <p>Description: operationplan(作业计划)</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public interface OperationplanService {
	
	List<Map<String, Object>> queryLists(Map<String, Object> map);
	
	List<Map<String, Object>> queryLists1(Map<String, Object> map);
	
	List<Map<String, Object>> queryObjects(Object oid);

	List<Map<String, Object>> queryBanCiMap();
	
	List<OperationplanEntity> querySEdate(Object[] sedate);
	
	/*<!-- 查询用户对应的班级 -->*/
	List<Map<String, Object>> queryDept(Map<String, Object> classotypemap);
	
	List<Map<String, Object>> queryCar(String dept);
	
	List<String> queryCla(String name);
	
	List<Map<String, Object>> queryOpList(Object id);
	
	List<Map<String, Object>> queryDataList(Long oid);
	
	List<Map<String, Object>> queryDataListConctOptm(Long oid);
	
	List<Map<String, Object>> queryDataListConctOptmByOids(String oids);
	
	
	List<Map<String, Object>> queryDeptname(String dept);

	String queryDrivername(String drivername);

	String queryMonth(String date);
	
	String queryId(Long id);
	
	void copy(Map<String, Object> opmap);
	
	void save(OperationplanEntity operationplanEntity);

	void delete(Long id);

	void update(OperationplanEntity operationplan);

	void saveData(OperationplandataEntity operationplandataEntity);
	
	void updatedata(OperationplandataEntity operationplandataEntity);

	void deleteup(Long oid);
	
	int queryTotal(Map<String, Object> map);
	
	/*用部门和用户查询对应的排班信息*/
	List<OperationplanEntity> queryByProject(List<Long> depts);

	OperationplanEntity queryODB(Map<String, Object> oob);
}
