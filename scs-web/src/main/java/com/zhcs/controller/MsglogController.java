package com.zhcs.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.jeewx.api.core.exception.WexinReqException;
import org.jeewx.api.core.req.model.message.IndustryTemplateMessageSend;
import org.jeewx.api.core.req.model.message.TemplateData;
import org.jeewx.api.core.req.model.message.TemplateMessage;
import org.jeewx.api.wxsendmsg.JwTemplateMessageAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhcs.context.PlatformContext;
import com.zhcs.entity.MsglogEntity;
import com.zhcs.entity.MsgtemplateEntity;
import com.zhcs.entity.SysUserEntity;
import com.zhcs.service.MsglogService;
import com.zhcs.service.MsgtemplateService;
import com.zhcs.service.SysUserService;
import com.zhcs.utils.BeanUtil;
import com.zhcs.utils.Constant;
import com.zhcs.utils.LogUtil;
import com.zhcs.utils.PageUtils;
import com.zhcs.utils.R;
import com.zhcs.utils.StringUtil;

//*****************************************************************************
/**
 * <p>Title:MsglogController</p>
 * <p>Description: 消息记录</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
@Controller
@RequestMapping("msglog")
public class MsglogController extends AbstractController  {
	@Autowired
	private MsglogService msglogService;
	@Autowired
	private MsgtemplateService msgtemplateService;
	@Autowired
	private SysUserService sysUserService;	
	@RequestMapping("/msglog.html")
	public String list(){
		return "msglog/msglog.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("msglog:list")
	public R list(String sidx, String order, Integer page, Integer limit,String starttime,String endtime){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sidx", sidx);
		map.put("order", order);
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		
		map.put("starttime",starttime);
		map.put("endtime", endtime);
		
		//查询列表数据
		List<Map<String, Object>> msglogList = msglogService.queryList(map);
		int total = msglogService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(msglogList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{id}")
	@RequiresPermissions("msglog:info")
	public R info(@PathVariable("id") Long id){
		Map<String, Object> map = msglogService.queryObject(id);
		
		return R.ok().put("msglog", map);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("msglog:save")
	public R save(@RequestBody Map<String, Object> map){
		String[] receiverIds = ((String)map.get("receiver")).split(",");

		MsglogEntity msglog = null;
		MsgtemplateEntity msgtemplate = msgtemplateService.queryObject(Long.valueOf(StringUtil.valueOf(map.get("id"))));
		String content = msgtemplate.getContent();
		List<String> r = StringUtil.extractMessageByRegular(content, true);
		String v = "";
		for (String str : r) {
			v = StringUtil.extractMessageByRegular(str, false).get(0);
			content = content.replace(str.trim(), StringUtil.valueOf(map.get(v)).trim());
		}
		LogUtil.debug("---{}", content);
		SysUserEntity user = null;
		IndustryTemplateMessageSend industryTemplateMessageSend = null;
		TemplateMessage data = null;
		TemplateData first = null;
		TemplateData Topic = null;
		TemplateData Time = null;
		TemplateData Address = null;
		TemplateData keynote1 = null;
		TemplateData keynote2 = null;
		TemplateData keynote3 = null;
		TemplateData keyword1 = null;
		TemplateData keyword2 = null;
		TemplateData keyword3 = null;
		TemplateData remark = null;
		String s = "";
		for (Object receiver : receiverIds) {
			msglog = new MsglogEntity();
			user = sysUserService.queryObject(Long.valueOf(StringUtil.valueOf(receiver)));
			if(StringUtil.isValid(user.getWechatid())){
				industryTemplateMessageSend = new IndustryTemplateMessageSend();
				industryTemplateMessageSend.setAccess_token(Constant.access_token2);
				industryTemplateMessageSend.setTemplate_id(msgtemplate.getTid());
				industryTemplateMessageSend.setTouser(user.getWechatid());
				industryTemplateMessageSend.setTopcolor("#ffAADD");
				data = new TemplateMessage();
				first = new TemplateData();

				first.setColor("#173177");
				first.setValue(user.getName() + ":" + StringUtil.valueOf(map.get("first.DATA")));
				data.setFirst(first);
				
				if(map.containsKey("Topic.DATA")){
					Topic = new TemplateData();
					Topic.setColor("#ff0000");
					Topic.setValue(StringUtil.valueOf(map.get("Topic.DATA")));
					data.setTopic(Topic);
				}
				
				if(map.containsKey("Time.DATA")){
					Time = new TemplateData();
					Time.setColor("#000000");
					Time.setValue(StringUtil.valueOf(map.get("Time.DATA")));
					data.setTime(Time);
				}
				
				if(map.containsKey("Address.DATA")){
					Address = new TemplateData();
					Address.setColor("#000000");
					Address.setValue(StringUtil.valueOf(map.get("Address.DATA")));
					data.setAddress(Address);
				}
				
				if(map.containsKey("keynote1.DATA")){
					keynote1 = new TemplateData();
					keynote1.setValue(StringUtil.valueOf(map.get("keynote1.DATA")));
					data.setKeynote1(keynote1);
				}
				
				if(map.containsKey("keynote2.DATA")){
					keynote2 = new TemplateData();
					keynote2.setValue(StringUtil.valueOf(map.get("keynote2.DATA")));
					data.setKeynote2(keynote2);
				}
				
				if(map.containsKey("keynote3.DATA")){
					keynote3 = new TemplateData();
					keynote3.setValue(StringUtil.valueOf(map.get("keynote3.DATA")));
					data.setKeynote3(keynote3);
				}
				
				if(map.containsKey("keyword1.DATA")){
					keyword1 = new TemplateData();
					keyword1.setValue(StringUtil.valueOf(map.get("keyword1.DATA")));
					data.setKeyword1(keyword1);
				}
				
				if(map.containsKey("keyword2.DATA")){
					keyword2 = new TemplateData();
					keyword2.setValue(StringUtil.valueOf(map.get("keyword2.DATA")));
					data.setKeyword2(keyword2);
				}
				
				if(map.containsKey("keyword3.DATA")){
					keyword3 = new TemplateData();
					keyword3.setValue(StringUtil.valueOf(map.get("keyword3.DATA")));
					data.setKeyword3(keyword3);
				}

				if(map.containsKey("remark.DATA")){
					remark = new TemplateData();
					remark.setValue(StringUtil.valueOf(map.get("remark.DATA")));
					data.setRemark(remark);
				}
				
				industryTemplateMessageSend.setData(data);
				try {
					s = JwTemplateMessageAPI.sendTemplateMsg(industryTemplateMessageSend);
				} catch (WexinReqException e) {
					LogUtil.error("消息发送失败，请重新发送");
				} finally {
					LogUtil.info("消息发送结果为：{}", s);
				}
				msglog.setStatus(("ok".equalsIgnoreCase(s)) ? "1" : "0");
				msglog.setLog(s);
			} else {
				msglog.setStatus("0");
				msglog.setLog("该用户没有绑定微信，不能进行消息发送。");
			}
			msglog.setType("2");
			msglog.setReceiver(StringUtil.valueOf(user.getId()));
			msglog.setContent(content);
			msglog.setTid(StringUtil.valueOf(msgtemplate.getTid()));
			BeanUtil.fillCCUUD(msglog, getUserId(), getUserId());
			msglogService.save(msglog);
		}
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("msglog:update")
	public R update(@RequestBody MsglogEntity msglog){
		
		BeanUtil.fillCCUUD(msglog, getUserId());
		msglogService.update(msglog);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete/{id}")
	@RequiresPermissions("msglog:delete")
	public R delete(@PathVariable("id") Long id){
		if(PlatformContext.getGoalbalContext("adminId", String.class).equals(StringUtil.valueOf(getUserId()))){
			msglogService.delete(id);
		} else {
			MsglogEntity msglog = new MsglogEntity();
			msglog.setId(id);
			msglog.setStatus("0");
			BeanUtil.fillCCUUD(msglog, getUserId());
			msglogService.update(msglog);
		}
		return R.ok();
	}
	
}
