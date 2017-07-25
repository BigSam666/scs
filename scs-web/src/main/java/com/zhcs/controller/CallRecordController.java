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
import com.zhcs.entity.CallRecordEntity;
import com.zhcs.service.CallRecordService;
import com.zhcs.utils.BeanUtil;
import com.zhcs.utils.PageUtils;
import com.zhcs.utils.StringUtil;
import com.zhcs.utils.R;

//*****************************************************************************
/**
 * <p>Title:CallRecordController</p>
 * <p>Description: 通话记录表</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
@Controller
@RequestMapping("call")
public class CallRecordController extends AbstractController  {
	@Autowired
	private CallRecordService callRecordService;
	
	@RequestMapping("/callrecord.html")
	public String list(){
		return "call/callrecord.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("callrecord:list")
	public R list(String sidx, String order, Integer page, Integer limit){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sidx", sidx);
		map.put("order", order);
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		
		//查询列表数据
		List<CallRecordEntity> callRecordList = callRecordService.queryList(map);
		int total = callRecordService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(callRecordList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{id}")
	@RequiresPermissions("callrecord:info")
	public R info(@PathVariable("id") Long id){
		CallRecordEntity callRecord = callRecordService.queryObject(id);
		
		return R.ok().put("callRecord", callRecord);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("callrecord:save")
	public R save(@RequestBody CallRecordEntity callRecord){
	
		BeanUtil.fillCCUUD(callRecord, getUserId(), getUserId());
		callRecordService.save(callRecord);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("callrecord:update")
	public R update(@RequestBody CallRecordEntity callRecord){
		
		BeanUtil.fillCCUUD(callRecord, getUserId());
		callRecordService.update(callRecord);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete/{id}")
	@RequiresPermissions("callrecord:delete")
	public R delete(@PathVariable("id") Long id){
		if(PlatformContext.getGoalbalContext("adminId", String.class).equals(StringUtil.valueOf(getUserId()))){
			callRecordService.delete(id);
		} else {
			CallRecordEntity callRecord = new CallRecordEntity();
			callRecord.setId(id);
			BeanUtil.fillCCUUD(callRecord, getUserId());
			callRecordService.update(callRecord);
		}
		return R.ok();
	}
	
}
