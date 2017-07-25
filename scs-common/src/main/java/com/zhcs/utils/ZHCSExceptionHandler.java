package com.zhcs.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.AuthorizationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;

//*****************************************************************************
/**
 * <p>Title:ZhcsExceptionHandler</p>
 * <p>Description:异常处理器</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司</p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
@Component
public class ZHCSExceptionHandler implements HandlerExceptionResolver {
	
	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		R r = new R();
		try {
			response.setContentType("application/json;charset=utf-8");
			response.setCharacterEncoding("utf-8");
			
			if (ex instanceof ZHCSException) {
				r.put("code", ((ZHCSException) ex).getCode());
				r.put("msg", ((ZHCSException) ex).getMessage());
			}else if(ex instanceof DuplicateKeyException){
				LogUtil.info("数据库中已存在该记录：{}", ex.getCause().getMessage());
				r = R.error("数据库中已存在该记录："+ex.getCause().getMessage());
			}else if(ex instanceof AuthorizationException){
				LogUtil.info("没有权限，请联系管理员授权");
				r = R.error("没有权限，请联系管理员授权");
			}else{
				r = R.error();
			}
			
			//记录异常日志
			LogUtil.error(ex.getMessage(), ex);
			
			String json = JSON.toJSONString(r);
			response.getWriter().print(json);
		} catch (Exception e) {
			LogUtil.error("ZhcsExceptionHandler 异常处理失败", e);
		}
		return new ModelAndView();
	}
}
