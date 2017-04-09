package com.webservice.rest;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;


@ApplicationPath("rest/registerpooling")
public class PoolingContainer extends Application {
    @Override
    public Set<Class<?>> getClasses() {
    	Set<Class<?>> sets=new HashSet<Class<?>>();
        sets.add(ChatImpl.class);
        return sets;
        
    }
}
