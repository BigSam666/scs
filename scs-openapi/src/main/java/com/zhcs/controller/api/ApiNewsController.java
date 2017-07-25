package com.zhcs.controller.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.ArrayList;
import java.util.Date;
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
import com.zhcs.entity.BaseCodeEntity;
import com.zhcs.entity.EventEntity;
import com.zhcs.entity.MsglogEntity;
import com.zhcs.entity.NewsListEntity;
import com.zhcs.entity.OnlyTokenEntity;
import com.zhcs.entity.SysUserEntity;
import com.zhcs.entity.TokenEntity;
import com.zhcs.service.ApiEventService;
import com.zhcs.service.BaseCodeService;
import com.zhcs.service.MsglogService;
import com.zhcs.service.SysUserService;
import com.zhcs.service.TokenService;
import com.zhcs.utils.DateUtil;
import com.zhcs.utils.R;
import com.zhcs.utils.StringUtil;

@Api(value = "消息管理", description = "消息管理")
@Controller
@RequestMapping("/api/news")
@CrossOrigin
public class ApiNewsController {

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private BaseCodeService baseCodeService;
    @Autowired
    private MsglogService msglogService;
    @Autowired
    private ApiEventService eventService;


    @ApiOperation(value = "消息类型", notes = "消息类型")
    @RequestMapping(value = "/type", method = RequestMethod.POST)
    @ResponseBody
    public R type(@RequestBody OnlyTokenEntity onlyTokenEntity) {
        TokenEntity token = tokenService.queryByToken(onlyTokenEntity.getToken());
        if (StringUtil.isValid(token) && DateUtil.diffDateSecond(DateUtil.getSystemDate(), token.getExpireTime()) > 0) {
            return R.ok().putData(baseCodeService.getCnmNoMapByType("newstype"));
        } else {
            return R.error(50000, "身份过期，请重新登录");
        }
    }

    @ApiOperation(value = "消息列表", notes = "消息列表")
    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public R add(@RequestBody NewsListEntity newsListEntity) {
        TokenEntity token = tokenService.queryByToken(newsListEntity.getToken());
        if (StringUtil.isValid(token) && DateUtil.diffDateSecond(DateUtil.getSystemDate(), token.getExpireTime()) > 0) {
            SysUserEntity user = sysUserService.queryObject(token.getUserId()); // 拿到当前用户
            Date etime = new Date();                                     // 结束时间
            Date btime = newsListEntity.getBtime();                      // 开始时间
            if (btime == null) {
                Integer days = Integer.parseInt(PlatformContext.getGoalbalContext("newsDays", String.class));
                btime = new Date(etime.getTime() - days * 24 * 60 * 60 * 1000);
            }

            List<BaseCodeEntity> list = baseCodeService.selectByType("newstype"); // 消息类型
            Map<String, String> types = new HashMap<String, String>(); // 将消息类型转成一个map
            for (BaseCodeEntity tmp : list) {
                if (tmp.getCnm().contains("公告")) {
                    types.put("公告", tmp.getNo());
                }
                if (tmp.getCnm().contains("预警")) {
                    types.put("预警", tmp.getNo());
                }
                if (tmp.getCnm().contains("任务")) {
                    types.put("任务", tmp.getNo());
                }
            }

            List<Map<String, Object>> ret = new ArrayList<>();

            Map<String, Object> map = new HashMap<>();
            map.put("userId", user.getId());
            map.put("btime", btime);
            map.put("etime", etime);
            // 公告消息
            List<MsglogEntity> msglist = msglogService.queryListBetweenTime(map);
            List<Long> ids = new ArrayList<>();
            for (MsglogEntity tmp : msglist) {
                ids.add(tmp.getId());
            }
            Map<String, Object> msgmap = new HashMap<>();
            String content = null;
            Date crttm = null;
            if (msglist != null && msglist.size() > 0) {
                content = msglist.get(0).getContent(); // 最新一条消息的内容
                crttm = msglist.get(0).getCrttm(); // 最新一条消息的时间
            }
            msgmap.put("ids", ids);
            msgmap.put("type", types.get("公告"));
            msgmap.put("typeName", "公告栏");
            msgmap.put("crttm", crttm);
            msgmap.put("content", content);
            // 预警消息

            // 任务
            List<EventEntity> eventlist = eventService.queryListBetweenTime(map);
            ids = new ArrayList<Long>();
            for (EventEntity tmp : eventlist) {
                ids.add(tmp.getId());
            }
            Map<String, Object> eventMap = new HashMap<>();
            String qdescribe = null;
            String des = null;
            crttm = null;
            if (eventlist != null && eventlist.size() > 0) {
                qdescribe = eventlist.get(0).getQdescribe(); // 最新一条事件的内容
                des = eventlist.get(0).getDes();             // 最新一条事件的工作反馈
                crttm = eventlist.get(0).getHtm();           // 最新一条事件的时间
            }
            eventMap.put("ids", ids);
            eventMap.put("type", types.get("任务"));
            eventMap.put("typeName", "任务来了");
            eventMap.put("crttm", crttm);
            eventMap.put("qdescribe", qdescribe);
            eventMap.put("des", des);


            ret.add(msgmap);
            ret.add(eventMap);


            return R.ok().putData(ret);
        } else {
            return R.error(50000, "身份过期，请重新登录");
        }
    }

