package com.webservice.dto;

import java.util.List;

public class DrugInfoTO {

	private Integer id;
	private String name;
	private String form;
	private String standardUnits;
	private String packageForm;
	private String price;
	private String size;
	private String manufacturer;
	private List<ConstituentsTO> constituents;
	private ScheduleTO schedule;
	private String medicine_id;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getForm() {
		return form;
	}
	public void setForm(String form) {
		this.form = form;
	}
	public String getStandardUnits() {
		return standardUnits;
	}
	public void setStandardUnits(String standardUnits) {
		this.standardUnits = standardUnits;
	}
	
	
	public String getPackageForm() {
		return packageForm;
	}
	public void setPackageForm(String packageForm) {
		this.packageForm = packageForm;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	
	public List<ConstituentsTO> getConstituents() {
		return constituents;
	}
	public void setConstituents(List<ConstituentsTO> constituents) {
		this.constituents = constituents;
	}
	public ScheduleTO getSchedule() {
		return schedule;
	}
	public void setSchedule(ScheduleTO schedule) {
		this.schedule = schedule;
	}
	public String getMedicine_id() {
		return medicine_id;
	}
	public void setMedicine_id(String medicine_id) {
		this.medicine_id = medicine_id;
	}
	

	
	
}
