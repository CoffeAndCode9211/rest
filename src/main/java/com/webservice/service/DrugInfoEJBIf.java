package com.webservice.service;

import java.util.List;

import com.webservice.model.DrugInfo;

public interface DrugInfoEJBIf {

	public boolean addDrugInfo(DrugInfo DrugInfo) throws Exception;

	public boolean updateDrugInfo(DrugInfo DrugInfo) throws Exception;

	public DrugInfo getDrugInfoById(Integer id) throws Exception;

	public List<DrugInfo> getDrugInfosByFilter(String drugName) throws Exception;

}
