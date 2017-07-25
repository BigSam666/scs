package com.zhcs.service;

import com.zhcs.entity.CallPopEntity;

//*****************************************************************************

/**
 * <p>
 * Title:CallJobselService
 * </p>
 * <p>
 * Description: 工单查询
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
public interface CallPopService {
    /**
     * 保存
     *
     * @param callPopsel
     */
    void save(CallPopEntity callPopsel);

    /**
     * 查询分机号码
     */
    int queryCallNumber(Long id);

    /**
     * 根据手机号码查询姓名
     *
     * @param phone
     * @return
     */
    String queryName(String phone);

}
