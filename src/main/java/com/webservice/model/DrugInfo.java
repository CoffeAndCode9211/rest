package com.webservice.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "DRUG_INFO")
public class DrugInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
	private Integer id;

	@Column(name = "NAME", length=500)
	private String name;

	@Column(name = "FORM")
	private String form;

	@Column(name = "STANDARDUNITS")
	private String standardUnits;

	@Column(name = "PACKAGEFORM")
	private String pkgForm;
	
	@Column(name = "PRICE")
	private String price;
	
	@Column(name = "SIZE")
	private String size;
	
	@Column(name = "MANUFACTURER")
	private String manufacturer;
	
	@Column(name = "CONSTITUENTS", length=1000)
	private String constituents;
	
	@Column(name = "SCHEDULE")
	private String schedule;
	
	@Column(name = "DRUD_ID" , unique=true)
	private String drugId;

	

	
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




	

	public String getPkgForm() {
		return pkgForm;
	}




	public void setPkgForm(String pkgForm) {
		this.pkgForm = pkgForm;
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




	public String getConstituents() {
		return constituents;
	}




	public void setConstituents(String constituents) {
		this.constituents = constituents;
	}




	public String getSchedule() {
		return schedule;
	}




	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}




	public String getDrugId() {
		return drugId;
	}




	public void setDrugId(String drugId) {
		this.drugId = drugId;
	}




	public String toString(){
		return id+" "+name;
	}
}
