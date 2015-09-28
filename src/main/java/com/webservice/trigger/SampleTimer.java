package com.webservice.trigger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Startup
@Singleton
public class SampleTimer {

	@Resource
	private TimerService service;

	private static final Logger logger = LoggerFactory.getLogger(SampleTimer.class);

	private long timervalue=0;

	@PostConstruct
	public void startUp()
	{
		timervalue = 30000;
		if(timervalue!=-1){
			service.createSingleActionTimer(30000, new TimerConfig(null, false)); // Fire after 1 minute.
		}	
	}

	@Timeout
	public void timerTriggered(Timer timer)
	{
		try{
			logger.info("response from Data : ");
		}catch(Exception e){
			logger.info("exception while running vista timer thread :"+e.getMessage());
		}
		finally{
			timervalue = 30000;
			if(timervalue!=-1){
				service.createSingleActionTimer(30000, new TimerConfig(null, false)); // Fire after 1 minute.
			}	
		}

	}

	@PreDestroy
	public void destroy()
	{        
		logger.info("destroy timer called");

	}
}
