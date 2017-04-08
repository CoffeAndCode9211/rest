package com.webservice.rest;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;

/**
 * Created with IntelliJ IDEA.
 * User: usta
 * Date: 07.04.2013
 * Time: 10:46
 * To change this template use File | Settings | File Templates.
 */
@Stateless
public class Chat implements ChatIf {

    final static Map<String, AsyncResponse> waiters = new ConcurrentHashMap<String, AsyncResponse>();
    final static ExecutorService ex = Executors.newSingleThreadExecutor();

    public void hangUp(@Suspended AsyncResponse asyncResp, @PathParam("assignedTo") String nick) {

        waiters.put(nick, asyncResp);
    }

    public String sendMessage(final @PathParam("nick") String nick, final String message) {

        ex.submit(new Runnable() {
            public void run() {
                Set<String> nicks = waiters.keySet();
                for (String n : nicks) {
                    // Sends message to all, except sender
                    if (!n.equalsIgnoreCase(nick))
                        waiters.get(n).resume(nick + " said that: " + message);
                }
            }
        });

        return "Message is sent..";
    }
}

