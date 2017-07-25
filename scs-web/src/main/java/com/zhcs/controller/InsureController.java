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
import com.zhcs.entity.InsureEntity;
import com.zhcs.service.InsureService;
import com.zhcs.utils.BeanUtil;
import com.zhcs.utils.PageUtils;
import com.zhcs.utils.R;
import com.zhcs.utils.StringUtil;

//*****************************************************************************
/**
 * <p>Title:InsureController</p>
 * <p>Description: 保险管理</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
@Controller
@RequestMapping("insure")
public class InsureController extends AbstractController  {
	@Autowired
	private InsureService insureService;
	
	@RequestMapping("/insure.html")
	public String list(){
		return "insure/insure.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("insure:list")
	public R list(String sidx, String order, Integer page, Integer limit,String condition,String starttime,String endtime){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sidx", sidx);
		map.put("order", order);
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		map.put("condition",condition);
		map.put("starttime", starttime);
		map.put("endtime", endtime);
		//查询列表数据
//		List<InsureEntity> insureList = insureService.queryList(map);
		List<InsureEntity> insureList = insureService.queryList1(map);
		int total = insureService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(insureList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{id}")
	@RequiresPermissions("insure:info")
	public R info(@PathVariable("id") Long id){
		InsureEntity insure = insureService.queryObject(id);
		
		return R.ok().put("insure", insure);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("insure:save")
	public R save(@RequestBody InsureEntity insure){
	
		BeanUtil.fillCCUUD(insure, getUserId(), getUserId());
		insureService.save(insure);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("insure:update")
	public R update(@RequestBody InsureEntity insure){
		
		BeanUtil.fillCCUUD(insure, getUserId());
		insureService.update(insure);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete/{id}")
	@RequiresPermissions("insure:delete")
	public R delete(@PathVariable("id") Long id){
		if(PlatformContext.getGoalbalContext("adminId", String.class).equals(StringUtil.valueOf(getUserId()))){
			insureService.delete(id);
		} else {
			InsureEntity insure = new InsureEntity();
			insure.setId(id);
			insure.setStatus("0");
			BeanUtil.fillCCUUD(insure, getUserId());
			insureService.update(insure);
		}
		return R.ok();
	}
	
}
