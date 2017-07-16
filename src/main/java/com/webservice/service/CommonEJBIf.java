package com.webservice.service;

import java.util.List;

import com.webservice.dto.ActivityLogTO;
import com.webservice.model.Property;

public interface CommonEJBIf {

	public void activityLogIt(ActivityLogTO activityLogTO);
	
	public List<Property> getData();
	
}
