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
import com.zhcs.entity.MsgtemplateEntity;
import com.zhcs.service.MsgtemplateService;
import com.zhcs.utils.BeanUtil;
import com.zhcs.utils.PageUtils;
import com.zhcs.utils.StringUtil;
import com.zhcs.utils.R;

//*****************************************************************************
/**
 * <p>Title:MsgtemplateController</p>
 * <p>Description: 消息模板</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
@Controller
@RequestMapping("msgtemplate")
public class MsgtemplateController extends AbstractController  {
	@Autowired
	private MsgtemplateService msgtemplateService;
	
	@RequestMapping("/msgtemplate.html")
	public String list(){
		return "msgtemplate/msgtemplate.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("msgtemplate:list")
	public R list(String sidx, String order, Integer page, Integer limit,String nm){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sidx", sidx);
		map.put("order", order);
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		map.put("nm", nm);
		//查询列表数据
		List<MsgtemplateEntity> msgtemplateList = msgtemplateService.queryList(map);
		int total = msgtemplateService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(msgtemplateList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{id}")
	@RequiresPermissions("msgtemplate:info")
	public R info(@PathVariable("id") Long id){
		MsgtemplateEntity msgtemplate = msgtemplateService.queryObject(id);
		
		return R.ok().put("msgtemplate", msgtemplate);
	}
	
	/**
	 * 信息：用于解析参数
	 */
	@ResponseBody
	@RequestMapping("/info2/{id}")
	@RequiresPermissions("msgtemplate:info2")
	public R info2(@PathVariable("id") Long id){
		MsgtemplateEntity msgtemplate = msgtemplateService.queryObject(id);
		List<String> list = StringUtil.extractMessageByRegular(msgtemplate.getContent(), false);  
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("parSize", list.size());
		map.put("par", list);
		map.put("msgtemplate", msgtemplate);
		return R.ok().putData(map);
	}
	
	/**
	 * 用于获取消息发送模板
	 */
	@ResponseBody
	@RequestMapping("/getSendTemplate")
	@RequiresPermissions("msgtemplate:getSendTemplate")
	public R getSendTemplate(){
		List<Map<String, Object>> templateList = msgtemplateService.getSendTemplate();
		
		return R.ok().putData(templateList);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("msgtemplate:save")
	public R save(@RequestBody MsgtemplateEntity msgtemplate){
	
		BeanUtil.fillCCUUD(msgtemplate, getUserId(), getUserId());
		msgtemplateService.save(msgtemplate);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("msgtemplate:update")
	public R update(@RequestBody MsgtemplateEntity msgtemplate){
		
		BeanUtil.fillCCUUD(msgtemplate, getUserId());
		msgtemplateService.update(msgtemplate);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete/{id}")
	@RequiresPermissions("msgtemplate:delete")
	public R delete(@PathVariable("id") Long id){
		if(PlatformContext.getGoalbalContext("adminId", String.class).equals(StringUtil.valueOf(getUserId()))){
			msgtemplateService.delete(id);
		} else {
			MsgtemplateEntity msgtemplate = new MsgtemplateEntity();
			msgtemplate.setId(id);
			msgtemplate.setStatus("0");
			BeanUtil.fillCCUUD(msgtemplate, getUserId());
			msgtemplateService.update(msgtemplate);
		}
		return R.ok();
	}
	
}
