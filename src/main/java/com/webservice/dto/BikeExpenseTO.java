package com.webservice.dto;

public class BikeExpenseTO {

	private Integer id;
	private String fromDate;
	private String toDate;
	
	private String eventDate;

	private String amount;

	private String pricePerLtr;

	private String meterReading;
	
	private String petrolQty;
	
	private String reason;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public String getEventDate() {
		return eventDate;
	}

	public void setEventDate(String eventDate) {
		this.eventDate = eventDate;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getPricePerLtr() {
		return pricePerLtr;
	}

	public void setPricePerLtr(String pricePerLtr) {
		this.pricePerLtr = pricePerLtr;
	}

	public String getMeterReading() {
		return meterReading;
	}

	public void setMeterReading(String meterReading) {
		this.meterReading = meterReading;
	}

	public String getPetrolQty() {
		return petrolQty;
	}

	public void setPetrolQty(String petrolQty) {
		this.petrolQty = petrolQty;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	
	
}
