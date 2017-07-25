package com.zhcs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhcs.dao.PartyDao;
import com.zhcs.entity.PartyEntity;
import com.zhcs.service.PartyService;

@Service("partyService")
public class PartyServiceImpl implements PartyService {

	@Autowired
	private PartyDao partyDao;
	
	@Override
	public PartyEntity queryObject(Long id) {
		return partyDao.queryObject(id);
	}
	

}
