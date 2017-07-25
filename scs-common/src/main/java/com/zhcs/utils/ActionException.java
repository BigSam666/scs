package com.zhcs.utils;


//*****************************************************************************
/**
 * <p>Title: ActionException</p>
 * <p>Description: 业务处理层异常类</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************

@SuppressWarnings("serial")
public class ActionException extends Exception {
	
	/**
	 * 错误信息
	 */
	private String errorMessage = null;
	
	//*************************************************************************	
	/**
	 * DaoException构造函数
	 */
	//*************************************************************************
	public ActionException() {
		super("");
		errorMessage = "";
	}
	
	//*************************************************************************
	/**
	 * DaoException构造函数根据传递的异常信息
	 * @param argMessage	日志信息
	 */
	//*************************************************************************
	public ActionException(String argMessage) {
		super(argMessage);
		errorMessage = argMessage;
	}
	
	//*************************************************************************
	/**
	 * DaoException构造函数根据传递的异常信息
	 * @param argMessage	日志信息
	 * @param argThr		异常对象
	 */
	//*************************************************************************
	public ActionException(String argMessage, Throwable argThr) {
		super(argMessage,argThr);
		errorMessage = argMessage;
	}
	
	//*************************************************************************
	/**
	 * DaoException构造函数通过传递异常对象
	 * @param argThr		异常对象
	 */
	//*************************************************************************
	public ActionException(Throwable argThr) {
		super(argThr);
		errorMessage = argThr.getLocalizedMessage();
	}
	
}