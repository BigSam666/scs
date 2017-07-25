package com.zhcs.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//*****************************************************************************
/**
 * <p>Title:DateUtil</p>
 * <p>Description:日期处理工具类</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司</p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public class DateUtil {
	private final static SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");

	private final static SimpleDateFormat sdfDay = new SimpleDateFormat("yyyy-MM-dd");
	
	private final static SimpleDateFormat sdfDays = new SimpleDateFormat("yyyyMMdd");

	private final static SimpleDateFormat sdfTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static int		DATE_TYPE_YEAR		= Calendar.YEAR;

	public static int		DATE_TYPE_MONTH		= Calendar.MONTH;

	public static int		DATE_TYPE_DAY		= Calendar.DAY_OF_MONTH;

	public static int		DATE_TYPE_HOUR		= Calendar.HOUR;

	public static int		DATE_TYPE_MINUTE	= Calendar.MINUTE;

	public static String	DATA_TIME_CONSTANT	= "yyyy-MM-dd HH:mm:ss";

	public static String	DATE_CONSTANT		= "yyyy-MM-dd";
	
	public static String	TIME_CONSTANT		= "HH:mm:ss";

	public static Calendar calendar = null;
	
	/**
	 * 获取YYYY格式
	 * 
	 * @return
	 */
	public static String getYear() {
		return sdfYear.format(new Date());
	}

	/**
	 * 获取YYYY-MM-DD格式
	 * 
	 * @return
	 */
	public static String getDay() {
		return sdfDay.format(new Date());
	}
	
	/**
	 * 获取YYYYMMDD格式
	 * 
	 * @return
	 */
	public static String getDays(){
		return sdfDays.format(new Date());
	}

	/**
	 * 获取YYYY-MM-DD HH:mm:ss格式
	 * 
	 * @return
	 */
	public static String getTime() {
		return sdfTime.format(new Date());
	}
	
	
	/**
	 * 获取YYYY-MM-DD HH:mm:ss格式
	 * 
	 * @return
	 * @throws Exception 
	 */
	public static String getTimesStr(String dateStr) throws Exception {
		return DateUtil.dateToStr(sdfTime.parse(dateStr), DateUtil.TIME_CONSTANT);
	}

	/**
	* @Title: compareDate
	* @Description: (日期比较，如果s>=e 返回true 否则返回false)
	* @param s
	* @param e
	* @return boolean  
	* @throws
	* @author luguosui
	 */
	public static boolean compareDate(String s, String e) {
		if(fomatDate(s)==null||fomatDate(e)==null){
			return false;
		}
		return fomatDate(s).getTime() >=fomatDate(e).getTime();
	}

	/**
	 * 格式化日期
	 * 
	 * @return
	 */
	public static Date fomatDate(String date) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return fmt.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 格式化日期
	 * 
	 * @return
	 */
	public static Date fomatDate(String date, String format) {
		DateFormat fmt = new SimpleDateFormat(format);
		try {
			return fmt.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 校验日期是否合法
	 * 
	 * @return
	 */
	public static boolean isValidDate(String s) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			fmt.parse(s);
			return true;
		} catch (Exception e) {
			// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
			return false;
		}
	}
    
    /**
     * 得到n天之后的日期
     * @param days
     * @return
     */
    public static String getAfterDayDate(String days) {
    	int daysInt = Integer.parseInt(days);
    	
        Calendar canlendar = Calendar.getInstance(); // java.util包
        canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
        Date date = canlendar.getTime();
        
        SimpleDateFormat sdfd = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = sdfd.format(date);
        
        return dateStr;
    }
    
    /**
     * 得到n天之后是周几
     * @param days
     * @return
     */
    public static String getAfterDayWeek(String days) {
    	int daysInt = Integer.parseInt(days);
    	
        Calendar canlendar = Calendar.getInstance(); // java.util包
        canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
        Date date = canlendar.getTime();
        
        SimpleDateFormat sdf = new SimpleDateFormat("E");
        String dateStr = sdf.format(date);
        
        return dateStr;
    }

	/**
	 * 时间加运算
	 * 
	 * @param type 要做加的日期部分：年/月/日等
	 * @param dt 初始日期对象
	 * @param count 要加的数值
	 * @return Date
	 */
	public static Date dateAdd(int type, Date date, int count) {
		GregorianCalendar g = new GregorianCalendar();
		g.setTime(date);
		g.add(type, count);
		return g.getTime();
	}
	
	public static Date strToDate(String date) {
		try {
			String format = getDefautFormat(date);
			return DateUtil.strToDate(date, format);
		} catch (Exception e) {
			return null;
		}
	}
	
	//*************************************************************************
	/** 
	* 【取得】日期对象转换成字符串
	* @param date 待转换的日期
	* @return   String 转换后的字符串
	*/
	//*************************************************************************
	public static String dateToStr(Date date) {
		if (date == null) {
			return "";
		} else {
			return DateUtil.dateToStr(date, DateUtil.DATA_TIME_CONSTANT);
		}
	}

	//*************************************************************************
	/** 
	* 【取得】方法dateToStr的详细说明
	* @param date 待转换的日期
	* @param format 转换的日期格式
	* @return String 转换后的日期字符串
	*/
	//*************************************************************************
	public static String dateToStr(Date date, String format) {
		if (StringUtil.isBlank(format)) {
			format = DateUtil.DATE_CONSTANT;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}
	
	public static String getDefautFormat(String date) throws Exception {
		date = date.trim();
		String format = "";
		String dCode[] = new String[] { "yyyy", "-MM", "-dd" };
		String tCode[] = new String[] { " HH", ":mm", ":ss" };
		int dF = StringUtil.split(date, "-").length;
		date = StringUtil.replace(date, ":", "#");
		int tF = StringUtil.split(date, "#").length;
		int dtF = StringUtil.split(date, " ") != null ? StringUtil.split(date, " ").length : 0;
		for (int i = 0; i < dF; i++) {
			format += dCode[i];
		}
		for (int j = 0; j < tF && tF > 1; j++) {
			format += tCode[j];
		}
		if (dtF > 1 && tF < 1) {
			format += tCode[0];
		}
		if (StringUtil.split(date, ".").length > 1) {
			format += ".SSS";
		}

		if (format.equals("")) {
			throw new Exception("解析字符时间的格式错误： 时间值=" + date);
		}
		return format;
	}
	
	//*************************************************************************
	/** 
	* 【取得】日期字符串转换为日期对象
	* @param date 待转换的日期字符串
	* @param format 转换的日期格式
	* @return
	* @throws Exception  
	*/
	//*************************************************************************
	public static Date strToDate(String date, String format) throws Exception {
		if (format == null || format.equals("")) {
			format = DateUtil.DATA_TIME_CONSTANT;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format.trim());
		return sdf.parse(date.trim());
	}
	
	//*************************************************************************
	/** 
	* 【计算】时间相减(小时)
	* @param date
	* @param date1
	* @return  
	*/
	//*************************************************************************
	public static int diffDate(Date date, Date date1) {
		return (int) ((getMillis(date1) - getMillis(date)) / (3600 * 1000));
	}
	
	//*************************************************************************
	/** 
	 * 【计算】时间相减(分钟)
	 * @param date
	 * @param date1
	 * @return  
	 */
	//*************************************************************************
	public static int diffDateHorse(Date date, Date date1) {
		return (int) ((getMillis(date1) - getMillis(date)) / (60 * 1000));
	}
	
	//*************************************************************************
	/** 
	 * 【计算】时间相减(秒)
	 * @param date
	 * @param date1
	 * @return  
	 */
	//*************************************************************************
	public static int diffDateSecond(Date date, Date date1) {
		return (int) ((getMillis(date1) - getMillis(date)) / (1000));
	}
	
	//*************************************************************************
	/** 
	 * 【计算】时间差，返回xx天xx分xx秒
	 * @param date    开始时间
	 * @param date1      结束时间
	 * @return  
	 */
	//*************************************************************************
	public static String diffDateStringInfo(Date date, Date date1) {
		String timeInfo = "";
		long count = (getMillis(date1) - getMillis(date)) / 1000;
		int day = 24*60*60, hour = 60*60, mm = 60;
		if(count >= day){
			timeInfo += count/day + "天";
			count %= day;
		}
		if(count >= hour){
			timeInfo += count/hour + "小时";
			count %= hour;
		}
		if(count >= mm){
			timeInfo += count/mm + "分钟";
			count %= mm;
		}
		if(count > 0){
			timeInfo += count + "秒";
		}
		
		return timeInfo;
	}
	
	 //*************************************************************************
    /** 
    * 【计算】时间相减得到年
    * @param beginDateStr
    * @param endDateStr
    * @return  
    */
    //*************************************************************************
	public static long getDiffYear(String startTime, String endTime) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        try {
            Date startDate = sdf.parse(startTime);
            Date endDate = sdf.parse(endTime);
            Calendar startCalendar = Calendar.getInstance();
            Calendar endCalendar = Calendar.getInstance();

            startCalendar.setTime(startDate);
            endCalendar.setTime(endDate);

            int day = endCalendar.get(Calendar.DAY_OF_MONTH)
                    - startCalendar.get(Calendar.DAY_OF_MONTH);
            int month = endCalendar.get(Calendar.MONTH)
                    - startCalendar.get(Calendar.MONTH);
            int year = endCalendar.get(Calendar.YEAR)
                    - startCalendar.get(Calendar.YEAR);

            if (day < 0) {
                month--;
            }
            if (month < 0) {
                month += 12;
                year--;
            }
            return year;
        } catch (ParseException e) {
			// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            return 0;
        }
	}
	
	//*************************************************************************
	/** 
	 * 【计算】时间相减得到月
	 * @param beginDateStr
	 * @param endDateStr
	 * @return  
	 */
	//*************************************************************************
	public static long getDiffMonth(String startTime,String endTime) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        try {
            Date startDate = sdf.parse(startTime);
            Date endDate = sdf.parse(endTime);
            Calendar startCalendar = Calendar.getInstance();
            Calendar endCalendar = Calendar.getInstance();

            startCalendar.setTime(startDate);
            endCalendar.setTime(endDate);

            int day = endCalendar.get(Calendar.DAY_OF_MONTH)
                    - startCalendar.get(Calendar.DAY_OF_MONTH);
            int month = endCalendar.get(Calendar.MONTH)
                    - startCalendar.get(Calendar.MONTH);
            
            if (day < 0) {
                month--;
            }
            if (month < 0) {
                month += 12;
            }
            return month;
        } catch (ParseException e) {
			// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            return 0;
        }
	}
	
    //*************************************************************************
    /** 
    * 【计算】时间相减得到天数
    * @param beginDateStr
    * @param endDateStr
    * @return  
    */
    //*************************************************************************
    public static long getDaySub(String beginDateStr,String endDateStr){
        long day=0;
        java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
        java.util.Date beginDate = null;
        java.util.Date endDate = null;
        try {
			beginDate = format.parse(beginDateStr);
			endDate= format.parse(endDateStr);
			day = (endDate.getTime()-beginDate.getTime())/(1000 * 60 * 60 * 24);
            //System.out.println("相隔的天数="+day);
			return day;
		} catch (ParseException e) {
			return 0;
		}
    }

	
	//*************************************************************************
	/** 
	* 【取得】返回年份
	* @param date
	* @return  
	*/
	//*************************************************************************
	public static int getYear(Date date) {
		calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.YEAR);
	}

	//*************************************************************************
	/** 
	* 【取得】返回月份
	* @param date
	* @return  
	*/
	//*************************************************************************
	public static int getMonth(Date date) {
		calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.MONTH) + 1;
	}

	//*************************************************************************
	/** 
	* 【取得】返回日份
	* @param date
	* @return  
	*/
	//*************************************************************************
	public static int getDay(Date date) {
		calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_MONTH);
	}

	//*************************************************************************
	/** 
	* 【取得】返回小时
	* @param date
	* @return  
	*/
	//*************************************************************************
	public static int getHour(Date date) {
		calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.HOUR_OF_DAY);
	}

	//*************************************************************************
	/** 
	* 【取得】返回分钟
	* @param date
	* @return  
	*/
	//*************************************************************************
	public static int getMinute(Date date) {
		calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.MINUTE);
	}

	//*************************************************************************
	/** 
	* 【取得】返回秒钟
	* @param date
	* @return  
	*/
	//*************************************************************************
	public static int getSecond(Date date) {
		calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.SECOND);
	}

	//*************************************************************************
	/** 
	* 【取得】返回毫秒
	* @param date
	* @return  
	*/
	//*************************************************************************
	public static long getMillis(Date date) {
		calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.getTimeInMillis();
	}
	
	//*************************************************************************
	/** 
	* 【获得】周几日期时间
	* @param index   (1,2,3,4,5,6,7)
	* @param df   	 DateFormat定义返回格式new SimpleDateFormat("yyyy-MM-dd 00:00:00")
	* @return  
	*/
	//*************************************************************************
	public static String getWeekTime(int index,DateFormat df){
		String time = "";
		Calendar cal = Calendar.getInstance();
		switch (index) {
		case 1:
			cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
			break;
		case 2:
			cal.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
			break;
		case 3:
			cal.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
			break;
		case 4:
			cal.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
			break;
		case 5:
			cal.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
			break;
		case 6:
			cal.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
			break;
		case 7:
			cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
			break;
		default:
			break;
		}
		time = df.format(cal.getTime());
		
		return time;
	}
	
	//*************************************************************************
	/** 
	* 【获得】某月第一天
	* @param num   (-1,0,1,2,3,4,5,6,7)月份
	* @return  
	*/
	//*************************************************************************
	public static String getMonthFirstDay(int num){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
		Calendar cal=Calendar.getInstance();//获取当前日期 
        cal.add(Calendar.MONTH, 1);
        int month = cal.get(Calendar.MONTH);
        num = num - month - 1;
        cal.add(Calendar.MONTH, num);
        cal.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天 
        String firstDay = format.format(cal.getTime());
        return firstDay;
	}
	
	//*************************************************************************
	/** 
	* 【获得】某月最后一天
	* @param num   (-1,0,1,2,3,4,5,6,7)月份
	* @return  
	*/
	//*************************************************************************
	public static String getMonthLastDay(int num){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
		Calendar cal=Calendar.getInstance();//获取当前日期 
        cal.add(Calendar.MONTH, 1);
        int month = cal.get(Calendar.MONTH);
        num = num - month;
        cal.add(Calendar.MONTH, num);
        cal.set(Calendar.DAY_OF_MONTH,0);//设置为1号,当前日期既为本月第一天 
        String lastDay = format.format(cal.getTime());
        return lastDay;
	}
	
	//*************************************************************************
	/** 
	* 【获取】获取时间差，会返回几小时前或者是几分钟几秒钟前
	* @param StrDate
	* @return  
	*/
	//*************************************************************************
	public static String getTimes(String StrDate){
		String resultTimes = "";
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date now;
		try {
			now = new Date();
			java.util.Date date = df.parse(StrDate);
			long times = now.getTime() - date.getTime();
			long day = times / (24 * 60 * 60 * 1000);
			long hour = (times / (60 * 60 * 1000) - day * 24);
			long min = ((times / (60 * 1000)) - day * 24 * 60 - hour * 60);
			long sec = (times / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
			StringBuffer sb = new StringBuffer();
			// sb.append("发表于：");
			if (hour > 0) {
				sb.append(hour + "小时");
			} else if (min > 0) {
				sb.append(min + "分钟");
			} else {
				sb.append(sec + "秒");
			}
			resultTimes = sb.toString();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return resultTimes;
	}
	
	/*************************************************************************
	/** 
	* 【获取】时间格式化成字符串
	* @param date
	* @return  
	*/
	//*************************************************************************
	public static String dateToString(Date date){
		
		return sdfTime.format(date);
	}
	
	 //*************************************************************************
	/** 
	* 【判断】判断是否润年
	* 
	* 详细设计： 1.被400整除是闰年，否则： 2.不能被4整除则不是闰年 3.能被4整除同时不能被100整除则是闰年
	* 3.能被4整除同时能被100整除则不是闰年
	* 
	* @param ddate
	* @return
	* @throws Exception  
	*/
	//*************************************************************************
	public static boolean isLeapYear(String ddate) throws Exception {
		Date d = strToDate(ddate);
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(d);
		int year = gc.get(Calendar.YEAR);
		if ((year % 400) == 0)
			return true;
		else if ((year % 4) == 0) {
			if ((year % 100) == 0)
				return false;
			else
				return true;
		} else
			return false;
	}
	
	//*************************************************************************
	/** 
	* 【获取】获取默认格式时间戳
	* @return  
	*/
	//*************************************************************************
	public static String getSystemDateStr() {

		return DateUtil.dateToStr(new Date(), DateUtil.DATA_TIME_CONSTANT);
	}
	
	//*************************************************************************
	/** 
	* 【获取】获取指定格式时间戳
	* @param format
	* @return  
	*/
	//*************************************************************************
	public static String getSystemDateStr(String format) {

		return DateUtil.dateToStr(new Date(), format);
	}
	
	 //*************************************************************************
	/** 
	* 【取得】根据一个日期，返回是星期几的字符串
	* @param sdate
	* @return
	* @throws Exception  
	*/
	//*************************************************************************
	public static String getWeek(String sdate) throws Exception {
		// 再转换为时间
		Date date = DateUtil.strToDate(sdate);
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		// int hour=c.get(Calendar.DAY_OF_WEEK);
		// hour中存的就是星期几了，其范围 1~7
		// 1=星期日 7=星期六，其他类推
		return new SimpleDateFormat("EEEE").format(c.getTime());
	}
	
	 public static String getWeekStr(String sdate) throws Exception{
		 String str = "";
		  Date date = DateUtil.strToDate(sdate);
		  Calendar c = Calendar.getInstance();
		  c.setTime(date);
		  int week=c.get(Calendar.DAY_OF_WEEK);
		  switch (week) {
			case 1:
				str = "周日";
				break;
			case 2:
				str = "周一";
				break;
			case 3:
				str = "周二";
				break;
			case 4:
				str = "周三";
				break;
			case 5:
				str = "周四";
				break;
			case 6:
				str = "周五";
				break;
			case 7:
				str = "周六";
				break;
			}
		  return str;
		 }
	 
	 //*************************************************************************
	/** 
	* 【得到】得到一个日期的下一个日期
	* @param nowdate 对比日期
	* @param delay 操作时长
	* @return  
	*/
	//*************************************************************************
	public static String getNextDay(String nowdate, Integer delay) {
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date d = DateUtil.strToDate(nowdate);
			long myTime = (d.getTime() / 1000) + delay * 24 * 60 * 60;
			d.setTime(myTime * 1000);
			return format.format(d);
		} catch (Exception e) {
			return "";
		}
	}
	
	 //*************************************************************************
	/** 
	* 【得到】得到时分秒字符串
	* @param dateStr
	* @return  
	*/
	//*************************************************************************
	public static String getTimeString(String dateStr) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		return sdf.format(sdfTime.parse(dateStr.replace("/", "-")));
	}
	 
	//*************************************************************************
	/** 
	* 【取得】根据年月得到这个月的天数
	* @param year 年
	* @param mounth 月
	* @return  
	*/
	//*************************************************************************
	public static int getDayCount(int year, int month){
//		Calendar c = Calendar.getInstance();
//		c.set(Calendar.YEAR, year);
//		c.set(Calendar.MONTH, month - 1);
//		System.out.println("------------" + c.get(Calendar.YEAR) + "年" + (c.get(Calendar.MONTH) + 1) + "月的天数-------------");
//		System.out.println("天数：" + c.getActualMaximum(Calendar.DAY_OF_MONTH));
//		return c.getActualMaximum(Calendar.DAY_OF_MONTH);
		int[] monDays = new int[] {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
		if ( ( (year) % 4 == 0 && (year) % 100 != 0) ||(year) % 400 == 0) 
		        monDays[1]++;
		int day = monDays[month-1];
		System.out.println("------------" + year + "年" + month + "月的天数-------------");
		System.out.println("天数：" + day);
		return day;
	}
	
	//*************************************************************************
	/** 
	* 【加减】时间值
	* @param date    要处理的时间(要包含有年月日时分秒)
	* @param option  + 或 -
	* @param num	  数值
	* @param flag	 d 天  h小时  m分钟   s秒
	* @return  
	*/
	//*************************************************************************
	public static Date getOptionTime(Date date,String option,int num,String flag)	{
		if("+".equals(option)){
			if("s".equals(flag)){
				date = new Date(date.getTime() + num*1000);
			} else if("m".equals(flag)){
				date = new Date(date.getTime() + num*60*1000);
			} else if("h".equals(flag)){
				date = new Date(date.getTime() + num*60*60*1000);
			} else {
				date = new Date(date.getTime() + num*24*60*60*1000);
			}
		} else {
			if("s".equals(flag)){
				date = new Date(date.getTime() - num*1000);
			} else if("m".equals(flag)){
				date = new Date(date.getTime() - num*60*1000);
			} else if("h".equals(flag)){
				date = new Date(date.getTime() - num*60*60*1000);
			} else {
				date = new Date(date.getTime() - num*24*60*60*1000);
			}
		}
		
		return date;
	}
	
	//*************************************************************************
	/** 
	* 【获取】获取某个月的第一天
	* @param year 年
	* @param month 月
	* @return  
	*/
	//*************************************************************************
	public static String getFisrtDayOfMonth(int year,int month)	{		
		Calendar cal = Calendar.getInstance();		
		cal.set(Calendar.YEAR,year);
		cal.set(Calendar.MONTH, month-1);
		int firstDay = cal.getActualMinimum(Calendar.DAY_OF_MONTH);	
		cal.set(Calendar.DAY_OF_MONTH, firstDay);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");		
		String firstDayOfMonth = sdf.format(cal.getTime());				
		return firstDayOfMonth;
	}
	
	//*************************************************************************
	/** 
	* 【获取】月份加减
	* @param time 要处理的时间
	* @param num 正负月数
	* @return  
	*/
	//*************************************************************************
	public static String monthOption(Date time,int num)	{
		int year = getYear(time);
		int month = getMonth(time);
		Calendar cal = Calendar.getInstance();		
		cal.set(Calendar.YEAR,year);
		cal.set(Calendar.MONTH, month+num-1);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");		
		String firstDayOfMonth = sdf.format(cal.getTime());				
		return firstDayOfMonth;
	}
	
	//*************************************************************************
	/** 
	* 【取得】获取系统日期
	* @return  
	*/
	//*************************************************************************
	public static Date getSystemDate() {

		return new Date();
	}
	
	   
		//*************************************************************************
			/**
			 * 【取得】list<map>   =>   text  yyyy-MM-dd w   / value   yyyy-MM-dd
			 * 传入类型:Object => yyyyMM
			 * @return 年月日 星期
			 */
		//*************************************************************************
	 	
	   public static List<Map<String, Object>> getMapYMDW(Object date) throws Exception {
		  List<Map<String, Object>> listday = new ArrayList<Map<String, Object>>();
		  Map<String, Object> mapymdw = null;
		  int year = Integer.parseInt(StringUtil.valueOf(date).substring(0,4));
		  int nub = StringUtil.valueOf(date).indexOf("-") != -1 ? 1 : 0;
		  int day = Integer.parseInt(StringUtil.valueOf(date).substring(4+nub,6+nub));
		  int tday = DateUtil.getDayCount(year,day);
		  for (int i = 1; i <= tday; i++) {
			  	mapymdw = new HashMap<String, Object>();
			  	String datatime = null;
			  	if(i < 10){
			  		if(day < 10){
			  			datatime = year +"-0"+ day +"-0" + i;
			  		} else {
			  			datatime = year +"-"+ day +"-0" + i;
			  		}
				} else {
					if(day < 10){
			  			datatime = year +"-0"+ day +"-" + i;
					} else {
			  			datatime = year +"-"+ day +"-" + i;
					}
				}
				String weekstr = DateUtil.getWeekStr(datatime);
				mapymdw.put("text", datatime + " " +weekstr);
				mapymdw.put("value", datatime);
				listday.add(mapymdw);
			}
		  return listday; 
	   }
	   
	//*************************************************************************
		/**
		 * 【取得】yyyy-MM
		 * 传入类型:yyyyMM
		 * @return yyyy-MM
		 */
	//*************************************************************************
	
	  public static String getYtoM(Object date) {
		  return StringUtil.valueOf(date).substring(0,4)+"-"+StringUtil.valueOf(date).substring(4,6); 
	  }
}
