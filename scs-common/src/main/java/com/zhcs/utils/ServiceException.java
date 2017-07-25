package com.zhcs.utils;

//*****************************************************************************
/**
* <p>Title: ServiceException</p>
* <p>Description: 业务处理层异常类</p>
* <p>Copyright: Copyright (c) 2017</p>
* <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
* @author 刘晓东 - Alter
* @version v1.0 2017年2月23日
*/
//*****************************************************************************
@SuppressWarnings("serial")
public class ServiceException extends Exception {
	
	private boolean customMsg = false;
	
	public boolean isCustomMsg() {
	
		return customMsg;
	}

	//*************************************************************************	
	/**
	 * ServiceException构造函数
	 */
	//*************************************************************************
	public ServiceException() {
		super();
	}
	
	//*************************************************************************
	/**
	 * DaoException构造函数根据传递的异常信息
	 * @param argMessage	日志信息
	 */
	//*************************************************************************
	public ServiceException(String argMessage) {
		super(argMessage);
		customMsg = true;
	}
	
	//*************************************************************************
	/**
	 * DaoException构造函数根据传递的异常信息
	 * @param argMessage	日志信息
	 * @param argThr		异常对象
	 */
	//*************************************************************************
	public ServiceException(String argMessage, Throwable argThr) {
		super(argMessage,argThr);
	}
	
	//*************************************************************************
	/**
	 * DaoException构造函数通过传递异常对象
	 * @param argThr		异常对象
	 */
	//*************************************************************************
	public ServiceException(Throwable argThr) {
		super(argThr);
	}
	
}