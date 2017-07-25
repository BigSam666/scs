package com.zhcs.controller.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhcs.context.PlatformContext;
import com.zhcs.entity.BrknewsEntity;
import com.zhcs.entity.CitizenEntity;
import com.zhcs.entity.CompleteSpeed;
import com.zhcs.entity.EventDetail;
import com.zhcs.entity.EventEntity;
import com.zhcs.entity.EventLogEntity;
import com.zhcs.entity.HandleEntity;
import com.zhcs.entity.PartyEntity;
import com.zhcs.entity.PendingEvent;
import com.zhcs.entity.PendingSpeed;
import com.zhcs.entity.SysUserEntity;
import com.zhcs.entity.TokenEntity;
import com.zhcs.entity.WithdrawEntity;
import com.zhcs.entity.WorkflowEntity;
import com.zhcs.service.ApiEventLogService;
import com.zhcs.service.ApiEventService;
import com.zhcs.service.BaseCodeService;
import com.zhcs.service.BrknewsService;
import com.zhcs.service.CitizenService;
import com.zhcs.service.PartyService;
import com.zhcs.service.SysUserService;
import com.zhcs.service.TokenService;
import com.zhcs.service.WorkflowService;
import com.zhcs.utils.BeanUtil;
import com.zhcs.utils.DateUtil;
import com.zhcs.utils.R;
import com.zhcs.utils.StringUtil;

@Api(value = "事件管理" , description = "事件管理")
@Controller
@RequestMapping("/api/event")
@CrossOrigin
public class ApiEventController {
	
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private TokenService tokenService;
	@Autowired
	private BaseCodeService baseCodeService;
	@Autowired
	private ApiEventService apiEventService;
	@Autowired
	private ApiEventLogService apiEventLogService;
	@Autowired
	private CitizenService citizenService;
	@Autowired
	private PartyService partyService;
	@Autowired
	private BrknewsService brknewsService;
	@Autowired
	private WorkflowService workflowService;
	
	
	@ApiOperation(value = "待处理事件" , notes = "待处理事件")
	@RequestMapping(value = "/pending" , method = RequestMethod.POST)
	@ResponseBody
	public R pending(@RequestBody PendingEvent pendingEvent){
		TokenEntity token = tokenService.queryByToken(pendingEvent.getToken());
		if(StringUtil.isValid(token) && DateUtil.diffDateSecond(DateUtil.getSystemDate(), token.getExpireTime()) > 0){
			SysUserEntity user = sysUserService.queryObject(token.getUserId()); // 拿到当前用户
			
			Map<String, Object> map = new HashMap<>();
			String[] ss = PlatformContext.getGoalbalContext("ntdw", String.class).split(","); // 代办
			String[] sss = PlatformContext.getGoalbalContext("rpending", String.class).split(","); // 复合待办
			List<String> ccs = new ArrayList<>();
			ccs.addAll(Arrays.asList(ss));
			ccs.addAll(Arrays.asList(sss));
			map.put("ccs", ccs.toArray());
			map.put("userId", user.getId());
			
			
			if (pendingEvent.getPage() == null || pendingEvent.getPage() < 1 ) {
				pendingEvent.setPage(1);
			}
			if (pendingEvent.getPagesize() == null || pendingEvent.getPagesize() < 1) {
				pendingEvent.setPagesize(10);
			}
			map.put("page", pendingEvent.getPage());
			map.put("offset", (pendingEvent.getPage()-1) * pendingEvent.getPagesize());
			map.put("pagesize", pendingEvent.getPagesize());
			
			
			//查询列表数据
			List<Map<String, Object>> eventList = apiEventService.queryPendingEvents(map); // 需要显示的原始数据
			Integer count = apiEventService.getCount(map);                                 // 总条数
			List<Map<String, Object>> result = new ArrayList<>();                          // 最终返回的数据
			String data = "";
			for (Map<String, Object> tmp : eventList) {
				Map<String, Object> item = new HashMap<>(); // 一条数据
				
				data = StringUtil.valueOf(tmp.get("busseg")); // 综合巡查
				item.put("busseg", baseCodeService.selectByTypeValue("biztype", data).getCnm());
				
				item.put("describe", tmp.get("qdescribe")); // 事件描述
				item.put("dltm", tmp.get("dltm")); // 派单时间
				item.put("addr", tmp.get("addr")); // 地址
				item.put("id", tmp.get("id"));     // 事件id
				
				if(Arrays.asList(ss).contains(tmp.get("current"))) {
					item.put("type", PlatformContext.getGoalbalContext("ntdwText", String.class));
				}
				if(Arrays.asList(sss).contains(tmp.get("current"))) {
					item.put("type", PlatformContext.getGoalbalContext("rpendingText", String.class));
				}
				
				if(StringUtil.isValid(tmp.get("qimg"))){
					item.put("qimg", StringUtil.valueOf(tmp.get("img")));
				} else {
					item.put("qimg", "");
				}
				if(StringUtil.isValid(tmp.get("img"))){
					item.put("img", StringUtil.valueOf(tmp.get("img")));
				} else {
					item.put("img", "");
				}
				
				result.add(item);
			}
			
			Map<String,Object> ret = new HashMap<>();   // 包括数据和分页信息
			ret.put("page", map.get("page"));           // 当前页码
			ret.put("pagesize", map.get("pagesize"));   // 每页显示的条数
			ret.put("count", count);                    // 总条数
			ret.put("data", result);
			
			return R.ok().putData(ret);
		} else {
			return R.error(50000, "身份过期，请重新登录");
		}
	}
	
