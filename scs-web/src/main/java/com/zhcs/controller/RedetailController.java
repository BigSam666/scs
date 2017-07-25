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
import com.zhcs.entity.RedetailEntity;
import com.zhcs.service.RedetailService;
import com.zhcs.utils.BeanUtil;
import com.zhcs.utils.PageUtils;
import com.zhcs.utils.R;
import com.zhcs.utils.StringUtil;

//*****************************************************************************
/**
 * <p>Title:RedetailController</p>
 * <p>Description: 维修明细</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
@Controller
@RequestMapping("redetail")
public class RedetailController extends AbstractController  {
	@Autowired
	private RedetailService redetailService;
	
	@RequestMapping("/redetail.html")
	public String list(){
		return "redetail/redetail.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("redetail:list")
	public R list(String sidx, String order, Integer page, Integer limit){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sidx", sidx);
		map.put("order", order);
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		
		//查询列表数据
		List<RedetailEntity> redetailList = redetailService.queryList(map);
		int total = redetailService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(redetailList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	/**
	 * 根据维修id查询维修明细
	 */
	@ResponseBody
	@RequestMapping("/listByReid/{reid}")
	@RequiresPermissions("redetail:list")
	public R listByInsid(@PathVariable("reid") Long reid) {
		List<RedetailEntity> redetailList = redetailService.queryListByReid(reid);
		return R.ok().putData(redetailList);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{id}")
	@RequiresPermissions("redetail:info")
	public R info(@PathVariable("id") Long id){
		RedetailEntity redetail = redetailService.queryObject(id);
		
		return R.ok().put("redetail", redetail);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("redetail:save")
	public R save(@RequestBody RedetailEntity redetail){
	
		BeanUtil.fillCCUUD(redetail, getUserId(), getUserId());
		redetailService.save(redetail);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("redetail:update")
	public R update(@RequestBody RedetailEntity redetail){
		
		BeanUtil.fillCCUUD(redetail, getUserId());
		redetailService.update(redetail);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete/{id}")
	@RequiresPermissions("redetail:delete")
	public R delete(@PathVariable("id") Long id){
		if(PlatformContext.getGoalbalContext("adminId", String.class).equals(StringUtil.valueOf(getUserId()))){
			redetailService.delete(id);
		} else {
			RedetailEntity redetail = new RedetailEntity();
			redetail.setId(id);
			redetail.setStatus("0");
			BeanUtil.fillCCUUD(redetail, getUserId());
			redetailService.update(redetail);
		}
		return R.ok();
	}
	
}
