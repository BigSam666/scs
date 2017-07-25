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
import com.zhcs.entity.FleettEntity;
import com.zhcs.service.FleettService;
import com.zhcs.utils.BeanUtil;
import com.zhcs.utils.PageUtils;
import com.zhcs.utils.StringUtil;
import com.zhcs.utils.R;

//*****************************************************************************
/**
 * <p>
 * Title:FleettController
 * </p>
 * <p>
 * Description: 车队管理
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
@RequestMapping("fleett")
public class FleettController extends AbstractController {
	@Autowired
	private FleettService fleettService;

	@RequestMapping("/fleett.html")
	public String list() {
		return "fleett/fleett.html";
	}

	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("fleett:list")
	public R list(String condition,String sidx, String order, Integer page, Integer limit) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringUtil.isValid(condition)) {
			map.put("condition", condition.trim());
		}
		map.put("sidx", sidx);
		map.put("order", order);
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);

		// 查询列表数据
//		List<FleettEntity> fleettList = fleettService.queryList(map);
		List<FleettEntity> fleettList = fleettService.queryList1(map);
		int total = fleettService.queryTotal(map);

		PageUtils pageUtil = new PageUtils(fleettList, total, limit, page);

		return R.ok().put("page", pageUtil);
	}

	/**
	 * 列出所有
	 */
	@ResponseBody
	@RequestMapping("/listall")
	@RequiresPermissions("fleett:list")
	public R listall() {
		// 查询列表数据
		List<FleettEntity> fleettList = fleettService
				.queryList(new HashMap<String, Object>());
		return R.ok().putData(fleettList);
	}

	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{id}")
	@RequiresPermissions("fleett:info")
	public R info(@PathVariable("id") Long id) {
		FleettEntity fleett = fleettService.queryObject(id);

		return R.ok().put("fleett", fleett);
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("fleett:save")
	public R save(@RequestBody FleettEntity fleett) {

		BeanUtil.fillCCUUD(fleett, getUserId(), getUserId());
		fleettService.save(fleett);

		return R.ok();
	}

	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("fleett:update")
	public R update(@RequestBody FleettEntity fleett) {

		BeanUtil.fillCCUUD(fleett, getUserId());
		fleettService.update(fleett);

		return R.ok();
	}

	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete/{id}")
	@RequiresPermissions("fleett:delete")
	public R delete(@PathVariable("id") Long id) {
		if (PlatformContext.getGoalbalContext("adminId", String.class).equals(
				StringUtil.valueOf(getUserId()))) {
			fleettService.delete(id);
		} else {
			FleettEntity fleett = new FleettEntity();
			fleett.setId(id);
			fleett.setStatus("0");
			BeanUtil.fillCCUUD(fleett, getUserId());
			fleettService.update(fleett);
		}
		return R.ok();
	}

}
