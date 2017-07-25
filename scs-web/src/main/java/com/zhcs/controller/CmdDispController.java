package com.zhcs.controller;

import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.zhcs.context.PlatformContext;
import com.zhcs.entity.BrknewsEntity;
import com.zhcs.entity.CitizenEntity;
import com.zhcs.entity.EventEntity;
import com.zhcs.entity.EventLogEntity;
import com.zhcs.entity.WorkflowEntity;
import com.zhcs.service.BrknewsService;
import com.zhcs.service.CitizenService;
import com.zhcs.service.EventLogService;
import com.zhcs.service.EventService;
import com.zhcs.service.SerianoService;
import com.zhcs.service.WorkflowService;
import com.zhcs.utils.BeanUtil;
import com.zhcs.utils.DateUtil;
import com.zhcs.utils.LogUtil;
import com.zhcs.utils.R;
import com.zhcs.utils.StringUtil;

//*****************************************************************************
/**
 * <p>Title:WorkrecordController</p>
 * <p>Description: 指挥调度</p>
 * <p>有冇人好似我咁不停重復？</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
@RestController
@RequestMapping("cmddisp")
public class CmdDispController extends AbstractController  {
	@Autowired
	private EventService eventService;
	@Autowired
	private EventLogService eventLogService;
	@Autowired
	private WorkflowService workflowService;
	@Autowired
	private SerianoService serianoService;
	@Autowired
	private BrknewsService brknewsService;
	@Autowired
	private CitizenService citizenService;

	//*************************************************************************
	/** 
	 * 【保存】保存派单信息
	 * @param map
	 * @return  
	 */
	//*************************************************************************
	@ResponseBody
	@RequestMapping("/savecmddisp")
	public R saveCmdDisp(@RequestBody Map<String, Object> map) {
		String flag = StringUtil.valueOf(map.get("btype"));
		Long id = Long.valueOf(StringUtil.valueOf(map.get("id")));
		String current = StringUtil.valueOf(map.get("current"));
		WorkflowEntity we = workflowService.queryOtherNodeObject(current, flag);
		EventEntity event = eventService.queryObject(id);
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
		eventlog.setDes(StringUtil.valueOf(map.get("content")));
		if(StringUtil.isValid(StringUtil.valueOf(map.get("handle")))){
			eventlog.setHandle(Long.valueOf(StringUtil.valueOf(map.get("handle"))));
		}
		if(StringUtil.isValid(StringUtil.valueOf(map.get("auxiliary")))){
			eventlog.setAuxiliary(Long.valueOf(StringUtil.valueOf(map.get("auxiliary"))));
		}
		if(StringUtil.isValid(StringUtil.valueOf(map.get("estimatetm")))){
			eventlog.setEstimatetm(DateUtil.strToDate(StringUtil.valueOf(map.get("estimatetm"))));
		}
		BeanUtil.fillCCUUD(eventlog, getUserId(), getUserId());
		eventLogService.save(eventlog);
		if(PlatformContext.getGoalbalContext("completed", "ja", String.class).equals(we.getNodeno())){
			eventlog.setFlag("1");
			eventlog.setDes("自动结案");
			eventlog.setCurrent(we.getNodeno());
			eventlog.setNext(we.getNextno());
			BeanUtil.fillCCUUD(eventlog, 99999L, 99999L);
			eventLogService.save(eventlog);
			eventService.delete(id);
		} else {
			event.setCurrent(eventlog.getNext());
			event.setHandle(eventlog.getHandle());
			event.setAuxiliary(eventlog.getAuxiliary());
			event.setDltm(eventlog.getDltm());
			event.setEstimatetm(eventlog.getEstimatetm());
			event.setDes(eventlog.getDes());
			BeanUtil.fillCCUUD(event, getUserId());
			eventService.update(event);
		}
		return R.ok();
	}
	
	@ResponseBody
	@RequestMapping("/addEvent")
	@RequiresPermissions("event:add")
	public R addEvent(@RequestBody Map<String, Object> map) {
		
		String mobile = StringUtil.valueOf(map.get("mobile"));
		String soutce = StringUtil.valueOf(map.get("sour"));
		String busseg = StringUtil.valueOf(map.get("busseg"));
		String sourOject = StringUtil.valueOf(map.get("sourOject"));
		String addr = StringUtil.valueOf(map.get("addrs"));
		Long userid = null;
		if("0".equals(soutce)){
			CitizenEntity citizen = null;
			// 不存在userid时再进行添加操作
			LogUtil.debug(""+StringUtil.isBlank(StringUtil.valueOf(map.get("userid"))));
			if(StringUtil.isBlank(StringUtil.valueOf(map.get("userid")))){
				if(StringUtil.isValid(mobile)){
					// 检查一下mobile是否存在，不存在则表示新的市民，进行保存
					citizen = citizenService.queryObjectByMobile(mobile);
					if(StringUtil.isValid(citizen)){
						userid = citizen.getId();
					} else {
						LogUtil.info("市民手机号码不存在，进行保存操作。");
						citizen = saveCitizen(sourOject, mobile, addr);
						userid = citizen.getId();
					}
				} else {
					return R.error("手机号码不能为空");
				}
			} else {
				userid = Long.valueOf(StringUtil.valueOf(map.get("userid")));
			}
		} else {
			userid = Long.valueOf(StringUtil.valueOf(map.get("userid")));
		}
		String current = PlatformContext.getGoalbalContext("bn", "bl", String.class);
		WorkflowEntity we = workflowService.queryOtherNodeObject(current, "1");
		BrknewsEntity bn = new BrknewsEntity();
		bn.setType(busseg);
		bn.setAdds(addr);
		bn.setContent(StringUtil.guoHtml(StringUtil.valueOf(map.get("des"))));
		bn.setLat(StringUtil.valueOf(map.get("lat")));
		bn.setLng(StringUtil.valueOf(map.get("lng")));
		bn.setSource(soutce);
		String caseno = serianoService.getId("event.no", DateUtil.getSystemDateStr("yyyyMM"));
		bn.setCaseno(caseno);	
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
	}

	//*************************************************************************
	/** 
	* 【保存】保存市民信息
	* @param mobile 手机号码
	* @param addr 地址
	* @return  
	*/
	//*************************************************************************
	private CitizenEntity saveCitizen(String sourOject, String mobile, String addr) {
		CitizenEntity citizen;
		// 注册
		citizen = new CitizenEntity();
		citizen.setNm(sourOject);
		citizen.setMobile(mobile);
		citizen.setGender("3");
		citizen.setAddr(addr);
		BeanUtil.fillCCUUD(citizen);
		citizenService.save(citizen);
		LogUtil.debug("###" + citizen.getId());
		return citizen;
	}
	
}
