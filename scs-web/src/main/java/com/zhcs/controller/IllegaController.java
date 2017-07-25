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
import com.zhcs.entity.IllegaEntity;
import com.zhcs.service.IllegaService;
import com.zhcs.utils.BeanUtil;
import com.zhcs.utils.PageUtils;
import com.zhcs.utils.StringUtil;
import com.zhcs.utils.R;

//*****************************************************************************
/**
 * <p>Title:IllegaController</p>
 * <p>Description: 违章管理</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
@Controller
@RequestMapping("illega")
public class IllegaController extends AbstractController  {
	@Autowired
	private IllegaService illegaService;
	
	@RequestMapping("/illega.html")
	public String list(){
		return "illega/illega.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("illega:list")
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
//		List<IllegaEntity> illegaList = illegaService.queryList(map);
		List<IllegaEntity> illegaList = illegaService.queryList1(map);
		int total = illegaService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(illegaList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{id}")
	@RequiresPermissions("illega:info")
	public R info(@PathVariable("id") Long id){
		IllegaEntity illega = illegaService.queryObject(id);
		
		return R.ok().put("illega", illega);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("illega:save")
	public R save(@RequestBody IllegaEntity illega){
	
		BeanUtil.fillCCUUD(illega, getUserId(), getUserId());
		illegaService.save(illega);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("illega:update")
	public R update(@RequestBody IllegaEntity illega){
		
		BeanUtil.fillCCUUD(illega, getUserId());
		illegaService.update(illega);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete/{id}")
	@RequiresPermissions("illega:delete")
	public R delete(@PathVariable("id") Long id){
		if(PlatformContext.getGoalbalContext("adminId", String.class).equals(StringUtil.valueOf(getUserId()))){
			illegaService.delete(id);
		} else {
			IllegaEntity illega = new IllegaEntity();
			illega.setId(id);
			illega.setStatus("0");
			BeanUtil.fillCCUUD(illega, getUserId());
			illegaService.update(illega);
		}
		return R.ok();
	}
	
}
