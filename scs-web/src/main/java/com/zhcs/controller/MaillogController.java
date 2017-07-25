package com.zhcs.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhcs.context.PlatformContext;
import com.zhcs.entity.MaillogEntity;
import com.zhcs.entity.MailtemplateEntity;
import com.zhcs.entity.SysUserEntity;
import com.zhcs.service.MaillogService;
import com.zhcs.service.MailtemplateService;
import com.zhcs.service.SysUserService;
import com.zhcs.utils.BeanUtil;
import com.zhcs.utils.LogUtil;
import com.zhcs.utils.PageUtils;
import com.zhcs.utils.R;
import com.zhcs.utils.StringUtil;
import com.zhcs.utils.mail.SimpleMailSender;

//*****************************************************************************
/**
 * <p>Title:MaillogController</p>
 * <p>Description: 邮件记录</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
@Controller
@RequestMapping("maillog")
public class MaillogController extends AbstractController  {
	@Autowired
	private MaillogService maillogService;
	@Autowired
	private MailtemplateService mailtemplateService;
	@Autowired
	private SysUserService sysUserService;
	
	@RequestMapping("/maillog.html")
	public String list(){
		return "maillog/maillog.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("maillog:list")
	public R list(String sidx, String order, Integer page, Integer limit,String starttime,String endtime){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sidx", sidx);
		map.put("order", order);
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		map.put("starttime",starttime);
		map.put("endtime", endtime);
		//查询列表数据
		List<Map<String, Object>> maillogList = maillogService.queryList(map);
		int total = maillogService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(maillogList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{id}")
	@RequiresPermissions("maillog:info")
	public R info(@PathVariable("id") Long id){
		Map<String, Object> map = maillogService.queryObject(id);
		
		return R.ok().put("maillog", map);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("maillog:save")
	public R save(@RequestBody Map<String, Object> map){
		String[] receiverIds = ((String)map.get("receiver")).split(",");

		MaillogEntity maillog = null;
		MailtemplateEntity mailtemplate = mailtemplateService.queryObject(Long.valueOf(StringUtil.valueOf(map.get("id"))));
		String content = mailtemplate.getContent();
		List<String> r = StringUtil.extractMessageByRegular(content, true);
		Map<String, Object> m = new HashMap<String, Object>();
		String v = "";
		for (String str : r) {
			v = StringUtil.extractMessageByRegular(str, false).get(0);
			content = content.replace(str.trim(), StringUtil.valueOf(map.get(v)).trim());
			m.put(v, StringUtil.valueOf(map.get(v)).trim());
		}
		LogUtil.debug("---:"+content);
		SysUserEntity user = null;
		boolean s = false;
		for (Object receiver : receiverIds) {
			maillog = new MaillogEntity();
			user = sysUserService.queryObject(Long.valueOf(StringUtil.valueOf(receiver)));
			if(StringUtil.isValid(user.getEmail())){
				try {
					s = SimpleMailSender.sendEmail(user.getEmail(), mailtemplate.getNm(), content, true);
					maillog.setLog(StringUtil.valueOf(s));
					maillog.setStatus(s ? "1" : "0");
				} catch (MessagingException e) {
					maillog.setLog(e.getMessage());
					maillog.setStatus("0");
				}
			} else {
				maillog.setStatus("0");
				maillog.setLog("该用户没有绑定邮箱，不能发送邮件。");
			}
			maillog.setType("2");
			maillog.setReceiver(StringUtil.valueOf(user.getId()));
			maillog.setContent(content);
			BeanUtil.fillCCUUD(maillog, getUserId(), getUserId());
			maillogService.save(maillog);
		}
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("maillog:update")
	public R update(@RequestBody MaillogEntity maillog){
		
		BeanUtil.fillCCUUD(maillog, getUserId());
		maillogService.update(maillog);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete/{id}")
	@RequiresPermissions("maillog:delete")
	public R delete(@PathVariable("id") Long id){
		if(PlatformContext.getGoalbalContext("adminId", String.class).equals(StringUtil.valueOf(getUserId()))){
			maillogService.delete(id);
		} else {
			MaillogEntity maillog = new MaillogEntity();
			maillog.setId(id);
			maillog.setStatus("0");
			BeanUtil.fillCCUUD(maillog, getUserId());
			maillogService.update(maillog);
		}
		return R.ok();
	}
	
}
