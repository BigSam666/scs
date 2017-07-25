package com.zhcs.dao;

import com.zhcs.entity.OperationplanEntity;
import com.zhcs.entity.OperationplandataEntity;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zhcs.entity.OperationplanEntity;
import com.zhcs.entity.OperationplandataEntity;


//*****************************************************************************
/**
 * <p>Title:OperationplanDao</p>
 * <p>Description: operationplanpop(新增计划)</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public interface OperationplanDao extends BaseDao<OperationplanEntity> {
	
	List<Map<String, Object>> queryLists(Map<String, Object> map);
	
	List<Map<String, Object>> queryLists1(Map<String, Object> map);
	
	List<Map<String, Object>> queryObjects(Object id);
	
	List<Map<String, Object>> queryBanCiMap();

	List<OperationplanEntity> querySEdate(Object[] sedate);

	List<Map<String, Object>> queryDept(Map<String, Object> bumen);

	List<Map<String, Object>> queryDeptname(String dept);

	List<Map<String, Object>> queryCar(String dept);

	List<String> queryCla(String name);
	
	List<Map<String, Object>> queryOpList(Object id);
	
	List<Map<String, Object>> queryDataList(Long oid);

	List<Map<String, Object>> queryDataListConctOptm(Long oid);
	List<Map<String, Object>> queryDataListConctOptmByOids(@Param("oids") String oids);
	
	String queryDrivername(String drivername);

	String queryNametodept(String name);

	String queryMonth(String date);
	
	String queryId(Long id);

	void deleteup(Long oid);
	
	void copy(Map<String, Object> opmap);
	
	void saveData(OperationplandataEntity operationplandataEntity);
	
	int updatedata(OperationplandataEntity operationplandataEntity);

	List<OperationplanEntity> queryByProject(Object[] array);

	OperationplanEntity queryODB(Map<String, Object> oob);
}
