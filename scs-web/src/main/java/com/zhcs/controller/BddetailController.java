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

import com.zhcs.entity.BddetailEntity;
import com.zhcs.service.BddetailService;
import com.zhcs.utils.BeanUtil;
import com.zhcs.utils.PageUtils;
import com.zhcs.utils.R;

//*****************************************************************************
/**
 * <p>Title:BddetailController</p>
 * <p>Description: 预算明细</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
@Controller
@RequestMapping("bddetail")
public class BddetailController extends AbstractController  {
	@Autowired
	private BddetailService bddetailService;
	
	@RequestMapping("/bddetail.html")
	public String list(){
		return "bddetail/bddetail.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("bddetail:list")
	public R list(String sidx, String order, Integer page, Integer limit){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sidx", sidx);
		map.put("order", order);
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		
		//查询列表数据
		List<BddetailEntity> bddetailList = bddetailService.queryList(map);
		int total = bddetailService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(bddetailList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{id}")
	@RequiresPermissions("bddetail:info")
	public R info(@PathVariable("id") Long id){
		BddetailEntity bddetail = bddetailService.queryObject(id);
		
		return R.ok().put("bddetail", bddetail);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("bddetail:save")
	public R save(@RequestBody BddetailEntity bddetail){
	
		BeanUtil.fillCCUUD(bddetail, getUserId(), getUserId());
		bddetailService.save(bddetail);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("bddetail:update")
	public R update(@RequestBody BddetailEntity bddetail){
		
		BeanUtil.fillCCUUD(bddetail, getUserId());
		bddetailService.update(bddetail);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete/{id}")
	@RequiresPermissions("bddetail:delete")
	public R delete(@PathVariable("id") Long id){
		bddetailService.delete(id);
		return R.ok();
	}
	
}
