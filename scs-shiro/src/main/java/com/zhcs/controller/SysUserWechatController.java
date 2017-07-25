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
import com.zhcs.entity.SysUserWechatEntity;
import com.zhcs.service.SysUserWechatService;
import com.zhcs.utils.BeanUtil;
import com.zhcs.utils.PageUtils;
import com.zhcs.utils.R;
import com.zhcs.utils.StringUtil;

//*****************************************************************************
/**
 * <p>Title:SysUserWechatController</p>
 * <p>Description: 用户与微信对应关系(只与管理员有关)</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
@Controller
@RequestMapping("sysuserwechat")
public class SysUserWechatController extends AbstractController  {
	@Autowired
	private SysUserWechatService sysUserWechatService;
	
	@RequestMapping("/sysuserwechat.html")
	public String list(){
		return "sysuserwechat/sysuserwechat.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("sysuserwechat:list")
	public R list(String sidx, String order, Integer page, Integer limit){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sidx", sidx);
		map.put("order", order);
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		
		//查询列表数据
		List<SysUserWechatEntity> sysUserWechatList = sysUserWechatService.queryList(map);
		int total = sysUserWechatService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(sysUserWechatList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{id}")
	@RequiresPermissions("sysuserwechat:info")
	public R info(@PathVariable("id") Long id){
		SysUserWechatEntity sysUserWechat = sysUserWechatService.queryObject(id);
		
		return R.ok().put("sysUserWechat", sysUserWechat);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("sysuserwechat:save")
	public R save(@RequestBody SysUserWechatEntity sysUserWechat){
	
		BeanUtil.fillCCUUD(sysUserWechat, getUserId(), getUserId());
		sysUserWechatService.save(sysUserWechat);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("sysuserwechat:update")
	public R update(@RequestBody SysUserWechatEntity sysUserWechat){
		
		BeanUtil.fillCCUUD(sysUserWechat, getUserId());
		sysUserWechatService.update(sysUserWechat);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete/{id}")
	@RequiresPermissions("sysuserwechat:delete")
	public R delete(@PathVariable("id") Long id){
		if(PlatformContext.getGoalbalContext("adminId", String.class).equals(StringUtil.valueOf(getUserId()))){
			sysUserWechatService.delete(id);
		} else {
			SysUserWechatEntity sysUserWechat = new SysUserWechatEntity();
			sysUserWechat.setId(id);
			/*sysUserWechat.setStatus("0");*/
			BeanUtil.fillCCUUD(sysUserWechat, getUserId());
			sysUserWechatService.update(sysUserWechat);
		}
		return R.ok();
	}
	
}
