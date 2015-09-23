package com.webservice.security;


import java.io.IOException;
import java.security.Principal;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class LogoutFilter
 */
public class LogoutFilter implements Filter {

    /**
     * Default constructor. 
     */
    public LogoutFilter() {
        // TODO Auto-generated constructor stub
    	System.out.println("inside logout filter");
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		System.out.println("checking filter for each rest call");
		// pass the request along the filter chain
		HttpServletRequest req = (HttpServletRequest) request;
		Principal p = req.getUserPrincipal();
		if (p == null || !(p instanceof SecurityPrincipal))
		{
			HttpServletResponse resp = (HttpServletResponse)response;
			resp.sendRedirect("/login.jsp");
			return ;
		}
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}

