package com.zhcs.controller.api;

import com.zhcs.context.PlatformContext;
import com.zhcs.entity.*;
import com.zhcs.service.BaseCodeService;
import com.zhcs.service.SysUserService;
import com.zhcs.service.TokenService;
import com.zhcs.service.WorkrecordService;
import com.zhcs.utils.BeanUtil;
import com.zhcs.utils.DateUtil;
import com.zhcs.utils.R;
import com.zhcs.utils.StringUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Api(value = "工作记录" , description = "工作记录")
@Controller
@RequestMapping("/api/workrecord")
@CrossOrigin
public class ApiWorkrecordController {
	
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private TokenService tokenService;
	@Autowired
	private BaseCodeService baseCodeService;
	@Autowired
	private WorkrecordService workrecordService;
	
	@ApiOperation(value = "列表" , notes = "列表")
	@RequestMapping(value = "/list" , method = RequestMethod.POST)
	@ResponseBody
	public R list(@RequestBody TokenAndPageEntity tokenAndPageEntity){
		TokenEntity token = tokenService.queryByToken(tokenAndPageEntity.getToken());
		if(StringUtil.isValid(token) && DateUtil.diffDateSecond(DateUtil.getSystemDate(), token.getExpireTime()) > 0){

			if (tokenAndPageEntity.getPage() == null || tokenAndPageEntity.getPage() < 1 ) {
				tokenAndPageEntity.setPage(1);
			}
			if (tokenAndPageEntity.getPagesize() == null || tokenAndPageEntity.getPagesize() < 1) {
				tokenAndPageEntity.setPagesize(10);
			}
			Map<String, Object> map = new HashMap<>();
			map.put("page", tokenAndPageEntity.getPage());
			map.put("offset", (tokenAndPageEntity.getPage()-1) * tokenAndPageEntity.getPagesize());
			map.put("pagesize", tokenAndPageEntity.getPagesize());
			
			List<WorkrecordEntity> workrecordList = workrecordService.queryList(map);
			List<Map<String, Object>> result = new ArrayList<>();
			Map<String, Object> item = null;
			for (WorkrecordEntity tmp : workrecordList) {
				item = new LinkedHashMap<>();
				result.add(item);
				item.put("id", tmp.getId());       // 工作记录的id
				item.put("expl", tmp.getExpl());   // 内容
				item.put("updtm", tmp.getUpdtm()); // 时间
				item.put("img", tmp.getImg());     // 图片
				item.put("addr", tmp.getAddr());   // 详细地址
				item.put("accaddr", tmp.getAccaddr()); // 精确地址
				item.put("busseg", baseCodeService.selectByTypeValue("biztype", tmp.getBusseg()).getCnm());
			}
			int count = workrecordService.queryTotal(map);
			
			Map<String,Object> ret = new HashMap<>();   // 包括数据和分页信息
			ret.put("page", tokenAndPageEntity.getPage());           // 当前页码
			ret.put("pagesize", tokenAndPageEntity.getPagesize());   // 每页显示的条数
			ret.put("count", count);                    // 总条数
			ret.put("data", result);
			
			return R.ok().putData(ret);
		} else {
			return R.error(50000, "身份过期，请重新登录");
		}
	}
	
	@ApiOperation(value = "详细信息" , notes = "详细信息")
	@RequestMapping(value = "/detail" , method = RequestMethod.POST)
	@ResponseBody
	public R detail(@RequestBody TokenAndIdEntity tokenAndidEntity){
		TokenEntity token = tokenService.queryByToken(tokenAndidEntity.getToken());
		if(StringUtil.isValid(token) && DateUtil.diffDateSecond(DateUtil.getSystemDate(), token.getExpireTime()) > 0){
			
			if (tokenAndidEntity.getId() == null) {
				return R.error();
			}
			WorkrecordEntity entity = workrecordService.queryObject(tokenAndidEntity.getId());
			Map<String, Object> ret = new HashMap<>();
			// 记录id
			ret.put("id", entity.getId());
			// 类型：比如清扫保洁
			ret.put("busseg", baseCodeService.selectByTypeValue("biztype", entity.getBusseg()).getCnm());
			// 时间
			ret.put("updtm", entity.getUpdtm());
			// 人
			ret.put("person", sysUserService.queryObject(entity.getCrtid()).getRealname());
			// 地址
			ret.put("addr", entity.getAccaddr());
			ret.put("lat", entity.getLat());
			ret.put("lng", entity.getLng());
			// 内容
			ret.put("expl", entity.getExpl());
			// 图片
			ret.put("img", entity.getImg());
			
			return R.ok().putData(ret);
		} else {
			return R.error(50000, "身份过期，请重新登录");
		}
	}
	
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
	
	@ApiOperation(value = "新增" , notes = "新增")
	@RequestMapping(value = "/add" , method = RequestMethod.POST)
	@ResponseBody
	public R add(@RequestBody WorkrecordEntity workrecordEntity){
		TokenEntity token = tokenService.queryByToken(workrecordEntity.getToken());
		if(StringUtil.isValid(token) && DateUtil.diffDateSecond(DateUtil.getSystemDate(), token.getExpireTime()) > 0){
			
			if (workrecordEntity.getBusseg() == null || 
				workrecordEntity.getAddr() == null ||
				workrecordEntity.getLat() == null ||
				workrecordEntity.getLng()==null ||
				workrecordEntity.getExpl() == null) {
				return R.error(91001, "类型、地址和说明必须填写");
			}
			
			
			SysUserEntity user = sysUserService.queryObject(token.getUserId()); // 拿到当前用户
			BeanUtil.fillCCUUD(workrecordEntity, user.getId(), user.getId());
			workrecordEntity.setAccaddr(workrecordEntity.getAddr());
			workrecordService.save(workrecordEntity);
			return R.ok();
		} else {
			return R.error(50000, "身份过期，请重新登录");
		}
	}
	
	
	
}
