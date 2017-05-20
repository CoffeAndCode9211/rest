package com.webservice.service;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.webservice.model.DrugInfo;

/**
 * Employee Implementation 
 *
 * @author  Ashish Kumar
 * @version 1.0
 * @since   2015-08-19 
 */

@Stateless
public class DrugInfoEJBImpl implements DrugInfoEJBIf {

	@PersistenceContext(unitName = "webUnit" )
	private EntityManager em;
	
	@Resource
	private SessionContext sctx;

	private static final Logger logger = LoggerFactory
			.getLogger(DrugInfoEJBImpl.class);

	@Override
	public boolean addDrugInfo(DrugInfo drugInfo) throws Exception {
		em.persist(drugInfo);
		em.flush();
		return true;
	}

	@Override
	public boolean updateDrugInfo(DrugInfo drugInfo) throws Exception {
		em.merge(drugInfo);
		em.flush();
		return true;
	}

	@Override
	public DrugInfo getDrugInfoById(Integer id) throws Exception {
		return em.find(DrugInfo.class, id);
	}

	@Override
	public List<DrugInfo> getDrugInfosByFilter(String drugName) throws Exception {
		StringBuilder query = new StringBuilder();
		query.append("from DrugInfo e where 1=1 ");
		if(drugName != null && !drugName.equals("")){
			query.append("  e.name like '"+drugName+"' " );
		}
		List<DrugInfo> lstDrugInfo = em.createQuery(query.toString(), DrugInfo.class).getResultList();
		return lstDrugInfo;
	}

	
	

}
