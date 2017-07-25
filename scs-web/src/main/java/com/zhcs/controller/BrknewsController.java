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

import com.zhcs.entity.BrknewsEntity;
import com.zhcs.service.BrknewsService;
import com.zhcs.utils.BeanUtil;
import com.zhcs.utils.PageUtils;
import com.zhcs.utils.R;

//*****************************************************************************
/**
 * <p>Title:BrknewsController</p>
 * <p>Description: 爆料事件</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
@Controller
@RequestMapping("brknews")
public class BrknewsController extends AbstractController  {
	@Autowired
	private BrknewsService brknewsService;
	
	@RequestMapping("/brknews.html")
	public String list(){
		return "brknews/brknews.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("brknews:list")
	public R list(String sidx, String order, Integer page, Integer limit){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sidx", sidx);
		map.put("order", order);
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		
		//查询列表数据
		List<BrknewsEntity> brknewsList = brknewsService.queryList(map);
		int total = brknewsService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(brknewsList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{id}")
	@RequiresPermissions("brknews:info")
	public R info(@PathVariable("id") Long id){
		BrknewsEntity brknews = brknewsService.queryObject(id);
		
		return R.ok().put("brknews", brknews);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("brknews:save")
	public R save(@RequestBody BrknewsEntity brknews){
	
		BeanUtil.fillCCUUD(brknews, getUserId(), getUserId());
		brknewsService.save(brknews);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("brknews:update")
	public R update(@RequestBody BrknewsEntity brknews){
		
		BeanUtil.fillCCUUD(brknews, getUserId());
		brknewsService.update(brknews);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete/{id}")
	@RequiresPermissions("brknews:delete")
	public R delete(@PathVariable("id") Long id){
		brknewsService.delete(id);
		return R.ok();
	}
	
}
