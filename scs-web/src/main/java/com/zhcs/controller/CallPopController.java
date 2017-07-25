package com.zhcs.controller;

import com.zhcs.entity.CallPopEntity;
import com.zhcs.service.CallPopService;
import com.zhcs.utils.BeanUtil;
import com.zhcs.utils.R;
import com.zhcs.utils.StringUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

//*****************************************************************************

/**
 * <p>
 * Title:CallJobselController
 * </p>
 * <p>
 * Description: 工单查询
 * </p>
 * <p>
 * Copyright: Copyright (c) 2017
 * </p>
 * <p>
 * Company: 深圳市智慧城市管家信息科技有限公司
 * </p>
 *
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
// *****************************************************************************
@Controller
@RequestMapping("callpopsel")
public class CallPopController extends AbstractController {
    @Autowired
    private CallPopService callPopService;

    @RequestMapping("/callpopsel.html")
    @RequiresPermissions("callpopsel:callpop")
    public String list() {
        return "callpopsel/callpopsel.html";
    }

    /**
     * 保存
     */
    @ResponseBody
    @RequestMapping("/save")
    @RequiresPermissions("callpopsel:save")
    public R save(@RequestBody CallPopEntity callPopsel) {
        if (!"".equals(callPopsel.getImg().toString())) {
            String img = callPopsel.getImg().replace("undefined", "").substring(0, (callPopsel.getImg().replace("undefined", "").length() - 1));
            callPopsel.setImg(img);
        }
        BeanUtil.fillCCUUD(callPopsel, getUserId(), getUserId());
        callPopService.save(callPopsel);

        return R.ok();
    }

    /**
     * 获取分机号,姓名(根据电话号码查询)(弹屏的权限控制)
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/calllist")
    @RequiresPermissions("callpopsel:calllist")
    public R info() {
        int callphone = callPopService.queryCallNumber(getUser().getId());
        return R.ok().put("callphone", callphone);
    }

    /**
     * 根据手机号码查询对应的姓名
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/callname")
    @RequiresPermissions("callpopsel:callname")
    public R name(@RequestBody Map<String, Object> map) {
        String name = callPopService.queryName(StringUtil.valueOf(map.get("phone")));
        return R.ok().put("name", name);
    }

}
