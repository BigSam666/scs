package com.zhcs.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//*****************************************************************************
/**
 * <p>Title:LogUtil</p>
 * <p>Description:日志工具</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司</p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public class LogUtil {
	
	/**
	 * Return the name of this <code>Logger</code> instance.
	 * 
	 * @return name of this logger instance
	 */
	public static String getName() {
		Object[] obj = LogUtil.getLogger();
		Logger logger = (Logger)obj[0];
		return logger.getName();
	}

	/**
	 * Is the logger instance enabled for the TRACE level?
	 * 
	 * @return True if this Logger is enabled for the TRACE level, false
	 *         otherwise.
	 */
	public static boolean isTraceEnabled() {
		Object[] obj = LogUtil.getLogger();
		Logger logger = (Logger)obj[0];
		return logger.isTraceEnabled();
	}

	/**
	 * Log a message at the TRACE level.
	 * 
	 * @param msg
	 *            the message string to be logged
	 */
	public static void trace(Throwable e) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		trace(sw.toString());
	}
	
	/**
	 * Log a message at the TRACE level.
	 * 
	 * @param msg
	 *            the message string to be logged
	 */
	public static void trace(String msg) {
		Object[] obj = LogUtil.getLogger();
		Logger logger = (Logger)obj[0];
		if (logger.isTraceEnabled()) {
			logger.trace(msg+" ("+obj[1]+":"+obj[2]+")");
		}
	}

	/**
	 * Log a message at the TRACE level according to the specified format and
	 * argument.
	 * <p/>
	 * <p>
	 * This form avoids superfluous object creation when the logger is disabled
	 * for the TRACE level.
	 * </p>
	 * 
	 * @param format
	 *            the format string
	 * @param arg
	 *            the argument
	 */
	public static void trace(String format, Object arg) {
		Object[] obj = LogUtil.getLogger();
		Logger logger = (Logger)obj[0];
		if (logger.isTraceEnabled()) {
			logger.trace(format, arg);
		}
	}

	/**
	 * Log a message at the TRACE level according to the specified format and
	 * arguments.
	 * <p/>
	 * <p>
	 * This form avoids superfluous object creation when the logger is disabled
	 * for the TRACE level.
	 * </p>
	 * 
	 * @param format
	 *            the format string
	 * @param arg1
	 *            the first argument
	 * @param arg2
	 *            the second argument
	 */
	public static void trace(String format, Object arg1, Object arg2) {
		Object[] obj = LogUtil.getLogger();
		Logger logger = (Logger)obj[0];
		if (logger.isTraceEnabled()) {
			logger.trace(format, arg1, arg2);
		}
	}

	/**
	 * Log a message at the TRACE level according to the specified format and
	 * arguments.
	 * <p/>
	 * <p>
	 * This form avoids superfluous string concatenation when the logger is
	 * disabled for the TRACE level. However, this variant incurs the hidden
	 * (and relatively small) cost of creating an <code>Object[]</code> before
	 * invoking the method, even if this logger is disabled for TRACE. The
	 * variants taking {@link #trace(String, Object) one} and
	 * {@link #trace(String, Object, Object) two} arguments exist solely in
	 * order to avoid this hidden cost.
	 * </p>
	 * 
	 * @param format
	 *            the format string
	 * @param arguments
	 *            a list of 3 or more arguments
	 */
	public static void trace(String format, Object... arguments) {
		Object[] obj = LogUtil.getLogger();
		Logger logger = (Logger)obj[0];
		if (logger.isTraceEnabled()) {
			logger.trace(format, arguments);
		}
	}

	/**
	 * Log an exception (throwable) at the TRACE level with an accompanying
	 * message.
	 * 
	 * @param msg
	 *            the message accompanying the exception
	 * @param t
	 *            the exception (throwable) to log
	 */
	public static void trace(String msg, Throwable t) {
		Object[] obj = LogUtil.getLogger();
		Logger logger = (Logger)obj[0];
		if (logger.isTraceEnabled()) {
			logger.trace(msg+" ("+obj[1]+":"+obj[2]+")", t);
		}
	}

	/**
	 * Is the logger instance enabled for the DEBUG level?
	 * 
	 * @return True if this Logger is enabled for the DEBUG level, false
	 *         otherwise.
	 */
	public static boolean isDebugEnabled() {
		Object[] obj = LogUtil.getLogger();
		Logger logger = (Logger)obj[0];
		return logger.isDebugEnabled();
	}

	/**
	 * Log a message at the DEBUG level.
	 * 
	 * @param msg
	 *            the message string to be logged
	 */
	public static void debug(Throwable e) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		debug(sw.toString());
	}
	
	/**
	 * Log a message at the DEBUG level.
	 * 
	 * @param msg
	 *            the message string to be logged
	 */
	public static void debug(String msg) {
		Object[] obj = LogUtil.getLogger();
		Logger logger = (Logger)obj[0];
		if (logger.isDebugEnabled()) {
			logger.debug(msg+" ("+obj[1]+":"+obj[2]+")");
		}
	}

	/**
	 * Log a message at the DEBUG level according to the specified format and
	 * argument.
	 * <p/>
	 * <p>
	 * This form avoids superfluous object creation when the logger is disabled
	 * for the DEBUG level.
	 * </p>
	 * 
	 * @param format
	 *            the format string
	 * @param arg
	 *            the argument
	 */
	public static void debug(String format, Object arg) {
		Object[] obj = LogUtil.getLogger();
		Logger logger = (Logger)obj[0];
		if (logger.isDebugEnabled()) {
			logger.debug(format, arg);
		}
	}

	/**
	 * Log a message at the DEBUG level according to the specified format and
	 * arguments.
	 * <p/>
	 * <p>
	 * This form avoids superfluous object creation when the logger is disabled
	 * for the DEBUG level.
	 * </p>
	 * 
	 * @param format
	 *            the format string
	 * @param arg1
	 *            the first argument
	 * @param arg2
	 *            the second argument
	 */
	public static void debug(String format, Object arg1, Object arg2) {
		Object[] obj = LogUtil.getLogger();
		Logger logger = (Logger)obj[0];
		if (logger.isDebugEnabled()) {
			logger.debug(format, arg1, arg2);
		}
	}

	/**
	 * Log a message at the DEBUG level according to the specified format and
	 * arguments.
	 * <p/>
	 * <p>
	 * This form avoids superfluous string concatenation when the logger is
	 * disabled for the DEBUG level. However, this variant incurs the hidden
	 * (and relatively small) cost of creating an <code>Object[]</code> before
	 * invoking the method, even if this logger is disabled for DEBUG. The
	 * variants taking {@link #debug(String, Object) one} and
	 * {@link #debug(String, Object, Object) two} arguments exist solely in
	 * order to avoid this hidden cost.
	 * </p>
	 * 
	 * @param format
	 *            the format string
	 * @param arguments
	 *            a list of 3 or more arguments
	 */
	public static void debug(String format, Object... arguments) {
		Object[] obj = LogUtil.getLogger();
		Logger logger = (Logger)obj[0];
		if (logger.isDebugEnabled()) {
			logger.debug(format, arguments);
		}
	}

	/**
	 * Log an exception (throwable) at the DEBUG level with an accompanying
	 * message.
	 * 
	 * @param msg
	 *            the message accompanying the exception
	 * @param t
	 *            the exception (throwable) to log
	 */
	public static void debug(String msg, Throwable t) {
		Object[] obj = LogUtil.getLogger();
		Logger logger = (Logger)obj[0];
		if (logger.isDebugEnabled()) {
			logger.debug(msg+" ("+obj[1]+":"+obj[2]+")", t);
		}
	}

	/**
	 * Is the logger instance enabled for the INFO level?
	 * 
	 * @return True if this Logger is enabled for the INFO level, false
	 *         otherwise.
	 */
	public static boolean isInfoEnabled() {
		Object[] obj = LogUtil.getLogger();
		Logger logger = (Logger)obj[0];
		return logger.isInfoEnabled();
	}

	/**
	 * Log a message at the INFO level.
	 * 
	 * @param msg
	 *            the message string to be logged
	 */
	public static void info(Throwable e) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		info(sw.toString());
	}
	
	/**
	 * Log a message at the INFO level.
	 * 
	 * @param msg
	 *            the message string to be logged
	 */
	public static void info(String msg) {
		Object[] obj = LogUtil.getLogger();
		Logger logger = (Logger)obj[0];
		if (logger.isInfoEnabled()) {
			logger.info(msg);
		}
	}

	/**
	 * Log a message at the INFO level according to the specified format and
	 * argument.
	 * <p/>
	 * <p>
	 * This form avoids superfluous object creation when the logger is disabled
	 * for the INFO level.
	 * </p>
	 * 
	 * @param format
	 *            the format string
	 * @param arg
	 *            the argument
	 */
	public static void info(String format, Object arg) {
		Object[] obj = LogUtil.getLogger();
		Logger logger = (Logger)obj[0];
		if (logger.isInfoEnabled()) {
			logger.info(format, arg);
		}
	}

	/**
	 * Log a message at the INFO level according to the specified format and
	 * arguments.
	 * <p/>
	 * <p>
	 * This form avoids superfluous object creation when the logger is disabled
	 * for the INFO level.
	 * </p>
	 * 
	 * @param format
	 *            the format string
	 * @param arg1
	 *            the first argument
	 * @param arg2
	 *            the second argument
	 */
	public static void info(String format, Object arg1, Object arg2) {
		Object[] obj = LogUtil.getLogger();
		Logger logger = (Logger)obj[0];
		if (logger.isInfoEnabled()) {
			logger.info(format, arg1, arg2);
		}
	}

	/**
	 * Log a message at the INFO level according to the specified format and
	 * arguments.
	 * <p/>
	 * <p>
	 * This form avoids superfluous string concatenation when the logger is
	 * disabled for the INFO level. However, this variant incurs the hidden (and
	 * relatively small) cost of creating an <code>Object[]</code> before
	 * invoking the method, even if this logger is disabled for INFO. The
	 * variants taking {@link #info(String, Object) one} and
	 * {@link #info(String, Object, Object) two} arguments exist solely in order
	 * to avoid this hidden cost.
	 * </p>
	 * 
	 * @param format
	 *            the format string
	 * @param arguments
	 *            a list of 3 or more arguments
	 */
	public static void info(String format, Object... arguments) {
		Object[] obj = LogUtil.getLogger();
		Logger logger = (Logger)obj[0];
		if (logger.isInfoEnabled()) {
			logger.info(format, arguments);
		}
	}

	/**
	 * Log an exception (throwable) at the INFO level with an accompanying
	 * message.
	 * 
	 * @param msg
	 *            the message accompanying the exception
	 * @param t
	 *            the exception (throwable) to log
	 */
	public static void info(String msg, Throwable t) {
		Object[] obj = LogUtil.getLogger();
		Logger logger = (Logger)obj[0];
		if (logger.isInfoEnabled()) {
			logger.info(msg, t);
		}
	}

	/**
	 * Is the logger instance enabled for the WARN level?
	 * 
	 * @return True if this Logger is enabled for the WARN level, false
	 *         otherwise.
	 */
	public static boolean isWarnEnabled() {
		Object[] obj = LogUtil.getLogger();
		Logger logger = (Logger)obj[0];
		return logger.isWarnEnabled();
	}

	/**
	 * Log a message at the WARN level.
	 * 
	 * @param msg
	 *            the message string to be logged
	 */
	public static void warn(Throwable e) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		warn(sw.toString());
	}
	
	/**
	 * Log a message at the WARN level.
	 * 
	 * @param msg
	 *            the message string to be logged
	 */
	public static void warn(String msg) {
		Object[] obj = LogUtil.getLogger();
		Logger logger = (Logger)obj[0];
		if (logger.isWarnEnabled()) {
			logger.warn(msg+" ("+obj[1]+":"+obj[2]+")");
		}
	}

	/**
	 * Log a message at the WARN level according to the specified format and
	 * argument.
	 * <p/>
	 * <p>
	 * This form avoids superfluous object creation when the logger is disabled
	 * for the WARN level.
	 * </p>
	 * 
	 * @param format
	 *            the format string
	 * @param arg
	 *            the argument
	 */
	public static void warn(String format, Object arg) {
		Object[] obj = LogUtil.getLogger();
		Logger logger = (Logger)obj[0];
		if (logger.isWarnEnabled()) {
			logger.warn(format, arg);
		}
	}

	/**
	 * Log a message at the WARN level according to the specified format and
	 * arguments.
	 * <p/>
	 * <p>
	 * This form avoids superfluous string concatenation when the logger is
	 * disabled for the WARN level. However, this variant incurs the hidden (and
	 * relatively small) cost of creating an <code>Object[]</code> before
	 * invoking the method, even if this logger is disabled for WARN. The
	 * variants taking {@link #warn(String, Object) one} and
	 * {@link #warn(String, Object, Object) two} arguments exist solely in order
	 * to avoid this hidden cost.
	 * </p>
	 * 
	 * @param format
	 *            the format string
	 * @param arguments
	 *            a list of 3 or more arguments
	 */
	public static void warn(String format, Object... arguments) {
		Object[] obj = LogUtil.getLogger();
		Logger logger = (Logger)obj[0];
		if (logger.isWarnEnabled()) {
			logger.warn(format, arguments);
		}

	}

	/**
	 * Log a message at the WARN level according to the specified format and
	 * arguments.
	 * <p/>
	 * <p>
	 * This form avoids superfluous object creation when the logger is disabled
	 * for the WARN level.
	 * </p>
	 * 
	 * @param format
	 *            the format string
	 * @param arg1
	 *            the first argument
	 * @param arg2
	 *            the second argument
	 */
	public static void warn(String format, Object arg1, Object arg2) {
		Object[] obj = LogUtil.getLogger();
		Logger logger = (Logger)obj[0];
		if (logger.isWarnEnabled()) {
			logger.warn(format, arg1, arg2);
		}
	}

	/**
	 * Log an exception (throwable) at the WARN level with an accompanying
	 * message.
	 * 
	 * @param msg
	 *            the message accompanying the exception
	 * @param t
	 *            the exception (throwable) to log
	 */
	public static void warn(String msg, Throwable t) {
		Object[] obj = LogUtil.getLogger();
		Logger logger = (Logger)obj[0];
		if (logger.isWarnEnabled()) {
			logger.warn(msg+" ("+obj[1]+":"+obj[2]+")", t);
		}
	}

	/**
	 * Is the logger instance enabled for the ERROR level?
	 * 
	 * @return True if this Logger is enabled for the ERROR level, false
	 *         otherwise.
	 */
	public static boolean isErrorEnabled() {
		Object[] obj = LogUtil.getLogger();
		Logger logger = (Logger)obj[0];
		return logger.isErrorEnabled();
	}

	/**
	 * Log a message at the ERROR level.
	 * 
	 * @param msg
	 *            the message string to be logged
	 */
	public static void error(String msg) {
		Object[] obj = LogUtil.getLogger();
		Logger logger = (Logger)obj[0];
		if (logger.isErrorEnabled()) {
			//logger.error(msg+" ("+obj[1]+":"+obj[2]+")");
			logger.error(msg);
		}
	}

	/**
	 * 输出堆栈信息。
	 * 
	 * <pre>
	 * 
	 * Description：
	 * 
	 *     详细说明。
	 * 
	 * </pre>
	 * 
	 * @param e 异常对象
	 */
	public static void error(Throwable e) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		error(sw.toString());
	}
	
	/**
	 * Log a message at the ERROR level according to the specified format and
	 * argument.
	 * <p/>
	 * <p>
	 * This form avoids superfluous object creation when the logger is disabled
	 * for the ERROR level.
	 * </p>
	 * 
	 * @param format
	 *            the format string
	 * @param arg
	 *            the argument
	 */
	public static void error(String format, Object arg) {
		Object[] obj = LogUtil.getLogger();
		Logger logger = (Logger)obj[0];
		if (logger.isErrorEnabled()) {
			logger.error(format, arg);
		}
	}

	/**
	 * Log a message at the ERROR level according to the specified format and
	 * arguments.
	 * <p/>
	 * <p>
	 * This form avoids superfluous object creation when the logger is disabled
	 * for the ERROR level.
	 * </p>
	 * 
	 * @param format
	 *            the format string
	 * @param arg1
	 *            the first argument
	 * @param arg2
	 *            the second argument
	 */
	public static void error(String format, Object arg1, Object arg2) {
		Object[] obj = LogUtil.getLogger();
		Logger logger = (Logger)obj[0];
		if (logger.isErrorEnabled()) {
			logger.error(format, arg1, arg2);
		}
	}

	/**
	 * Log a message at the ERROR level according to the specified format and
	 * arguments.
	 * <p/>
	 * <p>
	 * This form avoids superfluous string concatenation when the logger is
	 * disabled for the ERROR level. However, this variant incurs the hidden
	 * (and relatively small) cost of creating an <code>Object[]</code> before
	 * invoking the method, even if this logger is disabled for ERROR. The
	 * variants taking {@link #error(String, Object) one} and
	 * {@link #error(String, Object, Object) two} arguments exist solely in
	 * order to avoid this hidden cost.
	 * </p>
	 * 
	 * @param format
	 *            the format string
	 * @param arguments
	 *            a list of 3 or more arguments
	 */
	public static void error(String format, Object... arguments) {
		Object[] obj = LogUtil.getLogger();
		Logger logger = (Logger)obj[0];
		if (logger.isErrorEnabled()) {
			logger.error(format, arguments);
		}
	}

	/**
	 * Log an exception (throwable) at the ERROR level with an accompanying
	 * message.
	 * 
	 * @param msg
	 *            the message accompanying the exception
	 * @param t
	 *            the exception (throwable) to log
	 */
	public static void error(String msg, Throwable t) {
		Object[] obj = LogUtil.getLogger();
		Logger logger = (Logger)obj[0];
		if (logger.isErrorEnabled()) {
			logger.error(msg+" ("+obj[1]+":"+obj[2]+")", t);
		}
	}

	/**
	 * Return a logger named according to the name parameter using the
	 * statically bound {@link ILoggerFactory} instance.
	 * 
	 * @return [logger, FileName, LineNum]
	 */
	private static Object[] getLogger() {
		String name = "ROOT";
		String fileName = "";
		String lineNum = "";
		StackTraceElement[] ste = Thread.currentThread().getStackTrace();
		if (ste != null) {
			if (ste.length >= 4) {
				name = ste[3].getClassName();
				fileName = ste[3].getFileName();
				lineNum = ste[3].getLineNumber()+"";
			} else if (ste.length == 3) {
				name = ste[2].getClassName();
				fileName = ste[2].getFileName();
				lineNum = ste[2].getLineNumber()+"";
			} else if (ste.length == 2) {
				name = ste[1].getClassName();
				fileName = ste[1].getFileName();
				lineNum = ste[1].getLineNumber()+"";
			} else if (ste.length == 1) {
				name = ste[0].getClassName();
				fileName = ste[0].getFileName();
				lineNum = ste[0].getLineNumber()+"";
			}
		}
		return new Object[]{LoggerFactory.getLogger(name), fileName, lineNum};
	}
	
}
