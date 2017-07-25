package com.zhcs.controller;

import java.util.ArrayList;
import java.util.Date;
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
import com.zhcs.entity.BrknewsEntity;
import com.zhcs.entity.CitizenEntity;
import com.zhcs.entity.EventEntity;
import com.zhcs.entity.EventLogEntity;
import com.zhcs.entity.PartyEntity;
import com.zhcs.entity.SysDepartmentEntity;
import com.zhcs.entity.SysUserEntity;
import com.zhcs.entity.WorkflowEntity;
import com.zhcs.service.ApiholdgpsService;
import com.zhcs.service.BaseCodeService;
import com.zhcs.service.BrknewsService;
import com.zhcs.service.CitizenService;
import com.zhcs.service.EventLogService;
import com.zhcs.service.EventService;
import com.zhcs.service.PartyService;
import com.zhcs.service.PositionService;
import com.zhcs.service.SysUserService;
import com.zhcs.service.WorkflowService;
import com.zhcs.utils.BeanUtil;
import com.zhcs.utils.DateUtil;
import com.zhcs.utils.LogUtil;
import com.zhcs.utils.PageUtils;
import com.zhcs.utils.R;
import com.zhcs.utils.StringUtil;

//*****************************************************************************
/**
 * <p>Title:EventController</p>
 * <p>Description: 案件</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
@Controller
@RequestMapping("event")
public class EventController extends AbstractController  {
	@Autowired
	private EventService eventService;
	@Autowired
	private PositionService positionService;
	@Autowired
	private BaseCodeService baseCodeService;
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private EventLogService eventLogService;
	@Autowired
	private WorkflowService workflowService;
	@Autowired
	private PartyService partyService;
	@Autowired
	private CitizenService citizenService;
	@Autowired
	private BrknewsService brknewsService;
	@Autowired
	private ApiholdgpsService apiholdgpsService;
	
	@RequestMapping("/event.html")
	public String list(){
		return "event/event.html";
	}
	
	@RequestMapping("/event_add.html")
	public String event_add(){
		return "event/event_add.html";
	}
	
	@RequestMapping("/cmddisp.html")
	public String cmddisp(){
		return "cmddisp/cmddisp.html";
	}

	@RequestMapping("/cmddisp_history.html")
	public String cmddisp_history(){
		return "cmddisp/cmddisp_history.html";
	}

	@RequestMapping("/cmddisp_history_re.html")
	public String cmddisp_history_re(){
		return "cmddisp/cmddisp_history_re.html";
	}
	
	@RequestMapping("/event_upc.html")
	public String list_upc(){
		return "event/event_upc.html";
	}
	@RequestMapping("/event_hbp.html")
	public String list_hbp(){
		return "event/event_hbp.html";
	}
	@RequestMapping("/event_ctdi.html")
	public String list_ctdi(){
		return "event/event_ctdi.html";
	}
	@RequestMapping("/event_fulfil.html")
	public String list_fulfil(){
		return "event/event_fulfil.html";
	}
	
	/**
	 * 待派单列表(包括处理派单和审核派单)
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("event:list")
	public R list(String sidx, String order, Integer page, Integer limit){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sidx", sidx);
		map.put("order", order);
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		map.put("ccs", PlatformContext.getGoalbalContext("commandCenterSingleList", String.class).split(","));
		LogUtil.info(StringUtil.toJson(map));
		
		//查询列表数据
		List<Map<String, Object>> eventList = eventService.queryList2(map);
		Map<String, String> m = null;
		String data = "";
		String qimg = "";
		for (Map<String, Object> map2 : eventList) {
			data = StringUtil.valueOf(map2.get("busseg"));
			m = new HashMap<String, String>();
			m.put("data", data);
			m.put("value", baseCodeService.selectByTypeValue("biztype", data).getCnm());
			map2.put("busseg", m);
			data = StringUtil.valueOf(map2.get("sour"));
			m = new HashMap<String, String>();
			m.put("data", data);
			m.put("value", baseCodeService.selectByTypeValue("caseSource", data).getCnm());
			map2.put("sour", m);
			if(StringUtil.isValid(map2.get("qimg"))){
				if(StringUtil.isValid(map2.get("img"))){
					qimg = StringUtil.valueOf(map2.get("qimg")) + "," + StringUtil.valueOf(map2.get("img"));
				} else {
					qimg = StringUtil.valueOf(map2.get("qimg"));
				}
			} else {
				qimg = StringUtil.valueOf(map2.get("img"));
			}
			map2.put("qimg", qimg);
		}
		int total = eventService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(eventList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	/**
	 * 历史调度(事件派单)
	 */
	@ResponseBody
	@RequestMapping("/historyList")
	@RequiresPermissions("event:historyList")
	public R historyList(String sidx, String order, Integer page, Integer limit){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sidx", sidx);
		map.put("order", order);
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		map.put("ccs", PlatformContext.getGoalbalContext("eventPie", String.class).split(","));
		LogUtil.info(StringUtil.toJson(map));
		
		//查询列表数据
		List<Map<String, Object>> eventList = eventService.historyList(map);
		Map<String, String> m = null;
		String data = "";
		String qimg = "";
		SysUserEntity user = null;
		for (Map<String, Object> map2 : eventList) {
			data = StringUtil.valueOf(map2.get("busseg"));
			m = new HashMap<String, String>();
			m.put("data", data);
			m.put("value", baseCodeService.selectByTypeValue("biztype", data).getCnm());
			map2.put("busseg", m);
			data = StringUtil.valueOf(map2.get("sour"));
			m = new HashMap<String, String>();
			m.put("data", data);
			m.put("value", baseCodeService.selectByTypeValue("caseSource", data).getCnm());
			map2.put("sour", m);
			if(StringUtil.isValid(map2.get("qimg"))){
				if(StringUtil.isValid(map2.get("img"))){
					qimg = StringUtil.valueOf(map2.get("qimg")) + "," + StringUtil.valueOf(map2.get("img"));
				} else {
					qimg = StringUtil.valueOf(map2.get("qimg"));
				}
			} else {
				qimg = StringUtil.valueOf(map2.get("img"));
			}
			map2.put("qimg", qimg);
			if(StringUtil.isValid(StringUtil.valueOf(map2.get("handle")))){
				user = sysUserService.queryObject(Long.valueOf(StringUtil.valueOf(map2.get("handle"))));
				map2.put("handle", user.getRealname());
			}
			if(StringUtil.isValid(StringUtil.valueOf(map2.get("auxiliary")))){
				user = sysUserService.queryObject(Long.valueOf(StringUtil.valueOf(map2.get("auxiliary"))));
				map2.put("auxiliary", user.getRealname());
			}
		}
		int total = eventService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(eventList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	/**
	 * 待办事件列表
	 */
	@ResponseBody
	@RequestMapping("/upcList")
	@RequiresPermissions("event:upcList")
	public R upcList(String sidx, String order, Integer page, Integer limit){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sidx", sidx);
		map.put("order", order);
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		map.put("ccs", PlatformContext.getGoalbalContext("ntdw", String.class).split(","));
		//查询列表数据
//		List<Map<String, Object>> eventList = eventService.queryList2(map);
		List<Map<String, Object>> eventList = eventService.queryList3(map);
		Map<String, String> m = null;
		String data = "";
		String qimg = "";
		for (Map<String, Object> map2 : eventList) {
			data = StringUtil.valueOf(map2.get("busseg"));
			m = new HashMap<String, String>();
			m.put("data", data);
			m.put("value", baseCodeService.selectByTypeValue("biztype", data).getCnm());
			map2.put("busseg", m);
			data = StringUtil.valueOf(map2.get("sour"));
			m = new HashMap<String, String>();
			m.put("data", data);
			m.put("value", baseCodeService.selectByTypeValue("caseSource", data).getCnm());
			map2.put("sour", m);
			if(StringUtil.isValid(map2.get("qimg"))){
				if(StringUtil.isValid(map2.get("img"))){
					qimg = StringUtil.valueOf(map2.get("qimg")) + "," + StringUtil.valueOf(map2.get("img"));
				} else {
					qimg = StringUtil.valueOf(map2.get("qimg"));
				}
			} else {
				qimg = StringUtil.valueOf(map2.get("img"));
			}
			map2.put("qimg", qimg);
		}
		int total = eventService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(eventList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}

	/**
	 * 已处理事件列表
	 */
	@ResponseBody
	@RequestMapping("/hbpList")
	@RequiresPermissions("event:hbpList")
	public R hbpList(String sidx, String order, Integer page, Integer limit){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sidx", sidx);
		map.put("order", order);
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		map.put("uid", getUserId());
		//查询列表数据
//		List<Map<String, Object>> eventList = eventService.getHandleList(map);
		List<Map<String, Object>> eventList = eventService.getHandleList1(map);
		Map<String, String> m = null;
		String data = "";
		String qimg = "";
		for (Map<String, Object> map2 : eventList) {
			data = StringUtil.valueOf(map2.get("busseg"));
			m = new HashMap<String, String>();
			m.put("data", data);
			m.put("value", baseCodeService.selectByTypeValue("biztype", data).getCnm());
			map2.put("busseg", m);
			data = StringUtil.valueOf(map2.get("sour"));
			m = new HashMap<String, String>();	
			m.put("data", data);
			m.put("value", baseCodeService.selectByTypeValue("caseSource", data).getCnm());
			map2.put("sour", m);
			if(StringUtil.isValid(map2.get("qimg"))){
				if(StringUtil.isValid(map2.get("img"))){
					qimg = StringUtil.valueOf(map2.get("qimg")) + "," + StringUtil.valueOf(map2.get("img"));
				} else {
					qimg = StringUtil.valueOf(map2.get("qimg"));
				}
			} else {
				qimg = StringUtil.valueOf(map2.get("img"));
			}
			map2.put("qimg", qimg);
		}
		int total = eventService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(eventList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	/**
	 * 复核待办列表
	 */
	@ResponseBody
	@RequestMapping("/ctdiList")
	@RequiresPermissions("event:ctdiList")
	public R ctdiList(String sidx, String order, Integer page, Integer limit){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sidx", sidx);
		map.put("order", order);
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		map.put("ccs", PlatformContext.getGoalbalContext("rpending", String.class).split(","));
		//查询列表数据
//		List<Map<String, Object>> eventList = eventService.queryList2(map);
		List<Map<String, Object>> eventList = eventService.queryList3(map);
		Map<String, String> m = null;
		String data = "";
		String qimg = "";
		for (Map<String, Object> map2 : eventList) {
			data = StringUtil.valueOf(map2.get("busseg"));
			m = new HashMap<String, String>();
			m.put("data", data);
			m.put("value", baseCodeService.selectByTypeValue("biztype", data).getCnm());
			map2.put("busseg", m);
			data = StringUtil.valueOf(map2.get("sour"));
			m = new HashMap<String, String>();
			m.put("data", data);
			m.put("value", baseCodeService.selectByTypeValue("caseSource", data).getCnm());
			map2.put("sour", m);
			if(StringUtil.isValid(map2.get("qimg"))){
				if(StringUtil.isValid(map2.get("img"))){
					qimg = StringUtil.valueOf(map2.get("qimg")) + "," + StringUtil.valueOf(map2.get("img"));
				} else {
					qimg = StringUtil.valueOf(map2.get("qimg"));
				}
			} else {
				qimg = StringUtil.valueOf(map2.get("img"));
			}
			map2.put("qimg", qimg);
		}
		int total = eventService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(eventList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	/**
	 * 已完成列表
	 */
	@ResponseBody
	@RequestMapping("/fulfilList")
	@RequiresPermissions("event:fulfilList")
	public R fulfilList(String sidx, String order, Integer page, Integer limit){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sidx", sidx);
		map.put("order", order);
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		map.put("ccs", PlatformContext.getGoalbalContext("completed", String.class).split(","));
		//查询列表数据
		List<Map<String, Object>> eventList = eventService.fulfilList(map);
		Map<String, String> m = null;
		String data = "";
		String qimg = "";
		BrknewsEntity brk = null;
		for (Map<String, Object> map2 : eventList) {
			data = StringUtil.valueOf(map2.get("busseg"));
			m = new HashMap<String, String>();
			m.put("data", data);
			m.put("value", baseCodeService.selectByTypeValue("biztype", data).getCnm());
			map2.put("busseg", m);
			data = StringUtil.valueOf(map2.get("sour"));
			m = new HashMap<String, String>();
			m.put("data", data);
			m.put("value", baseCodeService.selectByTypeValue("caseSource", data).getCnm());
			map2.put("sour", m);
			if(StringUtil.isValid(map2.get("qimg"))){
				if(StringUtil.isValid(map2.get("img"))){
					qimg = StringUtil.valueOf(map2.get("qimg")) + "," + StringUtil.valueOf(map2.get("img"));
				} else {
					qimg = StringUtil.valueOf(map2.get("qimg"));
				}
			} else {
				qimg = StringUtil.valueOf(map2.get("img"));
			}
			map2.put("qimg", qimg);
			brk = brknewsService.queryObjectByNo(StringUtil.valueOf(map2.get("no")));
			if(StringUtil.isValid(brk)){
				map2.put("evaluate", brk.getEvaluate());
			}
		}
		int total = eventService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(eventList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{id}")
	@RequiresPermissions("event:info")
	public R info(@PathVariable("id") Long id){
		EventEntity event = eventService.queryObject(id);
		
		return R.ok().put("event", event);
	}
	
	//*************************************************************************
	/** 
	* 【获取】用案件id获取到对应人员
	* @param id
	* @return  
	*/
	//*************************************************************************
	@ResponseBody
	@RequestMapping("/info")
	public R info(Integer radius,Long id, String sidx, String order, Integer page, Integer limit) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("radius", radius);
		map.put("id", id);
		map.put("sidx", sidx);
		map.put("order", order);
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		EventEntity event = eventService.queryObject(id);
		int totle = 0;
		if(StringUtil.isValid(event)){
			map.put("lng", event.getLng());
			map.put("lat", event.getLat());
			List<Map<String, Object>> list2 = new ArrayList<Map<String,Object>>(), list = apiholdgpsService.getUserMsgByPosition(map);
			List<SysDepartmentEntity> ld = null;
			List<String> rolenames = null;
			for (Map<String, Object> map2 : list) {
				if(StringUtil.isValid(StringUtil.valueOf((map2.get("lat"))))){
					// 获取当前用户的角色集合
					ld = sysUserService.queryDepartmentByUser(Long.valueOf(StringUtil.valueOf(map2.get("id"))));
					rolenames = new ArrayList<String>();
					for (SysDepartmentEntity d : ld) {
						rolenames.add(d.getName());
					}
					// 判断这货有多少完成多少没完成的
					map2.put("rolename", rolenames);
					map2.putAll(eventService.getHandleSituation(Integer.parseInt(StringUtil.valueOf(map2.get("id")))));
					list2.add(map2);
				}
			}
			totle = list2.size();
			PageUtils pageUtil = new PageUtils(list2, totle, limit, page);
			return R.ok().put("page", pageUtil);
		} else {
			return R.ok();
		}
	}

	/**
	 * 待办事件信息
	 */
	@ResponseBody
	@RequestMapping("/upcinfo/{id}")
	@RequiresPermissions("event:upcinfo")
	public R upcinfo(@PathVariable("id") Long id){
		EventEntity event = eventService.queryObject(id);
		Map<String, String> map = BeanUtil.bean2Map(event);
		String data = StringUtil.valueOf(map.get("busseg"));
		map.put("busseg", baseCodeService.selectByTypeValue("biztype", data).getCnm());
		data = StringUtil.valueOf(map.get("sour"));
		map.put("sour", baseCodeService.selectByTypeValue("caseSource", data).getCnm());
		return R.ok().put("event", map);
	}
	
	/**
	 * 已处理事件信息
	 */
	@ResponseBody
	@RequestMapping("/hbpinfo/{id}")
	@RequiresPermissions("event:hbpinfo")
	public R hbpinfo(@PathVariable("id") Long id){
		EventLogEntity event = eventLogService.queryObject(id);
		Map<String, String> map = BeanUtil.bean2Map(event);
		String data = StringUtil.valueOf(map.get("busseg"));
		map.put("busseg", baseCodeService.selectByTypeValue("biztype", data).getCnm());
		data = StringUtil.valueOf(map.get("sour"));
		map.put("sour", baseCodeService.selectByTypeValue("caseSource", data).getCnm());
		return R.ok().put("event", map);
	}
	
	/**
	 * 复核待办事件信息
	 */
	@ResponseBody
	@RequestMapping("/ctdiinfo/{id}")
	@RequiresPermissions("event:ctdiinfo")
	public R ctdiinfo(@PathVariable("id") Long id){
		EventEntity event = eventService.queryObject(id);
		Map<String, String> map = BeanUtil.bean2Map(event);
		String data = StringUtil.valueOf(map.get("busseg"));
		map.put("busseg", baseCodeService.selectByTypeValue("biztype", data).getCnm());
		data = StringUtil.valueOf(map.get("sour"));
		map.put("sour", baseCodeService.selectByTypeValue("caseSource", data).getCnm());
		return R.ok().put("event", map);
	}
	
	/**
	 * 已完成事件信息
	 */
	@ResponseBody
	@RequestMapping("/fulfilinfo/{id}")
	@RequiresPermissions("event:fulfilinfo")
	public R fulfilinfo(@PathVariable("id") Long id){
		EventLogEntity event = eventLogService.queryObject(id);
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
		return R.ok().put("event", map);
	}
	
	/**
	 * 详情
	 */
	@ResponseBody
	@RequestMapping("/historyInfo/{id}")
	@RequiresPermissions("event:fulfilinfo")
	public R historyInfo(@PathVariable("id") Long id){
		EventLogEntity event = eventLogService.queryObject(id);
		Map<String, String> map = BeanUtil.bean2Map(event);
		String data = StringUtil.valueOf(map.get("busseg"));
		map.put("busseg", baseCodeService.selectByTypeValue("biztype", data).getCnm());
		data = StringUtil.valueOf(map.get("sour"));
		map.put("sour", baseCodeService.selectByTypeValue("caseSource", data).getCnm());
		SysUserEntity user = null;
		if(StringUtil.isValid(StringUtil.valueOf(map.get("handle")))){
			user = sysUserService.queryObject(Long.valueOf(StringUtil.valueOf(map.get("handle"))));
			map.put("handle", user.getRealname());
		}
		if(StringUtil.isValid(StringUtil.valueOf(map.get("auxiliary")))){
			user = sysUserService.queryObject(Long.valueOf(StringUtil.valueOf(map.get("auxiliary"))));
			map.put("auxiliary", user.getRealname());
		}
		return R.ok().put("event", map);
	}
	
	/**
	 * 获取进度
	 */
	@ResponseBody
	@RequestMapping("/getSpeed/{id}")
	public R getSpeed(@PathVariable("id") Long id){
		EventEntity event = eventService.queryObject(id);
		String caseno = event.getNo();
		List<Map<String, Object>> list = eventLogService.getSpeed(caseno);
		Map<String, Object> map = null;
		Long updid = 0L;
		String uname = "";
		SysUserEntity sysuer = null;
		CitizenEntity citizen = null;
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
					sysuer = sysUserService.queryObject(updid);
					if(StringUtil.isValid(sysuer)){
						uname = sysuer.getRealname();
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
		return R.ok().put("speedList", list);
	}
	
	/**
	 * 获取进度2
	 */
	@ResponseBody
	@RequestMapping("/getSpeed2/{id}")
	public R getSpeed2(@PathVariable("id") Long id){
		EventLogEntity event = eventLogService.queryObject(id);
		String caseno = event.getNo();
		List<Map<String, Object>> list = eventLogService.getSpeed(caseno);
		Map<String, Object> map = null;
		Long updid = 0L;
		String uname = "";
		SysUserEntity sysuer = null;
		CitizenEntity citizen = null;
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
					sysuer = sysUserService.queryObject(updid);
					if(StringUtil.isValid(sysuer)){
						uname = sysuer.getRealname();
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
		return R.ok().put("speedList", list);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/modify")
	@RequiresPermissions("event:modify")
	public R modify(@RequestBody EventEntity event){
		EventLogEntity eventlog = eventLogService.queryObject(event.getId());
		String no = eventlog.getNo();
		eventlog = new EventLogEntity();
		eventlog.setId(event.getId());
		eventlog.setHandle(event.getHandle());
		eventlog.setAuxiliary(event.getAuxiliary());
		BeanUtil.fillCCUUD(eventlog, getUserId());
		eventLogService.update(eventlog);
		EventEntity event_temp = eventService.queryByNo(no);
		if(StringUtil.isValid(event_temp)){
			event.setId(event_temp.getId());
			BeanUtil.fillCCUUD(event, getUserId());
			eventService.update(event);
		}
		return R.ok();
	}
	
	/**
	 * 处理保存
	 */
	@ResponseBody
	@RequestMapping("/upcsave")
	@RequiresPermissions("event:upcsave")
	public R upcsave(@RequestBody Map<String, Object> map){
		Date tm = DateUtil.getSystemDate();
		String flag = StringUtil.valueOf(map.get("btype"));
		Long id = Long.valueOf(StringUtil.valueOf(map.get("id")));
		EventEntity event = eventService.queryObject(id);
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
		eventlog.setDes(StringUtil.valueOf(map.get("content")));
		eventlog.setImg(StringUtil.valueOf(map.get("img")));
		eventlog.setHandle(getUserId());
		eventlog.setHtm(tm);
		eventlog.setAuxiliary(event.getAuxiliary());
		eventlog.setEstimatetm(event.getEstimatetm());
		BeanUtil.fillCCUUD(eventlog, getUserId(), getUserId());
		eventLogService.save(eventlog);
		if(PlatformContext.getGoalbalContext("completed", "ja", String.class).equals(we.getNodeno())){
			eventlog.setCurrent(we.getNodeno());
			eventlog.setNext(we.getNextno());
			eventlog.setFlag("1");
			eventlog.setDes("自动结案");
			BeanUtil.fillCCUUD(eventlog, 99999L, 99999L);
			eventLogService.save(eventlog);
			eventService.delete(id);
		} else {
			event.setCurrent(eventlog.getNext());
			event.setHandle(eventlog.getHandle());
			event.setAuxiliary(eventlog.getAuxiliary());
			event.setDes(eventlog.getDes());
			event.setImg(eventlog.getImg());
			BeanUtil.fillCCUUD(event, getUserId());
			eventService.update(event);
		}
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("event:update")
	public R update(@RequestBody EventEntity event){
		
		BeanUtil.fillCCUUD(event, getUserId());
		eventService.update(event);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete/{id}")
	@RequiresPermissions("event:delete")
	public R delete(@PathVariable("id") Long id){
		eventService.delete(id);
		return R.ok();
	}
	
}