	@ApiOperation(value = "待处理事件详情" , notes = "待处理事件详情")
	@RequestMapping(value = "/pendingDetail" , method = RequestMethod.POST)
	@ResponseBody
	public R pendingDetail(@RequestBody EventDetail eventDetail){
		TokenEntity token = tokenService.queryByToken(eventDetail.getToken());
		if(StringUtil.isValid(token) && DateUtil.diffDateSecond(DateUtil.getSystemDate(), token.getExpireTime()) > 0){
			
			String eventId = eventDetail.getId();
			
			Map<String, Object> result = apiEventService.queryObject(eventId);
			
			String data = StringUtil.valueOf(result.get("busseg"));
			result.put("busseg", baseCodeService.selectByTypeValue("biztype", data).getCnm());
			data = StringUtil.valueOf(result.get("sour"));
			result.put("sour", baseCodeService.selectByTypeValue("caseSource", data).getCnm());
			
			result.put("crtName", sysUserService.queryObject((Long)result.get("crtid")).getRealname());
			result.put("updName", sysUserService.queryObject((Long)result.get("updid")).getRealname());
			
			if(StringUtil.isValid(result.get("qimg"))){
				result.put("qimg", StringUtil.valueOf(result.get("img")));
			} 
			if(StringUtil.isValid(result.get("img"))){
				result.put("img", StringUtil.valueOf(result.get("img")));
			} 
			
			return R.ok().putData(result);
		} else {
			return R.error(50000, "身份过期，请重新登录");
		}
	}
	
