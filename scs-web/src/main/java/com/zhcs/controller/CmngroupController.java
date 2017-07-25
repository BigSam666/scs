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

import com.zhcs.entity.CmngroupEntity;
import com.zhcs.service.CmngroupService;
import com.zhcs.utils.BeanUtil;
import com.zhcs.utils.PageUtils;
import com.zhcs.utils.R;

//*****************************************************************************
/**
 * <p>Title:CmngroupController</p>
 * <p>Description: 通讯组</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
@Controller
@RequestMapping("cmngroup")
public class CmngroupController extends AbstractController  {
	@Autowired
	private CmngroupService cmngroupService;
	
	@RequestMapping("/cmngroup.html")
	public String list(){
		return "cmngroup/cmngroup.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("cmngroup:list")
	public R list(Integer page, Integer limit,String nm){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		map.put("nm", nm);
		//查询列表数据
		/*List<CmngroupEntity> cmngroupList = cmngroupService.queryList(map);
		int total = cmngroupService.queryTotal(map);*/
		
		Map<String, Object> dataMap =  cmngroupService.queryPageList(map);
		List<Map<String, Object>> cmngroupList = (List<Map<String, Object>>) dataMap.get("datalist");
		int total = (int) dataMap.get("total");
		
		PageUtils pageUtil = new PageUtils(cmngroupList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	/**
	 * 获取所有
	 */
	@ResponseBody
	@RequestMapping("/listAll")
	@RequiresPermissions("cmngroup:list")
	public R listAll(Integer page, Integer limit){
		//查询列表数据
		List<CmngroupEntity> cmngroupList = cmngroupService.queryList(new HashMap<String, Object>());
		return R.ok().putData(cmngroupList);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{id}")
	@RequiresPermissions("cmngroup:info")
	public R info(@PathVariable("id") Long id){
		CmngroupEntity cmngroup = cmngroupService.queryObject(id);
		
		return R.ok().put("cmngroup", cmngroup);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("cmngroup:save")
	public R save(@RequestBody CmngroupEntity cmngroup){
	
		BeanUtil.fillCCUUD(cmngroup, getUserId(), getUserId());
		cmngroupService.save(cmngroup);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("cmngroup:update")
	public R update(@RequestBody CmngroupEntity cmngroup){
		
		BeanUtil.fillCCUUD(cmngroup, getUserId());
		cmngroupService.update(cmngroup);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("cmngroup:delete")
	public R delete(@RequestBody Long[] ids){
		cmngroupService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
