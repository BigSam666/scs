package com.zhcs.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.quartz.CronTrigger;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhcs.dao.ScheduleJobDao;
import com.zhcs.entity.ScheduleJobEntity;
import com.zhcs.service.ScheduleJobService;
import com.zhcs.utils.Constant.ScheduleStatus;
import com.zhcs.utils.ScheduleUtils;

@Service("scheduleJobService")
public class ScheduleJobServiceImpl implements ScheduleJobService {
	@Autowired
    private Scheduler scheduler;
	@Autowired
	private ScheduleJobDao scheduleJobDao;
	
	/**
	 * 项目启动时，初始化定时器
	 */
	@PostConstruct
	public void init(){
		List<ScheduleJobEntity> scheduleJobList = scheduleJobDao.queryList(new HashMap<String, Object>());
		for(ScheduleJobEntity scheduleJob : scheduleJobList){
			CronTrigger cronTrigger = ScheduleUtils.getCronTrigger(scheduler, scheduleJob.getId());
            //如果不存在，则创建
            if(cronTrigger == null) {
                ScheduleUtils.createScheduleJob(scheduler, scheduleJob);
            }else {
                ScheduleUtils.updateScheduleJob(scheduler, scheduleJob);
            }
		}
	}
	
	@Override
	public ScheduleJobEntity queryObject(Long id) {
		return scheduleJobDao.queryObject(id);
	}

	@Override
	public List<ScheduleJobEntity> queryList(Map<String, Object> map) {
		return scheduleJobDao.queryList(map);
	}

	@Override
	public int queryTotal(Map<String, Object> map) {
		return scheduleJobDao.queryTotal(map);
	}

	@Override
	@Transactional
	public void save(ScheduleJobEntity scheduleJob) {
		scheduleJob.setStatus(ScheduleStatus.NORMAL.getValue());
        scheduleJobDao.save(scheduleJob);
        
        ScheduleUtils.createScheduleJob(scheduler, scheduleJob);
    }
	
	@Override
	@Transactional
	public void update(ScheduleJobEntity scheduleJob) {
        ScheduleUtils.updateScheduleJob(scheduler, scheduleJob);
                
        scheduleJobDao.update(scheduleJob);
    }

	@Override
	@Transactional
    public void deleteBatch(Long[] ids) {
    	for(Long id : ids){
    		ScheduleUtils.deleteScheduleJob(scheduler, id);
    	}
    	
    	//删除数据
    	scheduleJobDao.deleteBatch(ids);
	}

	@Override
    public int updateBatch(Long[] ids, int status){
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("list", ids);
    	map.put("status", status);
    	return scheduleJobDao.updateBatch(map);
    }
    
	@Override
	@Transactional
    public void run(Long[] ids) {
    	for(Long id : ids){
    		ScheduleUtils.run(scheduler, queryObject(id));
    	}
    }

	@Override
	@Transactional
    public void pause(Long[] ids) {
        for(Long id : ids){
    		ScheduleUtils.pauseJob(scheduler, id);
    	}
        
    	updateBatch(ids, ScheduleStatus.PAUSE.getValue());
    }

	@Override
	@Transactional
    public void resume(Long[] ids) {
    	for(Long id : ids){
    		ScheduleUtils.resumeJob(scheduler, id);
    	}

    	updateBatch(ids, ScheduleStatus.NORMAL.getValue());
    }
    
//	@Transactional(rollbackFor = RuntimeException.class)
//	public void addAdmin(Admin admin) {
//	    try {
//	        this.adminDao.add(admin);
//	        admin.setUsername("123456789012345");
//	        this.adminDao.update(admin);
//	    } catch (Exception e) {
//	        e.printStackTrace();
//	        throw new RuntimeException("测试!");
//	    }
	
}