	@ApiOperation(value = "已处理事件详情" , notes = "已处理事件详情")
	@RequestMapping(value = "/completeDetail" , method = RequestMethod.POST)
	@ResponseBody
	public R completeDetail(@RequestBody EventDetail eventDetail){
		TokenEntity token = tokenService.queryByToken(eventDetail.getToken());
		if(StringUtil.isValid(token) && DateUtil.diffDateSecond(DateUtil.getSystemDate(), token.getExpireTime()) > 0){
			
			String eventId = eventDetail.getId();
			EventLogEntity event = apiEventLogService.queryObject(eventId);
			Map<String, String> map = BeanUtil.bean2Map(event);
			String data = StringUtil.valueOf(map.get("busseg"));
			map.put("busseg", baseCodeService.selectByTypeValue("biztype", data).getCnm());
			data = StringUtil.valueOf(map.get("sour"));
			map.put("sour", baseCodeService.selectByTypeValue("caseSource", data).getCnm());

			BrknewsEntity brk = null;
			brk = brknewsService.queryObjectByNo(StringUtil.valueOf(map.get("no")));
			if(StringUtil.isValid(brk)){
				map.put("evaluate", brk.getEvaluate());
			}
			
			map.put("handleName", sysUserService.queryObject(Long.parseLong(map.get("handle").toString())).getRealname()); // 处理人
			
			map.put("currentName", workflowService.queryByNodeNo(map.get("current").toString()).getNodename());
			
			Date dltm = event.getDltm(); // 派单时间
			Date htm = event.getHtm(); // 处理时间
			long l = htm.getTime() - dltm.getTime();
			long day = l / (24*60*60*1000);   
			long hour = (l / (60*60*1000) - day*24);   
			long min = (( l / (60*1000)) - day*24*60 - hour*60);  
			StringBuilder sb = new StringBuilder();
			if(day > 0) {
				sb.append(day+"天");
			}
			if(hour > 0) {
				sb.append(hour+"小时");
			}
			if(min > 0) {
				sb.append(min+"分钟");
			}
			map.put("time", sb.toString());

			
			map.put("crtName", sysUserService.queryObject(Long.parseLong(map.get("crtid"))).getRealname());
			map.put("updName", sysUserService.queryObject(Long.parseLong(map.get("updid"))).getRealname());
			
			if(StringUtil.isValid(map.get("qimg"))){
				map.put("qimg", StringUtil.valueOf(map.get("img")));
			} 
			if(StringUtil.isValid(map.get("img"))){
				map.put("img", StringUtil.valueOf(map.get("img")));
			} 
			return R.ok().putData(map);
		} else {
			return R.error(50000, "身份过期，请重新登录");
		}
	}
	
	
	@ApiOperation(value = "已完成事件" , notes = "已完成事件")
	@RequestMapping(value = "/complete" , method = RequestMethod.POST)
	@ResponseBody
	public R complete(@RequestBody PendingEvent pendingEvent){
		TokenEntity token = tokenService.queryByToken(pendingEvent.getToken());
		if(StringUtil.isValid(token) && DateUtil.diffDateSecond(DateUtil.getSystemDate(), token.getExpireTime()) > 0){
			SysUserEntity user = sysUserService.queryObject(token.getUserId()); // 拿到当前用户
			
			Map<String, Object> map = new HashMap<String, Object>();                     // 查询条件
			String[] ss = PlatformContext.getGoalbalContext("ycl", String.class).split(","); // 已处理
			String[] sss = PlatformContext.getGoalbalContext("fhcl", String.class).split(","); // 复合处理
			List<String> ccs = new ArrayList<>();
			ccs.addAll(Arrays.asList(ss));
			ccs.addAll(Arrays.asList(sss));
			map.put("ccs", ccs.toArray());
			map.put("userId", user.getId());
			
			if (pendingEvent.getPage() == null || pendingEvent.getPage() < 1 ) {
				pendingEvent.setPage(1);
			}
			if (pendingEvent.getPagesize() == null || pendingEvent.getPagesize() < 1) {
				pendingEvent.setPagesize(10);
			}
			map.put("page", pendingEvent.getPage());
			map.put("offset", (pendingEvent.getPage()-1) * pendingEvent.getPagesize());
			map.put("pagesize", pendingEvent.getPagesize());
			
			List<Map<String, Object>> eventList = apiEventLogService.getCompleteList(map); // 需要显示的原始数据
			Integer count = apiEventLogService.getCount(map);                              // 总条数
			
			List<Map<String, Object>> result = new ArrayList<>();                          // 最终返回的数据

			String data = "";
			for (Map<String, Object> tmp : eventList) {
				Map<String, Object> item = new HashMap<>(); // 一条数据
				
				data = StringUtil.valueOf(tmp.get("busseg")); // 综合巡查
				item.put("busseg", baseCodeService.selectByTypeValue("biztype", data).getCnm());
				
				item.put("describe", tmp.get("qdescribe")); // 事件描述
				item.put("dltm", tmp.get("dltm")); // 派单时间
				item.put("addr", tmp.get("addr")); // 地址
				item.put("id", tmp.get("id"));     // 事件id
				
				if(Arrays.asList(ss).contains(tmp.get("current"))) {
					item.put("type", PlatformContext.getGoalbalContext("yclText", String.class));
				}
				if(Arrays.asList(sss).contains(tmp.get("current"))) {
					item.put("type", PlatformContext.getGoalbalContext("fhclText", String.class));
				}
				
				if(StringUtil.isValid(tmp.get("qimg"))){
					item.put("qimg", StringUtil.valueOf(tmp.get("img")));
				} else {
					item.put("qimg", "");
				}
				if(StringUtil.isValid(tmp.get("img"))){
					item.put("img", StringUtil.valueOf(tmp.get("img")));
				} else {
					item.put("img", "");
				}
				
				result.add(item);
			}
			
			Map<String,Object> ret = new HashMap<>();                             // 包括数据和分页信息
			ret.put("page", map.get("page"));           // 当前页码
			ret.put("pagesize", map.get("pagesize"));   // 每页显示的条数
			ret.put("count", count);                    // 总条数
			ret.put("data", result);
			
			return R.ok().putData(ret);
		} else {
			return R.error(50000, "身份过期，请重新登录");
		}
	}
	
