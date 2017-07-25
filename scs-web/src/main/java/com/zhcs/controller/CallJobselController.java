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
import com.zhcs.entity.CallJobselEntity;
import com.zhcs.service.CallJobselService;
import com.zhcs.utils.BeanUtil;
import com.zhcs.utils.PageUtils;
import com.zhcs.utils.StringUtil;
import com.zhcs.utils.R;

//*****************************************************************************
/**
 * <p>Title:CallJobselController</p>
 * <p>Description: 工单查询</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
@Controller
@RequestMapping("calljobsel")
public class CallJobselController extends AbstractController  {
	@Autowired
	private CallJobselService callJobselService;
	
	@RequestMapping("/calljobsel.html")
	public String list(){
		return "calljobsel/calljobsel.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("calljobsel:list")
	public R list(String sidx, String order, Integer page, Integer limit){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sidx", sidx);
		map.put("order", order);
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		
		//查询列表数据
		List<CallJobselEntity> callJobselList = callJobselService.queryList(map);
		int total = callJobselService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(callJobselList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{id}")
	@RequiresPermissions("calljobsel:info")
	public R info(@PathVariable("id") Long id){
		CallJobselEntity callJobsel = callJobselService.queryObject(id);
		
		return R.ok().put("callJobsel", callJobsel);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("calljobsel:save")
	public R save(@RequestBody CallJobselEntity callJobsel){
	
		BeanUtil.fillCCUUD(callJobsel, getUserId(), getUserId());
		callJobselService.save(callJobsel);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("calljobsel:update")
	public R update(@RequestBody CallJobselEntity callJobsel){
		
		BeanUtil.fillCCUUD(callJobsel, getUserId());
		callJobselService.update(callJobsel);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete/{id}")
	@RequiresPermissions("calljobsel:delete")
	public R delete(@PathVariable("id") Long id){
		if(PlatformContext.getGoalbalContext("adminId", String.class).equals(StringUtil.valueOf(getUserId()))){
			callJobselService.delete(id);
		} else {
			CallJobselEntity callJobsel = new CallJobselEntity();
			callJobsel.setId(id);
			BeanUtil.fillCCUUD(callJobsel, getUserId());
			callJobselService.update(callJobsel);
		}
		return R.ok();
	}
	
}
