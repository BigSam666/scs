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
import com.zhcs.entity.InsdetailEntity;
import com.zhcs.service.InsdetailService;
import com.zhcs.utils.BeanUtil;
import com.zhcs.utils.PageUtils;
import com.zhcs.utils.StringUtil;
import com.zhcs.utils.R;

//*****************************************************************************
/**
 * <p>Title:InsdetailController</p>
 * <p>Description: 投保明细</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
@Controller
@RequestMapping("insdetail")
public class InsdetailController extends AbstractController  {
	@Autowired
	private InsdetailService insdetailService;
	
	@RequestMapping("/insdetail.html")
	public String list(){
		return "insdetail/insdetail.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("insdetail:list")
	public R list(String sidx, String order, Integer page, Integer limit){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sidx", sidx);
		map.put("order", order);
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		
		//查询列表数据
		List<InsdetailEntity> insdetailList = insdetailService.queryList(map);
		int total = insdetailService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(insdetailList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	/**
	 * 根据保险id查询保险明细
	 */
	@ResponseBody
	@RequestMapping("/listByInsid/{insid}")
	@RequiresPermissions("insdetail:list")
	public R listByInsid(@PathVariable("insid") Long insid) {
		List<InsdetailEntity> insdetailList = insdetailService.queryListByInsid(insid);
		return R.ok().putData(insdetailList);
	}
	
	
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{id}")
	@RequiresPermissions("insdetail:info")
	public R info(@PathVariable("id") Long id){
		InsdetailEntity insdetail = insdetailService.queryObject(id);
		
		return R.ok().put("insdetail", insdetail);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("insdetail:save")
	public R save(@RequestBody InsdetailEntity insdetail){
	
		BeanUtil.fillCCUUD(insdetail, getUserId(), getUserId());
		insdetailService.save(insdetail);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("insdetail:update")
	public R update(@RequestBody InsdetailEntity insdetail){
		
		BeanUtil.fillCCUUD(insdetail, getUserId());
		insdetailService.update(insdetail);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete/{id}")
	@RequiresPermissions("insdetail:delete")
	public R delete(@PathVariable("id") Long id){
		if(PlatformContext.getGoalbalContext("adminId", String.class).equals(StringUtil.valueOf(getUserId()))){
			insdetailService.delete(id);
		} else {
			InsdetailEntity insdetail = new InsdetailEntity();
			insdetail.setId(id);
			insdetail.setStatus("0");
			BeanUtil.fillCCUUD(insdetail, getUserId());
			insdetailService.update(insdetail);
		}
		return R.ok();
	}
	
}