	@ApiOperation(value = "待处理事件的追踪详情" , notes = "待处理事件的追踪详情")
	@RequestMapping(value = "/pendingSpeed" , method = RequestMethod.POST)
	@ResponseBody
	public R pendingSpeed(@RequestBody PendingSpeed pendingSpeed){
		TokenEntity token = tokenService.queryByToken(pendingSpeed.getToken());
		if(StringUtil.isValid(token) && DateUtil.diffDateSecond(DateUtil.getSystemDate(), token.getExpireTime()) > 0){
			EventEntity event = apiEventService.queryObjectById(pendingSpeed.getId());
			String caseno = event.getNo();
			List<Map<String, Object>> list = apiEventLogService.getSpeed(caseno);
			Map<String, Object> map = null;
			Long updid = 0L;
			String uname = "";
			SysUserEntity sysuer = null;
			CitizenEntity citizen = null;
			PartyEntity party = null; 
			for (int i = 0 ; i < list.size(); i++) {
				map = list.get(i);
				updid = Long.valueOf(StringUtil.valueOf(map.get("updid")));
				if(i == list.size() - 1){
					switch (StringUtil.valueOf(map.get("sour"))) {
					case "0":
						// 市民爆料
						citizen = citizenService.queryObject(updid);
						if(StringUtil.isValid(citizen)){
							uname = citizen.getNm();
						}
						break;
					case "1":
						// 领导爆料
						sysuer = sysUserService.queryObject(updid);
						if(StringUtil.isValid(sysuer)){
							uname = sysuer.getRealname();
						}
						break;
					case "2":
						// 数字化城管
						party = partyService.queryObject(updid);
						if(StringUtil.isValid(party)){
							uname = party.getNm();
						}
						break;
					case "3":
						// 员工爆料
						sysuer = sysUserService.queryObject(updid);
						if(StringUtil.isValid(sysuer)){
							uname = sysuer.getRealname();
						}
						break;
					case "4":
						uname = "呼叫工单";
						break;
					}
				} else {
					sysuer = sysUserService.queryObject(updid);
					if(StringUtil.isValid(sysuer)){
						uname = sysuer.getRealname();
					} else {
						uname = "未知";
					}
				}
				map.put("uname", uname);
			}
			return R.ok().putData(list);
		} else {
			return R.error(50000, "身份过期，请重新登录");
		}
	}
	
