package com.zhcs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//*****************************************************************************
/**
 * <p>Title:WorkStateStatisticsController</p>
 * <p>Description: 工作状态</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
@Controller
@RequestMapping("workstatestatistics")
public class WorkStateStatisticsController extends AbstractController  {
	
	@RequestMapping("/workstatestatistics.html")
	public String list(){
		return "workstatestatistics/workstatestatistics.html";
	}
	
}
