package com.zhcs.dao;

import com.zhcs.entity.CitizenEntity;

import java.util.Map;

public interface CitizenDao {

	CitizenEntity queryObject(Long updid);

	CitizenEntity queryObjectByWeChatId(String weChatId);

	CitizenEntity update(CitizenEntity citizen);
}
