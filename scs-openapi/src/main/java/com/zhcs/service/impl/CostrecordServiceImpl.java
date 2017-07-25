package com.zhcs.service.impl;

import com.zhcs.dao.CostrecordDao;
import com.zhcs.entity.CostrecordEntity;
import com.zhcs.service.CostrecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;


//*****************************************************************************

/**
 * <p>Title:CostrecordServiceImpl</p>
 * <p>Description: 费用记录</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************

@Service("costrecordService")
public class CostrecordServiceImpl implements CostrecordService {
	@Autowired
	private CostrecordDao costrecordDao;
	
	@Override
	public CostrecordEntity queryObject(Long id){
		return costrecordDao.queryObject(id);
	}
	
	@Override
	public List<CostrecordEntity> queryList(Map<String, Object> map){
		return costrecordDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return costrecordDao.queryTotal(map);
	}
	
	@Override
	@Transactional
	public void save(CostrecordEntity costrecord){
		costrecordDao.save(costrecord);
	}
	
	@Override
	@Transactional
	public void update(CostrecordEntity costrecord){
		costrecordDao.update(costrecord);
	}
	
	@Override
	@Transactional
	public void delete(Long id){
		costrecordDao.delete(id);
	}
	
	@Override
	@Transactional
	public void deleteBatch(Long[] ids){
		costrecordDao.deleteBatch(ids);
	}

	@Override
	public List<Map<String, Object>> queryListMap(Map<String, Object> map) {
		return costrecordDao.queryListMap(map);
	}
	
}
