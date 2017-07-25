package com.zhcs.controller.api;

import java.util.ArrayList;
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
import com.zhcs.entity.ClassesEntity;
import com.zhcs.entity.ScheduleEntity;
import com.zhcs.entity.SysDepartmentEntity;
import com.zhcs.entity.SysUserEntity;
import com.zhcs.entity.TokenAndIdEntity;
import com.zhcs.entity.TokenAndPageEntity;
import com.zhcs.entity.TokenEntity;
import com.zhcs.service.ClassesService;
import com.zhcs.service.SysDepartmentService;
import com.zhcs.service.SysUserService;
import com.zhcs.service.TokenService;
import com.zhcs.utils.BeanUtil;
import com.zhcs.utils.DateUtil;
import com.zhcs.utils.R;
import com.zhcs.utils.StringUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "排班管理" , description = "排班管理")
@Controller
@RequestMapping("/api/schedule")
@CrossOrigin
public class ApiClassesController {
	
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private TokenService tokenService;
	@Autowired
	private ClassesService classesService;
	
	@ApiOperation(value = "新增班次" , notes = "新增班次")
	@RequestMapping(value = "/addClasses" , method = RequestMethod.POST)
	@ResponseBody
	public R addClasses(@RequestBody ClassesEntity classes){
		TokenEntity token = tokenService.queryByToken(classes.getToken());
		if(StringUtil.isValid(token) && DateUtil.diffDateSecond(DateUtil.getSystemDate(), token.getExpireTime()) > 0){
			SysUserEntity user = sysUserService.queryObject(token.getUserId()); // 拿到当前用户
			
			classes.setBusiness(Long.valueOf(PlatformContext.getGoalbalContext("zhxc", String.class))); // 只有综合巡查的排班
			
			BeanUtil.fillCCUUD(classes, user.getId(), user.getId());
			classesService.save(classes);
			
			return R.ok();
		} else {
			return R.error(50000, "身份过期，请重新登录");
		}
	}
	
	@ApiOperation(value = "班次设置列表" , notes = "班次设置列表")
	@RequestMapping(value = "/listClasses" , method = RequestMethod.POST)
	@ResponseBody
	public R listClasses(@RequestBody TokenAndPageEntity tokenAndPageEntity){
		TokenEntity token = tokenService.queryByToken(tokenAndPageEntity.getToken());
		if(StringUtil.isValid(token) && DateUtil.diffDateSecond(DateUtil.getSystemDate(), token.getExpireTime()) > 0){
			
			Map<String, Object> map = new HashMap<>();
			
			if (tokenAndPageEntity.getPage() == null || tokenAndPageEntity.getPage() < 1 ) {
				tokenAndPageEntity.setPage(1);
			}
			if (tokenAndPageEntity.getPagesize() == null || tokenAndPageEntity.getPagesize() < 1) {
				tokenAndPageEntity.setPagesize(10);
			}
			map.put("page", tokenAndPageEntity.getPage());
			map.put("offset", (tokenAndPageEntity.getPage()-1) * tokenAndPageEntity.getPagesize());
			map.put("limit", tokenAndPageEntity.getPagesize());
			map.put("business", Long.valueOf(PlatformContext.getGoalbalContext("zhxc", String.class)));
			
			
			//查询列表数据
			List<ClassesEntity> classesList = classesService.queryList(map);
			int count = classesService.queryTotal(map);
			
			List<Map<String,Object>> data = new ArrayList<>();
			Map<String,Object> item = null;
			for (ClassesEntity temp : classesList) {
				item = new HashMap<>();
				data.add(item);
				item.put("id", temp.getId());               // 班次id
				item.put("classes", temp.getClasses());     // 班次名称
				item.put("starttime", temp.getStarttime()); // 开始时间
				item.put("endtime", temp.getEmdtime());     // 结束时间
				item.put("dept", temp.getPjdept());         // 所属项目部
			}
			Map<String,Object> ret = new HashMap<>();   // 包括数据和分页信息
			ret.put("page", map.get("page"));           // 当前页码
			ret.put("pagesize", map.get("pagesize"));   // 每页显示的条数
			ret.put("count", count);                    // 总条数
			ret.put("data", data);

			return R.ok().putData(ret);
			
		} else {
			return R.error(50000, "身份过期，请重新登录");
		}
	}
	
	@ApiOperation(value = "删除班次" , notes = "删除班次")
	@RequestMapping(value = "/deleteClasses" , method = RequestMethod.POST)
	@ResponseBody
	public R deleteClasses(@RequestBody TokenAndIdEntity tokenAndIdEntity){
		TokenEntity token = tokenService.queryByToken(tokenAndIdEntity.getToken());
		if(StringUtil.isValid(token) && DateUtil.diffDateSecond(DateUtil.getSystemDate(), token.getExpireTime()) > 0){
			
			Long id = tokenAndIdEntity.getId();
			classesService.delete(id);
			
			return R.ok();
			
		} else {
			return R.error(50000, "身份过期，请重新登录");
		}
	}
	
	@ApiOperation(value = "新增排班" , notes = "新增排班")
	@RequestMapping(value = "/addSchedule" , method = RequestMethod.POST)
	@ResponseBody
	public R addSchedule(@RequestBody ScheduleEntity scheduleEntity){
		TokenEntity token = tokenService.queryByToken(scheduleEntity.getToken());
		if(StringUtil.isValid(token) && DateUtil.diffDateSecond(DateUtil.getSystemDate(), token.getExpireTime()) > 0){
			
			String personName = scheduleEntity.getPersonName(); // 员工姓名
			Long dept = scheduleEntity.getDept(); // 所属部门
			
			
			
			return R.ok();
			
		} else {
			return R.error(50000, "身份过期，请重新登录");
		}
	}
	
	
	
}
