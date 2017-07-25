package com.zhcs.task;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zhcs.entity.SysUserEntity;
import com.zhcs.service.SysUserService;
import com.zhcs.utils.LogUtil;

//*****************************************************************************
/**
 * <p>Title:TestTask</p>
 * <p>Description:测试定时任务</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司</p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
@Component("testTask")
public class TestTask {
	
	@Autowired
	private SysUserService sysUserService;
	
	public void test(String params){
		LogUtil.info("我是带参数的test方法，正在被执行，参数为：" + params);
		
		try {
			Thread.sleep(1000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		SysUserEntity user = sysUserService.queryObject(1L);
		System.out.println(ToStringBuilder.reflectionToString(user));
		
	}
	
	public void test2(){
		LogUtil.info("我是不带参数的test2方法，正在被执行");
	}
}
