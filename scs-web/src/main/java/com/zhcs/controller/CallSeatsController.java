package com.zhcs.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.stereotype.Controller;

import com.zhcs.context.PlatformContext;
import com.zhcs.entity.CallSeatsEntity;
import com.zhcs.service.CallSeatsService;
import com.zhcs.utils.BeanUtil;
import com.zhcs.utils.PageUtils;
import com.zhcs.utils.StringUtil;
import com.zhcs.utils.R;

//*****************************************************************************
/**
 * <p>
 * Title:CallSeatsController
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
@RequestMapping("callseats")
public class CallSeatsController extends AbstractController {
	@Autowired
	private CallSeatsService callSeatsService;

	@RequestMapping("/callseats.html")
	public String list() {
		return "callseats/callseats.html";
	}

	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("callseats:list")
	public R list(String sidx, String order, Integer page, Integer limit) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sidx", sidx);
		map.put("order", order);
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);

		// 查询列表数据
		List<CallSeatsEntity> callSeatsList = callSeatsService.queryList(map);
		int total = callSeatsService.queryTotal(map);

		PageUtils pageUtil = new PageUtils(callSeatsList, total, limit, page);

		return R.ok().put("page", pageUtil);
	}

	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{id}")
	@RequiresPermissions("callseats:info")
	public R info(@PathVariable("id") Long id) {
		CallSeatsEntity callSeats = callSeatsService.queryObject(id);

		return R.ok().put("callSeats", callSeats);
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("callseats:save")
	public R save(@RequestBody CallSeatsEntity callSeats) {

		BeanUtil.fillCCUUD(callSeats, getUserId(), getUserId());
		callSeatsService.save(callSeats);

		return R.ok();
	}

	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("callseats:update")
	public R update(@RequestBody CallSeatsEntity callSeats) {

		BeanUtil.fillCCUUD(callSeats, getUserId());
		callSeatsService.update(callSeats);

		return R.ok();
	}

	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete/{id}")
	@RequiresPermissions("callseats:delete")
	public R delete(@PathVariable("id") Long id) {
		if (PlatformContext.getGoalbalContext("adminId", String.class).equals(StringUtil.valueOf(getUserId()))) {
			callSeatsService.delete(id);
		} else {
			CallSeatsEntity callSeats = new CallSeatsEntity();
			callSeats.setId(id);
			BeanUtil.fillCCUUD(callSeats, getUserId());
			callSeatsService.update(callSeats);
		}
		return R.ok();
	}

}
