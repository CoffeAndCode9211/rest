package com.webservice.service;

/**
* BikeExpense Service Interface 
*
* @author  Ashish Kumar
* @version 1.0
* @since   2015-08-19 
*/
import java.util.List;

import com.webservice.dto.BikeExpenseTO;
import com.webservice.model.BikeExpense;

public interface BikeExpenseEJBIf {

	/**
	 * Add an BikeExpense to Database
	 * @param BikeExpense
	 * @return boolean
	 * @throws Exception
	 */
	public boolean addBikeExpense(BikeExpense BikeExpense) throws Exception;
	
	/**
	 * Update an BikeExpense to Database
	 * @param BikeExpense
	 * @return boolean
	 * @throws Exception
	 */
	public boolean updateBikeExpense(BikeExpense BikeExpense) throws Exception;
	
	/**
	 * Get an BikeExpense by primary key
	 * @param id
	 * @return BikeExpense
	 * @throws Exception
	 */
	public BikeExpense getBikeExpenseById(Integer id) throws Exception;
	
	/**
	 * Filter BikeExpense by various Attributes
	 * @param BikeExpense
	 * @return List<BikeExpense>
	 * @throws Exception
	 */
	public List<BikeExpense> getBikeExpensesByFilter(BikeExpenseTO bikeExpenseTO) throws Exception;
	
	/**
	 * Delete an BikeExpense from Database
	 * @param id
	 * @return boolean
	 * @throws Exception
	 */
	public boolean deleteBikeExpense(BikeExpense BikeExpense) throws Exception;
	

	
}
