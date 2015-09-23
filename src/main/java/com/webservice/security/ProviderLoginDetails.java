package com.webservice.security;

public class ProviderLoginDetails {
	private int providerId;
	private String accessCode;
	
	public ProviderLoginDetails()
	{
		
	}

	public int getProviderId() {
		return providerId;
	}

	public void setProviderId(int providerId) {
		this.providerId = providerId;
	}

	public String getAccessCode() {
		return accessCode;
	}

	public void setAccessCode(String accessCode) {
		this.accessCode = accessCode;
	}
	
}
