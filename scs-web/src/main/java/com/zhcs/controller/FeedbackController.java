package com.zhcs.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.stereotype.Controller;

import com.zhcs.entity.FeedbackEntity;
import com.zhcs.entity.SysUserEntity;
import com.zhcs.service.BaseCodeService;
import com.zhcs.service.FeedbackService;
import com.zhcs.service.SysUserService;
import com.zhcs.utils.BeanUtil;
import com.zhcs.utils.PageUtils;
import com.zhcs.utils.R;
import com.zhcs.utils.StringUtil;

//*****************************************************************************
/**
 * <p>Title:FeedbackController</p>
 * <p>Description: 意见反馈</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
@Controller
@RequestMapping("feedback")
public class FeedbackController extends AbstractController  {
	@Autowired
	private FeedbackService feedbackService;
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private BaseCodeService basecodeService;
	
	@RequestMapping("/feedback.html")
	public String list(){
		return "feedback/feedback.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("feedback:list")
	public R list(String name,Integer type,Integer info,String sidx, String order, Integer page, Integer limit){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sidx", sidx);
		map.put("order", order);
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		if (StringUtil.isValid(name)) {
			map.put("name", name.trim());
		}
		if (type !=null) {
			map.put("type", type);
		}
		if (info!=null) {
			map.put("info", info);
		}
		
		//查询列表数据
		List<Map<String,Object>> feedbackList = feedbackService.queryListMap(map);
		for (Map<String,Object> tmp : feedbackList) {
			Long personId = Long.valueOf(tmp.get("person").toString());
			SysUserEntity user = sysUserService.queryObject(personId);
			tmp.put("name", user.getRealname());
			tmp.put("wechatid", user.getWechatid());
			tmp.put("mobile", user.getMobile());
			tmp.put("email", user.getEmail());
			
			tmp.put("infosourceText", basecodeService.selectByTypeValue("infosource",tmp.get("infosource").toString()).getCnm());
			tmp.put("problemtypeText", basecodeService.selectByTypeValue("problemtype",tmp.get("problemtype").toString()).getCnm());
		}
		int total = feedbackService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(feedbackList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{id}")
	@RequiresPermissions("feedback:info")
	public R info(@PathVariable("id") Long id){
		FeedbackEntity feedback = feedbackService.queryObject(id);
		
		return R.ok().put("feedback", feedback);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("feedback:save")
	public R save(@RequestBody FeedbackEntity feedback){
	
		BeanUtil.fillCCUUD(feedback, getUserId(), getUserId());
		feedbackService.save(feedback);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("feedback:update")
	public R update(@RequestBody FeedbackEntity feedback){
		
		BeanUtil.fillCCUUD(feedback, getUserId());
		feedbackService.update(feedback);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete/{id}")
	@RequiresPermissions("feedback:delete")
	public R delete(@PathVariable("id") Long id){
		feedbackService.delete(id);
		return R.ok();
	}
	
}
