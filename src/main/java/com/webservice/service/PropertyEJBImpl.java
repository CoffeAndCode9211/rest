package com.webservice.service;

import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.webservice.model.Property;
import com.webservice.model.Users;

/**
 * Property Implementation 
 *
 * @author  Ashish Kumar
 * @version 1.0
 * @since   2015-08-19 
 */

@Stateless
public class PropertyEJBImpl implements PropertyEJBIf {

	@PersistenceContext(unitName = "mongo-ogm" )
	private EntityManager em;
	
	@Resource
	private SessionContext sctx;

	private static final Logger logger = LoggerFactory
			.getLogger(PropertyEJBImpl.class);

	public boolean addProperty(Property employee) throws Exception {
		em.persist(employee);
		em.flush();
		return true;
	}

	public boolean updateProperty(Property employee) throws Exception {
		em.merge(employee);
		em.flush();
		return true;
	}

	public Property getPropertyById(Integer id) throws Exception {
		return em.find(Property.class, id);
	}

	public List<Property> getPropertysByFilter(Property employee)
			throws Exception {
		System.out.println("Session Id: "+sctx.getCallerPrincipal().getName());
		StringBuilder query = new StringBuilder();
		query.append("from Property e  ");
//		if(!StringUtil.isNullOrBlank(employee.getEmail())){
//			query.append(" and e.email like '");
//			query.append(employee.getEmail());
//			query.append("%' ");
//		}
//		if(!StringUtil.isNullOrBlank(employee.getFirstName())){
//			query.append(" and e.firstName like '");
//			query.append(employee.getFirstName());
//			query.append("%' ");
//		}
//		if(!StringUtil.isNullOrBlank(employee.getLastName())){
//			query.append(" and e.lastName like '");
//			query.append(employee.getLastName());
//			query.append("%' ");
//		}
//		if(!StringUtil.isNullOrBlank(employee.getPhone())){
//			query.append(" and e.phone like '");
//			query.append(employee.getPhone());
//			query.append("%' ");
//		}
		List<Property> employeeList = em.createQuery(query.toString(), Property.class).getResultList();
		return employeeList;
	}

	public boolean deleteProperty(Property employee) throws Exception {
		em.remove(employee);
		em.flush();
		return true;
	}

	
	

	@SuppressWarnings("unchecked")
	public Boolean checkLogin(String username, String password) {

		Query q = em.createQuery("SELECT u FROM Users u WHERE u.userName = "
				+ " '"+username+"' and u.password = '"+password+"' ");
		
		Collection<Users> s = q.getResultList();
		if(s != null && !s.isEmpty()){
			return true;
		}else{
			return false;
		}
	}


}
