package com.webservice.dto;

public class SentimentTO {
	
	private String text;
	private Float score;
	private Float magnitude;
	private String otherDetails;
	
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	public Float getScore() {
		return score;
	}
	public void setScore(Float score) {
		this.score = score;
	}
	public Float getMagnitude() {
		return magnitude;
	}
	public void setMagnitude(Float magnitude) {
		this.magnitude = magnitude;
	}
	public String getOtherDetails() {
		return otherDetails;
	}
	public void setOtherDetails(String otherDetails) {
		this.otherDetails = otherDetails;
	}
	
	
}
