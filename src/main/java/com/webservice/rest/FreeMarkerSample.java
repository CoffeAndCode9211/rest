package com.webservice.rest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.webservice.dto.EmployeeTO;

public class FreeMarkerSample extends HttpServlet {

	private static final long serialVersionUID = 1L;
    
    private static List<EmployeeTO> userList = new ArrayList<EmployeeTO>();
     
    static {
        userList.add(new EmployeeTO("Bill", "Gates"));
        userList.add(new EmployeeTO("Steve", "Jobs"));
        userList.add(new EmployeeTO("Larry", "Page"));
        userList.add(new EmployeeTO("Sergey", "Brin"));
        userList.add(new EmployeeTO("Larry", "Ellison"));
    }
    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("users", userList);
         
        request.getRequestDispatcher("/sample.ftl").forward(request, response);
     
    }
    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
         
        if(null != firstname && null != lastname
                && !firstname.isEmpty() && !lastname.isEmpty()) {
             
            synchronized (userList) {
                userList.add(new EmployeeTO(firstname, lastname));
            }
             
        }
         
        doGet(request, response);
    }
    
}
