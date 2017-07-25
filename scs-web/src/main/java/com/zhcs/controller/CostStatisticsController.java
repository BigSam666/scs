package com.zhcs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//*****************************************************************************
/**
 * <p>Title:CostStatisticsController</p>
 * <p>Description: 费用管理中的统计分析</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
@Controller
@RequestMapping("coststatistics")
public class CostStatisticsController extends AbstractController  {
	
	@RequestMapping("/coststatistics.html")
	public String list(){
		return "coststatistics/coststatistics.html";
	}
	
}
