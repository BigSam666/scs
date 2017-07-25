package com.zhcs.controller.api;

import com.zhcs.entity.*;
import com.zhcs.service.*;
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

@Api(value = "质量督导", description = "质量督导")
@Controller
@RequestMapping("/api/qltsu")
@CrossOrigin
public class ApiQltsuController {

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private BaseCodeService baseCodeService;
    @Autowired
    private QltsuService qltsuService;


    @ApiOperation(value = "添加", notes = "添加")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public R add(@RequestBody QltsuEntity qltsuEntity) {
        TokenEntity token = tokenService.queryByToken(qltsuEntity.getToken());
        if (StringUtil.isValid(token) && DateUtil.diffDateSecond(DateUtil.getSystemDate(), token.getExpireTime()) > 0) {
            SysUserEntity user = sysUserService.queryObject(token.getUserId()); // 拿到当前用户

            boolean valid = true;
            if (qltsuEntity.getAddr() == null ||
                    qltsuEntity.getPerson() == null ||
                    qltsuEntity.getScoretype() == null ||
                    qltsuEntity.getScore() == null ||
                    qltsuEntity.getImg() == null ||
                    qltsuEntity.getLat() == null ||
                    qltsuEntity.getLng() == null) {
                valid = false;
            }
            if (!valid) {
                return R.error("无效的输入");
            }

            qltsuEntity.setAccaddr(qltsuEntity.getAddr()); // 让详细地址和地址一样
            qltsuEntity.setStatus("0");       // 待审核
            qltsuEntity.setStime(new Date()); // 设置扣分时间为当前时间


            BeanUtil.fillCCUUD(qltsuEntity, user.getId(), user.getId());
            qltsuService.save(qltsuEntity);

            return R.ok();
        } else {
            return R.error(50000, "身份过期，请重新登录");
        }
    }

    @ApiOperation(value = "类型", notes = "类型")
    @RequestMapping(value = "/type", method = RequestMethod.POST)
    @ResponseBody
    public R type(@RequestBody OnlyTokenEntity onlyTokenEntity) {
        TokenEntity token = tokenService.queryByToken(onlyTokenEntity.getToken());
        if (StringUtil.isValid(token) && DateUtil.diffDateSecond(DateUtil.getSystemDate(), token.getExpireTime()) > 0) {
            return R.ok().putData(baseCodeService.getCnmNoMapByType("scoretype"));
        } else {
            return R.error(50000, "身份过期，请重新登录");
        }
    }


    @ApiOperation(value = "分值", notes = "分值")
    @RequestMapping(value = "/score", method = RequestMethod.POST)
    @ResponseBody
    public R score(@RequestBody OnlyTokenEntity onlyTokenEntity) {
        TokenEntity token = tokenService.queryByToken(onlyTokenEntity.getToken());
        if (StringUtil.isValid(token) && DateUtil.diffDateSecond(DateUtil.getSystemDate(), token.getExpireTime()) > 0) {
            return R.ok().putData(baseCodeService.getCnmNoMapByType("score"));
        } else {
            return R.error(50000, "身份过期，请重新登录");
        }
    }

    @ApiOperation(value = "列表", notes = "列表")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public R list(@RequestBody TokenAndPageEntity tokenAndPageEntity) {
        TokenEntity token = tokenService.queryByToken(tokenAndPageEntity.getToken());
        if (StringUtil.isValid(token) && DateUtil.diffDateSecond(DateUtil.getSystemDate(), token.getExpireTime()) > 0) {

            if (tokenAndPageEntity.getPage() == null || tokenAndPageEntity.getPage() < 1) {
                tokenAndPageEntity.setPage(1);
            }
            if (tokenAndPageEntity.getPagesize() == null || tokenAndPageEntity.getPagesize() < 1) {
                tokenAndPageEntity.setPagesize(10);
            }
            Map<String, Object> map = new HashMap<>();
            map.put("page", tokenAndPageEntity.getPage());
            map.put("offset", (tokenAndPageEntity.getPage() - 1) * tokenAndPageEntity.getPagesize());
            map.put("limit", tokenAndPageEntity.getPagesize());

            List<QltsuEntity> qltsuList = qltsuService.queryList(map);
            List<Map<String, Object>> result = new ArrayList<>();
            Map<String, Object> item = null;
            for (QltsuEntity tmp : qltsuList) {
                item = new LinkedHashMap<>();
                result.add(item);
                item.put("id", tmp.getId());
                item.put("scoretype", baseCodeService.selectByTypeValue("scoretype", tmp.getScoretype() + "").getCnm());
                item.put("img", tmp.getImg());
                item.put("time", tmp.getStime());
                item.put("addr", tmp.getAddr());
                item.put("accaddr", tmp.getAccaddr());
                item.put("expl", tmp.getExpl());
            }
            int count = qltsuService.queryTotal(map);

            Map<String, Object> ret = new HashMap<>();  // 包括数据和分页信息
            ret.put("page", map.get("page"));           // 当前页码
            ret.put("pagesize", map.get("pagesize"));   // 每页显示的条数
            ret.put("count", count);                    // 总条数
            ret.put("data", result);

            return R.ok().putData(ret);
        } else {
            return R.error(50000, "身份过期，请重新登录");
        }
    }

    @ApiOperation(value = "分值", notes = "分值")
    @RequestMapping(value = "/detail", method = RequestMethod.POST)
    @ResponseBody
    public R detail(@RequestBody TokenAndIdEntity tokenAndIdEntity) {
        TokenEntity token = tokenService.queryByToken(tokenAndIdEntity.getToken());
        if (StringUtil.isValid(token) && DateUtil.diffDateSecond(DateUtil.getSystemDate(), token.getExpireTime()) > 0) {

            Long id = tokenAndIdEntity.getId();
            QltsuEntity qltsuEntity = qltsuService.queryObject(id);
            Map<String, Object> ret = new HashMap<>();
            ret.put("id", qltsuEntity.getId());
            ret.put("person", qltsuEntity.getPersonName());
            ret.put("scoretype", baseCodeService.selectByTypeValue("scoretype", qltsuEntity.getScoretype() + "").getCnm());
            ret.put("score", baseCodeService.selectByTypeValue("score", qltsuEntity.getScore() + "").getCnm());
            ret.put("dept", qltsuEntity.getDeptName());
            ret.put("time", qltsuEntity.getStime());
            ret.put("addr", qltsuEntity.getAddr());
            ret.put("accaddr", qltsuEntity.getAccaddr());
            ret.put("lat", qltsuEntity.getLat());
            ret.put("lng", qltsuEntity.getLng());
            ret.put("expl", qltsuEntity.getExpl());
            ret.put("img", qltsuEntity.getImg());

            return R.ok().putData(ret);
        } else {
            return R.error(50000, "身份过期，请重新登录");
        }
    }

}
