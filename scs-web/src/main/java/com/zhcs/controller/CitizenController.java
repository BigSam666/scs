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
import com.zhcs.entity.CitizenEntity;
import com.zhcs.service.CitizenService;
import com.zhcs.utils.BeanUtil;
import com.zhcs.utils.PageUtils;
import com.zhcs.utils.R;
import com.zhcs.utils.StringUtil;

//*****************************************************************************
/**
 * <p>Title:CitizenController</p>
 * <p>Description: 市民</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
@Controller
@RequestMapping("citizen")
public class CitizenController extends AbstractController  {
	@Autowired
	private CitizenService citizenService;
	
	@RequestMapping("/citizen.html")
	public String list(){
		return "citizen/citizen.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("citizen:list")
	public R list(String sidx, String order, Integer page, Integer limit){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sidx", sidx);
		map.put("order", order);
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		
		//查询列表数据
		List<CitizenEntity> citizenList = citizenService.queryList(map);
		int total = citizenService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(citizenList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	/**
	 * 用于获取接收人列表
	 */
	@ResponseBody
	@RequestMapping("/getUserList")
	@RequiresPermissions("citizen:getUserList")
	public R getUserList(){
		List<Map<String, Object>> userList = citizenService.getUserList();
		
		return R.ok().putData(userList);
	}
	
	/**
	 * 获取市民用户树
	 */
	@ResponseBody
	@RequestMapping("/getUserTree")
	@RequiresPermissions("citizen:getUserTree")
	public R getUserTree(){
		//查询列表数据
		List<Map<String, Object>> userList = citizenService.getUserTree();
		
		return R.ok().put("userList", userList);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{id}")
	@RequiresPermissions("citizen:info")
	public R info(@PathVariable("id") Long id){
		CitizenEntity citizen = citizenService.queryObject(id);
		
		return R.ok().put("citizen", citizen);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("citizen:save")
	public R save(@RequestBody CitizenEntity citizen){
	
		BeanUtil.fillCCUUD(citizen, getUserId(), getUserId());
		citizenService.save(citizen);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("citizen:update")
	public R update(@RequestBody CitizenEntity citizen){
		
		BeanUtil.fillCCUUD(citizen, getUserId());
		citizenService.update(citizen);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete/{id}")
	@RequiresPermissions("citizen:delete")
	public R delete(@PathVariable("id") Long id){
		if(PlatformContext.getGoalbalContext("adminId", String.class).equals(StringUtil.valueOf(getUserId()))){
			citizenService.delete(id);
		} else {
			CitizenEntity citizen = new CitizenEntity();
			citizen.setId(id);
			/*citizen.setStatus("0");*/
			BeanUtil.fillCCUUD(citizen, getUserId());
			citizenService.update(citizen);
		}
		return R.ok();
	}
	
}
