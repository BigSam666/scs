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
import com.zhcs.entity.AreaEntity;
import com.zhcs.service.AreaService;
import com.zhcs.utils.BeanUtil;
import com.zhcs.utils.PageUtils;
import com.zhcs.utils.R;
import com.zhcs.utils.StringUtil;

//*****************************************************************************
/**
 * <p>Title:AreaController</p>
 * <p>Description: 省市数据 (T_AREA)</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
@Controller
@RequestMapping("area")
public class AreaController extends AbstractController  {
	@Autowired
	private AreaService areaService;
	
	@RequestMapping("/area.html")
	public String list(){
		return "area/area.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("area:list")
	public R list(Integer page, Integer limit){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		
		//查询列表数据
		List<AreaEntity> areaList = areaService.queryList(map);
		int total = areaService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(areaList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{id}")
	@RequiresPermissions("area:info")
	public R info(@PathVariable("id") Long id){
		AreaEntity area = areaService.queryObject(id);
		
		return R.ok().put("area", area);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("area:save")
	public R save(@RequestBody AreaEntity area){
	
		BeanUtil.fillCCUUD(area, getUserId(), getUserId());
		areaService.save(area);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("area:update")
	public R update(@RequestBody AreaEntity area){
		
		BeanUtil.fillCCUUD(area, getUserId());
		areaService.update(area);
		
		return R.ok();
	}

	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete/{id}")
	@RequiresPermissions("department:delete")
	public R delete(@PathVariable("id") Long id){
		if(PlatformContext.getGoalbalContext("adminId", String.class).equals(StringUtil.valueOf(getUserId()))){
			areaService.delete(id);
		} else {
			AreaEntity area = new AreaEntity();
			area.setId(id);
			/*area.setValid("0");*/
			BeanUtil.fillCCUUD(area, getUserId());
			areaService.update(area);
		}
		return R.ok();
	}
	
}
