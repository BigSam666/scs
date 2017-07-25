package com.zhcs.controller.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.HashMap;
import java.util.LinkedList;
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
import com.zhcs.entity.EventEntity;
import com.zhcs.entity.EventLogEntity;
import com.zhcs.entity.OnlyTokenEntity;
import com.zhcs.entity.StaffTipOffEntity;
import com.zhcs.entity.SysUserEntity;
import com.zhcs.entity.TokenAndIdEntity;
import com.zhcs.entity.TokenAndPageEntity;
import com.zhcs.entity.TokenEntity;
import com.zhcs.entity.WorkflowEntity;
import com.zhcs.service.ApiEventLogService;
import com.zhcs.service.ApiEventService;
import com.zhcs.service.BaseCodeService;
import com.zhcs.service.BrknewsService;
import com.zhcs.service.SerianoService;
import com.zhcs.service.SysUserService;
import com.zhcs.service.TokenService;
import com.zhcs.service.WorkflowService;
import com.zhcs.utils.BeanUtil;
import com.zhcs.utils.DateUtil;
import com.zhcs.utils.R;
import com.zhcs.utils.StringUtil;

@Api(value = "员工爆料" , description = "员工爆料")
@Controller
@RequestMapping("/api/stafftipoff")
@CrossOrigin
public class ApiStaffTipOffController {
	
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private TokenService tokenService;
	@Autowired
	private BaseCodeService baseCodeService;
	@Autowired
	private WorkflowService workflowService;
	@Autowired
	private BrknewsService brknewsService;
	@Autowired
	private ApiEventService eventService;
	@Autowired
	private ApiEventLogService eventLogService;
	@Autowired
	private SerianoService serianoService;
	
	
	@ApiOperation(value = "业务类型" , notes = "业务类型")
	@RequestMapping(value = "/type" , method = RequestMethod.POST)
	@ResponseBody
	public R type(@RequestBody OnlyTokenEntity onlyTokenEntity){
		TokenEntity token = tokenService.queryByToken(onlyTokenEntity.getToken());
		if(StringUtil.isValid(token) && DateUtil.diffDateSecond(DateUtil.getSystemDate(), token.getExpireTime()) > 0){
			return R.ok().putData(baseCodeService.getCnmNoMapByType(PlatformContext.getGoalbalContext("biztype", String.class)));
		} else {
			return R.error(50000, "身份过期，请重新登录");
		}
	}
	
	@ApiOperation(value = "添加" , notes = "添加")
	@RequestMapping(value = "/add" , method = RequestMethod.POST)
	@ResponseBody
	public R add(@RequestBody StaffTipOffEntity staffTipOffEntity){
		TokenEntity token = tokenService.queryByToken(staffTipOffEntity.getToken());
		if(StringUtil.isValid(token) && DateUtil.diffDateSecond(DateUtil.getSystemDate(), token.getExpireTime()) > 0){
			
			if (StringUtil.isBlank(staffTipOffEntity.getAddr()) || StringUtil.isBlank(staffTipOffEntity.getLat()) || StringUtil.isBlank(staffTipOffEntity.getLng())) {
				return R.error(61001, "地址不能为空");
			}
			if (StringUtil.isBlank(staffTipOffEntity.getContent())) {
				return R.error(71001, "原因说明不能为空");
			}
			if (StringUtil.isBlank(staffTipOffEntity.getBusseg())) {
				return R.error(71002, "类型不能为空");
			}
			
			SysUserEntity user = sysUserService.queryObject(token.getUserId()); // 拿到当前用户
			Long userid = user.getId();
			
			String current = PlatformContext.getGoalbalContext("bn", "bl", String.class);
			
			WorkflowEntity we = workflowService.queryOtherNodeObject(current, "1");
			BrknewsEntity bn = new BrknewsEntity();
			bn.setType(staffTipOffEntity.getBusseg());
			bn.setAdds(staffTipOffEntity.getAddr());
			bn.setContent(StringUtil.guoHtml(staffTipOffEntity.getContent()));
			bn.setLat(staffTipOffEntity.getLat());
			bn.setLng(staffTipOffEntity.getLng());
			bn.setSource(PlatformContext.getGoalbalContext("caseSource", 3, String.class));
			String caseno = serianoService.getId("event.no", DateUtil.getSystemDateStr("yyyyMM"));
			bn.setCaseno(caseno);	
			bn.setImg(staffTipOffEntity.getImg());
			BeanUtil.fillCCUUD(bn, userid, userid);
			brknewsService.save(bn);
			// 因为爆料和任务派单环节是在一起，也就是爆料是会自动运行并结束，所以案件爆料后的第一个环节不是爆料而是派单，所以先把爆料保存到日志表，
			// 保存日志信息
			EventLogEntity eventlog = new EventLogEntity();
			eventlog.setAddr(bn.getAdds());
			eventlog.setBusseg(bn.getType());
			eventlog.setLat(bn.getLat());
			eventlog.setLng(bn.getLng());
			eventlog.setNo(bn.getCaseno());
			eventlog.setSour(bn.getSource());
			eventlog.setQimg(bn.getImg());
			eventlog.setQdescribe(bn.getContent());
			eventlog.setCurrent(current);
			eventlog.setNext(we.getNodeno());
			eventlog.setFlag("1");
			BeanUtil.fillCCUUD(eventlog, userid, userid);
			eventLogService.save(eventlog);
			// 保存当前信息
			EventEntity event = new EventEntity();
			event.setAddr(bn.getAdds());
			event.setBusseg(bn.getType());
			event.setLat(bn.getLat());
			event.setLng(bn.getLng());
			event.setNo(bn.getCaseno());
			event.setSour(bn.getSource());
			event.setQimg(bn.getImg());
			event.setQdescribe(bn.getContent());
			event.setCurrent(we.getNodeno());
			BeanUtil.fillCCUUD(event, userid, userid);
			eventService.save(event);
			
			return R.ok();
		} else {
			return R.error(50000, "身份过期，请重新登录");
		}
	}
	
