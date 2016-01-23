package com.webservice.trigger;

import java.util.Hashtable;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.webservice.model.Employee;
import com.webservice.service.EmployeeEJBIf;
/**
 * Defines what job you want to run
 * @author ashishkumar
 *
 */
public class SampleExample implements Job{


	private static final Logger logger = LoggerFactory.getLogger(SampleExample.class);
	
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		try{
			final Hashtable<String, String> jndiProperties = new Hashtable<String, String>();
			jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
			final Context con = new InitialContext(jndiProperties);
			EmployeeEJBIf lif = (EmployeeEJBIf) con.lookup("java:global/webservice/EmployeeEJBImpl!com.webservice.service.EmployeeEJBIf");
			List<Employee> lst = lif.getEmployeesByFilter(new Employee());
			for(Employee emp : lst){
				logger.info("QuartzCdi Example trigger Running =>"+emp.toString());
			}
		}catch(Exception e){
			logger.info("exception while running timer thread :"+e.getMessage());
		}
	}

	
}
