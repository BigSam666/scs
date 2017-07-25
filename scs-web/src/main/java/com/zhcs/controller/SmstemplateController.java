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
import com.zhcs.entity.SmstemplateEntity;
import com.zhcs.service.SmstemplateService;
import com.zhcs.utils.BeanUtil;
import com.zhcs.utils.PageUtils;
import com.zhcs.utils.R;
import com.zhcs.utils.StringUtil;

//*****************************************************************************
/**
 * <p>Title:SmstemplateController</p>
 * <p>Description: 短信模板</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
@Controller
@RequestMapping("smstemplate")
public class SmstemplateController extends AbstractController  {
	@Autowired
	private SmstemplateService smstemplateService;
	
	@RequestMapping("/smstemplate.html")
	public String list(){
		return "smstemplate/smstemplate.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("smstemplate:list")
	public R list(String sidx, String order, Integer page, Integer limit,String nm){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sidx", sidx);
		map.put("order", order);
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		map.put("nm", nm);
		
		//查询列表数据
		//List<SmstemplateEntity> smstemplateList = smstemplateService.queryList(map);
		//int total = smstemplateService.queryTotal(map);
		
		Map<String, Object> dataMap =  smstemplateService.queryPageList(map);
		
		List<Map<String, Object> > smstemplateList = (List<Map<String, Object>>) dataMap.get("datalist");
		int total = (int) dataMap.get("total");
		
		PageUtils pageUtil = new PageUtils(smstemplateList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{id}")
	@RequiresPermissions("smstemplate:info")
	public R info(@PathVariable("id") Long id){
		SmstemplateEntity smstemplate = smstemplateService.queryObject(id);
		
		return R.ok().put("smstemplate", smstemplate);
	}
	
	/**
	 * 信息：用于解析参数
	 */
	@ResponseBody
	@RequestMapping("/info2/{id}")
	@RequiresPermissions("smstemplate:info2")
	public R info2(@PathVariable("id") Long id){
		SmstemplateEntity smstemplate = smstemplateService.queryObject(id);
		List<String> list = StringUtil.extractMessageByRegular(smstemplate.getContent(), false);  
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("parSize", list.size());
		map.put("par", list);
		map.put("smstemplate", smstemplate);
		return R.ok().putData(map);
	}

	/**
	 * 用于获取消息发送模板
	 */
	@ResponseBody
	@RequestMapping("/getSendTemplate")
	@RequiresPermissions("smstemplate:getSendTemplate")
	public R getSendTemplate(){
		List<Map<String, Object>> templateList = smstemplateService.getSendTemplate();
		
		return R.ok().putData(templateList);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("smstemplate:save")
	public R save(@RequestBody SmstemplateEntity smstemplate){
	
		BeanUtil.fillCCUUD(smstemplate, getUserId(), getUserId());
		smstemplateService.save(smstemplate);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("smstemplate:update")
	public R update(@RequestBody SmstemplateEntity smstemplate){
		
		BeanUtil.fillCCUUD(smstemplate, getUserId());
		smstemplateService.update(smstemplate);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete/{id}")
	@RequiresPermissions("smstemplate:delete")
	public R delete(@PathVariable("id") Long id){
		if(PlatformContext.getGoalbalContext("adminId", String.class).equals(StringUtil.valueOf(getUserId()))){
			smstemplateService.delete(id);
		} else {
			SmstemplateEntity smstemplate = new SmstemplateEntity();
			smstemplate.setId(id);
			smstemplate.setStatus("0");
			BeanUtil.fillCCUUD(smstemplate, getUserId());
			smstemplateService.update(smstemplate);
		}
		return R.ok();
	}
	
}
