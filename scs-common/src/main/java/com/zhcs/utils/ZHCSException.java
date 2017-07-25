package com.zhcs.utils;

//*****************************************************************************
/**
 * <p>Title:ZhcsException</p>
 * <p>Description:自定义异常</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司</p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public class ZHCSException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
    private String msg;
    private int code = 500;
    
    public ZHCSException(String msg) {
		super(msg);
		this.msg = msg;
	}
	
	public ZHCSException(String msg, Throwable e) {
		super(msg, e);
		this.msg = msg;
	}
	
	public ZHCSException(String msg, int code) {
		super(msg);
		this.msg = msg;
		this.code = code;
	}
	
	public ZHCSException(String msg, int code, Throwable e) {
		super(msg, e);
		this.msg = msg;
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
	
	
}
