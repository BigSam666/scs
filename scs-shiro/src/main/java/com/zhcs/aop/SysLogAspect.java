package com.zhcs.aop;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;
import com.zhcs.annotation.SysLog;
import com.zhcs.entity.SysLogEntity;
import com.zhcs.service.SysLogService;
import com.zhcs.utils.HttpContextUtils;
import com.zhcs.utils.IPUtils;
import com.zhcs.utils.LogUtil;
import com.zhcs.utils.ShiroUtils;
import com.zhcs.utils.StringUtil;
import com.zhcs.utils.WebUtil;

//*****************************************************************************
/**
 * <p>Title:SysLogAspect</p>
 * <p>Description:系统日志，切面处理类</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司</p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年5月26日
 */
//*****************************************************************************
@Aspect
@Component
public class SysLogAspect {
	@Autowired
	private SysLogService sysLogService;
	
	@Pointcut("@annotation(com.zhcs.annotation.SysLog)") 
	public void logPointCut() { 
	}

	@Pointcut("execution(public * com.zhcs.controller..*.*(..))")
	public void webLog() {
	}
	
	ThreadLocal<Long> startTime = new ThreadLocal<Long>();
	
	@Before("logPointCut()")
	public void saveSysLog(JoinPoint joinPoint) {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Method method = signature.getMethod();
		
		SysLogEntity sysLog = new SysLogEntity();
		SysLog syslog = method.getAnnotation(SysLog.class);
		if(syslog != null){
			//注解上的描述 
			sysLog.setOperation(syslog.value());
		}
		
		//请求的方法名
		String className = joinPoint.getTarget().getClass().getName();
		String methodName = signature.getName();
		sysLog.setMethod(className + "." + methodName + "()");
		
		//请求的参数
		Object[] args = joinPoint.getArgs();
		String params = JSON.toJSONString(args[0]);
		sysLog.setParams(params);
		
		//获取request
		HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
		//设置IP地址
		sysLog.setIp(IPUtils.getIpAddr(request));
		
		//用户名
		String username = ShiroUtils.getUserEntity().getRealname();
		sysLog.setUsername(username);
		
		sysLog.setCreateDate(new Date());
		//保存系统日志
		sysLogService.save(sysLog);
	}

	@Before("webLog()")
	public void doBefore(JoinPoint joinPoint) throws Throwable {

        startTime.set(System.currentTimeMillis());
		// 接收到请求，记录请求内容
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		// 记录下请求内容
		LogUtil.info("------------------------------------得到请求对象Start------------------------------------");
        LogUtil.info("URL : {}", request.getRequestURL().toString());
        LogUtil.info("HTTP_METHOD : {}", request.getMethod());
        LogUtil.info("IP : {}",  WebUtil.getClientIP(request));
        //LogUtil.info("IP : {}", request.getRemoteAddr());
        LogUtil.info("CLASS_METHOD : {}.{}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
        try {
			LogUtil.info("ARGS : {}", StringUtil.toJson(joinPoint.getArgs()));
		} catch (Exception e) {
			LogUtil.info("ARGS : {}", Arrays.toString(joinPoint.getArgs()));
		}
		LogUtil.info("------------------------------------得到请求对象End------------------------------------");
	}

	@AfterReturning(returning = "ret", pointcut = "webLog()")
	public void doAfterReturning(Object ret) throws Throwable {
		LogUtil.info("------------------------------------数据处理完成Start------------------------------------");
		// 处理完请求，返回内容
		LogUtil.info("RESPONSE : {}", JSON.toJSON(ret));
		LogUtil.info("SPEND TIME : {}", (System.currentTimeMillis() - startTime.get()));

		LogUtil.info("------------------------------------数据处理完成End------------------------------------");
	}
	
}
