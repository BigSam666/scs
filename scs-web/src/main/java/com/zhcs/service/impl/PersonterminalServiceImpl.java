package com.zhcs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import com.zhcs.dao.PersonterminalDao;
import com.zhcs.entity.PersonterminalEntity;
import com.zhcs.service.PersonterminalService;


//*****************************************************************************
/**
 * <p>Title:PersonterminalServiceImpl</p>
 * <p>Description: 人员终端</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************

@Service("personterminalService")
public class PersonterminalServiceImpl implements PersonterminalService {
	@Autowired
	private PersonterminalDao personterminalDao;
	
	@Override
	public PersonterminalEntity queryObject(Long id){
		return personterminalDao.queryObject(id);
	}
	
	@Override
	public List<PersonterminalEntity> queryList(Map<String, Object> map){
		return personterminalDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return personterminalDao.queryTotal(map);
	}
	
	@Override
	@Transactional
	public void save(PersonterminalEntity personterminal){
		personterminalDao.save(personterminal);
	}
	
	@Override
	@Transactional
	public void update(PersonterminalEntity personterminal){
		personterminalDao.update(personterminal);
	}
	
	@Override
	@Transactional
	public void delete(Long id){
		personterminalDao.delete(id);
	}
	
	@Override
	@Transactional
	public void deleteBatch(Long[] ids){
		personterminalDao.deleteBatch(ids);
	}
	
}