	@ApiOperation(value = "列表" , notes = "列表")
	@RequestMapping(value = "/list" , method = RequestMethod.POST)
	@ResponseBody
	public R list(@RequestBody TokenAndPageEntity tokenAndPageEntity){
		TokenEntity token = tokenService.queryByToken(tokenAndPageEntity.getToken());
		if(StringUtil.isValid(token) && DateUtil.diffDateSecond(DateUtil.getSystemDate(), token.getExpireTime()) > 0){
			SysUserEntity user = sysUserService.queryObject(token.getUserId()); // 拿到当前用户
			
			Map<String,Object> map = new HashMap<>();
			if (tokenAndPageEntity.getPage() == null || tokenAndPageEntity.getPage() < 1) {
				tokenAndPageEntity.setPage(1);
			}
			if (tokenAndPageEntity.getPagesize() == null || tokenAndPageEntity.getPagesize() < 1) {
				tokenAndPageEntity.setPagesize(10);
			}
			map.put("offset", (tokenAndPageEntity.getPage()-1) * tokenAndPageEntity.getPagesize());
			map.put("limit", tokenAndPageEntity.getPagesize());
			map.put("person", user.getId());
			
			List<BrknewsEntity> list = brknewsService.queryListByPerson(map);   // 需要展示的原始数据
			List<Map<String,Object>> data = new LinkedList<>();                 // 实际展示的数据
			
			Map<String,Object> ret = new HashMap<>();
			ret.put("page", tokenAndPageEntity.getPage());    // 当前页
			ret.put("pagesize", tokenAndPageEntity.getPagesize()); // 每页显示条数
			ret.put("count", brknewsService.queryCountByPerson(map)); // 总条数
			ret.put("data", data);
			
			Map<String, Object> _map = null;
			for (BrknewsEntity entity : list) {
				_map = new HashMap<>();
				_map.put("id", entity.getId());
				_map.put("busseg", baseCodeService.selectByTypeValue(PlatformContext.getGoalbalContext("biztype", String.class), entity.getType()).getCnm());
				_map.put("content", entity.getContent());
				_map.put("time", entity.getCrttm());
				_map.put("addr", entity.getAdds());
				_map.put("lat", entity.getLat());
				_map.put("lng", entity.getLng());
				_map.put("img", entity.getImg());
				data.add(_map);
			}
			
			return R.ok().putData(ret);
		} else {
			return R.error(50000, "身份过期，请重新登录");
		}
	}
	
	@ApiOperation(value = "爆料详情" , notes = "爆料详情")
	@RequestMapping(value = "/detail" , method = RequestMethod.POST)
	@ResponseBody
	public R detail(@RequestBody TokenAndIdEntity tokenAndIdEntity){
		TokenEntity token = tokenService.queryByToken(tokenAndIdEntity.getToken());
		if(StringUtil.isValid(token) && DateUtil.diffDateSecond(DateUtil.getSystemDate(), token.getExpireTime()) > 0){
			BrknewsEntity entity = brknewsService.queryObject(tokenAndIdEntity.getId());
			Map<String, Object> _map = new HashMap<>();
			_map.put("id", entity.getId());
			_map.put("caseno", entity.getCaseno());
			_map.put("busseg", baseCodeService.selectByTypeValue(PlatformContext.getGoalbalContext("biztype", String.class), entity.getType()).getCnm());
			_map.put("person", sysUserService.queryObject(Long.parseLong(entity.getCrtid())).getRealname());
			_map.put("content", entity.getContent());
			_map.put("time", entity.getCrttm());
			_map.put("addr", entity.getAdds());
			_map.put("lat", entity.getLat());
			_map.put("lng", entity.getLng());
			_map.put("img", entity.getImg());
			return R.ok().putData(_map);
		} else {
			return R.error(50000, "身份过期，请重新登录");
		}
	}
	
	
}
