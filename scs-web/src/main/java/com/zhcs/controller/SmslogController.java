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

import com.alibaba.fastjson.JSON;
import com.aliyuncs.exceptions.ClientException;
import com.zhcs.context.PlatformContext;
import com.zhcs.entity.SmslogEntity;
import com.zhcs.entity.SmstemplateEntity;
import com.zhcs.entity.SysUserEntity;
import com.zhcs.service.SmslogService;
import com.zhcs.service.SmstemplateService;
import com.zhcs.service.SysUserService;
import com.zhcs.utils.BeanUtil;
import com.zhcs.utils.LogUtil;
import com.zhcs.utils.PageUtils;
import com.zhcs.utils.R;
import com.zhcs.utils.SMSUtil;
import com.zhcs.utils.StringUtil;

//*****************************************************************************
/**
 * <p>Title:SmslogController</p>
 * <p>Description: 短信记录</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
@Controller
@RequestMapping("smslog")
public class SmslogController extends AbstractController  {
	@Autowired
	private SmslogService smslogService;
	@Autowired
	private SmstemplateService smstemplateService;
	@Autowired
	private SysUserService sysUserService;	
	
	@RequestMapping("/smslog.html")
	public String list(){
		return "smslog/smslog.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("smslog:list")
	public R list(String sidx, String order, Integer page, Integer limit,String starttime,String endtime){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sidx", sidx);
		map.put("order", order);
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		
		map.put("starttime",starttime);
		map.put("endtime", endtime);
		
		//查询列表数据
		List<Map<String, Object>> smslogList = smslogService.queryList(map);
		int total = smslogService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(smslogList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{id}")
	@RequiresPermissions("smslog:info")
	public R info(@PathVariable("id") Long id){
		Map<String, Object> map = smslogService.queryObject(id);
		map.put("log", StringUtil.prettyFormatJson(StringUtil.valueOf(map.get("log"))));
		return R.ok().put("smslog", map);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("smslog:save")
	public R save(@RequestBody Map<String, Object> map){
//		JSONArray receivers = (JSONArray) map.get("receiver");
		String[] receiverIds = ((String)map.get("receiver")).split(",");

		SmslogEntity smslog = null;
		SmstemplateEntity Smstemplate = smstemplateService.queryObject(Long.valueOf(StringUtil.valueOf(map.get("id"))));
		String content = Smstemplate.getContent();
		List<String> r = StringUtil.extractMessageByRegular(content, true);
		Map<String, Object> m = new HashMap<String, Object>();
		String v = "";
		for (String str : r) {
			v = StringUtil.extractMessageByRegular(str, false).get(0);
			// 因为短信参数有$所以需要拼接$一起替换
			content = content.replace("$" + str.trim(), StringUtil.valueOf(map.get(v)).trim());
			m.put(v, StringUtil.valueOf(map.get(v)).trim());
		}
		LogUtil.debug("---:"+content);
		SysUserEntity user = null;
		String s = "";
//		for (Object receiver : receivers) {
		for (Object receiver : receiverIds) {
			smslog = new SmslogEntity();
			user = sysUserService.queryObject(Long.valueOf(StringUtil.valueOf(receiver)));
			if(StringUtil.isValid(user.getMobile())){
				try {
					s = JSON.toJSONString(SMSUtil.SendSMS(user.getMobile(), Smstemplate.getTid(), SMSUtil.getParamString(m)));
					smslog.setLog(s);
					smslog.setStatus((s.contains("model")) ? "1" : "0");
				} catch (ClientException e) {
					smslog.setLog(e.getMessage());
					smslog.setStatus("0");
				}
			} else {
				smslog.setStatus("0");
				smslog.setLog("该用户没有绑定手机，不能进行消息发送。");
			}
			smslog.setType("2");
			smslog.setReceiver(StringUtil.valueOf(user.getId()));
			smslog.setContent(content);
			smslog.setTid(StringUtil.valueOf(Smstemplate.getTid()));
			BeanUtil.fillCCUUD(smslog, getUserId(), getUserId());
			smslogService.save(smslog);
		}
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("smslog:update")
	public R update(@RequestBody SmslogEntity smslog){
		
		BeanUtil.fillCCUUD(smslog, getUserId());
		smslogService.update(smslog);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete/{id}")
	@RequiresPermissions("smslog:delete")
	public R delete(@PathVariable("id") Long id){
		if(PlatformContext.getGoalbalContext("adminId", String.class).equals(StringUtil.valueOf(getUserId()))){
			smslogService.delete(id);
		} else {
			SmslogEntity smslog = new SmslogEntity();
			smslog.setId(id);
			smslog.setStatus("0");
			BeanUtil.fillCCUUD(smslog, getUserId());
			smslogService.update(smslog);
		}
		return R.ok();
	}
	
}
