package com.webservice.trigger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Startup
@Singleton
public class QuartzCdiExample {

	private static final Logger logger = LoggerFactory.getLogger(QuartzCdiExample.class);

	private Scheduler scheduler;
	
	@PostConstruct
	public void startUp()
	{
		try {
			scheduler = new StdSchedulerFactory().getScheduler();
			scheduler.start();
			scheduleJob(scheduler);
			
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
		
	}
	
	private void scheduleJob(Scheduler scheduler) throws SchedulerException{
		JobDetail job = JobBuilder.newJob(SampleExample.class)
				.withIdentity("dummyJobName", "group1").build();
			Trigger trigger = TriggerBuilder
				.newTrigger()
				.withIdentity("dummyTriggerName", "group1")
				.withSchedule(
					SimpleScheduleBuilder.simpleSchedule()
						.withIntervalInSeconds(3).repeatForever())
				.build();
			scheduler.scheduleJob(job, trigger);
			
	}
	
	@PreDestroy
	public void destroy()
	{        
		logger.info("destroy ttttttt called");
		try {
			scheduler.shutdown();
		} catch (SchedulerException e) {
			e.printStackTrace();
		}

	}
	

}
