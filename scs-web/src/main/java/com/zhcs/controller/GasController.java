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
import com.zhcs.entity.GasEntity;
import com.zhcs.service.GasService;
import com.zhcs.utils.BeanUtil;
import com.zhcs.utils.PageUtils;
import com.zhcs.utils.StringUtil;
import com.zhcs.utils.R;

//*****************************************************************************
/**
 * <p>Title:GasController</p>
 * <p>Description: 油耗管理</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
@Controller
@RequestMapping("gas")
public class GasController extends AbstractController  {
	@Autowired
	private GasService gasService;
	
	@RequestMapping("/gas.html")
	public String list(){
		return "gas/gas.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("gas:list")
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
//		List<GasEntity> gasList = gasService.queryList(map);
		List<GasEntity> gasList = gasService.queryList1(map);
		int total = gasService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(gasList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{id}")
	@RequiresPermissions("gas:info")
	public R info(@PathVariable("id") Long id){
		GasEntity gas = gasService.queryObject(id);
		
		return R.ok().put("gas", gas);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("gas:save")
	public R save(@RequestBody GasEntity gas){
	
		BeanUtil.fillCCUUD(gas, getUserId(), getUserId());
		gasService.save(gas);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("gas:update")
	public R update(@RequestBody GasEntity gas){
		
		BeanUtil.fillCCUUD(gas, getUserId());
		gasService.update(gas);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete/{id}")
	@RequiresPermissions("gas:delete")
	public R delete(@PathVariable("id") Long id){
		if(PlatformContext.getGoalbalContext("adminId", String.class).equals(StringUtil.valueOf(getUserId()))){
			gasService.delete(id);
		} else {
			GasEntity gas = new GasEntity();
			gas.setId(id);
			gas.setStatus("0");
			BeanUtil.fillCCUUD(gas, getUserId());
			gasService.update(gas);
		}
		return R.ok();
	}
	
}
