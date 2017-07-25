package com.zhcs.controller.api;

import com.zhcs.context.PlatformContext;
import com.zhcs.entity.*;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(value = "通用功能", description = "通用功能")
@Controller
@RequestMapping("/api/common")
@CrossOrigin
public class ApiCommonController {

    @Autowired
    private TokenService tokenService;
    @Autowired
    private SysDepartmentService sysDepartmentService;

    @ApiOperation(value = "获取项目部", notes = "获取项目部")
    @RequestMapping(value = "/getProjectDepts", method = RequestMethod.POST)
    @ResponseBody
    public R getProjectDepts(@RequestBody TokenAndIdEntity tokenAndIdEntity) {
        TokenEntity token = tokenService.queryByToken(tokenAndIdEntity.getToken());
        if (StringUtil.isValid(token) && DateUtil.diffDateSecond(DateUtil.getSystemDate(), token.getExpireTime()) > 0) {
            List<SysDepartmentEntity> depts = null;
            String projectOtype = PlatformContext.getGoalbalContext("projectOtype", String.class);
            if (tokenAndIdEntity.getId() == null) {
                depts = sysDepartmentService.queryListByOtype(projectOtype);
            } else {
                depts = sysDepartmentService.queryProjectByInstitution(tokenAndIdEntity.getId(), projectOtype);
            }
            Map<String, List<?>> result = new HashMap<>();
            List<String> names = new ArrayList<>();
            List<Long> ids = new ArrayList<>();
            for (SysDepartmentEntity dept : depts) {
                ids.add(dept.getId());
                names.add(dept.getName());
            }
            result.put("names", names);
            result.put("ids", ids);
            return R.ok().putData(result);
        } else {
            return R.error(50000, "身份过期，请重新登录");
        }
    }

    @ApiOperation(value = "根据项目部获取项目", notes = "根据项目部获取项目")
    @RequestMapping(value = "/getProjectByProjectDept", method = RequestMethod.POST)
    @ResponseBody
    public R getProjectByProjectDept(@RequestBody TokenAndIdEntity tokenAndIdEntity) {
        TokenEntity token = tokenService.queryByToken(tokenAndIdEntity.getToken());
        if (StringUtil.isValid(token) && DateUtil.diffDateSecond(DateUtil.getSystemDate(), token.getExpireTime()) > 0) {
            String pjtOtype = PlatformContext.getGoalbalContext("pjtOtype", String.class);
            List<SysDepartmentEntity> depts = sysDepartmentService.queryProjectByProjectDept(tokenAndIdEntity.getId(), pjtOtype);
            Map<String, List<?>> result = new HashMap<>();
            List<String> names = new ArrayList<>();
            List<Long> ids = new ArrayList<>();
            for (SysDepartmentEntity dept : depts) {
                ids.add(dept.getId());
                names.add(dept.getName());
            }
            result.put("names", names);
            result.put("ids", ids);
            return R.ok().putData(result);
        } else {
            return R.error(50000, "身份过期，请重新登录");
        }
    }

    @ApiOperation(value = "根据项目获取班级", notes = "根据项目获取班级")
    @RequestMapping(value = "/getClassByProject", method = RequestMethod.POST)
    @ResponseBody
    public R getClassByProject(@RequestBody TokenAndIdEntity tokenAndIdEntity) {
        TokenEntity token = tokenService.queryByToken(tokenAndIdEntity.getToken());
        if (StringUtil.isValid(token) && DateUtil.diffDateSecond(DateUtil.getSystemDate(), token.getExpireTime()) > 0) {
            String classOtype = PlatformContext.getGoalbalContext("classOtype", String.class);
            List<SysDepartmentEntity> depts = sysDepartmentService.queryClassByProject(tokenAndIdEntity.getId(), classOtype);
            Map<String, List<?>> result = new HashMap<>();
            List<String> names = new ArrayList<>();
            List<Long> ids = new ArrayList<>();
            for (SysDepartmentEntity dept : depts) {
                ids.add(dept.getId());
                names.add(dept.getName());
            }
            result.put("names", names);
            result.put("ids", ids);
            return R.ok().putData(result);
        } else {
            return R.error(50000, "身份过期，请重新登录");
        }
    }

    @ApiOperation(value = "根据班级获取人", notes = "根据班级获取人")
    @RequestMapping(value = "/getPersonByClass", method = RequestMethod.POST)
    @ResponseBody
    public R getPersonByClass(@RequestBody TokenAndIdEntity tokenAndIdEntity) {
        TokenEntity token = tokenService.queryByToken(tokenAndIdEntity.getToken());
        if (StringUtil.isValid(token) && DateUtil.diffDateSecond(DateUtil.getSystemDate(), token.getExpireTime()) > 0) {
            List<SysUserEntity> ps = sysDepartmentService.queryPersonByClass(tokenAndIdEntity.getId());
            Map<String, List<?>> result = new HashMap<>();
            List<String> names = new ArrayList<>();
            List<Long> ids = new ArrayList<>();
            for (SysUserEntity person : ps) {
                ids.add(person.getId());
                names.add(person.getRealname());
            }
            result.put("names", names);
            result.put("ids", ids);
            return R.ok().putData(result);
        } else {
            return R.error(50000, "身份过期，请重新登录");
        }
    }


}
