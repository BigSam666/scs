package com.zhcs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//*****************************************************************************
/**
 * <p>Title:EventStatisticsController</p>
 * <p>Description: 事件统计</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
@Controller
@RequestMapping("eventstatistics")
public class EventStatisticsController extends AbstractController  {
	
	@RequestMapping("/eventstatistics.html")
	public String list(){
		return "eventstatistics/eventstatistics.html";
	}
	
}
