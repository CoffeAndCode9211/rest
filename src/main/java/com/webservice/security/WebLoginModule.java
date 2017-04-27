
package com.webservice.security;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.Principal;
import java.security.acl.Group;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.security.auth.Subject;
import javax.security.auth.login.LoginException;

import org.jboss.security.SimpleGroup;
import org.jboss.security.auth.spi.UsernamePasswordLoginModule;

import com.webservice.service.EmployeeEJBIf;


/**
 * @author Ashish
 *
 */
public class WebLoginModule extends UsernamePasswordLoginModule{

	private SecurityPrincipal securityPrincipal = null;
	
	@Override
	protected boolean validatePassword(String username, String password)
	{
		boolean status = false;	
		Principal p = this.getIdentity();
		Subject sub = new Subject();

		if(password==null){
			password = username;
			username = p.getName();
		}
		if(p instanceof SecurityPrincipal) {					  

			try {
				securityPrincipal = (SecurityPrincipal)p;
				securityPrincipal.setUsername(new Date().toString());
				securityPrincipal.setPassword(password);				
				securityPrincipal.setSubj(sub);	
				securityPrincipal.setLstRole(null);
				status = isValidUser(username, password);
			}
			catch(Exception e) {
				System.out.println("Error: "+e);
			}
		}
		return status;		
	}

	@Override
	protected String getUsersPassword() throws LoginException {
		return null;
	}

	public boolean isValidUser(String username, String password)  {
		boolean result=false;
		try{
			final Hashtable<String, String> jndiProperties = new Hashtable<String, String>();
			jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
			final Context context = new InitialContext(jndiProperties);
			EmployeeEJBIf lif = (EmployeeEJBIf) context.lookup("java:global/webservice/EmployeeEJBImpl!com.webservice.service.EmployeeEJBIf");
			password=hashPassword(password);
			result = lif.checkLogin(username, password);
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return result;
	}


	@Override
	protected Group[] getRoleSets() throws LoginException {
		try {			
			Group callerPrincipal = new SimpleGroup("CallerPrincipal");
			Group roles = new SimpleGroup("Roles");
			Group[] groups = {roles,callerPrincipal};	        
			roles.addMember(new SecurityPrincipal("SecurityAdmin"));
			callerPrincipal.addMember(securityPrincipal);
			return groups;
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new LoginException(e.getMessage());
		}

	}
	private String hashPassword(String password) {
		String hashword = null;
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");            
			md5.update(password.getBytes());
			BigInteger hash = new BigInteger(1, md5.digest());
			hashword = hash.toString(16);
		}catch (Exception e) {
			e.printStackTrace();
		}	        
		return hashword;
	}



}
