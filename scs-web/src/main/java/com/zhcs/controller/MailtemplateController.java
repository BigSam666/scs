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
import com.zhcs.entity.MailtemplateEntity;
import com.zhcs.service.MailtemplateService;
import com.zhcs.utils.BeanUtil;
import com.zhcs.utils.PageUtils;
import com.zhcs.utils.R;
import com.zhcs.utils.StringUtil;

//*****************************************************************************
/**
 * <p>Title:MailtemplateController</p>
 * <p>Description: 邮件模板</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
@Controller
@RequestMapping("mailtemplate")
public class MailtemplateController extends AbstractController  {
	@Autowired
	private MailtemplateService mailtemplateService;
	
	@RequestMapping("/mailtemplate.html")
	public String list(){
		return "mailtemplate/mailtemplate.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("mailtemplate:list")
	public R list(String sidx, String order, Integer page, Integer limit,String nm){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sidx", sidx);
		map.put("order", order);
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		map.put("nm", nm);
		//查询列表数据
		List<MailtemplateEntity> mailtemplateList = mailtemplateService.queryList(map);
		int total = mailtemplateService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(mailtemplateList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{id}")
	@RequiresPermissions("mailtemplate:info")
	public R info(@PathVariable("id") Long id){
		MailtemplateEntity mailtemplate = mailtemplateService.queryObject(id);
		
		return R.ok().put("mailtemplate", mailtemplate);
	}
	
	/**
	 * 信息：用于解析参数
	 */
	@ResponseBody
	@RequestMapping("/info2/{id}")
	@RequiresPermissions("mailtemplate:info2")
	public R info2(@PathVariable("id") Long id){
		MailtemplateEntity mailtemplate = mailtemplateService.queryObject(id);
		List<String> list = StringUtil.extractMessageByRegular(mailtemplate.getContent(), false);  
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("parSize", list.size());
		map.put("par", list);
		map.put("mailtemplate", mailtemplate);
		return R.ok().putData(map);
	}

	/**
	 * 用于获取消息发送模板
	 */
	@ResponseBody
	@RequestMapping("/getSendTemplate")
	@RequiresPermissions("mailtemplate:getSendTemplate")
	public R getSendTemplate(){
		List<Map<String, Object>> templateList = mailtemplateService.getSendTemplate();
		
		return R.ok().putData(templateList);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("mailtemplate:save")
	public R save(@RequestBody MailtemplateEntity mailtemplate){
	
		BeanUtil.fillCCUUD(mailtemplate, getUserId(), getUserId());
		mailtemplateService.save(mailtemplate);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("mailtemplate:update")
	public R update(@RequestBody MailtemplateEntity mailtemplate){
		
		BeanUtil.fillCCUUD(mailtemplate, getUserId());
		mailtemplateService.update(mailtemplate);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete/{id}")
	@RequiresPermissions("mailtemplate:delete")
	public R delete(@PathVariable("id") Long id){
		if(PlatformContext.getGoalbalContext("adminId", String.class).equals(StringUtil.valueOf(getUserId()))){
			mailtemplateService.delete(id);
		} else {
			MailtemplateEntity mailtemplate = new MailtemplateEntity();
			mailtemplate.setId(id);
			mailtemplate.setStatus("0");
			BeanUtil.fillCCUUD(mailtemplate, getUserId());
			mailtemplateService.update(mailtemplate);
		}
		return R.ok();
	}
	
}
