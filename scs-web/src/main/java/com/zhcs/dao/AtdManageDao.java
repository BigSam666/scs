package com.zhcs.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.zhcs.entity.AtdManageEntity;

//*****************************************************************************
/**
 * <p>Title:AtdManageDao</p>
 * <p>Description: 考勤管理申报</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public interface AtdManageDao extends BaseDao<AtdManageEntity> {

	/**
	 * 参数(time)在返回结果中的每一个元素的ftime-stime中
	 */
	AtdManageEntity queryListIncludeTime(Long userId, Date time);

	/** 
	 * 查询指定用户在指定时间段上的调休调整
	 * 我也不知道怎么说，自己看SQL吧。。。。
	 * @param person 哪个人
	 * @param bdate 开始时间
	 * @param edate 结束时间
	 * @return  
	 */
	List<AtdManageEntity> queryTxListIncludeDuration(Long person, Date sdate, Date edate);

	/** 
	 * 查询指定用户在指定时间段上的调班调整
	 * 我也不知道怎么说，自己看SQL吧。。。。
	 * @param person 哪个人
	 * @param bdate 开始时间
	 * @param edate 结束时间
	 * @return  
	 */
	List<AtdManageEntity> queryTbListIncludeDuration(Long person, Date sdate, Date edate);
	
	/**
	 * 根据员工查询 班次上的时间调休 调班
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> findTiaoxiuOrxiu(Map<String, Object> map);

}
