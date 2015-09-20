package com.webservice.service;

/**
* Employee Service Interface 
*
* @author  Ashish Kumar
* @version 1.0
* @since   2015-08-19 
*/
import java.util.List;

import com.webservice.model.Employee;

public interface EmployeeEJBIf {

	/**
	 * Add an Employee to Database
	 * @param employee
	 * @return boolean
	 * @throws Exception
	 */
	public boolean addEmployee(Employee employee) throws Exception;
	
	/**
	 * Update an Employee to Database
	 * @param employee
	 * @return boolean
	 * @throws Exception
	 */
	public boolean updateEmployee(Employee employee) throws Exception;
	
	/**
	 * Get an Employee by primary key
	 * @param id
	 * @return Employee
	 * @throws Exception
	 */
	public Employee getEmployeeById(Integer id) throws Exception;
	
	/**
	 * Filter Employee by various Attributes
	 * @param employee
	 * @return List<Employee>
	 * @throws Exception
	 */
	public List<Employee> getEmployeesByFilter(Employee employee) throws Exception;
	
	/**
	 * Delete an Employee from Database
	 * @param id
	 * @return boolean
	 * @throws Exception
	 */
	public boolean deleteEmployee(Employee employee) throws Exception;
}
