package com.zhcs.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhcs.dao.AtdManageDao;
import com.zhcs.dao.AtdRecordDao;
import com.zhcs.entity.AtdRecordEntity;
import com.zhcs.service.AtdRecordService;
import com.zhcs.utils.DepartmentUtil;


//*****************************************************************************
/**
 * <p>Title:AtdRecordServiceImpl</p>
 * <p>Description: 考勤记录</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************

@Service("atdRecordService")
public class AtdRecordServiceImpl implements AtdRecordService {
	@Autowired
	private AtdRecordDao atdRecordDao;
	
	@Autowired
	private AtdManageDao atdManageDao;
	
	@Autowired
	private DepartmentUtil departmentUtil;
	
	@Override
	public AtdRecordEntity queryObject(Long id){
		return atdRecordDao.queryObject(id);
	}
	
	@Override
	public List<AtdRecordEntity> queryList(Map<String, Object> map){
		List<AtdRecordEntity> ares = atdRecordDao.queryList(map);
		// 更新状态信息
		/*for (AtdRecordEntity are : ares) {
			Long shift = are.getShift(); // 班次
			ClassesEntity target = classesDao.queryObject(shift);

			DateTimeFormatter timeFormatter = DateTimeFormat.forPattern("HH:mm:ss");
			DateTimeFormatter dateFormatter = DateTimeFormat.forPattern("yyyy-MM-dd");
			DateTimeFormatter datertimeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");

			String dateStr = dateFormatter.print(new LocalDate(are.getBtime().getTime()));

			LocalDateTime startTime =  datertimeFormatter.parseLocalDateTime(dateStr+" "+target.getStarttime()); // 正常上班时间
			LocalDateTime endTime =  datertimeFormatter.parseLocalDateTime(dateStr+" "+target.getEmdtime()); // 正常上班时间
			LocalDateTime btime = new LocalDateTime(are.getBtime().getTime()); // 实际上班时间
			LocalDateTime etime = new LocalDateTime(are.getEtime().getTime()); // 实际下班时间
			List<AtdManageEntity> ames = atdManageDao.queryTxListIncludeDuration(are.getPerson(),startTime.toDate(),endTime.toDate()); // 处于这个时间段的调休
			List<AtdManageEntity> ame2s = atdManageDao.queryTbListIncludeDuration(are.getPerson(),startTime.toDate(),endTime.toDate());// 处于这个时间段的调班

			if (btime.compareTo(startTime) < 0 && etime.compareTo(endTime) > 0) {
				are.setStatus(1); // 正常
				break;
			}
			if (btime.compareTo(startTime) > 0) { // 实际上班时间大于正常上班时间
				Duration duration = new Duration(startTime, btime);
				List<Duration> rest = new ArrayList<Duration>(); // 尚不确定是否正常的时间段
				rest.add(duration);
				for (AtdManageEntity a : ames) {
					for (Duration d:rest) {

					}
				}
			}
			if (etime.compareTo(endTime) > 0) { // 实际下班时间小于正常下班时间


			}

		}*/
		return ares;
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return atdRecordDao.queryTotal(map);
	}
	
	@Override
	@Transactional
	public void save(AtdRecordEntity atdRecord){
		atdRecordDao.save(atdRecord);
	}
	
	@Override
	@Transactional
	public void update(AtdRecordEntity atdRecord){
		atdRecordDao.update(atdRecord);
	}
	
	@Override
	@Transactional
	public void delete(Long id){
		atdRecordDao.delete(id);
	}
	
	@Override
	@Transactional
	public void deleteBatch(Long[] ids){
		atdRecordDao.deleteBatch(ids);
	}
	
	public static void main(String[] args) {
		System.out.println(DateTimeFormat.forPattern("yyyy-MM-dd").print(new LocalDate(new Date().getTime())));
	}

	@Override
	public Map<String, Object> queryOperatorRecordDatalist(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		Map<String, Object> resultMap = new HashMap<>();
		
		String ids = departmentUtil.queryNodeDeptChildIds();
		if (ids != null && !"".equals(ids)) {
			paramMap.put("ids", ids);
		}
		List<Map<String, Object>> datalist =  atdRecordDao.queryOperatorRecordDatalist(paramMap);
		int total = atdRecordDao.queryOperatorRecordDatalistCount(paramMap);
		
		resultMap.put("datalist", datalist);
		resultMap.put("total", total);
		return resultMap;
	}

	@Override
	public List<Map<String, Object>> findTiaoxiuOrBan(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		
		
		
		return atdManageDao.findTiaoxiuOrxiu(paramMap);
	}
	
}
