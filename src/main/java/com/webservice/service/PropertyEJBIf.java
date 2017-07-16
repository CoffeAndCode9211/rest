package com.webservice.service;

/**
* Property Service Interface 
*
* @author  Ashish Kumar
* @version 1.0
* @since   2015-08-19 
*/
import java.util.List;

import com.webservice.model.Property;

public interface PropertyEJBIf {

	/**
	 * Add an Property to Database
	 * @param employee
	 * @return boolean
	 * @throws Exception
	 */
	public boolean addProperty(Property employee) throws Exception;
	
	/**
	 * Update an Property to Database
	 * @param employee
	 * @return boolean
	 * @throws Exception
	 */
	public boolean updateProperty(Property employee) throws Exception;
	
	/**
	 * Get an Property by primary key
	 * @param id
	 * @return Property
	 * @throws Exception
	 */
	public Property getPropertyById(Integer id) throws Exception;
	
	/**
	 * Filter Property by various Attributes
	 * @param employee
	 * @return List<Property>
	 * @throws Exception
	 */
	public List<Property> getPropertysByFilter(Property employee) throws Exception;
	
	/**
	 * Delete an Property from Database
	 * @param id
	 * @return boolean
	 * @throws Exception
	 */
	public boolean deleteProperty(Property employee) throws Exception;
	
	/**
	 * Check user login
	 * @param username
	 * @param password
	 * @return
	 */
	public Boolean checkLogin(String username, String password);
	
	
}
