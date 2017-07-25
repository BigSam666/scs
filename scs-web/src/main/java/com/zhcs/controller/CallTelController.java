package com.zhcs.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhcs.entity.CallTelEntity;
import com.zhcs.service.CallTelService;
import com.zhcs.utils.PageUtils;
import com.zhcs.utils.R;

//*****************************************************************************
/**
 * <p>
 * Title:calltelController
 * </p>
 * <p>
 * Description: 坐席管理
 * </p>
 * <p>
 * Copyright: Copyright (c) 2017
 * </p>
 * <p>
 * Company: 深圳市智慧城市管家信息科技有限公司
 * </p>
 * 
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
// *****************************************************************************
@Controller
@RequestMapping("calltel")
public class CallTelController extends AbstractController {
	@Autowired
	private CallTelService callTelService;

	@RequestMapping("/calltel.html")
	public String list() {
		return "calltel/calltel.html";
	}

	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("calltel:list")
	public R list(String sidx, String order,String query, Integer page, Integer limit) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("query", query);
		map.put("sidx", sidx);
		map.put("order", order);
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);

		// 查询列表数据
		List<CallTelEntity> calltelList = callTelService.queryList(map);
		int total = callTelService.queryTotal(map);

		PageUtils pageUtil = new PageUtils(calltelList, total, limit, page);

		return R.ok().put("page", pageUtil);
	}

}
