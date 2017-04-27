package com.webservice.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "BIKE_EXPENSE_DETAILS")
public class BikeExpense implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
	private Integer id;

	@Column(name = "EVENT_DATE")
	private Date eventDate;

	@Column(name = "AMOUNT")
	private String amount;

	@Column(name = "PRICE_PER_LTR")
	private Double pricePerLtr;

	@Column(name = "METER_READING")
	private Integer meterReading;
	
	@Column(name = "PETROL_QTY")
	private Double petrolQty;
	
	@Column(name = "REASON")
	private String reason;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public Double getPricePerLtr() {
		return pricePerLtr;
	}

	public void setPricePerLtr(Double pricePerLtr) {
		this.pricePerLtr = pricePerLtr;
	}

	public Integer getMeterReading() {
		return meterReading;
	}

	public void setMeterReading(Integer meterReading) {
		this.meterReading = meterReading;
	}

	public Double getPetrolQty() {
		return petrolQty;
	}

	public void setPetrolQty(Double petrolQty) {
		this.petrolQty = petrolQty;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

}
