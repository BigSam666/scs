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
import com.zhcs.entity.QltsuEntity;
import com.zhcs.service.QltsuService;
import com.zhcs.utils.BeanUtil;
import com.zhcs.utils.PageUtils;
import com.zhcs.utils.StringUtil;
import com.zhcs.utils.R;

//*****************************************************************************
/**
 * <p>Title:QltsuController</p>
 * <p>Description: 质量督导</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
@Controller
@RequestMapping("qltsu")
public class QltsuController extends AbstractController  {
	@Autowired
	private QltsuService qltsuService;
	
	@RequestMapping("/qltsu.html")
	public String list(){
		return "qltsu/qltsu.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("qltsu:list")
	public R list(String sidx, String name, String order, Integer page, Integer limit){
		Map<String, Object> map = new HashMap<String, Object>();
		if (!StringUtil.isBlank(name)) {
			map.put("name", name.trim());
		}
		map.put("sidx", sidx);
		map.put("order", order);
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		
		//查询列表数据
//		List<QltsuEntity> qltsuList = qltsuService.queryList(map);
		List<QltsuEntity> qltsuList = qltsuService.queryList1(map);
		int total = qltsuService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(qltsuList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{id}")
	@RequiresPermissions("qltsu:info")
	public R info(@PathVariable("id") Long id){
		QltsuEntity qltsu = qltsuService.queryObject(id);
		
		return R.ok().put("qltsu", qltsu);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("qltsu:save")
	public R save(@RequestBody QltsuEntity qltsu){
	
		BeanUtil.fillCCUUD(qltsu, getUserId(), getUserId());
		qltsuService.save(qltsu);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("qltsu:update")
	public R update(@RequestBody QltsuEntity qltsu){
		
		BeanUtil.fillCCUUD(qltsu, getUserId());
		qltsuService.update(qltsu);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete/{id}")
	@RequiresPermissions("qltsu:delete")
	public R delete(@PathVariable("id") Long id){
		if(PlatformContext.getGoalbalContext("adminId", String.class).equals(StringUtil.valueOf(getUserId()))){
			qltsuService.delete(id);
		} else {
			QltsuEntity qltsu = new QltsuEntity();
			qltsu.setId(id);
			qltsu.setStatus("0");
			BeanUtil.fillCCUUD(qltsu, getUserId());
			qltsuService.update(qltsu);
		}
		return R.ok();
	}
	
}
