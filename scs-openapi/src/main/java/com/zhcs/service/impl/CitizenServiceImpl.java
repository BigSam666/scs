package com.zhcs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhcs.dao.CitizenDao;
import com.zhcs.entity.CitizenEntity;
import com.zhcs.service.CitizenService;

import java.util.Map;

@Service("citizenService")
public class CitizenServiceImpl implements CitizenService {
	
	@Autowired
	private CitizenDao citizenDao;

	@Override
	public CitizenEntity queryObject(Long updid) {
		return citizenDao.queryObject(updid);
	}

	@Override
	public CitizenEntity queryObjectByWeChatId(String weChatId) {
		return citizenDao.queryObjectByWeChatId(weChatId);
	}

	@Override
	public CitizenEntity update(CitizenEntity citizen) {
		return citizenDao.update(citizen);
	}
}
