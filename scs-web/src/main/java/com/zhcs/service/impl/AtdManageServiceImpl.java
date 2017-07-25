package com.zhcs.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhcs.dao.AtdManageDao;
import com.zhcs.entity.AtdManageEntity;
import com.zhcs.service.AtdManageService;
import com.zhcs.utils.DepartmentUtil;
import com.zhcs.utils.ServiceException;


//*****************************************************************************
/**
 * <p>Title:AtdManageServiceImpl</p>
 * <p>Description: 考勤管理申报</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司 </p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************

@Service("atdManageService")
public class AtdManageServiceImpl implements AtdManageService {
	@Autowired
	private AtdManageDao atdManageDao;
	@Autowired
	private DepartmentUtil departmentUtil;
	
	@Override
	public AtdManageEntity queryObject(Long id){
		return atdManageDao.queryObject(id);
	}
	
	@Override
	public List<AtdManageEntity> queryList(Map<String, Object> map){
		return atdManageDao.queryList(map);
	}
	
	@Override
	public List<AtdManageEntity> queryList1(Map<String, Object> map){
		String ids = departmentUtil.queryNodeDeptChildIds();
		if (ids != null && !"".equals(ids)) {
			map.put("ids", ids);
		}
		return atdManageDao.queryList1(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return atdManageDao.queryTotal(map);
	}
	
	@Override
	@Transactional
	public void save(AtdManageEntity atdManage) throws ServiceException{
		// 同一时间段的时间不能多次调整
		Date ftime = atdManage.getFtime();
		Date stime = atdManage.getStime();
		AtdManageEntity fs = atdManageDao.queryListIncludeTime(atdManage.getPerson(),ftime);
		AtdManageEntity ss = atdManageDao.queryListIncludeTime(atdManage.getPerson(),stime);
		if (fs != null || ss != null) {
			throw new ServiceException("不能再次调整该时间段");
		}
		atdManage.setStatus("9");
		// 如果没有相对应班次的调休请假不计
		atdManageDao.save(atdManage);
	}
	
	@Override
	@Transactional
	public void update(AtdManageEntity atdManage){
		atdManageDao.update(atdManage);
	}
	
	@Override
	@Transactional
	public void delete(Long id){
		atdManageDao.delete(id);
	}
	
	@Override
	@Transactional
	public void deleteBatch(Long[] ids){
		atdManageDao.deleteBatch(ids);
	}
	
}
