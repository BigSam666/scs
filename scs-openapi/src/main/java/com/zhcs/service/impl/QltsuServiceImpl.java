package com.zhcs.service.impl;

import com.zhcs.dao.QltsuDao;
import com.zhcs.entity.QltsuEntity;
import com.zhcs.service.QltsuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;


//*****************************************************************************

/**
 * <p>Title:QltsuServiceImpl</p>
 * <p>Description: 质量督导</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************

@Service("qltsuService")
public class QltsuServiceImpl implements QltsuService {
	@Autowired
	private QltsuDao qltsuDao;
	
	@Override
	public QltsuEntity queryObject(Long id){
		return qltsuDao.queryObject(id);
	}
	
	@Override
	public List<QltsuEntity> queryList(Map<String, Object> map){
		return qltsuDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return qltsuDao.queryTotal(map);
	}
	
	@Override
	@Transactional
	public void save(QltsuEntity qltsu){
		qltsuDao.save(qltsu);
	}
	
	@Override
	@Transactional
	public void update(QltsuEntity qltsu){
		qltsuDao.update(qltsu);
	}
	
	@Override
	@Transactional
	public void delete(Long id){
		qltsuDao.delete(id);
	}
	
	@Override
	@Transactional
	public void deleteBatch(Long[] ids){
		qltsuDao.deleteBatch(ids);
	}
	
}
