package com.webservice.model;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Edgeware
 */
@Entity
@Table(name = "REST_ACTIVITY_LOG")
public class RestActivityLog implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false, unique=true)
    private Integer id;
    @Column(name = "PATH", length=255)
    private String Path;
    @Column(name = "METHOD", length = 10)
    private String method;
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Users userId;
    @Column(name = "REQUEST_URI", columnDefinition ="LONGTEXT")
    private String requestURI;
    @Column(name = "COOKIES", columnDefinition ="LONGTEXT")
    private String cookies;
    @Column(name = "FORM_DATA", columnDefinition ="LONGTEXT")
    private String formData;
    @Column(name = "HEADERS", columnDefinition ="LONGTEXT")
    private String headers;
    @Basic(optional = false)
    @Column(name = "LOG_TIME", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date logTime;
    @Column(name = "REQUEST_START_TIME", length = 20)
    private String requestStartTime;
    @Column(name = "REQUEST_STOP_TIME", length = 20)
    private String requestStopTime;
    @Column(name = "TOTAL_TIME_TAKEN", length = 20)
    private String totalTimeTaken;    
    
    public RestActivityLog(Integer id) {
        this.id = id;
    }
    public RestActivityLog() {        
    }

    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPath() {
		return Path;
	}
	public void setPath(String path) {
		Path = path;
	}
	public String getRequestURI() {
		return requestURI;
	}

	public void setRequestURI(String requestURI) {
		this.requestURI = requestURI;
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

	public Date getLogTime() {
		return logTime;
	}

	public void setLogTime(Date logTime) {
		this.logTime = logTime;
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


	public Users getUserId() {
		return userId;
	}

	public void setUserId(Users userId) {
		this.userId = userId;
	}

	@Override
    public String toString() {
        return "com.webservice.model.RestActivityLog[ id=" + id + " ]";
    }
    
}

