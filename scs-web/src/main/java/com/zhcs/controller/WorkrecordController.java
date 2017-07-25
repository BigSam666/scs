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

import com.zhcs.entity.WorkrecordEntity;
import com.zhcs.service.BaseCodeService;
import com.zhcs.service.WorkrecordService;
import com.zhcs.utils.BeanUtil;
import com.zhcs.utils.PageUtils;
import com.zhcs.utils.R;
import com.zhcs.utils.StringUtil;

//*****************************************************************************
/**
 * <p>Title:WorkrecordController</p>
 * <p>Description: 工作记录</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
@Controller
@RequestMapping("workrecord")
public class WorkrecordController extends AbstractController  {
	@Autowired
	private WorkrecordService workrecordService;
	@Autowired
	private BaseCodeService baseCodeService;
	
	@RequestMapping("/workrecord.html")
	public String list(){
		return "workrecord/workrecord.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("workrecord:list")
	public R list(String sidx, String order, Integer page, Integer limit, String busseg, String expl,String condition){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sidx", sidx);
		map.put("busseg", busseg);
		map.put("expl", expl);
		map.put("order", order);
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		
		map.put("condition", condition);
		
		//查询列表数据
		List<Map<String, Object>> workrecordList = workrecordService.queryList(map);
		Map<String, String> m = null;
		String data = "";
		for (Map<String, Object> map2 : workrecordList) {
			data = StringUtil.valueOf(map2.get("busseg"));
			m = new HashMap<String, String>();
			m.put("data", data);
			m.put("value", baseCodeService.selectByTypeValue("biztype", data).getCnm());
			map2.put("busseg", m);
		}
		int total = workrecordService.queryTotal(map);
		PageUtils pageUtil = new PageUtils(workrecordList, total, limit, page);
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{id}")
	@RequiresPermissions("workrecord:info")
	public R info(@PathVariable("id") Long id){
		WorkrecordEntity workrecord = workrecordService.queryObject(id);
		
		return R.ok().put("workrecord", workrecord);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("workrecord:save")
	public R save(@RequestBody WorkrecordEntity workrecord){
	
		BeanUtil.fillCCUUD(workrecord, getUserId(), getUserId());
		workrecordService.save(workrecord);
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("workrecord:update")
	public R update(@RequestBody WorkrecordEntity workrecord){
		
		BeanUtil.fillCCUUD(workrecord, getUserId());
		workrecordService.update(workrecord);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete/{id}")
	@RequiresPermissions("workrecord:delete")
	public R delete(@PathVariable("id") Long id){
			workrecordService.delete(id);
		return R.ok();
	}
	
}
