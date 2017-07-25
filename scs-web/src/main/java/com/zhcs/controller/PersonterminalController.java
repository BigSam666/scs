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

import com.zhcs.entity.PersonterminalEntity;
import com.zhcs.service.PersonterminalService;
import com.zhcs.utils.BeanUtil;
import com.zhcs.utils.PageUtils;
import com.zhcs.utils.R;
import com.zhcs.utils.StringUtil;

//*****************************************************************************
/**
 * <p>Title:PersonterminalController</p>
 * <p>Description: 人员终端</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
@Controller
@RequestMapping("personterminal")
public class PersonterminalController extends AbstractController  {
	@Autowired
	private PersonterminalService personterminalService;
	
	@RequestMapping("/personterminal.html")
	public String list(){
		return "personterminal/personterminal.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("personterminal:list")
	public R list(String sidx,Integer qdevtype,String keyword, String order, Integer page, Integer limit){
		Map<String, Object> map = new HashMap<String, Object>();
		if (qdevtype != null && qdevtype != -1) { // 选择所有时值为-1
			map.put("qdevtype", qdevtype);
		}
		if (!StringUtil.isBlank(keyword)) {
			map.put("keyword", keyword.trim());
		}
		map.put("sidx", sidx);
		map.put("order", order);
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		
		//查询列表数据
		List<PersonterminalEntity> personterminalList = personterminalService.queryList(map);
		int total = personterminalService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(personterminalList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{id}")
	@RequiresPermissions("personterminal:info")
	public R info(@PathVariable("id") Long id){
		PersonterminalEntity personterminal = personterminalService.queryObject(id);
		
		return R.ok().put("personterminal", personterminal);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("personterminal:save")
	public R save(@RequestBody PersonterminalEntity personterminal){
	
		BeanUtil.fillCCUUD(personterminal, getUserId(), getUserId());
		personterminalService.save(personterminal);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("personterminal:update")
	public R update(@RequestBody PersonterminalEntity personterminal){
		
		BeanUtil.fillCCUUD(personterminal, getUserId());
		personterminalService.update(personterminal);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete/{id}")
	@RequiresPermissions("personterminal:delete")
	public R delete(@PathVariable("id") Long id){
		personterminalService.delete(id);
		return R.ok();
	}
	
}
