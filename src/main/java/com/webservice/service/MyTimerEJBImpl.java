package com.webservice.service;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.webservice.dto.StringUtil;
import com.webservice.model.MyTimer;

/**
 * 
 * @author ashishkumar
 *
 */
@Stateless
public class MyTimerEJBImpl implements MyTimerEJBIf {

	@PersistenceContext(unitName = "webUnit" )
	private EntityManager em;
	
	@Resource
	private SessionContext sctx;

	private static final Logger logger = LoggerFactory
			.getLogger(MyTimerEJBImpl.class);

	public boolean addUpdateDelMyTimer(MyTimer myTimer) throws Exception {
		em.merge(myTimer);
		em.flush();
		return true;
	}

	public MyTimer getMyTimerById(Integer id) throws Exception {
		return em.find(MyTimer.class, id);
	}

	public List<MyTimer> getMyTimersByFilter(MyTimer myTimer) throws Exception {
		StringBuilder query = new StringBuilder();
		query.append("from MyTimer e where e.status='A' ");
		
		if(!StringUtil.isNullOrBlank(myTimer.getReason())){
			query.append(" and e.reason like '");
			query.append(myTimer.getReason());
			query.append("%' ");
		}
		List<MyTimer> employeeList = em.createQuery(query.toString(), MyTimer.class).getResultList();
		return employeeList;
	}
	

}
