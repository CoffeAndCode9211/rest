/**
 * This class models the Role that a user has. The instance of this class
 * is enclosed in the SecurityPrincipal.
 */
package com.webservice.security;

import java.io.Serializable;

/**
 * @author Sushant
 *
 */
public class Role implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Role Identifier
	 */
	private int roleId;
	
	/**
	 * Role name
	 */
	private String roleName;
	public Role()
	{
		
	}
	public Role(int id, String roleName)
	{
		this.roleId = id;
		this.roleName = roleName;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	
}
