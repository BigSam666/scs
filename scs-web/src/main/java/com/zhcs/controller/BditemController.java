package com.zhcs.controller;

import java.util.ArrayList;
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

import com.zhcs.entity.BditemEntity;
import com.zhcs.service.BditemService;
import com.zhcs.utils.BeanUtil;
import com.zhcs.utils.PageUtils;
import com.zhcs.utils.R;

//*****************************************************************************
/**
 * <p>Title:BditemController</p>
 * <p>Description: 预算明细有哪些项</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
@Controller
@RequestMapping("bditem")
public class BditemController extends AbstractController  {
	@Autowired
	private BditemService bditemService;
	
	@RequestMapping("/bditem.html")
	public String list(){
		return "bditem/bditem.html";
	}
	
	
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/showbditem")
//	@RequiresPermissions("bditem:list")
	public R showbditem(){
		// 顶级容器（List），每个元素（Map）就是一级
		List<List<BditemEntity>> god = new ArrayList<>();
		// 一共有多少项
		int totalCount = bditemService.queryTotal(null);
		// 当前已取出多少项
		int currentCount = 0;
		// 先弄出第一级
		List<BditemEntity> firstLevel = bditemService.queryListByParent(null);
		currentCount += firstLevel.size();
		// 将第一级放入顶级容器中
		god.add(firstLevel);
		// 每次循环都会获取下一级的全部项
		// 如果当前已取出的项的数量   小于    总的数量，则表示还有下一级没有取完
		while (currentCount < totalCount) {
			List<BditemEntity> tmp = god.get(god.size()-1);        // 目前的最后一级
			List<BditemEntity> nextLevelList = new ArrayList<>();      // 构造出下一级的List!!!!!!!!!!!!!
//			for(Map.Entry<String, List<BditemEntity>> entry : tmp.entrySet()) { // 遍历出最后一级的所有元素
				for (BditemEntity bditemEntity : tmp) { 
					List<BditemEntity> _tmp = bditemService.queryListByParent(bditemEntity);
					if (_tmp != null && _tmp.size() > 0) {
						nextLevelList.addAll(_tmp);
						// 每次取出后都需要更新当前已取出项的数量
						currentCount += _tmp.size();
						bditemEntity.setHasChild(true);
					} else {
						// 当前项没有子项，则跳过
						bditemEntity.setHasChild(false);
						continue;
					}
				}
//			}
			// 将下一级的Map放入顶级容器中
			god.add(nextLevelList);
		}
		
		return R.ok().putData(god);
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("bditem:list")
	public R list(String sidx, String order, Integer page, Integer limit){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sidx", sidx);
		map.put("order", order);
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		
		//查询列表数据
		List<BditemEntity> bditemList = bditemService.queryList(map);
		int total = bditemService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(bditemList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{id}")
	@RequiresPermissions("bditem:info")
	public R info(@PathVariable("id") Long id){
		BditemEntity bditem = bditemService.queryObject(id);
		
		return R.ok().put("bditem", bditem);
	}
	
	
	/**
	 * 获取所有的预算明细项（以ztree需要的格式）
	 */
	@ResponseBody
	@RequestMapping("/selectForZtree")
	@RequiresPermissions("bditem:list")
	public R selectForZtree(){
		List<Map<String,Object>> bditems = bditemService.selectForZtree();
		
		return R.ok().putData(bditems);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("bditem:save")
	public R save(@RequestBody BditemEntity bditem){
	
		BeanUtil.fillCCUUD(bditem, getUserId(), getUserId());
		bditemService.save(bditem);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("bditem:update")
	public R update(@RequestBody BditemEntity bditem){
		
		BeanUtil.fillCCUUD(bditem, getUserId());
		bditemService.update(bditem);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete/{id}")
	@RequiresPermissions("bditem:delete")
	public R delete(@PathVariable("id") Long id){
		bditemService.delete(id);
		return R.ok();
	}
	
}
