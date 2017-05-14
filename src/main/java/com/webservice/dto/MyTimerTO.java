package com.webservice.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class MyTimerTO {

	private Integer id;

	@NotNull
	@Size(min = 1, message = "Please select Date")
	private String startDate;

	@NotNull
	@Size(min = 1, message = "Please select Reason")
	private String reason;

	private String status;
	
	private String totalTime;
	private String nextOccTime;
	
	
	
	
	public Integer getId() {
		return id;
	}




	public void setId(Integer id) {
		this.id = id;
	}




	public String getStartDate() {
		return startDate;
	}




	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}




	public String getReason() {
		return reason;
	}




	public void setReason(String reason) {
		this.reason = reason;
	}




	public String getStatus() {
		return status;
	}




	public void setStatus(String status) {
		this.status = status;
	}




	public String getTotalTime() {
		return totalTime;
	}




	public void setTotalTime(String totalTime) {
		this.totalTime = totalTime;
	}




	public String getNextOccTime() {
		return nextOccTime;
	}




	public void setNextOccTime(String nextOccTime) {
		this.nextOccTime = nextOccTime;
	}




	@Override
	public String toString() {
		return startDate+" : "+reason+" : "+status;
	}
}
