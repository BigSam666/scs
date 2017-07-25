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
import com.zhcs.entity.CartypeEntity;
import com.zhcs.service.CartypeService;
import com.zhcs.utils.BeanUtil;
import com.zhcs.utils.PageUtils;
import com.zhcs.utils.R;
import com.zhcs.utils.StringUtil;

//*****************************************************************************
/**
 * <p>Title:CartypeController</p>
 * <p>Description: 车辆类型</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
@Controller
@RequestMapping("cartype")
public class CartypeController extends AbstractController  {
	@Autowired
	private CartypeService cartypeService;
	
	@RequestMapping("/cartype.html")
	public String list(){
		return "cartype/cartype.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("cartype:list")
	public R list(String sidx, String order, Integer page, Integer limit){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sidx", sidx);
		map.put("order", order);
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		
		//查询列表数据
		List<CartypeEntity> cartypeList = cartypeService.queryList(map);
		int total = cartypeService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(cartypeList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	/**
	 * 列出所有
	 */
	@ResponseBody
	@RequestMapping("/listall")
	@RequiresPermissions("cartype:list")
	public R listall() {
		// 查询列表数据
		List<CartypeEntity> cartypeList = cartypeService
				.queryList(new HashMap<String, Object>());
		return R.ok().putData(cartypeList);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{id}")
	@RequiresPermissions("cartype:info")
	public R info(@PathVariable("id") Long id){
		CartypeEntity cartype = cartypeService.queryObject(id);
		
		return R.ok().put("cartype", cartype);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("cartype:save")
	public R save(@RequestBody CartypeEntity cartype){
	
		BeanUtil.fillCCUUD(cartype, getUserId(), getUserId());
		cartypeService.save(cartype);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("cartype:update")
	public R update(@RequestBody CartypeEntity cartype){
		
		BeanUtil.fillCCUUD(cartype, getUserId());
		cartypeService.update(cartype);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete/{id}")
	@RequiresPermissions("cartype:delete")
	public R delete(@PathVariable("id") Long id){
		if(PlatformContext.getGoalbalContext("adminId", String.class).equals(StringUtil.valueOf(getUserId()))){
			cartypeService.delete(id);
		} else {
			CartypeEntity cartype = new CartypeEntity();
			cartype.setId(id);
			cartype.setStatus("0");
			BeanUtil.fillCCUUD(cartype, getUserId());
			cartypeService.update(cartype);
		}
		return R.ok();
	}
	
}
