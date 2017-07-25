package com.zhcs.dao;

import com.zhcs.entity.JobwarningEntity;

import java.util.List;
import java.util.Map;

//*****************************************************************************

/**
 * <p>Title:JobwarningDao</p>
 * <p>Description: jobwarning工作预警</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 *
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public interface JobwarningDao extends BaseDao<JobwarningEntity> {
    List<Map<String, Object>> queryListMap(Map<String, Object> map);
}
