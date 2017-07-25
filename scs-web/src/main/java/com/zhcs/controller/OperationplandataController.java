package com.zhcs.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zhcs.annotation.SysLog;
import com.zhcs.context.PlatformContext;
import com.zhcs.entity.OperationplanEntity;
import com.zhcs.entity.OperationplandataEntity;
import com.zhcs.service.OperationplanService;
import com.zhcs.service.OperationplandataService;
import com.zhcs.utils.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//*****************************************************************************

/**
 * <p>Title:OperationplandataController</p>
 * <p>Description: 作业计划数据</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 *
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
@Controller
@RequestMapping("operationplandata")
public class OperationplandataController extends AbstractController {
    @Autowired
    private OperationplandataService operationplandataService;
    @Autowired
    private OperationplanService operationplanService;

    @RequestMapping("/operationplandata.html")
    public String list() {
        return "operationplandata/operationplandata.html";
    }

    /**
     * 列表
     */
    @ResponseBody
    @RequestMapping("/list")
    @RequiresPermissions("operationplandata:list")
    public R list(String sidx, String order, Integer page, Integer limit) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("sidx", sidx);
        map.put("order", order);
        map.put("offset", (page - 1) * limit);
        map.put("limit", limit);

        //查询列表数据
        List<Map<String, Object>> oList = operationplandataService.queryList2(map);
        int total = operationplandataService.queryTotal(map);

        PageUtils pageUtil = new PageUtils(oList, total, limit, page);

        return R.ok().put("page", pageUtil);
    }

    /**
     * 信息
     */
    @ResponseBody
    @RequestMapping("/info/{id}")
    @RequiresPermissions("operationplandata:info")
    public R info(@PathVariable("id") Long id) {
        OperationplandataEntity operationplandata = operationplandataService.queryObject(id);

        return R.ok().put("operationplandata", operationplandata);
    }

    /**
     * 获取重复排版天
     */
    @ResponseBody
    @RequestMapping("/day")
    @RequiresPermissions("operationplandata:day")
    public R queryDay(@RequestBody Map<String, Object> tdate) {
        try {
            String[] banci = StringUtil.StrList(StringUtil.listToString((JSONArray) tdate.get("banci")));
            int date = operationplandataService.queryDay(StringUtil.valueOf(tdate.get("date")), banci);
            return R.ok().put("date", date);
        } catch (RuntimeException e) {
            throw new ZHCSException("服务器异常：(获取重复排版月份失败)" + e.getMessage());
        }
    }

    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("operationplandata:update")
    public R update(@RequestBody OperationplandataEntity operationplandata) {

        BeanUtil.fillCCUUD(operationplandata, getUserId());
        operationplandataService.update(operationplandata);

        return R.ok();
    }

    /**
     * 删除
     */
    @ResponseBody
    @RequestMapping("/delete/{id}")
    @RequiresPermissions("operationplandata:delete")
    public R delete(@PathVariable("id") Long id) {
        operationplandataService.delete(id);
        return R.ok();
    }

    /**
     * 获取排班信息
     */
    @ResponseBody
    @RequestMapping("/paiban")
    @RequiresPermissions("operationplandata:paiban")
    public R queryPaiban(@RequestBody Map<String, Object> map) {
        //获取某月的   => 年月日星期
        Map<String, Object> maps;
        try {
            maps = new HashMap<String, Object>();
            String tempDate = StringUtil.valueOf(map.get("time"));
            String week = DateUtil.getWeekStr(tempDate);
            Map<String, Object> t = new HashMap<String, Object>();
            t.put("value", tempDate);
            t.put("text", tempDate.concat(" ").concat(week));
            List<Map<String, Object>> array = new ArrayList<Map<String, Object>>();
            array.add(t);
            maps.put("days", array);
        } catch (Exception e) {
            throw new ZHCSException("服务器异常：(获取日期失败)" + e.getMessage());
        }

        //获取班次的 => 开始和结束时间
        String[] sedate = StringUtil.StrList(StringUtil.listToString((JSONArray) map.get("banci")));
        List<OperationplanEntity> sEdate = operationplanService.querySEdate(sedate);
        if (StringUtil.isValid(sEdate)) {
            maps.put("sedate", sEdate);
        } else {
            return R.error(500, "获取班次失败和开始结束时间失败!");
        }

        List<Map<String, Object>> business;
        //根据相关的项目部获取对应的班级
        Map<String, Object> classotypemap = new HashMap<String, Object>();
        classotypemap.put("otype", PlatformContext.getGoalbalContext("classotype", String.class));
        classotypemap.put("userid", getUserId());
        business = operationplanService.queryDept(classotypemap);
        if (StringUtil.isValid(business)) {
            maps.put("business", business);
        } else {
            return R.error(500, "由项目部获取对应的班级失败!");
        }

        //获取班级下的所有成员
        List<Map<String, Object>> deptnames;
        deptnames = null;
        Map<String, Object> temp = null;
        List<Integer> bl = new ArrayList<Integer>();
        List<Map<String, Object>> nmap = new ArrayList<Map<String, Object>>();
        for (Map<String, Object> bs : business) {
            temp = new HashMap<String, Object>();
            deptnames = operationplanService.queryDeptname(StringUtil.valueOf(bs.get("value")));
            temp.put("name", StringUtil.valueOf(bs.get("value")));
            temp.put("value", deptnames);
            bl.add(deptnames.size());
            nmap.add(temp);
        }
        if (StringUtil.isValid(nmap)) {
            maps.put("nmap", nmap);
        } else {
            return R.error(500, "获取班级下的成员失败!");
        }
        maps.put("bl", bl);


        //获取项目部下所有的车辆
        List<Map<String, Object>> carlist = operationplanService.queryCar(StringUtil.valueOf(map.get("project")));
        if (StringUtil.isValid(carlist)) {
            maps.put("carlist", carlist);
        } else {
            return R.error(500, "获取项目部下所有的车辆失败!");
        }

        return R.ok().put("maps", maps);
    }

    /**
     * 保存作业计划
     *
     * @param data
     * @return
     * @throws Exception
     */
    @SysLog("保存作业计划(按天排班)")
    @SuppressWarnings("unchecked")
    @ResponseBody
    @RequestMapping("/save")
    @RequiresPermissions("operationplandata:save")
    public R savePlan(@RequestBody Map<String, Object> data) {

        Map<String, Object> ccuu = new HashMap<String, Object>();
        ccuu.put("crtid", getUserId());
        ccuu.put("updid", getUserId());
        ccuu.put("crttm", DateUtil.getSystemDate());
        ccuu.put("updtm", DateUtil.getSystemDate());

        OperationplanEntity o = null;
        String bsment = StringUtil.valueOf(data.get("bsment")); // 业务板块
        String banci = StringUtil.listToString((List<String>) data.get("banci"));
        String dept = StringUtil.valueOf(data.get("project"));
        String optime = StringUtil.valueOf(data.get("date")).substring(0, 7);
        Map<String, Object> oob = new HashMap<String, Object>();
        oob.put("optime", optime);
        oob.put("dept", dept);
        oob.put("bsment", bsment);
        o = operationplanService.queryODB(oob);

        if (StringUtil.isBlank(o)) {
            Map<String, Object> operationplan = new HashMap<String, Object>();
            operationplan.putAll(ccuu);
            operationplan.put("optime", optime);
            operationplan.put("dept", data.get("project"));
            operationplan.put("bsment", data.get("bsment"));
            operationplan.put("banci", StringUtil.listToString((List<String>)data.get("banci")));

            // 查询详细班级信息
            Map<String, Object> classotypemap = new HashMap<String, Object>();
            classotypemap.put("otype", PlatformContext.getGoalbalContext("classotype", String.class));
            classotypemap.put("userid", getUserId());
            List<Map<String, Object>> business = operationplanService.queryDept(classotypemap);
            StringBuffer cla = new StringBuffer();
            for (Map<String, Object> map : business) {
                if(StringUtil.isValid(StringUtil.valueOf(cla))){
                    cla.append(",");
                }
                cla.append(map.get("value"));
            }
            operationplan.put("cla", StringUtil.valueOf(cla));
            o = BeanUtil.map2Bean(operationplan, OperationplanEntity.class);
            // 保存主表信息
            o.setStatus("1");
            operationplanService.save(o);
        }

        JSONObject datas = (JSONObject) data.get("datas");
        Map<String, Object> operationplandata = null;
        OperationplandataEntity od = null;
        Object[] otemp = null;
        for (String tempa : datas.keySet()) {
            if (StringUtil.isValid(tempa)) {
                otemp = tempa.split("#");
                if (otemp.length == 4) {
                    operationplandata = new HashMap<String, Object>();
                    operationplandata.putAll(ccuu);
                    operationplandata.put("oid", o.getId());
                    operationplandata.put("optime", otemp[0]);
                    operationplandata.put("shift", otemp[1]);
                    operationplandata.put("cla", otemp[2]);
                    operationplandata.put("member", otemp[3]);
                    operationplandata.put("driver", StringUtil.isValid(datas.get(tempa + "#driver")) ? 1 : 0);
                    operationplandata.put("car", StringUtil.isValid(datas.get(tempa + "#driver#car")) ? datas.get(tempa + "#driver#car") : null);
                    // 执行保存操作
                    od = BeanUtil.map2Bean(operationplandata, OperationplandataEntity.class);
                    od.setStatus("1");
                    operationplanService.updatedata(od);
                    // 保存完成之后,清理对象
                    operationplandata = null;
                    od = null;
                } else {
                    // 不是成员信息不保存
                    continue;
                }
            } else {
                // 数据无效不执行保存
                continue;
            }
        }
        return R.ok();
    }
}