    @ApiOperation(value = "任务列表", notes = "任务列表")
    @RequestMapping(value = "/eventList", method = RequestMethod.POST)
    @ResponseBody
    public R eventList(@RequestBody NewsListEntity newsListEntity) {
        TokenEntity token = tokenService.queryByToken(newsListEntity.getToken());
        if (StringUtil.isValid(token) && DateUtil.diffDateSecond(DateUtil.getSystemDate(), token.getExpireTime()) > 0) {
            SysUserEntity user = sysUserService.queryObject(token.getUserId()); // 拿到当前用户
            Date etime = new Date();                                     // 结束时间
            Date btime = newsListEntity.getBtime();                      // 开始时间
            if (btime == null) {
                Integer days = Integer.parseInt(PlatformContext.getGoalbalContext("newsDays", String.class));
                btime = new Date(etime.getTime() - days * 24 * 60 * 60 * 1000);
            }

            Map<String, Object> map = new HashMap<String, Object>();
            map.put("userId", user.getId());
            map.put("btime", btime);
            map.put("etime", etime);
            // 任务
            List<EventEntity> eventlist = eventService.queryListBetweenTime(map);

            List<Map<String, Object>> ret = new ArrayList<>();
            Map<String, Object> item = null;
            for (EventEntity tmp : eventlist) {
                item = new HashMap<>();
                item.put("htm", tmp.getHtm()); // 派单时间
                item.put("des", tmp.getDes()); // 工作反馈
                item.put("qdescribe", tmp.getQdescribe()); // 问题描述
                ret.add(item);
            }

            return R.ok().putData(ret);
        } else {
            return R.error(50000, "身份过期，请重新登录");
        }
    }

    @ApiOperation(value = "公告栏列表", notes = "公告栏列表")
    @RequestMapping(value = "/msgList", method = RequestMethod.POST)
    @ResponseBody
    public R msgList(@RequestBody NewsListEntity newsListEntity) {
        TokenEntity token = tokenService.queryByToken(newsListEntity.getToken());
        if (StringUtil.isValid(token) && DateUtil.diffDateSecond(DateUtil.getSystemDate(), token.getExpireTime()) > 0) {
            SysUserEntity user = sysUserService.queryObject(token.getUserId()); // 拿到当前用户
            Date etime = new Date();                                     // 结束时间
            Date btime = newsListEntity.getBtime();                      // 开始时间
            if (btime == null) {
                Integer days = Integer.parseInt(PlatformContext.getGoalbalContext("newsDays", String.class));
                btime = new Date(etime.getTime() - days * 24 * 60 * 60 * 1000);
            }

            Map<String, Object> map = new HashMap<String, Object>();
            map.put("userId", user.getId());
            map.put("btime", btime);
            map.put("etime", etime);

            List<MsglogEntity> msglist = msglogService.queryListBetweenTime(map);
            List<Map<String, Object>> ret = new ArrayList<>();
            Map<String, Object> item = null;
            for (MsglogEntity tmp : msglist) {
                item = new HashMap<>();
                item.put("crttm", tmp.getCrttm()); // 时间
                item.put("content", tmp.getContent()); // 内容
                ret.add(item);
            }

            return R.ok().putData(ret);
        } else {
            return R.error(50000, "身份过期，请重新登录");
        }
    }
}
