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

import com.zhcs.entity.BditemEntity;
import com.zhcs.entity.CostrecordEntity;
import com.zhcs.entity.SysUserEntity;
import com.zhcs.service.BditemService;
import com.zhcs.service.CostrecordService;
import com.zhcs.service.SysUserService;
import com.zhcs.utils.BeanUtil;
import com.zhcs.utils.PageUtils;
import com.zhcs.utils.R;
import com.zhcs.utils.StringUtil;

//*****************************************************************************
/**
 * <p>Title:CostrecordController</p>
 * <p>Description: 费用记录</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
@Controller
@RequestMapping("costrecord")
public class CostrecordController extends AbstractController  {
	@Autowired
	private CostrecordService costrecordService;
	@Autowired
	private BditemService bditemService;
	@Autowired
	private SysUserService sysUserService;
	
	@RequestMapping("/costrecord.html")
	public String list(){
		return "costrecord/costrecord.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("costrecord:list")
	public R list(String qdtype, String sidx, String order, Integer page, Integer limit){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sidx", sidx);
		map.put("order", order);
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		if (!StringUtil.isBlank(qdtype)) {
			map.put("qdtype", qdtype.trim());
		}
		
		//查询列表数据
		List<Map<String,Object>> costrecordList = costrecordService.queryListMap(map);
		for (Map<String, Object> costrecord : costrecordList) {
			String dtype = (String) costrecord.get("dtype"); // 费用类型(code值)
			BditemEntity bditemEntity = bditemService.queryObjectByCode(dtype);
			costrecord.put("dtypeName", bditemEntity.getCnm());
			
			Long personId = (Long) costrecord.get("person"); // 申报人员（id值）
			SysUserEntity userEntity = sysUserService.queryObject(personId);
			costrecord.put("personName", userEntity.getRealname());
		}
		
		
		
		int total = costrecordService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(costrecordList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{id}")
	@RequiresPermissions("costrecord:info")
	public R info(@PathVariable("id") Long id){
		CostrecordEntity costrecord = costrecordService.queryObject(id);
		
		return R.ok().put("costrecord", costrecord);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("costrecord:save")
	public R save(@RequestBody CostrecordEntity costrecord){
	
		BeanUtil.fillCCUUD(costrecord, getUserId(), getUserId());
		costrecordService.save(costrecord);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("costrecord:update")
	public R update(@RequestBody CostrecordEntity costrecord){
		
		BeanUtil.fillCCUUD(costrecord, getUserId());
		costrecordService.update(costrecord);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete/{id}")
	@RequiresPermissions("costrecord:delete")
	public R delete(@PathVariable("id") Long id){
		costrecordService.delete(id);
		return R.ok();
	}
	
}
