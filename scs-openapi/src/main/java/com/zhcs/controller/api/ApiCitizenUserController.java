package com.zhcs.controller.api;

import com.zhcs.context.PlatformContext;
import com.zhcs.entity.*;
import com.zhcs.service.BaseCodeService;
import com.zhcs.service.CitizenService;
import com.zhcs.service.FeedBackService;
import com.zhcs.util.WeChatUtil;
import com.zhcs.utils.ActionException;
import com.zhcs.utils.BeanUtil;
import com.zhcs.utils.R;
import com.zhcs.utils.StringUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Api(value = "微信市民端用户相关" , description = "用户相关")
@Controller
@RequestMapping("/api/citizen/user")
@CrossOrigin
public class ApiCitizenUserController {

	@Autowired
	private CitizenService citizenService;
	@Autowired
	private FeedBackService feedBackService;
	@Autowired
	private BaseCodeService baseCodeService;

	@ApiOperation(value = "通过CODE获取openId" , notes = "通过CODE获取openId")
	@RequestMapping(value = "/getOpenId" , method = RequestMethod.POST)
	@ResponseBody
	public R login(@RequestBody CitizenUserEntity cizizenUser){
		String code = cizizenUser.getCode();
		if (StringUtil.isValid(code)) {
			String openId = null;
			try {
				openId = WeChatUtil.getOpenId(PlatformContext.getGoalbalContext("appid", String.class),
						PlatformContext.getGoalbalContext("appscret", String.class), code);
				return R.ok().putData(openId);
			} catch (ActionException e) {
				if (e.getMessage().contains("无效的code")) {
					return R.error(51005, e.getMessage());
				} else {
					return R.error(51008, e.getMessage());
				}
			}
		}
		return R.error(51005,"无效的code");
	}

	@ApiOperation(value = "通过openId获取我的个人信息" , notes = "通过openId获取我的个人信息")
	@RequestMapping(value = "/getMyAccount" , method = RequestMethod.POST)
	@ResponseBody
	public R getMyAccount(@RequestBody OnlyOpenIdEntity onlyOpenId){
		// 检查openId是否在我们市民关注列表，不在表示无效
		if (StringUtil.isValid(onlyOpenId.getOpenId())) {
			CitizenEntity citizen = citizenService.queryObjectByWeChatId(onlyOpenId.getOpenId());
			return R.ok().putData(citizen);
		} else {
			return R.error(60000, "openId无效");
		}
	}

	@ApiOperation(value = "修改个人资料" , notes = "修改个人资料")
	@RequestMapping(value = "/modifyMyAccount" , method = RequestMethod.POST)
	@ResponseBody
	public R modifyMyAccount(@RequestBody CitizenEntity citizen){
		// 检查openId是否在我们市民关注列表，不在表示无效
		if (StringUtil.isValid(citizen.getWechatid())) {
			CitizenEntity c = citizenService.queryObjectByWeChatId(citizen.getWechatid());
			if (StringUtil.isValid(c)) {
				if(StringUtil.isValid(citizen.getGender())){
					c.setGender(citizen.getGender());
				}
				if(StringUtil.isValid(citizen.getNm())){
					c.setNm(citizen.getNm());
				}
				if(StringUtil.isValid(citizen.getFaceimg())){
					c.setFaceimg(citizen.getFaceimg());
				}
				if(StringUtil.isValid(citizen.getAddr())){
					c.setAddr(citizen.getAddr());
				}
				BeanUtil.fillCCUUD(c, c.getId());
				citizenService.update(citizen);
				return R.ok();
			} else {
				return R.error(60000, "openId无效");
			}
		} else {
			return R.error(60000, "openId无效");
		}
	}

	@ApiOperation(value = "意见反馈" , notes = "意见反馈")
	@RequestMapping(value = "/feedBack" , method = RequestMethod.POST)
	@ResponseBody
	public R feedBack(@RequestBody CitizenFeedBackEntity citizenFeedBack){
		// 检查openId是否在我们市民关注列表，不在表示无效
		if (StringUtil.isValid(citizenFeedBack.getOpenId())) {
			CitizenEntity citizen = citizenService.queryObjectByWeChatId(citizenFeedBack.getOpenId());
			if (StringUtil.isValid(citizen)) {
				FeedBackEntity fb = new FeedBackEntity();
				fb.setContent(citizenFeedBack.getContent());
				fb.setImg(citizenFeedBack.getImg());
				fb.setProblemtype(citizenFeedBack.getProblemtype());
				String infosource = PlatformContext.getGoalbalContext("infosource_0","0", String.class );
				fb.setInfosource(infosource);
				fb.setPerson(citizen.getId());
				feedBackService.save(fb);
				return  R.ok();
			} else {
				return R.error(60000, "openId无效");
			}
		} else {
			return R.error(60000, "openId无效");
		}
	}

	@ApiOperation(value = "意见反馈类型" , notes = "意见反馈类型")
	@RequestMapping(value = "/feedBackType" , method = RequestMethod.POST)
	@ResponseBody
	public R feedBackType(@RequestBody OnlyOpenIdEntity onlyOpenApi){
		// 检查openId是否在我们市民关注列表，不在表示无效
		if (StringUtil.isValid(onlyOpenApi.getOpenId())) {
			CitizenEntity citizen = citizenService.queryObjectByWeChatId(onlyOpenApi.getOpenId());
			if (StringUtil.isValid(citizen)) {
				String problemtype = PlatformContext.getGoalbalContext("problemtype", String.class);
				List<BaseCodeEntity> list = baseCodeService.selectByType(problemtype);
				Map<String, List<String>> map = new LinkedHashMap<>();
				List<String> keys = new ArrayList<>();
				List<String> values = new ArrayList<>();
				for (BaseCodeEntity bce : list) {
					keys.add(bce.getCnm());
					values.add(bce.getNo());
				}
				map.put("keys", keys);
				map.put("values", values);
				return R.ok().putData(map);
			} else {
				return R.error(60000, "openId无效");
			}
		} else {
			return R.error(60000, "openId无效");
		}
	}

}
