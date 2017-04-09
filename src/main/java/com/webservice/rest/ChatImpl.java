package com.webservice.rest;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.ejb.Stateless;
import javax.ws.rs.container.AsyncResponse;

@Stateless
public class ChatImpl implements ChatIf {

	final static Map<String, AsyncResponse> waiters = new ConcurrentHashMap<String, AsyncResponse>();
	final static ExecutorService ex = Executors.newSingleThreadExecutor();

	public void addUser(AsyncResponse asyncResp, String userName) {

		waiters.put(userName, asyncResp);
	}

	public String sendMessage(final String fromuserName, final String message,
			final String sendtoall, final String tousername) {
			String msg = "";
		try {
			ex.submit(new Runnable() {
				public void run() {
					Set<String> lstUsers = waiters.keySet();
					if(sendtoall != null && sendtoall.equalsIgnoreCase("Y")){
						for (String user : lstUsers) {
							if (!user.equalsIgnoreCase(fromuserName)){
								waiters.get(tousername).resume(fromuserName + " said that: " + message);
							}
						}
					}else{
						waiters.get(tousername).resume(fromuserName + " said that: " + message);
					}
					
				}
			});
			msg = "Messge Sent Successfully.";
		} catch (Exception e) {
			System.out.println("Error: "+e);
		}
		return msg;
	}
}

