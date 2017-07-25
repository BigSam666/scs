package com.zhcs.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhcs.context.PlatformContext;
import com.zhcs.entity.BaseCodeEntity;
import com.zhcs.entity.BddetailEntity;
import com.zhcs.entity.BditemEntity;
import com.zhcs.entity.BudgetEntity;
import com.zhcs.entity.SysUserEntity;
import com.zhcs.service.AuditscheduleService;
import com.zhcs.service.BaseCodeService;
import com.zhcs.service.BddetailService;
import com.zhcs.service.BditemService;
import com.zhcs.service.BudgetService;
import com.zhcs.service.SysUserService;
import com.zhcs.utils.BeanUtil;
import com.zhcs.utils.PageUtils;
import com.zhcs.utils.R;
import com.zhcs.utils.StringUtil;

//*****************************************************************************
/**
 * <p>Title:BudgetController</p>
 * <p>Description: 预算申报</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
@Controller
@RequestMapping("budget")
public class BudgetController extends AbstractController  {
	@Autowired
	private BudgetService budgetService;
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private BaseCodeService baseCodeService;
	@Autowired
	private BddetailService bddetailService;
	@Autowired
	private BditemService bditemService;
	@Autowired
	private AuditscheduleService auditscheduleService;
	
	@RequestMapping("/budget.html")
	public String list(){
		return "budget/budget.html";
	}
	
	/**
	 * 预算申报明细
	 */
	@ResponseBody
	@RequestMapping("/seeSchedule/{budgetId}")
	public R seeSchedule(@PathVariable Long budgetId) {
		BudgetEntity budgetEntity = budgetService.queryObject(budgetId);
		List<Map<String,Object>> allAuditschedules = auditscheduleService.queryByBudget(budgetId);
		for (Map<String,Object> _map : allAuditschedules) {
			Long personId = (Long) _map.get("person");
			Integer schedule = (Integer) _map.get("schedule");
			SysUserEntity personEntity = sysUserService.queryObject(personId);
			_map.put("personEntity", personEntity);
			String scheduleText = null;
			switch (schedule) {
				case 1:scheduleText = "项目部";break;
				case 2:scheduleText = "事业部";break;
				case 3:scheduleText = "财务部";break;
				case 4:scheduleText = "副总";break;
				default:scheduleText = "";break;
			}
			_map.put("scheduleText", scheduleText);
		}
		
		return R.ok().putData(new Object[]{budgetEntity.getCrttm(), allAuditschedules});
	}
	
	
	/**
	 * 预算申报明细
	 */
	@ResponseBody
	@RequestMapping("/seeDetailTable/{budgetId}")
	public R seeDetailTable(@PathVariable Long budgetId) {
		List<List<BditemEntity>> god = bditemService.getTotalItemByCode(null);
		
		// 获取钱
		List<Map<String, BddetailEntity>> allMoney = getTreeMoneyByBudget(budgetId);
		
		// 如果有新增，计算出总金额
		Map<String, BigDecimal> totalMoney = getTotalMoney(allMoney);
		
		if (totalMoney != null) {
			return R.ok().putData(new Object[]{god, allMoney,totalMoney});
		}
		return R.ok().putData(new Object[]{god, allMoney});
	}


	/**
	 * 费用预算明细
	 */
	@ResponseBody
	@RequestMapping("/costBudgetDetailTable/{budgetId}")
	public R costBudgetDetailTable(@PathVariable Long budgetId) {
		// 营业成本预算
		String businessCostBudget = PlatformContext.getGoalbalContext("business_cost_budget", String.class);
		// 管理费用预算
		String manageCostBudget = PlatformContext.getGoalbalContext("manage_cost_budget", String.class);
		// 财务费用预算
		String financeCostBudget = PlatformContext.getGoalbalContext("finance_cost_budget", String.class);
		List<List<BditemEntity>> items1 = bditemService.getTotalItemByCode(businessCostBudget); // 所有可能的项
		List<List<BditemEntity>> items2 = bditemService.getTotalItemByCode(manageCostBudget);
		List<List<BditemEntity>> items3 = bditemService.getTotalItemByCode(financeCostBudget);
		
		Set<String> allCodes1 = new HashSet<>(); // 所有可能的项的code
		for (List<BditemEntity> tmp: items1) {
			for (BditemEntity _tmp: tmp) {
				allCodes1.add(_tmp.getCode());
			}
		}
		Set<String> allCodes2 = new HashSet<>(); // 所有可能的项的code
		for (List<BditemEntity> tmp: items2) {
			for (BditemEntity _tmp: tmp) {
				allCodes2.add(_tmp.getCode());
			}
		}
		Set<String> allCodes3 = new HashSet<>(); // 所有可能的项的code
		for (List<BditemEntity> tmp: items3) {
			for (BditemEntity _tmp: tmp) {
				allCodes3.add(_tmp.getCode());
			}
		}
		
		// 获取钱
		List<Map<String, BddetailEntity>> allMoney = getTreeMoneyByBudget(budgetId);
		// 实际需要的钱
		List<Map<String, BddetailEntity>> realAllMoney = new ArrayList<>();
		// 合计
		BigDecimal[] totalMoney = new BigDecimal[]{new BigDecimal("0"),new BigDecimal("0"),new BigDecimal("0")};
		
		for (int i=0; i<allMoney.size(); i++) {
			for (Map.Entry<String, BddetailEntity> _tmp: allMoney.get(i).entrySet()) {
				if (allCodes1.contains(_tmp.getKey())) {
					int _size = realAllMoney.size();
					if (_size <= i) {
						for (int j=0; j<i-_size+1; j++) {
							realAllMoney.add(new HashMap<String, BddetailEntity>());
						}
					}
					realAllMoney.get(i).put(_tmp.getKey(), _tmp.getValue());
					totalMoney[0] = totalMoney[0].add(_tmp.getValue().getMoney());
				}
			}
		}
		
		for (int i=0; i<allMoney.size(); i++) {
			for (Map.Entry<String, BddetailEntity> _tmp: allMoney.get(i).entrySet()) {
				if (allCodes2.contains(_tmp.getKey())) {
					int _size = realAllMoney.size();
					if (_size <= i) {
						for (int j=0; j<i-_size+1; j++) {
							realAllMoney.add(new HashMap<String, BddetailEntity>());
						}
					}
					realAllMoney.get(i).put(_tmp.getKey(), _tmp.getValue());
					totalMoney[1] = totalMoney[1].add(_tmp.getValue().getMoney());
				}
			}
		}
		
		for (int i=0; i<allMoney.size(); i++) {
			for (Map.Entry<String, BddetailEntity> _tmp: allMoney.get(i).entrySet()) {
				if (allCodes3.contains(_tmp.getKey())) {
					int _size = realAllMoney.size();
					if (_size <= i) {
						for (int j=0; j<i-_size+1; j++) {
							realAllMoney.add(new HashMap<String, BddetailEntity>());
						}
					}
					realAllMoney.get(i).put(_tmp.getKey(), _tmp.getValue());
					totalMoney[2] = totalMoney[2].add(_tmp.getValue().getMoney());
				}
			}
		}
		// 将所有可能的项合并
		List<List<BditemEntity>> allItems = new ArrayList<>();
		for (int i = 0; i<items1.size(); i++) {
			int currentSize = allItems.size();
			if (i+1 > currentSize) {
				allItems.add(new ArrayList<BditemEntity>());
			}
			allItems.get(i).addAll(items1.get(i));
		}
		for (int i = 0; i<items2.size(); i++) {
			int currentSize = allItems.size();
			if (i+1 > currentSize) {
				allItems.add(new ArrayList<BditemEntity>());
			}
			allItems.get(i).addAll(items2.get(i));
		}
		for (int i = 0; i<items3.size(); i++) {
			int currentSize = allItems.size();
			if (i+1 > currentSize) {
				allItems.add(new ArrayList<BditemEntity>());
			}
			allItems.get(i).addAll(items3.get(i));
		}
		
		// 如果有新增，计算出总金额
		Map<String, BigDecimal> itemTotalMoney = getTotalMoney(realAllMoney);
		
		if (itemTotalMoney != null) {
			return R.ok().putData(new Object[]{allItems, realAllMoney, itemTotalMoney, totalMoney});
		}
		return R.ok().putData(new Object[]{allItems, realAllMoney, totalMoney});
		
	}
	
	/**
	 * 营业收入预算明细
	 */
	@ResponseBody
	@RequestMapping("/businessIncomeBudgetDetail/{budgetId}")
	public R businessIncomeBudgetDetail(@PathVariable Long budgetId) {
		String businessIncomeBudget = PlatformContext.getGoalbalContext("business_income_budget", String.class);
		List<List<BditemEntity>> allItems = bditemService.getTotalItemByCode(businessIncomeBudget); // 营业收入预算下所有可能的项
		
		Set<String> allCodes = new HashSet<>(); // 所有可能的项的code
		for (List<BditemEntity> tmp: allItems) {
			for (BditemEntity _tmp: tmp) {
				allCodes.add(_tmp.getCode());
			}
		}
		
		// 获取钱
		List<Map<String, BddetailEntity>> allMoney = getTreeMoneyByBudget(budgetId);
		// 实际需要的钱
		List<Map<String, BddetailEntity>> realAllMoney = new ArrayList<>();
		
		BigDecimal wholeTotalMoney = new BigDecimal("0");
		for (int i=0; i<allMoney.size(); i++) {
			for (Map.Entry<String, BddetailEntity> _tmp: allMoney.get(i).entrySet()) {
				if (allCodes.contains(_tmp.getKey())) {
					int _size = realAllMoney.size();
					if (_size <= i) {
						for (int j=0; j<i-_size+1; j++) {
							realAllMoney.add(new HashMap<String, BddetailEntity>());
						}
					}
					realAllMoney.get(i).put(_tmp.getKey(), _tmp.getValue());
					wholeTotalMoney = wholeTotalMoney.add(_tmp.getValue().getMoney());
				}
			}
		}
		
		// 如果有新增，计算出总金额
		Map<String, BigDecimal> totalMoney = getTotalMoney(realAllMoney);
		
		if (totalMoney != null) {
			return R.ok().putData(new Object[]{allItems, realAllMoney, totalMoney, wholeTotalMoney});
		}
		return R.ok().putData(new Object[]{allItems, realAllMoney, wholeTotalMoney});
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("budget:list")
	public R list(String project, Boolean auditing, String sidx, String order, Integer page, Integer limit){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sidx", sidx);
		map.put("order", order);
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		
		if (!StringUtil.isBlank(project)) {
			map.put("project", project.trim());
		}
		
		List<Integer> schedues = new ArrayList<>();
		if (!auditing) { // 查询已结束的
			schedues.add(0);
			schedues.add(5);
			map.put("schedules", schedues);
		} else if (auditing) { // 查询未结束的
			schedues.add(1);
			schedues.add(2);
			schedues.add(3);
			schedues.add(4);
			map.put("schedules", schedues);
		}
		
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
	 * 如果有新增，计算出总金额
	 */
	private Map<String, BigDecimal> getTotalMoney(List<Map<String, BddetailEntity>> allMoney) {
		int _size = allMoney.size();
		Map<String,BigDecimal> totalMoney = null; // 总金额
		if (_size > 1) { // 说明有新增
			totalMoney = new HashMap<String, BigDecimal>();
			for (int i=0;i<_size;i++) {
				Map<String, BddetailEntity> map = allMoney.get(i);
				for (Map.Entry<String, BddetailEntity> entry: map.entrySet()) {
					String targetKey = entry.getKey();
					BigDecimal currentMoney = new BigDecimal("0");
					for (int j=0; j<_size; j++) {
						Map<String, BddetailEntity> _map = allMoney.get(j);
						if (_map.containsKey(targetKey)) {
							currentMoney = currentMoney.add(_map.get(targetKey).getMoney());
						}
					}
					totalMoney.put(targetKey, currentMoney);
				}
			}
		}
		return totalMoney;
	}

	/**
	 * 根据Budget的id查询出下面的项中所有的钱
	 * 
	 * 返回结果中第一个元素是初始金额，第二个元素是第一次新增，第三个元素是第二次新增，第四个元素是第三次新增...
	 */
	private List<Map<String, BddetailEntity>> getTreeMoneyByBudget(Long budgetId) {
		// 金额
		List<BddetailEntity> realAllItems = bddetailService.getAllItemsByBudget(budgetId);
		List<Map<String,BddetailEntity>> allMoney = new ArrayList<>(); 
		for (int i = 0; i < 4; i++) {
			allMoney.add(new HashMap<String,BddetailEntity>());
		}
		
		while (realAllItems.size() > 0) {
			BddetailEntity tmp = realAllItems.remove(0); // 依次取出元素
			allMoney.get(tmp.getOrigin()).put(tmp.getCode(), tmp);
		}
		
		// 去除无用的Map
		int _size = 5;
		while (allMoney.size() < _size) {
			_size = allMoney.size();
			if (allMoney.get(_size-1).size() < 1) {
				allMoney.remove(_size-1);
			};
		}
		return allMoney;
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
	 * 调整
	 */
	@ResponseBody
	@RequestMapping("/adjust")
	@RequiresPermissions("budget:adjust")
	public R adjust(@RequestBody BudgetEntity budget){
		
		BeanUtil.fillCCUUD(budget, getUserId(), getUserId());
		budgetService.adjust(budget);
		
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
