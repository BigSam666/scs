package com.zhcs.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhcs.context.PlatformContext;
import com.zhcs.entity.MaintainEntity;
import com.zhcs.service.MaintainService;
import com.zhcs.utils.BeanUtil;
import com.zhcs.utils.PageUtils;
import com.zhcs.utils.R;
import com.zhcs.utils.StringUtil;

//*****************************************************************************
/**
 * <p>Title:MaintainController</p>
 * <p>Description: 保养管理</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
@Controller
@RequestMapping("maintain")
public class MaintainController extends AbstractController  {
	@Autowired
	private MaintainService maintainService;
	
	@RequestMapping("/maintain.html")
	public String list(){
		return "maintain/maintain.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("maintain:list")
	public R list(String sidx, String order, Integer page, Integer limit,String condition,String starttime,String endtime){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sidx", sidx);
		map.put("order", order);
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		
		map.put("condition", condition);
		map.put("starttime", starttime);
		map.put("endtime", endtime);
		//查询列表数据
//		List<MaintainEntity> maintainList = maintainService.queryList(map);
		List<MaintainEntity> maintainList = maintainService.queryList1(map);
		int total = maintainService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(maintainList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{id}")
	@RequiresPermissions("maintain:info")
	public R info(@PathVariable("id") Long id){
		MaintainEntity maintain = maintainService.queryObject(id);
		
		return R.ok().put("maintain", maintain);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("maintain:save")
	public R save(@RequestBody MaintainEntity maintain){
	
		BeanUtil.fillCCUUD(maintain, getUserId(), getUserId());
		maintainService.save(maintain);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("maintain:update")
	public R update(@RequestBody MaintainEntity maintain){
		
		BeanUtil.fillCCUUD(maintain, getUserId());
		maintainService.update(maintain);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete/{id}")
	@RequiresPermissions("maintain:delete")
	public R delete(@PathVariable("id") Long id){
		if(PlatformContext.getGoalbalContext("adminId", String.class).equals(StringUtil.valueOf(getUserId()))){
			maintainService.delete(id);
		} else {
			MaintainEntity maintain = new MaintainEntity();
			maintain.setId(id);
			maintain.setStatus("0");
			BeanUtil.fillCCUUD(maintain, getUserId());
			maintainService.update(maintain);
		}
		return R.ok();
	}
	
}
