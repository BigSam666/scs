package com.zhcs.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.zhcs.context.PlatformContext;
import com.zhcs.entity.CarEntity;
import com.zhcs.service.CarService;
import com.zhcs.utils.BeanUtil;
import com.zhcs.utils.PageUtils;
import com.zhcs.utils.R;
import com.zhcs.utils.StringUtil;

//*****************************************************************************
/**
 * <p>Title:CarController</p>
 * <p>Description: 车辆管理</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
@Controller
@RequestMapping("car")
public class CarController extends AbstractController  {
	@Autowired
	private CarService carService;
	
	@RequestMapping("/car.html")
	public String list(){
		return "car/car.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("car:list")
	public R list(String condition, String carType,String sidx, String order, Integer page, Integer limit){
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringUtil.isValid(condition)) {
			map.put("condition", condition.trim());
		}
		map.put("carType", carType);
		map.put("sidx", sidx);
		map.put("order", order);
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		
		//查询列表数据
//		List<CarEntity> carList = carService.queryList(map);
		List<CarEntity> carList = carService.queryList1(map);
		int total = carService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(carList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}

	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/listAll")
	@RequiresPermissions("car:list")
	public R listAll(){
		//查询列表数据
//		List<CarEntity> carList = carService.queryList(new HashMap<String, Object>());
		List<CarEntity> carList = carService.queryList1(new HashMap<String, Object>());
		return R.ok().putData(carList);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{id}")
	@RequiresPermissions("car:info")
	public R info(@PathVariable("id") Long id){
		CarEntity car = carService.queryObject(id);
		
		return R.ok().put("car", car);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("car:save")
	public R save(@RequestBody CarEntity car){
			
		BeanUtil.fillCCUUD(car, getUserId(), getUserId());
		carService.save(car);
		
		return R.ok().putData(car);
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("car:update")
	public R update(@RequestBody CarEntity car){
		
		BeanUtil.fillCCUUD(car, getUserId());
		carService.update(car);
		
		return R.ok().putData(car);
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete/{id}")
	@RequiresPermissions("car:delete")
	public R delete(@PathVariable("id") Long id){
		if(PlatformContext.getGoalbalContext("adminId", String.class).equals(StringUtil.valueOf(getUserId()))){
			carService.delete(id);
		} else {
			CarEntity car = new CarEntity();
			car.setId(id);
			car.setStatus("0");
			BeanUtil.fillCCUUD(car, getUserId());
			carService.update(car);
		}
		return R.ok();
	}
	
}
