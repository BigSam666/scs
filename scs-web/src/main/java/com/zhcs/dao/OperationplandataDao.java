package com.zhcs.dao;

import com.zhcs.entity.OperationplandataEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

//*****************************************************************************
/**
 * <p>Title:OperationplandataDao</p>
 * <p>Description: 作业计划数据</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public interface OperationplandataDao extends BaseDao<OperationplandataEntity> {

    List<Map<String, Object>> queryList2(Object id);

    int queryDay(@Param("date") String date, @Param("banci") String[] banci);
}
