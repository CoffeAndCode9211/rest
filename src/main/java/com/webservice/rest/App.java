package com.webservice.rest;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * Created with IntelliJ IDEA.
 * User: usta
 * Date: 07.04.2013
 * Time: 10:44
 * To change this template use File | Settings | File Templates.
 */
@ApplicationPath("app")
public class App extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> sets=new HashSet<Class<?>>();
        sets.add(Chat.class);
        return sets;
    }
}
