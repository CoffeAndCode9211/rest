package com.webservice.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.webservice.model.Employee;

/**
 * Employee Service Implementation 
 *
 * @author  Ashish Kumar
 * @version 1.0
 * @since   2015-08-19 
 */

@Stateless
public class EmployeeEJBImpl implements EmployeeEJBIf {

	@PersistenceContext(unitName = "webUnit" )
	private EntityManager em;

	private static final Logger logger = LoggerFactory
			.getLogger(EmployeeEJBImpl.class);

	public boolean addEmployee(Employee employee) throws Exception {
		em.persist(employee);
		em.flush();
		return true;
	}

	public boolean updateEmployee(Employee employee) throws Exception {
		em.merge(employee);
		em.flush();
		return true;
	}

	public Employee getEmployeeById(Integer id) throws Exception {
		return em.find(Employee.class, id);
	}

	public List<Employee> getEmployeesByFilter(Employee employee)
			throws Exception {
		StringBuilder query = new StringBuilder();
		query.append("from Employee e where 1=1 ");
		if(!isNullOrEmpty(employee.getEmail())){
			query.append(" and e.email like '");
			query.append(employee.getEmail());
			query.append("%' ");
		}
		if(!isNullOrEmpty(employee.getFirstName())){
			query.append(" and e.firstName like '");
			query.append(employee.getFirstName());
			query.append("%' ");
		}
		if(!isNullOrEmpty(employee.getLastName())){
			query.append(" and e.lastName like '");
			query.append(employee.getLastName());
			query.append("%' ");
		}
		if(!isNullOrEmpty(employee.getPhone())){
			query.append(" and e.phone like '");
			query.append(employee.getPhone());
			query.append("%' ");
		}
		List<Employee> employeeList = em.createQuery(query.toString(), Employee.class).getResultList();
		return employeeList;
	}

	public boolean deleteEmployee(Employee employee) throws Exception {
		em.remove(employee);
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
