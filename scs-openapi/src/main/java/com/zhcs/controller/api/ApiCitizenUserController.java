package com.zhcs.controller.api;

import com.zhcs.context.PlatformContext;
import com.zhcs.entity.CitizenUserEntity;
import com.zhcs.service.SignService;
import com.zhcs.util.WeChatUtil;
import com.zhcs.utils.ActionException;
import com.zhcs.utils.R;
import com.zhcs.utils.StringUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Api(value = "微信市民端用户相关" , description = "用户相关")
@Controller
@RequestMapping("/api/citizen/user")
@CrossOrigin
public class ApiCitizenUserController {

	@Autowired
	private SignService signService;

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

}
