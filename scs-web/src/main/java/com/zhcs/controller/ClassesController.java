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

import com.zhcs.entity.ClassesEntity;
import com.zhcs.service.ClassesService;
import com.zhcs.utils.BeanUtil;
import com.zhcs.utils.PageUtils;
import com.zhcs.utils.R;

//*****************************************************************************
/**
 * <p>Title:ClassesController</p>
 * <p>Description: 班次管理</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
@Controller
@RequestMapping("classes")
public class ClassesController extends AbstractController  {
	@Autowired
	private ClassesService classesService;
	@RequestMapping("/classes.html")
	public String list(){
		return "classes/classes.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("classes:list")
	public R list(String sidx, String name, String order, Integer page, Integer limit){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", name);
		map.put("sidx", sidx);
		map.put("order", order);
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		//查询列表数据
//		List<Map<String, Object>> classesList = classesService.queryListMap(map);
		List<Map<String, Object>> classesList = classesService.queryListMap1(map);
		int total = classesService.queryTotal(map);
		PageUtils pageUtil = new PageUtils(classesList, total, limit, page);
		return R.ok().put("page", pageUtil);
	}
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{id}")
	@RequiresPermissions("classes:info")
	public R info(@PathVariable("id") Long id){
		List<Map<String, Object>> classes = classesService.queryObjectMap(id);
		return R.ok().put("classes", classes);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("classes:save")
	public R save(@RequestBody ClassesEntity classes){
		BeanUtil.fillCCUUD(classes, getUserId(), getUserId());
		classesService.save(classes);
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("classes:update")
	public R update(@RequestBody Map<String, Object> classes){
		classes.remove("crtid");
		ClassesEntity classesEntity = BeanUtil.map2Bean(classes, ClassesEntity.class);
		BeanUtil.fillCCUUD(classesEntity,getUserId());
		classesService.update(classesEntity);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete/{id}")
	@RequiresPermissions("classes:delete")
	public R delete(@PathVariable("id") Long id){
		classesService.delete(id);
		return R.ok();
	}
	
}
