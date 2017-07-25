package com.zhcs.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zhcs.entity.*;
import com.zhcs.service.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhcs.context.PlatformContext;
import com.zhcs.utils.BeanUtil;
import com.zhcs.utils.PageUtils;
import com.zhcs.utils.R;
import com.zhcs.utils.ShiroUtils;
import com.zhcs.utils.StringUtil;

//*****************************************************************************
/**
 * <p>Title:BudgetController</p>
 * <p>Description: 费用审核</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
@Controller
@RequestMapping("budgetaudit")
public class BudgetAuditController extends AbstractController  {
	@Autowired
	private BudgetService budgetService;
	@Autowired
	private SysUserService sysUserService;
	@Autowired
    private SysDepartmentService sysDepartmentService;
	@Autowired
	private BaseCodeService baseCodeService;
	@Autowired
	private BddetailService bddetailService;
	@Autowired
	private BditemService bditemService;
	@Autowired
	private AuditscheduleService auditscheduleService;
	
	@RequestMapping("/budgetaudit.html")
	public String list(){
		return "budgetaudit/budgetaudit.html";
	}
	
	
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("budget:list")
	public R list(String project, Boolean auditing, String sidx, String order, Integer page, Integer limit){
		
		SysUserEntity loginUser = ShiroUtils.getUserEntity();
		// 拿到当前登录用户的角色
        List<SysRoleEntity> roles = sysUserService.queryRoleByUser(loginUser.getId());

        // 审批不通过     0
		// 发起申报/待审批
		// 项目部审批     1
		// 事业部审批     2
		// 财务部审批     3
		// 副总审批         4
		// 全部审批通过  5
		List<Integer> schedules = new ArrayList<>();
		if (!auditing) { // 查询已结束的
			schedules.add(0);
			schedules.add(5);
		} else if (auditing) { // 审批中
			 // 当前登录用户能够进行审核的预算审核
            String role1 = PlatformContext.getGoalbalContext("项目部审批需要的角色", String.class);
            String role2 = PlatformContext.getGoalbalContext("事业部审批需要的角色", String.class);
            String role3 = PlatformContext.getGoalbalContext("财务部审批需要的角色", String.class);
            String role4 = PlatformContext.getGoalbalContext("副总审批需要的角色", String.class);
            for (SysRoleEntity role : roles) {
                String roleName = role.getName().trim();
                if (role1.trim().equals(roleName)) {
                    // 项目部审批
                    schedules.add(1);
                }
                if (role2.trim().equals(roleName))  {
                    // 事业部审批
                    schedules.add(2);
                }
                if (role3.trim().equals(roleName))  {
                    // 财务部审批
                    schedules.add(3);
                }
                if (role4.trim().equals(roleName))  {
                    // 副总
                    schedules.add(4);
                }
			}
		}

		Map<String, Object> map = new HashMap<>();
		map.put("sidx", sidx);
		map.put("order", order);
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		if (schedules.size() < 1) {
			return R.ok().put("page", null);
		}
		if (!StringUtil.isBlank(project)) {
			map.put("project", project.trim());
		}

		map.put("schedules", schedules);

		//查询列表数据
		List<Map<String,Object>> budgetListMap = budgetService.queryListMap(map);
		for (Map<String,Object> budget: budgetListMap) {
			// 申报人员
			Long personId = (Long) budget.get("person");
			SysUserEntity user = sysUserService.queryObject(personId);
			budget.put("personName", user.getName());
			budget.put("mobile", user.getMobile());
			// 所属项目
			String projectCode = (String) budget.get("project");
			BaseCodeEntity baseCode = baseCodeService.selectByTypeValue("project", projectCode);
			budget.put("projectName", baseCode.getCnm());
			// 营业收入预算
			String businessIncomeBudget = PlatformContext.getGoalbalContext("business_income_budget", String.class);
			List<List<BditemEntity>> allItems = bditemService.getTotalItemByCode(businessIncomeBudget); // 营业收入预算下所有可能的项

			List<BddetailEntity> realAllItems = bddetailService.getAllItemsByBudget((Long)budget.get("id")); // 实际所有的项，只有部分是营业收入的

			Map<String,BigDecimal> realAllItemsMap = new HashMap<>(); // 转换成一个Map
			for (BddetailEntity bd : realAllItems) {
				if (realAllItemsMap.containsKey(bd.getCode())) {
					realAllItemsMap.put(bd.getCode(), bd.getMoney().add(realAllItemsMap.get(bd.getCode())));
				} else {
					realAllItemsMap.put(bd.getCode(), bd.getMoney());
				}
			}
			BigDecimal totalMoney = new BigDecimal("0");
			for (List<BditemEntity> tmp: allItems) {
				for (BditemEntity _tmp: tmp) {
					if (realAllItemsMap.containsKey(_tmp.getCode())) {
						totalMoney = totalMoney.add(realAllItemsMap.get(_tmp.getCode()));
					}
				}
			}
			budget.put("businessIncomeBudget", totalMoney);

			// 费用预算
			// 营业成本预算
			String businessCostBudget = PlatformContext.getGoalbalContext("business_cost_budget", String.class);
			// 管理费用预算
			String manageCostBudget = PlatformContext.getGoalbalContext("manage_cost_budget", String.class);
			// 财务费用预算
			String financeCostBudget = PlatformContext.getGoalbalContext("finance_cost_budget", String.class);
			List<List<BditemEntity>> items1 = bditemService.getTotalItemByCode(businessCostBudget);
			List<List<BditemEntity>> items2 = bditemService.getTotalItemByCode(manageCostBudget);
			List<List<BditemEntity>> items3 = bditemService.getTotalItemByCode(financeCostBudget);

			// 人力成本
			BigDecimal laborCost = new BigDecimal("0");
			String laborCostCode = PlatformContext.getGoalbalContext("labor_cost", String.class);

			BigDecimal totalMoney1 = new BigDecimal("0");
			for (List<BditemEntity> tmp: items1) {
				for (BditemEntity _tmp: tmp) {
					if (realAllItemsMap.containsKey(_tmp.getCode())) {
						totalMoney1 = totalMoney1.add(realAllItemsMap.get(_tmp.getCode()));
						if (_tmp.getCode().equals(laborCostCode)) {
							laborCost = realAllItemsMap.get(_tmp.getCode());
						}
					}
				}
			}
			for (List<BditemEntity> tmp: items2) {
				for (BditemEntity _tmp: tmp) {
					if (realAllItemsMap.containsKey(_tmp.getCode())) {
						totalMoney1 = totalMoney1.add(realAllItemsMap.get(_tmp.getCode()));
					}
				}
			}
			for (List<BditemEntity> tmp: items3) {
				for (BditemEntity _tmp: tmp) {
					if (realAllItemsMap.containsKey(_tmp.getCode())) {
						totalMoney1 = totalMoney1.add(realAllItemsMap.get(_tmp.getCode()));
					}
				}
			}
			budget.put("costBudget", totalMoney1);

			// 净利润
			budget.put("np", totalMoney.subtract(totalMoney1));
			// 利润率
			try {
				budget.put("npr", totalMoney.subtract(totalMoney1).divide(totalMoney,4,RoundingMode.HALF_UP));
			} catch (Exception e) {
				budget.put("npr","");
			}
			// 人力成本/收入
			try {
				budget.put("laborCost1", laborCost.divide(totalMoney, 4, RoundingMode.HALF_UP));
			} catch (Exception e) {
				budget.put("laborCost1","");
			}
			// 人力成本/总成本
			try {
				budget.put("laborCost2", laborCost.divide(totalMoney1, 4, RoundingMode.HALF_UP));
			} catch (Exception e) {
				budget.put("laborCost2","");
			}
		}

		int total = budgetService.queryTotal(map);

		PageUtils pageUtil = new PageUtils(budgetListMap, total, limit, page);

		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{id}")
	@RequiresPermissions("budget:info")
	public R info(@PathVariable("id") Long id){
		BudgetEntity budget = budgetService.queryObject(id);
		
		return R.ok().put("budget", budget);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("budget:save")
	public R save(@RequestBody BudgetEntity budget){
	
		BeanUtil.fillCCUUD(budget, getUserId(), getUserId());
		budgetService.save(budget);
		
		return R.ok();
	}
	
	/**
	 * 审核
	 */
	@ResponseBody
	@RequestMapping("/doAudit")
	@RequiresPermissions("budget:doAudit")
	public R doAudit(@RequestBody AuditscheduleEntity as) {

		BeanUtil.fillCCUUD(as, getUserId(), getUserId());
		auditscheduleService.save(as); // 保存审核意见
		// 更新审核进度
		if (as.getPass() == 1) {
			// 通过，将进度加一
			budgetService.updateSchedule(as.getBudget(), as.getSchedule() + 1);
		} else {
			// 不通过，将进度置为0
			budgetService.updateSchedule(as.getBudget(), 0);
		}
		return R.ok();
	}


	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("budget:update")
	public R update(@RequestBody BudgetEntity budget){
		
		BeanUtil.fillCCUUD(budget, getUserId());
		budgetService.update(budget);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete/{id}")
	@RequiresPermissions("budget:delete")
	public R delete(@PathVariable("id") Long id){
		budgetService.delete(id);
		return R.ok();
	}
	
}