	@ApiOperation(value = "已完成事件的追踪详情" , notes = "已完成事件的追踪详情")
	@RequestMapping(value = "/completeSpeed" , method = RequestMethod.POST)
	@ResponseBody
	public R completeSpeed(@RequestBody CompleteSpeed completeSpeed){
		TokenEntity token = tokenService.queryByToken(completeSpeed.getToken());
		if(StringUtil.isValid(token) && DateUtil.diffDateSecond(DateUtil.getSystemDate(), token.getExpireTime()) > 0){
			EventLogEntity event = apiEventLogService.queryObject(completeSpeed.getId());
			String caseno = event.getNo();
			List<Map<String, Object>> list = apiEventLogService.getSpeed(caseno);
			Map<String, Object> map = null;
			Long updid = 0L;
			String uname = "";
			SysUserEntity sysuer = null;
			CitizenEntity citizen = null;
			PartyEntity party = null; 
			for (int i = 0 ; i < list.size(); i++) {
				map = list.get(i);
				updid = Long.valueOf(StringUtil.valueOf(map.get("updid")));
				if(i == list.size() - 1){
					switch (StringUtil.valueOf(map.get("sour"))) {
					case "0":
						// 市民爆料
						citizen = citizenService.queryObject(updid);
						if(StringUtil.isValid(citizen)){
							uname = citizen.getNm();
						}
						break;
					case "1":
						// 领导爆料
						sysuer = sysUserService.queryObject(updid);
						if(StringUtil.isValid(sysuer)){
							uname = sysuer.getRealname();
						}
						break;
					case "2":
						// 数字化城管
						party = partyService.queryObject(updid);
						if(StringUtil.isValid(party)){
							uname = party.getNm();
						}
						break;
					case "3":
						// 员工爆料
						sysuer = sysUserService.queryObject(updid);
						if(StringUtil.isValid(sysuer)){
							uname = sysuer.getRealname();
						}
						break;
					case "4":
						uname = "呼叫工单";
						break;
					}
				} else {
					sysuer = sysUserService.queryObject(updid);
					if(StringUtil.isValid(sysuer)){
						uname = sysuer.getRealname();
					} else {
						uname = "未知";
					}
				}
				map.put("uname", uname);
			}
			return R.ok().putData(list);
		} else {
			return R.error(50000, "身份过期，请重新登录");
		}
	}
	
	@ApiOperation(value = "撤回事件" , notes = "撤回事件")
	@RequestMapping(value = "/withdraw" , method = RequestMethod.POST)
	@ResponseBody
	public R withdraw(@RequestBody WithdrawEntity withdrawEntity){
		TokenEntity token = tokenService.queryByToken(withdrawEntity.getToken());
		if(StringUtil.isValid(token) && DateUtil.diffDateSecond(DateUtil.getSystemDate(), token.getExpireTime()) > 0){
			SysUserEntity user = sysUserService.queryObject(token.getUserId()); // 拿到当前用户
			
			Date tm = DateUtil.getSystemDate();
			String flag = "0";
			Long id = Long.valueOf(withdrawEntity.getId());
			EventEntity event = apiEventService.queryObjectById(withdrawEntity.getId());
			String current = event.getCurrent();
			WorkflowEntity we = workflowService.queryOtherNodeObject(current, flag);
			EventLogEntity eventlog = new EventLogEntity();
			eventlog.setAddr(event.getAddr());
			eventlog.setBusseg(event.getBusseg());
			eventlog.setLat(event.getLat());
			eventlog.setLng(event.getLng());
			eventlog.setNo(event.getNo());
			eventlog.setSour(event.getSour());
			eventlog.setQimg(event.getQimg());
			eventlog.setQdescribe(event.getQdescribe());
			eventlog.setCurrent(current);
			eventlog.setNext(we.getNodeno());
			eventlog.setFlag(flag);
			eventlog.setDltm(event.getDltm());
			eventlog.setDes(StringUtil.valueOf(withdrawEntity.getContent()));
			eventlog.setImg("");
			eventlog.setHandle(user.getId());
			eventlog.setHtm(tm);
			eventlog.setAuxiliary(event.getAuxiliary());
			eventlog.setEstimatetm(event.getEstimatetm());
			BeanUtil.fillCCUUD(eventlog, user.getId(), user.getId());
			apiEventLogService.save(eventlog);
			if(PlatformContext.getGoalbalContext("completed", "ja", String.class).equals(we.getNodeno())){
				eventlog.setCurrent(we.getNodeno());
				eventlog.setNext(we.getNextno());
				eventlog.setFlag("1");
				eventlog.setDes("自动结案");
				BeanUtil.fillCCUUD(eventlog, 99999L, 99999L);
				apiEventLogService.save(eventlog);
				apiEventService.delete(id);
			} else {
				event.setCurrent(eventlog.getNext());
				event.setHandle(eventlog.getHandle());
				event.setAuxiliary(eventlog.getAuxiliary());
				event.setDes(eventlog.getDes());
				event.setImg(eventlog.getImg());
				BeanUtil.fillCCUUD(event, user.getId());
				apiEventService.update(event);
			}
			return R.ok();
		} else {
			return R.error(50000, "身份过期，请重新登录");
		}
	}
	
