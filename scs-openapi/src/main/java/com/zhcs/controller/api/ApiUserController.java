package com.zhcs.controller.api;

import com.zhcs.context.PlatformContext;
import com.zhcs.entity.*;
import com.zhcs.service.*;
import com.zhcs.util.WeChatUtil;
import com.zhcs.utils.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.jeewx.api.core.exception.WexinReqException;
import org.jeewx.api.wxuser.user.JwUserAPI;
import org.jeewx.api.wxuser.user.model.Wxuser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Api(value = "用户登录" , description = "微信员工端")
@Controller
@RequestMapping("/api/user")
@CrossOrigin
public class ApiUserController {
	
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private TokenService tokenService;
	@Autowired
	private WeixintokenService weixintokenService;
	@Autowired
	private BaseCodeService baseCodeService;
	@Autowired
	private FeedBackService feedBackService;
	@Autowired
	private SysDepartmentService sysDepartmentService;
	
	@ApiOperation(value = "登录" , notes = "用户登录")
	@RequestMapping(value = "/login" , method = RequestMethod.POST)
	@ResponseBody
	public R login(@RequestBody LoginEntity Login){
		if(StringUtil.isValid(Login.getMobile())){
			if(StringUtil.isValid(Login.getPwd())){
				Login.setPwd(Base64Util.decodeData(Login.getPwd()));
				// 查询用户信息
				SysUserEntity u = sysUserService.queryByMobile(Login.getMobile());   // 用户
				if(StringUtil.isValid(u)){
					if(u.getPwd().equals(new Sha256Hash(Login.getPwd() + u.getSalt()).toHex())){
						String code = Login.getCode();
						if (StringUtil.isValid(code)) {
							Map<String, Object> map = tokenService.createToken(u.getId());   // 生成token
							String openId = null;
							try {
								openId = WeChatUtil.getOpenId(PlatformContext.getGoalbalContext("appidadmin", String.class),
                                        PlatformContext.getGoalbalContext("appscretadmin", String.class), code);
							} catch (ActionException e) {
								if(e.getMessage().contains("无效的code")){
									return R.error(51005, e.getMessage());
								} else {
									return R.error(51008, e.getMessage());
								}
							}

							// String openId = "oY9cUv1xT_DWh0p1QJ2M6fZ8oSOQ";
							
							// 强制换绑
							u.setWechatid(openId);
							sysUserService.updateUser(u);
							
							
							// 获取用户的一些信息
							Wxuser wxuser = null;
							try {
								String accessTokenAdmin = weixintokenService.getAccessTokenAdmin();
								wxuser = JwUserAPI.getWxuser(accessTokenAdmin, openId);
							} catch (WexinReqException e) {
								LogUtil.error("根据 user_openid 获取用户的基本信息失败：{}", openId);
								return R.error(51006, "根据 user_openid 获取用户的基本信息失败");
							}
							
							// 填充页面需要的数据
							map.put("headimgurl", wxuser.getHeadimgurl()); // 头像
							map.put("username", u.getRealname());          // 姓名
							map.put("sex", wxuser.getSex());               // 性别
							map.put("mobile", u.getMobile());              // 电话
							// List<SysDepartmentEntity> list = sysUserService.queryDepartmentByUser(u.getId());
							List<SysDepartmentEntity> pdepts = sysUserService.getUserProject(u.getId()); // 项目部
							List<SysDepartmentEntity> idepts = sysUserService.getUserInstitution(u.getId()); // 项目部
							List<String> pdept = new ArrayList<>();
							List<Long> pdeptId = new ArrayList<>();
							List<String> idept = new ArrayList<>();
							List<Long> ideptId = new ArrayList<>();
							for (SysDepartmentEntity tmp: pdepts) {
								pdept.add(tmp.getName());
								pdeptId.add(tmp.getId());
							}
							for (SysDepartmentEntity tmp: idepts) {
								idept.add(tmp.getName());
								ideptId.add(tmp.getId());
							}
							map.put("pdept", pdept);                   // 项目部
							map.put("pdeptId", pdeptId);
							map.put("idept", idept);                   // 事业部
							map.put("ideptId", ideptId);
							
							List<SysRoleEntity> roles = sysUserService.queryRoleByUser(u.getId());
							List<String> role = new ArrayList<>();
							for (SysRoleEntity tmp : roles) {
								role.add(tmp.getName());
							}
							map.put("role", role);                      // 角色
							
							return R.ok().putData(map);
						} else {
							return R.error(51005,"无效的code");
						}
					} else {
						return R.error(51004,"密码错误");
					}
				} else {
					return R.error(51003,"手机号错误");
				}
			} else {
				return R.error(51002, "密码不能为空。");
			}
		} else {
			return R.error(51001, "手机号码不能为空。");
		}
	}
	
