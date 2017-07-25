package com.zhcs.service;

import com.zhcs.entity.CallTelEntity;

import java.util.List;
import java.util.Map;

//*****************************************************************************
/**
 * <p>
 * Title:CallSeatsService
 * </p>
 * <p>
 * Description: 坐席管理
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
public interface CallTelService {

	List<CallTelEntity> queryList(Map<String, Object> map);

	int queryTotal(Map<String, Object> map);
}
