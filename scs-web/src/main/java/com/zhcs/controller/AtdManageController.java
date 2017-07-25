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
import com.zhcs.entity.AtdManageEntity;
import com.zhcs.service.AtdManageService;
import com.zhcs.utils.BeanUtil;
import com.zhcs.utils.PageUtils;
import com.zhcs.utils.ServiceException;
import com.zhcs.utils.StringUtil;
import com.zhcs.utils.R;

//*****************************************************************************
/**
 * <p>Title:AtdManageController</p>
 * <p>Description: 考勤管理申报</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
@Controller
@RequestMapping("atdmanage")
public class AtdManageController extends AbstractController  {
	@Autowired
	private AtdManageService atdManageService;
	
	@RequestMapping("/atdmanage.html")
	public String list(){
		return "atdmanage/atdmanage.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("atdmanage:list")
	public R list(String sidx, String order, Integer page, Integer limit,String condition){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sidx", sidx);
		map.put("order", order);
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);

		map.put("condition", condition);
		//查询列表数据
//		List<AtdManageEntity> atdManageList = atdManageService.queryList(map);
		List<AtdManageEntity> atdManageList = atdManageService.queryList1(map);
		int total = atdManageService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(atdManageList, total, limit, page);

		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{id}")
	@RequiresPermissions("atdmanage:info")
	public R info(@PathVariable("id") Long id){
		AtdManageEntity atdManage = atdManageService.queryObject(id);

		return R.ok().put("atdManage", atdManage);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("atdmanage:save")
	public R save(@RequestBody AtdManageEntity atdManage){
		try {
			BeanUtil.fillCCUUD(atdManage, getUserId(), getUserId());
			atdManageService.save(atdManage);
		} catch (ServiceException e) {
			return R.error(e.getMessage());
		}
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("atdmanage:update")
	public R update(@RequestBody AtdManageEntity atdManage){
		
		BeanUtil.fillCCUUD(atdManage, getUserId());
		atdManageService.update(atdManage);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete/{id}")
	@RequiresPermissions("atdmanage:delete")
	public R delete(@PathVariable("id") Long id){
		if(PlatformContext.getGoalbalContext("adminId", String.class).equals(StringUtil.valueOf(getUserId()))){
			atdManageService.delete(id);
		} else {
			AtdManageEntity atdManage = new AtdManageEntity();
			atdManage.setId(id);
			atdManage.setStatus("0");
			BeanUtil.fillCCUUD(atdManage, getUserId());
			atdManageService.update(atdManage);
		}
		return R.ok();
	}
	
}
