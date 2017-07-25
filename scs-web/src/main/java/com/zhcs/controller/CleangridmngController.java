package com.zhcs.controller;

import java.util.ArrayList;
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
import com.zhcs.entity.GriddesEntity;
import com.zhcs.entity.GridmngEntity;
import com.zhcs.entity.SysDepartmentEntity;
import com.zhcs.service.GriddesService;
import com.zhcs.service.GridmngService;
import com.zhcs.service.SysUserService;
import com.zhcs.utils.BeanUtil;
import com.zhcs.utils.LatLonUtil;
import com.zhcs.utils.PageUtils;
import com.zhcs.utils.R;
import com.zhcs.utils.StringUtil;

//*****************************************************************************
/**
 * <p>Title:CleangridmngController</p>
 * <p>Description: 清扫保洁网格</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
@Controller
@RequestMapping("gridmng")
public class CleangridmngController extends AbstractController  {
	@Autowired
	private GridmngService gridmngService;
	@Autowired
	private GriddesService griddesService;
	@Autowired
	private SysUserService sysUserService;
	
	@RequestMapping("/cleangridmng.html")
	public String list(){
		return "gridmng/cleangridmng.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/cleanList")
	@RequiresPermissions("cleangridmng:list")
	public R list(String sidx, String order, Integer page, Integer limit, String dept){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sidx", sidx);
		map.put("order", order);
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		//查询列表数据
		List<Map<String, Object>> cleangridmngList = null;
		int total = 0;
		if(StringUtil.isBlank(dept)){
			List<SysDepartmentEntity> projectList = sysUserService.getUserProject(getUserId());
			if(StringUtil.isValid(projectList)){
				dept = StringUtil.valueOf(projectList.get(0).getId());
			} else {
				dept = null;
			}
		}
		if(StringUtil.isValid(dept)){
			map.put("dept", dept);
			cleangridmngList = gridmngService.queryList(map);
			total = gridmngService.queryTotal(map);
		}
		PageUtils pageUtil = new PageUtils(cleangridmngList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	/**
	 * 获取用户部门信息
	 */
	@ResponseBody
	@RequestMapping("/getUserProjectMsg")
	public R getUserProjectMsg(){
		List<SysDepartmentEntity> projectList = sysUserService.getUserProject(getUserId());
		return R.ok().putData(projectList);
	}
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{id}")
	@RequiresPermissions("cleangridmng:info")
	public R info(@PathVariable("id") Long id){
		Map<String, Object> gridmng = gridmngService.queryObject(id);
		
		return R.ok().put("gridmng", gridmng);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("cleangridmng:save")
	public R save(@RequestBody Map<String, Object> map){
		@SuppressWarnings("unchecked")
		List<String> lnglatList = (List<String>)map.get("lnglatList");
		List<GriddesEntity> list = new ArrayList<GriddesEntity>();
		GriddesEntity griddes = null;
		
		GridmngEntity gme = new GridmngEntity();
		gme.setName(StringUtil.valueOf(map.get("name")));
		gme.setType(PlatformContext.getGoalbalContext("cleaning", String.class));
		gme.setUid(StringUtil.valueOf(map.get("uid")));
		gme.setDept(Long.valueOf(StringUtil.valueOf(map.get("dept"))));
		BeanUtil.fillCCUUD(gme);
		gridmngService.save(gme);
		int i = 1;
		for (String str : lnglatList) {
			griddes = new GriddesEntity();
			griddes.setFid(gme.getId());
			griddes.setSort(Long.valueOf(i));
			griddes.setLon(str.split(",")[0]);
			griddes.setLat(str.split(",")[1]);
			BeanUtil.fillCCUUD(griddes, getUserId(), getUserId());
			list.add(griddes);
			i++;
		}
		griddesService.saveBatch(list);
		return R.ok();
	}
	
	/**
	 * 生成网格
	 */
	@ResponseBody
	@RequestMapping("/generateGrid")
	@RequiresPermissions("cleangridmng:generateGrid")
	public R generateGrid(@RequestBody Map<String, Object> map){
		@SuppressWarnings("unchecked")
		List<String> lnglatList = (List<String>)map.get("lnglatList");
		String ra = PlatformContext.getGoalbalContext("raidus", "1000", String.class);
		int raidus = Integer.parseInt(ra);
		Map<String, Object> point = null;
		int i = 1;
		List<List<Double>> list = new ArrayList<List<Double>>(), list2 = new ArrayList<List<Double>>();
		List<Double> temp = null, temp2 = null;
		for (String str : lnglatList) {
			point = LatLonUtil.getAround(Double.parseDouble(str.split(",")[1]), Double.parseDouble(str.split(",")[0]), raidus);
			if(i == 1){
				temp = new ArrayList<Double>();
				temp.add(Double.parseDouble(StringUtil.valueOf(point.get("minLng"))));
				temp.add(Double.parseDouble(StringUtil.valueOf(point.get("minLat"))));
				list.add(temp);
			} 
			if(i == lnglatList.size()){
				temp2 = new ArrayList<Double>();
				temp2.add(Double.parseDouble(StringUtil.valueOf(point.get("maxLng"))));
				temp2.add(Double.parseDouble(StringUtil.valueOf(point.get("maxLat"))));
				list2.add(temp2);
			}
			temp = new ArrayList<Double>();
			temp.add(Double.parseDouble(StringUtil.valueOf(point.get("minLng"))));
			temp.add(Double.parseDouble(StringUtil.valueOf(point.get("minLat"))));
			list.add(temp);
			temp2 = new ArrayList<Double>();
			temp2.add(Double.parseDouble(StringUtil.valueOf(point.get("maxLng"))));
			temp2.add(Double.parseDouble(StringUtil.valueOf(point.get("maxLat"))));
			list2.add(temp2);
			i++;
		}
		list.addAll(StringUtil.ListReverse(list2));
		return R.ok().putData(list);
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("cleangridmng:update")
	public R update(@RequestBody  Map<String, Object> map){
		@SuppressWarnings("unchecked")
		List<String> lnglatList = (List<String>)map.get("lnglatList");
		Long id = Long.valueOf(StringUtil.valueOf(map.get("id")));
		List<GriddesEntity> list = new ArrayList<GriddesEntity>();
		GriddesEntity griddes = null;
		GridmngEntity gme = new GridmngEntity();
		gme.setId(id);
		gme.setName(StringUtil.valueOf(map.get("name")));
		gme.setType(PlatformContext.getGoalbalContext("cleaning", String.class));
		gme.setUid(StringUtil.valueOf(map.get("uid")));
		gme.setDept(Long.valueOf(StringUtil.valueOf(map.get("dept"))));
		BeanUtil.fillCCUUD(gme);
		gridmngService.update(gme);
		// 删除副表
		griddesService.deleteByFid(id);
		int i = 1;
		for (String str : lnglatList) {
			griddes = new GriddesEntity();
			griddes.setFid(gme.getId());
			griddes.setSort(Long.valueOf(i));
			griddes.setLon(str.split(",")[0]);
			griddes.setLat(str.split(",")[1]);
			BeanUtil.fillCCUUD(griddes, getUserId(), getUserId());
			list.add(griddes);
			i++;
		}
		griddesService.saveBatch(list);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete/{name}")
	@RequiresPermissions("cleangridmng:delete")
	public R delete(@PathVariable("id") Long id){
		gridmngService.delete(id);
		// 删除副表
		griddesService.deleteByFid(id);
		return R.ok();
	}
	
}
