package com.webservice.dto;

public class ActivityLogTO {
	
	String requestURI, userId, userSessionId, cookies, formData, headers, method, requestStartTime, requestStopTime, totalTimeTaken, path;

	public String getRequestURI() {
		return requestURI;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public void setRequestURI(String requestURI) {
		this.requestURI = requestURI;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserSessionId() {
		return userSessionId;
	}

	public void setUserSessionId(String userSessionId) {
		this.userSessionId = userSessionId;
	}

	public String getCookies() {
		return cookies;
	}

	public void setCookies(String cookies) {
		this.cookies = cookies;
	}

	public String getFormData() {
		return formData;
	}

	public void setFormData(String formData) {
		this.formData = formData;
	}

	public String getHeaders() {
		return headers;
	}

	public void setHeaders(String headers) {
		this.headers = headers;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getRequestStartTime() {
		return requestStartTime;
	}

	public void setRequestStartTime(String requestStartTime) {
		this.requestStartTime = requestStartTime;
	}

	public String getRequestStopTime() {
		return requestStopTime;
	}

	public void setRequestStopTime(String requestStopTime) {
		this.requestStopTime = requestStopTime;
	}

	public String getTotalTimeTaken() {
		return totalTimeTaken;
	}

	public void setTotalTimeTaken(String totalTimeTaken) {
		this.totalTimeTaken = totalTimeTaken;
	}
	
}
