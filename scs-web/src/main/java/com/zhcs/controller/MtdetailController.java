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
import com.zhcs.entity.MtdetailEntity;
import com.zhcs.service.MtdetailService;
import com.zhcs.utils.BeanUtil;
import com.zhcs.utils.PageUtils;
import com.zhcs.utils.R;
import com.zhcs.utils.StringUtil;

//*****************************************************************************
/**
 * <p>Title:MtdetailController</p>
 * <p>Description: 保养明细</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
@Controller
@RequestMapping("mtdetail")
public class MtdetailController extends AbstractController  {
	@Autowired
	private MtdetailService mtdetailService;
	
	@RequestMapping("/mtdetail.html")
	public String list(){
		return "mtdetail/mtdetail.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("mtdetail:list")
	public R list(String sidx, String order, Integer page, Integer limit){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sidx", sidx);
		map.put("order", order);
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		
		//查询列表数据
		List<MtdetailEntity> mtdetailList = mtdetailService.queryList(map);
		int total = mtdetailService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(mtdetailList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	/**
	 * 根据保养id查询保养明细
	 */
	@ResponseBody
	@RequestMapping("/listByMtid/{mtid}")
	@RequiresPermissions("mtdetail:list")
	public R listByInsid(@PathVariable("mtid") Long mtid) {
		List<MtdetailEntity> mtdetailList = mtdetailService.queryListByMtid(mtid);
		return R.ok().putData(mtdetailList);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{id}")
	@RequiresPermissions("mtdetail:info")
	public R info(@PathVariable("id") Long id){
		MtdetailEntity mtdetail = mtdetailService.queryObject(id);
		
		return R.ok().put("mtdetail", mtdetail);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("mtdetail:save")
	public R save(@RequestBody MtdetailEntity mtdetail){
	
		BeanUtil.fillCCUUD(mtdetail, getUserId(), getUserId());
		mtdetailService.save(mtdetail);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("mtdetail:update")
	public R update(@RequestBody MtdetailEntity mtdetail){
		
		BeanUtil.fillCCUUD(mtdetail, getUserId());
		mtdetailService.update(mtdetail);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete/{id}")
	@RequiresPermissions("mtdetail:delete")
	public R delete(@PathVariable("id") Long id){
		if(PlatformContext.getGoalbalContext("adminId", String.class).equals(StringUtil.valueOf(getUserId()))){
			mtdetailService.delete(id);
		} else {
			MtdetailEntity mtdetail = new MtdetailEntity();
			mtdetail.setId(id);
			mtdetail.setStatus("0");
			BeanUtil.fillCCUUD(mtdetail, getUserId());
			mtdetailService.update(mtdetail);
		}
		return R.ok();
	}
	
}
