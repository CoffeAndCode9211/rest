package com.webservice.security;

import java.io.Serializable;
import java.security.Principal;
import java.util.List;

import javax.security.auth.Subject;

/**
 * 
 * @author ashishkumar
 *
 */
public class SecurityPrincipal implements Principal,Serializable{

	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	private List<Role> lstRole;
	private Subject subj;	
	public SecurityPrincipal(String name) {
		this.username = name;
	}
	public SecurityPrincipal(String name, String verifyCode, List<Role> lstRole) {
		this.username = name;
		this.password = verifyCode;
		this.lstRole = lstRole;
	}


	public String getName() {
		return username;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public List<Role> getLstRole() {
		return lstRole;
	}
	public void setLstRole(List<Role> lstRole) {
		this.lstRole = lstRole;
	}
	public Subject getSubj() {
		return subj;
	}
	public void setSubj(Subject subj) {
		this.subj = subj;
	}

}
