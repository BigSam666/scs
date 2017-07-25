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

import com.zhcs.context.PlatformContext;
import com.zhcs.entity.QltsuaAuditeeEntity;
import com.zhcs.service.QltsuaAuditeeService;
import com.zhcs.utils.BeanUtil;
import com.zhcs.utils.PageUtils;
import com.zhcs.utils.StringUtil;
import com.zhcs.utils.R;

//*****************************************************************************
/**
 * <p>Title:QltsuaAuditeeController</p>
 * <p>Description: 质量督导审核方</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
@Controller
@RequestMapping("qltsuaauditee")
public class QltsuaAuditeeController extends AbstractController  {
	@Autowired
	private QltsuaAuditeeService qltsuaAuditeeService;
	
	@RequestMapping("/qltsuaauditee.html")
	public String list(){
		return "qltsuaauditee/qltsuaauditee.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("qltsuaauditee:list")
	public R list(String sidx, String name, String order, Integer page, Integer limit){
		Map<String, Object> map = new HashMap<String, Object>();
		if (!StringUtil.isBlank(name)) {
			map.put("name", name.trim());
		}
		map.put("sidx", sidx);
		map.put("order", order);
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		map.put("status", "0");
		
		//查询列表数据
//		List<QltsuaAuditeeEntity> qltsuaAuditeeList = qltsuaAuditeeService.queryList(map);
		List<QltsuaAuditeeEntity> qltsuaAuditeeList = qltsuaAuditeeService.queryList1(map);
		int total = qltsuaAuditeeService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(qltsuaAuditeeList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{id}")
	@RequiresPermissions("qltsuaauditee:info")
	public R info(@PathVariable("id") Long id){
		QltsuaAuditeeEntity qltsuaAuditee = qltsuaAuditeeService.queryObject(id);
		
		return R.ok().put("qltsuaAuditee", qltsuaAuditee);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("qltsuaauditee:save")
	public R save(@RequestBody QltsuaAuditeeEntity qltsuaAuditee){
	
		BeanUtil.fillCCUUD(qltsuaAuditee, getUserId(), getUserId());
		qltsuaAuditeeService.save(qltsuaAuditee);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("qltsuaauditee:update")
	public R update(@RequestBody QltsuaAuditeeEntity qltsuaAuditee){
		
		BeanUtil.fillCCUUD(qltsuaAuditee, getUserId());
		qltsuaAuditeeService.update(qltsuaAuditee);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete/{id}")
	@RequiresPermissions("qltsuaauditee:delete")
	public R delete(@PathVariable("id") Long id){
		if(PlatformContext.getGoalbalContext("adminId", String.class).equals(StringUtil.valueOf(getUserId()))){
			qltsuaAuditeeService.delete(id);
		} else {
			QltsuaAuditeeEntity qltsuaAuditee = new QltsuaAuditeeEntity();
			qltsuaAuditee.setId(id);
			qltsuaAuditee.setStatus("0");
			BeanUtil.fillCCUUD(qltsuaAuditee, getUserId());
			qltsuaAuditeeService.update(qltsuaAuditee);
		}
		return R.ok();
	}
	
	/**
	 * 审核
	 */
	@ResponseBody
	@RequestMapping("/auditing")
	@RequiresPermissions("qltsuaauditee:auditing")
	public R auditing(@RequestBody QltsuaAuditeeEntity qltsuaAuditee){
		
		qltsuaAuditeeService.update(qltsuaAuditee);
		
		return R.ok();
	}
}
