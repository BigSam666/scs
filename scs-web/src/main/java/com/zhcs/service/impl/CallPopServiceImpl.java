package com.zhcs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhcs.dao.CallPopDao;
import com.zhcs.entity.CallPopEntity;
import com.zhcs.service.CallPopService;


//*****************************************************************************

/**
 * <p>Title:CallJobselServiceImpl</p>
 * <p>Description: 工单查询</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 *
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************

@Service("callPopService")
public class CallPopServiceImpl implements CallPopService {
    @Autowired
    private CallPopDao callPopDao;

    @Override
    @Transactional
    public void save(CallPopEntity callPopsel) {
        callPopDao.save(callPopsel);
    }

    @Override
    public int queryCallNumber(Long id) {
        return callPopDao.queryCallNumber(id);
    }

    @Override
    public String queryName(String phone) {
        return callPopDao.queryName(phone);
    }

}
