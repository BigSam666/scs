package com.zhcs.service;

import com.zhcs.entity.CitizenEntity;

import java.util.Map;

public interface CitizenService {

	CitizenEntity queryObject(Long updid);

	CitizenEntity queryObjectByWeChatId(String weChatId);

	CitizenEntity update(CitizenEntity citizen);
}
