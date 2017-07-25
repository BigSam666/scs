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

import com.zhcs.entity.MsgconfigEntity;
import com.zhcs.service.MsgconfigService;
import com.zhcs.utils.BeanUtil;
import com.zhcs.utils.PageUtils;
import com.zhcs.utils.R;

//*****************************************************************************
/**
 * <p>Title:MsgconfigController</p>
 * <p>Description: 信息基础设置</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
@Controller
@RequestMapping("msgconfig")
public class MsgconfigController extends AbstractController  {
	@Autowired
	private MsgconfigService msgconfigService;
	
	@RequestMapping("/msgconfig.html")
	public String list(){
		return "msgconfig/msgconfig.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("msgconfig:list")
	public R list(Integer page, Integer limit){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		
		//查询列表数据
		List<MsgconfigEntity> msgconfigList = msgconfigService.queryList(map);
		int total = msgconfigService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(msgconfigList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{id}")
	@RequiresPermissions("msgconfig:info")
	public R info(@PathVariable("id") Long id){
		MsgconfigEntity msgconfig = msgconfigService.queryObject(id);
		
		return R.ok().put("msgconfig", msgconfig);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("msgconfig:save")
	public R save(@RequestBody MsgconfigEntity msgconfig){
	
		BeanUtil.fillCCUUD(msgconfig, getUserId(), getUserId());
		msgconfigService.save(msgconfig);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("msgconfig:update")
	public R update(@RequestBody MsgconfigEntity msgconfig){
		
		BeanUtil.fillCCUUD(msgconfig, getUserId());
		msgconfigService.update(msgconfig);
		
		return R.ok();
	}

	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete/{id}")
	@RequiresPermissions("msgconfig:delete")
	public R delete(@PathVariable("id") Long id){
		msgconfigService.delete(id);
		return R.ok();
	}
	
	
}
