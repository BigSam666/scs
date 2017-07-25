package com.zhcs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import com.zhcs.dao.BasicssetupDao;
import com.zhcs.entity.BasicssetupEntity;
import com.zhcs.service.BasicssetupService;


//*****************************************************************************
/**
 * <p>Title:BasicssetupServiceImpl</p>
 * <p>Description: 预警管理----基础设置
</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************

@Service("basicssetupService")
public class BasicssetupServiceImpl implements BasicssetupService {
	@Autowired
	private BasicssetupDao basicssetupDao;
	
	@Override
	public BasicssetupEntity queryObject(Long id){
		return basicssetupDao.queryObject(id);
	}
	
	@Override
	public List<BasicssetupEntity> queryList(Map<String, Object> map){
		return basicssetupDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return basicssetupDao.queryTotal(map);
	}
	
	@Override
	@Transactional
	public void save(BasicssetupEntity basicssetup){
		basicssetupDao.save(basicssetup);
	}
	
	@Override
	@Transactional
	public void update(BasicssetupEntity basicssetup){
		basicssetupDao.update(basicssetup);
	}
	
	@Override
	@Transactional
	public void delete(Long id){
		basicssetupDao.delete(id);
	}
	
	@Override
	@Transactional
	public void deleteBatch(Long[] ids){
		basicssetupDao.deleteBatch(ids);
	}
	
}
