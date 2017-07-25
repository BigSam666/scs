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

@Api(value = "费用申报", description = "费用申报")
@Controller
@RequestMapping("/api/costrecord")
@CrossOrigin
public class ApiCostRecordController {

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private CostrecordService costrecordService;
    @Autowired
    private BditemService bditemService;


    @ApiOperation(value = "费用申报", notes = "费用申报")
    @RequestMapping(value = "/addCostRecord", method = RequestMethod.POST)
    @ResponseBody
    public R addCostRecord(@RequestBody CostrecordEntity costrecordEntity) {
        TokenEntity token = tokenService.queryByToken(costrecordEntity.getToken());
        if (StringUtil.isValid(token) && DateUtil.diffDateSecond(DateUtil.getSystemDate(), token.getExpireTime()) > 0) {
            SysUserEntity user = sysUserService.queryObject(token.getUserId()); // 拿到当前用户
            if (costrecordEntity.getDdate() == null) {
                return R.error(11001, "申报日期不能为空");
            }
            if (costrecordEntity.getDtype() == null) {
                return R.error(11002, "费用类型不能为空");
            }
            if (costrecordEntity.getMoney() == null || costrecordEntity.getMoney().doubleValue() < 0) {
                return R.error(11003, "无效的金额");
            }
            if (costrecordEntity.getPerson() == null) {
                return R.error(11004, "申报人员不能为空");
            }

            BeanUtil.fillCCUUD(costrecordEntity, user.getId(), user.getId());
            costrecordService.save(costrecordEntity);

            return R.ok();
        } else {
            return R.error(50000, "身份过期，请重新登录");
        }
    }

    @ApiOperation(value = "费用类型", notes = "费用类型")
    @RequestMapping(value = "/costrecordType", method = RequestMethod.POST)
    @ResponseBody
    public R costrecordType(@RequestBody OnlyTokenEntity onlyTokenEntity) {
        TokenEntity token = tokenService.queryByToken(onlyTokenEntity.getToken());
        if (StringUtil.isValid(token) && DateUtil.diffDateSecond(DateUtil.getSystemDate(), token.getExpireTime()) > 0) {

            List<Map<String,Object>> bditems = bditemService.selectForZtree();

            return R.ok().putData(bditems);
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
            SysUserEntity user = sysUserService.queryObject(token.getUserId()); // 拿到当前用户

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
            map.put("userid", user.getId());

            List<CostrecordEntity> costrecordList = costrecordService.queryList(map);
            List<Map<String, Object>> result = new ArrayList<>();
            Map<String, Object> item = null;
            for (CostrecordEntity tmp : costrecordList) {
                item = new LinkedHashMap<>();
                result.add(item);
                item.put("id", tmp.getId());
                item.put("name", sysUserService.queryObject(tmp.getPerson()).getRealname()); // 人
                item.put("time", tmp.getDdate()); // 时间
                item.put("type", bditemService.queryObjectByCode(tmp.getDtype()).getCnm()); // 费用类型
                item.put("money", tmp.getMoney());
                item.put("rmk", tmp.getRmk());
            }
            int count = costrecordService.queryTotal(map);

            Map<String, Object> ret = new HashMap<>();  // 包括数据和分页信息
            ret.put("page", tokenAndPageEntity.getPage());           // 当前页码
            ret.put("pagesize", tokenAndPageEntity.getPagesize());   // 每页显示的条数
            ret.put("count", count);                    // 总条数
            ret.put("data", result);

            return R.ok().putData(ret);
        } else {
            return R.error(50000, "身份过期，请重新登录");
        }
    }

}
