package com.zhcs.utils;

//*****************************************************************************
/**
* <p>Title:DaoException</p>
* <p>Description: 工作记录</p>
* <p>Copyright: Copyright (c) 2017</p>
* <p>Company: 数据处理层异常类 </p>
* @author 刘晓东 - Alter
* @version v1.0 2017年2月23日
*/
//*****************************************************************************
@SuppressWarnings("serial")
public class DaoException extends Exception {
	
	//*************************************************************************	
	/**
	 * DaoException构造函数
	 */
	//*************************************************************************
	public DaoException() {
		super();
	}
	
	//*************************************************************************
	/**
	 * DaoException构造函数根据传递的异常信息
	 * @param argMessage	日志信息
	 */
	//*************************************************************************
	public DaoException(String argMessage) {
		super(argMessage);
	}
	
	//*************************************************************************
	/**
	 * DaoException构造函数根据传递的异常信息
	 * @param argMessage	日志信息
	 * @param argThr		异常对象
	 */
	//*************************************************************************
	public DaoException(String argMessage, Throwable argThr) {
		super(argMessage,argThr);
	}
	
	//*************************************************************************
	/**
	 * DaoException构造函数通过传递异常对象
	 * @param argThr		异常对象
	 */
	//*************************************************************************
	public DaoException(Throwable argThr) {
		super(argThr);
	}
	
}