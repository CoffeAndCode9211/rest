package com.webservice.trigger;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.webservice.model.Employee;
import com.webservice.service.EmployeeEJBIf;


/**
 * Sample Timer service
 * @author ashishkumar
 *
 */
@Startup
@Singleton
public class SampleTimer {

	@Resource
	private TimerService service;

	@EJB
	EmployeeEJBIf empEJBIf;
	
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
			List<Employee> lst = empEJBIf.getEmployeesByFilter(new Employee());
			for(Employee emp : lst){
				logger.info(emp.toString());
			}
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
		logger.info("destroying EJB Timer");

	}
}
