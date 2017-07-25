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

import com.zhcs.entity.CarterminalEntity;
import com.zhcs.service.CarterminalService;
import com.zhcs.utils.BeanUtil;
import com.zhcs.utils.PageUtils;
import com.zhcs.utils.R;
import com.zhcs.utils.StringUtil;

//*****************************************************************************
/**
 * <p>Title:CarterminalController</p>
 * <p>Description: 车载终端</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
@Controller
@RequestMapping("carterminal")
public class CarterminalController extends AbstractController  {
	@Autowired
	private CarterminalService carterminalService;
	
	@RequestMapping("/carterminal.html")
	public String list(){
		return "carterminal/carterminal.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("carterminal:list")
	public R list(String sidx,String qdevid, String order, Integer page, Integer limit){
		Map<String, Object> map = new HashMap<String, Object>();
		if (!StringUtil.isBlank(qdevid)) {
			map.put("qdevid", qdevid.trim());
		}
		map.put("sidx", sidx);
		map.put("order", order);
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		
		//查询列表数据
		List<CarterminalEntity> carterminalList = carterminalService.queryList(map);
		int total = carterminalService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(carterminalList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{id}")
	@RequiresPermissions("carterminal:info")
	public R info(@PathVariable("id") Long id){
		CarterminalEntity carterminal = carterminalService.queryObject(id);
		
		return R.ok().put("carterminal", carterminal);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("carterminal:save")
	public R save(@RequestBody CarterminalEntity carterminal){
	
		BeanUtil.fillCCUUD(carterminal, getUserId(), getUserId());
		carterminalService.save(carterminal);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("carterminal:update")
	public R update(@RequestBody CarterminalEntity carterminal){
		
		BeanUtil.fillCCUUD(carterminal, getUserId());
		carterminalService.update(carterminal);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete/{id}")
	@RequiresPermissions("carterminal:delete")
	public R delete(@PathVariable("id") Long id){
		carterminalService.delete(id);
		return R.ok();
	}
	
}
