package com.webservice.service;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.webservice.dto.BikeExpenseTO;
import com.webservice.model.BikeExpense;

/**
 * BikeExpense Implementation 
 *
 * @author  Ashish Kumar
 * @version 1.0
 * @since   2015-08-19 
 */

@Stateless
public class BikeExpenseEJBImpl implements BikeExpenseEJBIf {

	@PersistenceContext(unitName = "webUnit" )
	private EntityManager em;
	
	@Resource
	private SessionContext sctx;

	private static final Logger logger = LoggerFactory
			.getLogger(BikeExpenseEJBImpl.class);

	public boolean addBikeExpense(BikeExpense BikeExpense) throws Exception {
		em.persist(BikeExpense);
		em.flush();
		return true;
	}

	public boolean updateBikeExpense(BikeExpense BikeExpense) throws Exception {
		em.merge(BikeExpense);
		em.flush();
		return true;
	}

	public BikeExpense getBikeExpenseById(Integer id) throws Exception {
		return em.find(BikeExpense.class, id);
	}

	public List<BikeExpense> getBikeExpensesByFilter(BikeExpenseTO bikeExpenseTO)
			throws Exception {
		System.out.println("Session Id: "+sctx.getCallerPrincipal().getName());
		StringBuilder query = new StringBuilder();
		query.append("from BikeExpense e where 1=1 ");
		
		List<BikeExpense> bikeExpenseList = em.createQuery(query.toString(), BikeExpense.class).getResultList();
		return bikeExpenseList;
	}

	public boolean deleteBikeExpense(BikeExpense BikeExpense) throws Exception {
		em.remove(BikeExpense);
		em.flush();
		return true;
	}

	
	private Boolean isNullOrEmpty(String str){
		if(str == null || str.isEmpty()){
			return true;
		}
		return false;
	}

	

}
