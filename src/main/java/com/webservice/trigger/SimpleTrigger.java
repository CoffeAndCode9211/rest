package com.webservice.trigger;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class SimpleTrigger {

	SimpleTrigger(){
		 
				JobDetail job = JobBuilder.newJob(SampleExample.class)
					.withIdentity("dummyJobName", "group1").build();
				Trigger trigger = TriggerBuilder
					.newTrigger()
					.withIdentity("dummyTriggerName", "group1")
					.withSchedule(
						SimpleScheduleBuilder.simpleSchedule()
							.withIntervalInSeconds(3).repeatForever())
					.build();
		 try{
				Scheduler scheduler = new StdSchedulerFactory().getScheduler();
				scheduler.start();
				scheduler.scheduleJob(job, trigger);
				
		 }catch(Exception e){
			 e.printStackTrace();
		 }
	}
}	
