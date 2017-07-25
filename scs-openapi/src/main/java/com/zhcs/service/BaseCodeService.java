package com.zhcs.service;

import com.zhcs.entity.BaseCodeEntity;

import java.util.List;
import java.util.Map;

//*****************************************************************************

/**
 * <p>Title:BaseCdeService</p>
 * <p>Description: 基础代码 (T_BASE_CDE)</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 *
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public interface BaseCodeService {

    BaseCodeEntity queryObject(Long id);

    List<BaseCodeEntity> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    void save(BaseCodeEntity baseCde);

    void update(BaseCodeEntity baseCde);

    void delete(Long id);

    void deleteBatch(Long[] ids);

    /**
     * 获取指定类型的所有数据
     */
    List<BaseCodeEntity> selectByType(String type);

    BaseCodeEntity selectByTypeValue(String type, String no);

    /**
     * 查询出数据字典中指定类型所有数据的  cnm:no  对
     * 应前端要求，所有cnm保存在第一个列表中，所有no保存在第二个列表中
     *
     * @param type 数据字典中的type
     * @return
     */
    Map<String, List<String>> getCnmNoMapByType(String type);
}
