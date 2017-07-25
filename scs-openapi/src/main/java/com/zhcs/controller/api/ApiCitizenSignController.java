package com.zhcs.controller.api;

import com.zhcs.context.PlatformContext;
import com.zhcs.entity.SignApplyEntity;
import com.zhcs.entity.SignEntity;
import com.zhcs.service.SignService;
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

@Api(value = "微信市民端招牌相关" , description = "招牌相关")
@Controller
@RequestMapping("/api/citizen/sign")
@CrossOrigin
public class ApiCitizenSignController {

	@Autowired
	private SignService signService;

	@ApiOperation(value = "招牌申请" , notes = "招牌申请")
	@RequestMapping(value = "/apply" , method = RequestMethod.POST)
	@ResponseBody
	public R login(@RequestBody SignApplyEntity sign){
		String code = sign.getCode();
		if (StringUtil.isValid(code)) {
			String openId = null;
			try {
				openId = WeChatUtil.getOpenId(PlatformContext.getGoalbalContext("appid", String.class),
						PlatformContext.getGoalbalContext("appscret", String.class), code);
				SignEntity signEntity = new SignEntity();
				signEntity.setSignimg(sign.getSignImg());
				signEntity.setContent(sign.getContent());
				signEntity.setLength(sign.getLength());
				signEntity.setWidth(sign.getWidth());
				signEntity.setHeight(sign.getHeight());
				signEntity.setNature(sign.getNature());
				signEntity.setType(sign.getType());
				signEntity.setLight(sign.getLight());
				signEntity.setTerm(sign.getTerm());
				signEntity.setProvince(sign.getProvince());
				signEntity.setCity(sign.getCity());
				signEntity.setCounty(sign.getCounty());
				signEntity.setDtladdress(sign.getDtlAddress());
				signEntity.setUnm(sign.getUnm());
				signEntity.setUnit(sign.getUnit());
				signEntity.setBlcs(sign.getBlcs());
				signEntity.setOpenid(openId);
				signEntity.setMobile(sign.getMobile());
				BeanUtil.fillCCUUD(signEntity);
				signService.save(signEntity);
				return R.ok();
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

}