	@ApiOperation(value = "修改密码" , notes = "修改密码")
	@RequestMapping(value = "/modifyPwd" , method = RequestMethod.POST)
	@ResponseBody
	public R modifyPwd(@RequestBody NewPassword newPassword){
		TokenEntity token = tokenService.queryByToken(newPassword.getToken());
		if(StringUtil.isValid(token) && DateUtil.diffDateSecond(DateUtil.getSystemDate(), token.getExpireTime()) > 0){
			SysUserEntity user = sysUserService.queryObject(token.getUserId()); // 拿到当前用户
			
			String oldMobile = user.getMobile(); // 手机号
			if (!StringUtil.equals(oldMobile, newPassword.getMobile().trim())) {
				return R.error(51003, "手机号错误");
			}
			
			newPassword.setPwd(Base64Util.decodeData(newPassword.getPwd()));
			newPassword.setNewPwd(Base64Util.decodeData(newPassword.getNewPwd()));
			if (!validPwd(newPassword.getNewPwd())) {
				return R.error(51007, "密码要求：长度不小于6位，必须以字母开头，必须包含特殊字符，必须包含数字。");
			}
			
			if(user.getPwd().equals(new Sha256Hash(newPassword.getPwd() + user.getSalt()).toHex())){
				user.setPwd(new Sha256Hash(newPassword.getNewPwd() + user.getSalt()).toHex());
				sysUserService.updateUser(user);
				return R.ok();
			} else {
				return R.error(51004, "原密码错误");
			}
		} else {
			return R.error(50000, "身份过期，请重新登录");
		}
	}


	@ApiOperation(value = "部门通讯录" , notes = "部门通讯录")
	@RequestMapping(value = "/contacts" , method = RequestMethod.POST)
	@ResponseBody
	public R contacts(@RequestBody OnlyTokenEntity onlyTokenEntity){
		TokenEntity token = tokenService.queryByToken(onlyTokenEntity.getToken());
		if(StringUtil.isValid(token) && DateUtil.diffDateSecond(DateUtil.getSystemDate(), token.getExpireTime()) > 0){
			SysUserEntity user = sysUserService.queryObject(token.getUserId()); // 拿到当前用户
			List<Map<String,Object>> ret = new ArrayList<>();
			
			List<SysDepartmentEntity> projects = sysUserService.queryDepartmentByUser(user.getId());
			for (SysDepartmentEntity sde : projects) {
				List<SysDepartmentEntity> subDepartments = sysDepartmentService.querySubDepartments(sde.getId());
				List<Long> depts = new ArrayList<>();
				for (SysDepartmentEntity tmp : subDepartments) {
					depts.add(tmp.getId());
				}
				List<SysUserEntity> users = sysUserService.queryUsersBydepts(depts);
				
				Map<String, Object> item = null;
				for (SysUserEntity tmp : users) {
					List<SysRoleEntity> roles = sysUserService.queryRoleByUser(tmp.getId());
					item = new HashMap<>();
					ret.add(item);
					item.put("name", tmp.getRealname());
					String role = "";
					for (SysRoleEntity tmp1 : roles) {
						role += tmp1.getName();
					}
					item.put("role", role);
					item.put("mobile", tmp.getMobile());
				}
				
			}
			return R.ok().putData(ret);
		} else {
			return R.error(50000, "身份过期，请重新登录");
		}
	}
	
	
	@ApiOperation(value = "意见反馈" , notes = "意见反馈")
	@RequestMapping(value = "/feedback" , method = RequestMethod.POST)
	@ResponseBody
	public R feedback(@RequestBody FeedBackEntity feedBackEntity){
		TokenEntity token = tokenService.queryByToken(feedBackEntity.getToken());
		if(StringUtil.isValid(token) && DateUtil.diffDateSecond(DateUtil.getSystemDate(), token.getExpireTime()) > 0){
			if (StringUtil.isBlank(feedBackEntity.getProblemtype())) {
				return R.error(62001, "意见类型不能为空");
			}
			if (StringUtil.isBlank(feedBackEntity.getContent())) {
				return R.error(62002, "反馈内容不能为空");
			}
			
			SysUserEntity user = sysUserService.queryObject(token.getUserId()); // 拿到当前用户
			String infosource = PlatformContext.getGoalbalContext("infosource","1", String.class);
			
			feedBackEntity.setPerson(user.getId());
			feedBackEntity.setInfosource(infosource);
			BeanUtil.fillCCUUD(feedBackEntity, user.getId(), user.getId());
			feedBackService.save(feedBackEntity);
			return R.ok();
		} else {
			return R.error(50000, "身份过期，请重新登录");
		}
	}
	
	@ApiOperation(value = "意见反馈类型" , notes = "意见反馈类型")
	@RequestMapping(value = "/feedbacktype" , method = RequestMethod.POST)
	@ResponseBody
	public R feedbacktype(@RequestBody OnlyTokenEntity onlyTokenEntity){
		TokenEntity token = tokenService.queryByToken(onlyTokenEntity.getToken());
		if(StringUtil.isValid(token) && DateUtil.diffDateSecond(DateUtil.getSystemDate(), token.getExpireTime()) > 0){
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
			return R.error(50000, "身份过期，请重新登录");
		}
	}

	
	/**
	 * 校验密码
	 * 1、长度不小于6位
	 * 2、必须以字母开头
	 * 3、必须包含特殊字符
	 * 4、必须包含数字
	 * @param pwd
	 * @return
	 */
	private static boolean validPwd(String pwd) {
		if(StringUtils.isEmpty(pwd)){
			return false;
		}
		if(pwd.length() < 6){
			return false;
		}
		if(pwd.matches("^[a-zA-z](.*)") && pwd.matches("(.*)[-`=\\\\\\[\\];',./~!@#$%^&*()_+|{}:\"<>?]+(.*)") && pwd.matches("(.*)\\d+(.*)")){
			return true;
		}
		return false;
	}
	
}
