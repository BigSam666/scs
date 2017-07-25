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
import com.zhcs.entity.PartyEntity;
import com.zhcs.service.PartyService;
import com.zhcs.utils.BeanUtil;
import com.zhcs.utils.PageUtils;
import com.zhcs.utils.R;
import com.zhcs.utils.StringUtil;

//*****************************************************************************
/**
 * <p>Title:PartyController</p>
 * <p>Description: 甲方</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
@Controller
@RequestMapping("party")
public class PartyController extends AbstractController  {
	@Autowired
	private PartyService partyService;
	
	@RequestMapping("/party.html")
	public String list(){
		return "party/party.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("party:list")
	public R list(Integer page, Integer limit){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		
		//查询列表数据
		List<PartyEntity> partyList = partyService.queryList(map);
		int total = partyService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(partyList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	/**
	 * 获取树
	 */
	@ResponseBody
	@RequestMapping("/getPartyTree")
	@RequiresPermissions("party:getPartyTreet")
	public R getUserTree(){
		//查询列表数据
		List<Map<String, Object>> userList = partyService.getPartyTree();
		
		return R.ok().put("userList", userList);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{id}")
	@RequiresPermissions("party:info")
	public R info(@PathVariable("id") Long id){
		PartyEntity party = partyService.queryObject(id);
		
		return R.ok().put("party", party);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("party:save")
	public R save(@RequestBody PartyEntity party){
	
		BeanUtil.fillCCUUD(party, getUserId(), getUserId());
		partyService.save(party);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("party:update")
	public R update(@RequestBody PartyEntity party){
		
		BeanUtil.fillCCUUD(party, getUserId());
		partyService.update(party);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete/{id}")
	@RequiresPermissions("party:delete")
	public R delete(@PathVariable("id") Long id){
		if(PlatformContext.getGoalbalContext("adminId", String.class).equals(StringUtil.valueOf(getUserId()))){
			partyService.delete(id);
		} else {
			PartyEntity party = new PartyEntity();
			party.setId(id);
			party.setStatus("0");
			BeanUtil.fillCCUUD(party, getUserId());
			partyService.update(party);
		}
		return R.ok();
	}
	
}
