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
import com.zhcs.entity.YeartrialEntity;
import com.zhcs.service.YeartrialService;
import com.zhcs.utils.BeanUtil;
import com.zhcs.utils.PageUtils;
import com.zhcs.utils.StringUtil;
import com.zhcs.utils.R;

//*****************************************************************************
/**
 * <p>Title:YeartrialController</p>
 * <p>Description: 年审管理</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
@Controller
@RequestMapping("yeartrial")
public class YeartrialController extends AbstractController  {
	@Autowired
	private YeartrialService yeartrialService;
	
	@RequestMapping("/yeartrial.html")
	public String list(){
		return "yeartrial/yeartrial.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("yeartrial:list")
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
//		List<YeartrialEntity> yeartrialList = yeartrialService.queryList(map);
		List<YeartrialEntity> yeartrialList = yeartrialService.queryList1(map);
		int total = yeartrialService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(yeartrialList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{id}")
	@RequiresPermissions("yeartrial:info")
	public R info(@PathVariable("id") Long id){
		YeartrialEntity yeartrial = yeartrialService.queryObject(id);
		
		return R.ok().put("yeartrial", yeartrial);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("yeartrial:save")
	public R save(@RequestBody YeartrialEntity yeartrial){
	
		BeanUtil.fillCCUUD(yeartrial, getUserId(), getUserId());
		yeartrialService.save(yeartrial);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("yeartrial:update")
	public R update(@RequestBody YeartrialEntity yeartrial){
		
		BeanUtil.fillCCUUD(yeartrial, getUserId());
		yeartrialService.update(yeartrial);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete/{id}")
	@RequiresPermissions("yeartrial:delete")
	public R delete(@PathVariable("id") Long id){
		if(PlatformContext.getGoalbalContext("adminId", String.class).equals(StringUtil.valueOf(getUserId()))){
			yeartrialService.delete(id);
		} else {
			YeartrialEntity yeartrial = new YeartrialEntity();
			yeartrial.setId(id);
			yeartrial.setStatus("0");
			BeanUtil.fillCCUUD(yeartrial, getUserId());
			yeartrialService.update(yeartrial);
		}
		return R.ok();
	}
	
}
