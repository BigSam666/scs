package com.zhcs.controller;

import com.zhcs.context.PlatformContext;
import com.zhcs.entity.BaseCodeEntity;
import com.zhcs.service.BaseCodeService;
import com.zhcs.utils.BeanUtil;
import com.zhcs.utils.PageUtils;
import com.zhcs.utils.R;
import com.zhcs.utils.StringUtil;
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
 * <p>Title:BaseCdeController</p>
 * <p>Description: 基础代码 (T_BASE_CDE)</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
@Controller
@RequestMapping("basecode")
public class BaseCodeController extends AbstractController  {
	@Autowired
	private BaseCodeService baseCodeService;
	
	@RequestMapping("/basecode.html")
	public String list(){
		return "basecode/basecode.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("basecode:list")
	public R list(String keyword, Integer page, Integer limit){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		map.put("keyword", keyword);
		
		//查询列表数据
		List<BaseCodeEntity> baseCdeList = baseCodeService.queryList(map);
		int total = baseCodeService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(baseCdeList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{id}")
	@RequiresPermissions("basecode:info")
	public R info(@PathVariable("id") Long id){
		BaseCodeEntity basecode = baseCodeService.queryObject(id);
		
		return R.ok().put("basecode", basecode);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("basecode:save")
	public R save(@RequestBody BaseCodeEntity basecode){
	
		BeanUtil.fillCCUUD(basecode, getUserId(), getUserId());
		baseCodeService.save(basecode);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("basecode:update")
	public R update(@RequestBody BaseCodeEntity basecode){
		
		BeanUtil.fillCCUUD(basecode, getUserId());
		baseCodeService.update(basecode);
		
		return R.ok();
	}

	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete/{id}")
	@RequiresPermissions("basecode:delete")
	public R delete(@PathVariable("id") Long id){
		if(PlatformContext.getGoalbalContext("adminId", String.class).equals(StringUtil.valueOf(getUserId()))){
			baseCodeService.delete(id);
		} else {
			BaseCodeEntity basecode = new BaseCodeEntity();
			basecode.setId(id);
			basecode.setValid("0");
			BeanUtil.fillCCUUD(basecode, getUserId());
			baseCodeService.update(basecode);
		}
		return R.ok();
	}
	
	/**
	 * 获取数据字典所有数据信息
	 */
	@ResponseBody
	@RequestMapping("/selectByType/{type}")
	public R selectByType(@PathVariable("type") String type){
		List<BaseCodeEntity> ret = baseCodeService.selectByType(type);
		return R.ok().putData(ret);
	}
	
	/**
	 * 按类型、值查询到指定数据
	 */
	public BaseCodeEntity selectByTypeValue(String type, String value){
		BaseCodeEntity ret = baseCodeService.selectByTypeValue(type, value);
		return ret;
	}
	
}
