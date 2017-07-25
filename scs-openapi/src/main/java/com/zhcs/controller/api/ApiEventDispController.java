package com.zhcs.controller.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
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
import com.zhcs.entity.CitizenEntity;
import com.zhcs.entity.DlEntity;
import com.zhcs.entity.EventDetail;
import com.zhcs.entity.EventEntity;
import com.zhcs.entity.EventLogEntity;
import com.zhcs.entity.PartyEntity;
import com.zhcs.entity.PendingEvent;
import com.zhcs.entity.PendingSpeed;
import com.zhcs.entity.SysDepartmentEntity;
import com.zhcs.entity.SysUserEntity;
import com.zhcs.entity.TokenAndIdEntity;
import com.zhcs.entity.TokenEntity;
import com.zhcs.entity.WorkflowEntity;
import com.zhcs.service.ApiEventLogService;
import com.zhcs.service.ApiEventService;
import com.zhcs.service.BaseCodeService;
import com.zhcs.service.CitizenService;
import com.zhcs.service.PartyService;
import com.zhcs.service.SysDepartmentService;
import com.zhcs.service.SysUserService;
import com.zhcs.service.TokenService;
import com.zhcs.service.WorkflowService;
import com.zhcs.utils.BeanUtil;
import com.zhcs.utils.DateUtil;
import com.zhcs.utils.R;
import com.zhcs.utils.StringUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "事件调度" , description = "事件调度")
@Controller
@RequestMapping("/api/eventdisp")
@CrossOrigin
public class ApiEventDispController {
	
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
	private WorkflowService workflowService;
	
	
	@ApiOperation(value = "待派单事件" , notes = "待派单事件")
	@RequestMapping(value = "/needdisp" , method = RequestMethod.POST)
	@ResponseBody
	public R needDisp(@RequestBody PendingEvent pendingEvent){
		TokenEntity token = tokenService.queryByToken(pendingEvent.getToken());
		if(StringUtil.isValid(token) && DateUtil.diffDateSecond(DateUtil.getSystemDate(), token.getExpireTime()) > 0){
			
			Map<String, Object> map = new HashMap<String, Object>();
			String[] ss = PlatformContext.getGoalbalContext("sjpd", String.class).split(","); // 事件派单
			String[] sss = PlatformContext.getGoalbalContext("fhpd", String.class).split(","); // 复合派单
			List<String> ccs = new ArrayList<>();
			ccs.addAll(Arrays.asList(ss));
			ccs.addAll(Arrays.asList(sss));
			map.put("ccs", ccs.toArray());
			
			
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
			List<Map<String, Object>> eventList = apiEventService.queryNeedDispEvents(map); // 需要显示的原始数据
			Integer count = apiEventService.getCount(map);                                 // 总条数
			List<Map<String, Object>> result = new ArrayList<>();                          // 最终返回的数据
			String data = "";
			for (Map<String, Object> tmp : eventList) {
				Map<String, Object> item = new HashMap<>(); // 一条数据
				
				data = StringUtil.valueOf(tmp.get("busseg")); // 综合巡查
				item.put("busseg", baseCodeService.selectByTypeValue("biztype", data).getCnm());
				
				item.put("describe", tmp.get("qdescribe")); // 事件描述
				item.put("crttm", tmp.get("crttm")); // 事件发生时间
				item.put("addr", tmp.get("addr")); // 地址
				item.put("id", tmp.get("id"));     // 事件id
				
				if(Arrays.asList(ss).contains(tmp.get("current"))) {
					item.put("type", PlatformContext.getGoalbalContext("sjpdText", String.class));
				}
				if(Arrays.asList(sss).contains(tmp.get("current"))) {
					item.put("type", PlatformContext.getGoalbalContext("fhpdText", String.class));
				}
				
				if(StringUtil.isValid(tmp.get("qimg"))){
					item.put("qimg", StringUtil.valueOf(tmp.get("img")));
				} else {
					item.put("qimg", "");
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
	
	@ApiOperation(value = "待派单事件详情" , notes = "待派单事件详情")
	@RequestMapping(value = "/needDispEventDetail" , method = RequestMethod.POST)
	@ResponseBody
	public R needDispEventDetail(@RequestBody EventDetail eventDetail){
		TokenEntity token = tokenService.queryByToken(eventDetail.getToken());
		if(StringUtil.isValid(token) && DateUtil.diffDateSecond(DateUtil.getSystemDate(), token.getExpireTime()) > 0){
			
			String eventId = eventDetail.getId();
			
			EventEntity item = apiEventService.queryObjectById(eventId);
			Map<String,Object> result = new LinkedHashMap<>();
			// 事件id
			result.put("id", item.getId());
			// 事件编号
			result.put("no", item.getNo());
			// 事件类型
			result.put("busseg", baseCodeService.selectByTypeValue("biztype", item.getBusseg()).getCnm());
			// 事件来源
			result.put("sour", baseCodeService.selectByTypeValue("caseSource", item.getSour()).getCnm());
			// 来源时间
			result.put("crttm", item.getCrttm());
			// 提供人
			Long crtid = item.getCrtid();
			String sour = item.getSour();
			String smbl = PlatformContext.getGoalbalContext("smbl", String.class);
			if (sour.equals(smbl)) {
				// 市民爆料
				result.put("person", citizenService.queryObject(crtid).getNm());
			} else {
				// 不是市民爆料
				result.put("person", sysUserService.queryObject(crtid).getRealname());
			}
			
			// 地址
			result.put("addr", item.getAddr());
			result.put("lat", item.getLat());
			result.put("lng", item.getLng());
			// 内容
			result.put("qdescribe", item.getQdescribe());
			// 图片
			result.put("qimg", item.getQimg());
			
			return R.ok().putData(result);
		} else {
			return R.error(50000, "身份过期，请重新登录");
		}
	}
	
	@ApiOperation(value = "已派单事件详情" , notes = "已派单事件详情")
	@RequestMapping(value = "/dispedEventDetail" , method = RequestMethod.POST)
	@ResponseBody
	public R dispedEventDetail(@RequestBody EventDetail eventDetail){
		TokenEntity token = tokenService.queryByToken(eventDetail.getToken());
		if(StringUtil.isValid(token) && DateUtil.diffDateSecond(DateUtil.getSystemDate(), token.getExpireTime()) > 0){
			
			String eventId = eventDetail.getId();
			
			EventEntity item = apiEventService.queryObjectById(eventId);
			Map<String,Object> result = new LinkedHashMap<>();
			// 事件id
			result.put("id", item.getId());
			// 事件编号
			result.put("no", item.getNo());
			// 事件类型
			result.put("busseg", baseCodeService.selectByTypeValue("biztype", item.getBusseg()).getCnm());
			// 事件来源
			result.put("sour", baseCodeService.selectByTypeValue("caseSource", item.getSour()).getCnm());
			// 来源时间
			result.put("crttm", item.getCrttm());
			// 提供人
			Long crtid = item.getCrtid();
			String sour = item.getSour();
			String smbl = PlatformContext.getGoalbalContext("smbl", String.class);
			if (sour.equals(smbl)) {
				// 市民爆料
				result.put("person", citizenService.queryObject(crtid).getNm());
			} else {
				// 不是市民爆料
				result.put("person", sysUserService.queryObject(crtid).getRealname());
			}
			
			// 地址
			result.put("addr", item.getAddr());
			result.put("lat", item.getLat());
			result.put("lng", item.getLng());
			// 内容
			result.put("qdescribe", item.getQdescribe());
			// 图片
			result.put("qimg", item.getQimg());
			result.put("img", item.getImg());
			// 处理人员
			Long handle = item.getHandle();
			SysUserEntity handlePerson = sysUserService.queryObject(handle);
			if (handlePerson != null) {
				result.put("handlePerson", handlePerson.getRealname());
			} else {
				result.put("handlePerson", "");
			}
			// 协作人员
			Long auxiliary = item.getAuxiliary();
			SysUserEntity auxiliaryPerson = sysUserService.queryObject(auxiliary);
			if (auxiliaryPerson != null) {
				result.put("auxiliaryPerson", auxiliaryPerson.getRealname());
			} else {
				result.put("auxiliaryPerson", "");
			}
			
			
			return R.ok().putData(result);
		} else {
			return R.error(50000, "身份过期，请重新登录");
		}
	}
	
	@ApiOperation(value = "已派单事件" , notes = "已派单事件")
	@RequestMapping(value = "/disped" , method = RequestMethod.POST)
	@ResponseBody
	public R disped(@RequestBody PendingEvent pendingEvent){
		TokenEntity token = tokenService.queryByToken(pendingEvent.getToken());
		if(StringUtil.isValid(token) && DateUtil.diffDateSecond(DateUtil.getSystemDate(), token.getExpireTime()) > 0){
			
			Map<String, Object> map = new HashMap<String, Object>();                     // 查询条件
			String[] ss = PlatformContext.getGoalbalContext("ycl", String.class).split(","); // 已处理
			String[] sss = PlatformContext.getGoalbalContext("fhcl", String.class).split(","); // 复合处理
			List<String> ccs = new ArrayList<>();
			ccs.addAll(Arrays.asList(ss));
			ccs.addAll(Arrays.asList(sss));
			map.put("ccs", ccs.toArray());
			
			if (pendingEvent.getPage() == null || pendingEvent.getPage() < 1 ) {
				pendingEvent.setPage(1);
			}
			if (pendingEvent.getPagesize() == null || pendingEvent.getPagesize() < 1) {
				pendingEvent.setPagesize(10);
			}
			map.put("page", pendingEvent.getPage());
			map.put("offset", (pendingEvent.getPage()-1) * pendingEvent.getPagesize());
			map.put("pagesize", pendingEvent.getPagesize());
			
			List<Map<String, Object>> eventList = apiEventService.queryNeedDispEvents(map); // 需要显示的原始数据
			Integer count = apiEventService.getCount(map);                              // 总条数
			
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
					item.put("type", PlatformContext.getGoalbalContext("yclText1", String.class));
				}
				if(Arrays.asList(sss).contains(tmp.get("current"))) {
					item.put("type", PlatformContext.getGoalbalContext("fhclText1", String.class));
				}
				
				if(StringUtil.isValid(tmp.get("qimg"))){
					item.put("qimg", StringUtil.valueOf(tmp.get("img")));
				} else {
					item.put("qimg", "");
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
	
	@ApiOperation(value = "事件的追踪详情" , notes = "事件的追踪详情")
	@RequestMapping(value = "/eventSpeed" , method = RequestMethod.POST)
	@ResponseBody
	public R eventSpeed(@RequestBody PendingSpeed pendingSpeed){
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

	@ApiOperation(value = "事件调度" , notes = "事件调度")
	@RequestMapping(value = "/dl" , method = RequestMethod.POST)
	@ResponseBody
	public R dl(@RequestBody DlEntity dlEntity) {
		TokenEntity token = tokenService.queryByToken(dlEntity.getToken());
		if(StringUtil.isValid(token) && DateUtil.diffDateSecond(DateUtil.getSystemDate(), token.getExpireTime()) > 0){
			String flag = dlEntity.getBtype();
			if ("1".equals(flag)) {
				if (dlEntity.getHandle()==null){
					return R.error(81001, "处理人不能为空");
				}
				if (dlEntity.getAuxiliary()!=null && dlEntity.getAuxiliary()==dlEntity.getHandle()) {
					return R.error(81002, "处理人和协作人员不能是同一个");
				}
				if (dlEntity.getEstimatetm() == null) {
					return R.error(81003, "预计完成时间是必填项");
				}
			}
			
			if ("fhpd".equals(dlEntity.getBtype()) && "0".equals(dlEntity.getBtype())){
				return R.error(81004, "复合派单时只能派单，不能回复");
			}
			
			SysUserEntity user = sysUserService.queryObject(token.getUserId()); // 拿到当前用户
			Long id = dlEntity.getEventId();
			String current = StringUtil.valueOf(dlEntity.getCurrent());
			WorkflowEntity we = workflowService.queryOtherNodeObject(current, flag);
			EventEntity event = apiEventService.queryObjectById(id+"");
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
			eventlog.setDltm(DateUtil.getSystemDate());
			eventlog.setDes(StringUtil.valueOf(dlEntity.getContent()));
			if(StringUtil.isValid(StringUtil.valueOf(dlEntity.getHandle()))){
				eventlog.setHandle(Long.valueOf(StringUtil.valueOf(dlEntity.getHandle())));
			}
			if(StringUtil.isValid(StringUtil.valueOf(dlEntity.getAuxiliary()))){
				eventlog.setAuxiliary(Long.valueOf(StringUtil.valueOf(dlEntity.getAuxiliary())));
			}
			eventlog.setEstimatetm(dlEntity.getEstimatetm());
			BeanUtil.fillCCUUD(eventlog, user.getId(), user.getId());
			apiEventLogService.save(eventlog);
			if(PlatformContext.getGoalbalContext("completed", "ja", String.class).equals(we.getNodeno())){
				eventlog.setFlag("1");
				eventlog.setDes("自动结案");
				eventlog.setCurrent(we.getNodeno());
				eventlog.setNext(we.getNextno());
				BeanUtil.fillCCUUD(eventlog, 99999L, 99999L);
				apiEventLogService.save(eventlog);
				apiEventService.delete(id);
			} else {
				event.setCurrent(eventlog.getNext());
				event.setHandle(eventlog.getHandle());
				event.setAuxiliary(eventlog.getAuxiliary());
				event.setDltm(eventlog.getDltm());
				event.setEstimatetm(eventlog.getEstimatetm());
				event.setDes(eventlog.getDes());
				BeanUtil.fillCCUUD(event, user.getId());
				apiEventService.update(event);
			}
			
			return R.ok();
		} else {
			return R.error(50000, "身份过期，请重新登录");
		}
	}
	
	
}
