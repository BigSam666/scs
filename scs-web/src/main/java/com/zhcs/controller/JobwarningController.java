package com.zhcs.controller;

import com.zhcs.entity.JobwarningEntity;
import com.zhcs.service.JobwarningService;
import com.zhcs.utils.BeanUtil;
import com.zhcs.utils.PageUtils;
import com.zhcs.utils.R;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//*****************************************************************************
/**
 * <p>Title:JobwarningController</p>
 * <p>Description: jobwarning工作预警</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
@Controller
@RequestMapping("jobwarning")
public class JobwarningController extends AbstractController  {
	@Autowired
	private JobwarningService jobwarningService;
	
	@RequestMapping("/jobwarning.html")
	public String list(){
		return "jobwarning/jobwarning.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("jobwarning:list")
	public R list(String sidx, String order, Integer page, Integer limit){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sidx", sidx);
		map.put("order", order);
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		
		//查询列表数据
		List<Map<String, Object>> jobwarningList = jobwarningService.queryListMap(map);
		int total = jobwarningService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(jobwarningList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{id}")
	@RequiresPermissions("jobwarning:info")
	public R info(@PathVariable("id") Long id){
		JobwarningEntity jobwarning = jobwarningService.queryObject(id);
		
		return R.ok().put("jobwarning", jobwarning);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("jobwarning:save")
	public R save(@RequestBody JobwarningEntity jobwarning){
	
		BeanUtil.fillCCUUD(jobwarning, getUserId(), getUserId());
		jobwarningService.save(jobwarning);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("jobwarning:update")
	public R update(@RequestBody JobwarningEntity jobwarning){
		
		BeanUtil.fillCCUUD(jobwarning, getUserId());
		jobwarningService.update(jobwarning);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete/{id}")
	@RequiresPermissions("jobwarning:delete")
	public R delete(@PathVariable("id") Long id){
		jobwarningService.delete(id);
		return R.ok();
	}
	
}
