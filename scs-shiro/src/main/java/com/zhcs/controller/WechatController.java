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
import com.zhcs.entity.WechatEntity;
import com.zhcs.service.WechatService;
import com.zhcs.utils.BeanUtil;
import com.zhcs.utils.PageUtils;
import com.zhcs.utils.R;
import com.zhcs.utils.StringUtil;

//*****************************************************************************
/**
 * <p>Title:WechatController</p>
 * <p>Description: 微信关注列表</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
@Controller
@RequestMapping("wechat")
public class WechatController extends AbstractController  {
	@Autowired
	private WechatService wechatService;
	
	@RequestMapping("/wechat.html")
	public String list(){
		return "wechat/wechat.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("wechat:list")
	public R list(String sidx, String order, Integer page, Integer limit){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sidx", sidx);
		map.put("order", order);
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		
		//查询列表数据
		List<WechatEntity> wechatList = wechatService.queryList(map);
		int total = wechatService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(wechatList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{id}")
	@RequiresPermissions("wechat:info")
	public R info(@PathVariable("id") Long id){
		WechatEntity wechat = wechatService.queryObject(id);
		
		return R.ok().put("wechat", wechat);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("wechat:save")
	public R save(@RequestBody WechatEntity wechat){
	
		BeanUtil.fillCCUUD(wechat, getUserId(), getUserId());
		wechatService.save(wechat);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("wechat:update")
	public R update(@RequestBody WechatEntity wechat){
		
		BeanUtil.fillCCUUD(wechat, getUserId());
		wechatService.update(wechat);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete/{id}")
	@RequiresPermissions("wechat:delete")
	public R delete(@PathVariable("id") Long id){
		if(PlatformContext.getGoalbalContext("adminId", String.class).equals(StringUtil.valueOf(getUserId()))){
			wechatService.delete(id);
		} else {
			WechatEntity wechat = new WechatEntity();
			wechat.setId(id);
			/*wechat.setStatus("0");*/
			BeanUtil.fillCCUUD(wechat, getUserId());
			wechatService.update(wechat);
		}
		return R.ok();
	}
	
}
