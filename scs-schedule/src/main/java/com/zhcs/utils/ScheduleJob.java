package com.zhcs.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.commons.lang.StringUtils;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.zhcs.entity.ScheduleJobEntity;
import com.zhcs.entity.ScheduleJobLogEntity;
import com.zhcs.service.ScheduleJobLogService;

//*****************************************************************************
/**
 * <p>Title:ScheduleJob</p>
 * <p>Description:定时任务</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司</p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public class ScheduleJob extends QuartzJobBean {
	private ExecutorService service = Executors.newSingleThreadExecutor(); 
	
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        ScheduleJobEntity scheduleJob = (ScheduleJobEntity) context.getMergedJobDataMap()
        		.get(ScheduleJobEntity.JOB_PARAM_KEY);
        
        //获取spring bean
        ScheduleJobLogService scheduleJobLogService = (ScheduleJobLogService) SpringContextUtils.getBean("scheduleJobLogService");
        
        //数据库保存执行记录
        ScheduleJobLogEntity log = new ScheduleJobLogEntity();
        log.setJobid(scheduleJob.getId());
        log.setBnm(scheduleJob.getBnm());
        log.setMnm(scheduleJob.getMnm());
        log.setParams(scheduleJob.getParams());
        BeanUtil.fillCCUUD(log);
        //任务开始时间
        long startTime = System.currentTimeMillis();
        
        try {
            //执行任务
        	LogUtil.info("任务准备执行，任务ID：{}", scheduleJob.getId());
            ScheduleRunnable task = new ScheduleRunnable(scheduleJob.getBnm(), 
            		scheduleJob.getMnm(), scheduleJob.getParams());
            Future<?> future = service.submit(task);
            
			future.get();
			
			//任务执行总时长
			long times = System.currentTimeMillis() - startTime;
			log.setTimes((int)times);
			//任务状态    0：成功    1：失败
			log.setStatus(0);
			
			LogUtil.info("任务执行完毕，任务ID：{}", scheduleJob.getId() + "  总共耗时：" + times + "毫秒");
		} catch (Exception e) {
			LogUtil.error("任务执行失败，任务ID：{}", scheduleJob.getId(), e);
			
			//任务执行总时长
			long times = System.currentTimeMillis() - startTime;
			log.setTimes((int)times);
			
			//任务状态    0：成功    1：失败
			log.setStatus(1);
			log.setError(StringUtils.substring(e.toString(), 0, 2000));
		}finally {
			BeanUtil.fillCCUUD(log);
			scheduleJobLogService.save(log);
		}
    }
}