	@ApiOperation(value = "处理事件" , notes = "处理事件")
	@RequestMapping(value = "/handle" , method = RequestMethod.POST)
	@ResponseBody
	public R handle(@RequestBody HandleEntity handleEntity){
		TokenEntity token = tokenService.queryByToken(handleEntity.getToken());
		if(StringUtil.isValid(token) && DateUtil.diffDateSecond(DateUtil.getSystemDate(), token.getExpireTime()) > 0){
			SysUserEntity user = sysUserService.queryObject(token.getUserId()); // 拿到当前用户
			
			Date tm = DateUtil.getSystemDate();
			String flag = "1";
			Long id = Long.valueOf(StringUtil.valueOf(handleEntity.getId()));
			EventEntity event = apiEventService.queryObjectById(handleEntity.getId());
			String current = event.getCurrent();
			WorkflowEntity we = workflowService.queryOtherNodeObject(current, flag);
			EventLogEntity eventlog = new EventLogEntity();
			eventlog.setAddr(event.getAddr());
			eventlog.setBusseg(event.getBusseg());
			eventlog.setLat(event.getLat());
			eventlog.setLng(event.getLng());
			eventlog.setNo(event.getNo());
			eventlog.setSour(event.getSour());
			eventlog.setQimg(event.getQimg());
			eventlog.setQdescribe(event.getQdescribe());
			eventlog.setCurrent(current);
			eventlog.setNext(we.getNodeno());
			eventlog.setFlag(flag);
			eventlog.setDltm(event.getDltm());
			eventlog.setDes(StringUtil.valueOf(handleEntity.getContent()));
			eventlog.setImg(StringUtil.valueOf(handleEntity.getImg()));
			eventlog.setHandle(user.getId());
			eventlog.setHtm(tm);
			eventlog.setAuxiliary(event.getAuxiliary());
			eventlog.setEstimatetm(event.getEstimatetm());
			BeanUtil.fillCCUUD(eventlog, user.getId(), user.getId());
			apiEventLogService.save(eventlog);
			if(PlatformContext.getGoalbalContext("completed", "ja", String.class).equals(we.getNodeno())){
				eventlog.setCurrent(we.getNodeno());
				eventlog.setNext(we.getNextno());
				eventlog.setFlag("1");
				eventlog.setDes("自动结案");
				BeanUtil.fillCCUUD(eventlog, 99999L, 99999L);
				apiEventLogService.save(eventlog);
				apiEventService.delete(id);
			} else {
				event.setCurrent(eventlog.getNext());
				event.setHandle(eventlog.getHandle());
				event.setAuxiliary(eventlog.getAuxiliary());
				event.setDes(eventlog.getDes());
				event.setImg(eventlog.getImg());
				BeanUtil.fillCCUUD(event, user.getId());
				apiEventService.update(event);
			}
			
			return R.ok();
		} else {
			return R.error(50000, "身份过期，请重新登录");
		}
	}	
			
	
	
}
