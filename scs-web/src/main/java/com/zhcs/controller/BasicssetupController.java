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

import com.zhcs.entity.BasicssetupEntity;
import com.zhcs.service.BasicssetupService;
import com.zhcs.utils.BeanUtil;
import com.zhcs.utils.PageUtils;
import com.zhcs.utils.R;

//*****************************************************************************
/**
 * <p>Title:BasicssetupController</p>
 * <p>Description: 预警管理----基础设置
</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
@Controller
@RequestMapping("basicssetup")
public class BasicssetupController extends AbstractController  {
	@Autowired
	private BasicssetupService basicssetupService;
	
	@RequestMapping("/basicssetup.html")
	public String list(){
		return "basicssetup/basicssetup.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("basicssetup:list")
	public R list(String sidx, String order, Integer page, Integer limit){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sidx", sidx);
		map.put("order", order);
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		
		//查询列表数据
		List<BasicssetupEntity> basicssetupList = basicssetupService.queryList(map);
		int total = basicssetupService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(basicssetupList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{id}")
	@RequiresPermissions("basicssetup:info")
	public R info(@PathVariable("id") Long id){
		BasicssetupEntity basicssetup = basicssetupService.queryObject(id);
		
		return R.ok().put("basicssetup", basicssetup);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("basicssetup:save")
	public R save(@RequestBody BasicssetupEntity basicssetup){
	
		BeanUtil.fillCCUUD(basicssetup, getUserId(), getUserId());
		basicssetupService.save(basicssetup);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("basicssetup:update")
	public R update(@RequestBody BasicssetupEntity basicssetup){
		
		BeanUtil.fillCCUUD(basicssetup, getUserId());
		basicssetupService.update(basicssetup);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete/{id}")
	@RequiresPermissions("basicssetup:delete")
	public R delete(@PathVariable("id") Long id){
		basicssetupService.delete(id);
		return R.ok();
	}
	
}
