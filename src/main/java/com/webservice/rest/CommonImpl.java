/**
 * Common REST Implementation
 * 
 * Copyright (c) 2013 Edgeware Technologies. All Rights Reserved.
 */
package com.webservice.rest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.Response;

import com.google.api.client.auth.oauth2.AuthorizationCodeRequestUrl;
import com.google.api.client.auth.oauth2.AuthorizationCodeTokenRequest;
import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.auth.oauth2.TokenResponseException;
import com.google.api.client.http.BasicAuthentication;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;

public class CommonImpl implements CommonIf{


	private static final String FITBIT_AUTHORIZE_URL = "https://www.fitbit.com/oauth2/authorize";
	private static final String FITBIT_AUTHORIZE_TOKEN = "https://api.fitbit.com/oauth2/token";
	private static final String FITBIT_CLIENT_ID = "228DJ5";
	private static final String FITBIT_CLIENT_SECRET = "c3c159f4fe751b023649450d6ad76609";
//	private static final String REDIRECTING_URL = "http://demo.ashishkumar.tech/webservice/service.html";
	private static final String REDIRECTING_URL = "http://localhost:8080/webservice/service.html";
	private static final String ALL_SCOPE = "weight activity sleep heartrate location nutrition profile settings social";

	Response.ResponseBuilder builder = null;

	public String getAuthURL() {
		String url = "";
		try{
			Collection<String> scope = new ArrayList<String>();
			scope.add(ALL_SCOPE);
			Collection<String> responseType = new ArrayList<String>();
			responseType.add("code");
			AuthorizationCodeRequestUrl autUrl = new AuthorizationCodeRequestUrl(FITBIT_AUTHORIZE_URL, FITBIT_CLIENT_ID)
			.setRedirectUri(REDIRECTING_URL)
			.setScopes(scope)
			.setResponseTypes(responseType);
			System.out.println("URL data initial:"+autUrl.build());
			url = autUrl.build();
		}catch(Exception e){
			e.printStackTrace();
		}
		return url;
	}


	public Response getDetails(String code) {
		Map<String, String> responseObj = new HashMap<String, String>();
		try {
			TokenResponse response =
					new AuthorizationCodeTokenRequest(new NetHttpTransport()
					, new JacksonFactory(), new GenericUrl(FITBIT_AUTHORIZE_TOKEN), code)
					.setRedirectUri(REDIRECTING_URL).setClientAuthentication(
					new BasicAuthentication(FITBIT_CLIENT_ID, FITBIT_CLIENT_SECRET) )
					.setGrantType("authorization_code").execute();

			if(response != null && response.getAccessToken() != null){
				System.out.println("Access token: " + response.getAccessToken());
				responseObj.put("access_token", response.getAccessToken());
				responseObj.put("expires_in", response.getExpiresInSeconds().toString());
				responseObj.put("refresh_token", response.getRefreshToken());
				responseObj.put("token_type", response.getTokenType());
				builder = Response.status(Response.Status.OK).entity(responseObj);
			}else{
				responseObj.put("Error", "Not able to connect");
				builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
			}
		} catch (TokenResponseException e) {
			if (e.getDetails() != null) {
				System.err.println("Error: " + e.getDetails().getError());
				if (e.getDetails().getErrorDescription() != null) {
					System.err.println(e.getDetails().getErrorDescription());
				}
				if (e.getDetails().getErrorUri() != null) {
					System.err.println(e.getDetails().getErrorUri());
				}
			} else {
				System.err.println(e.getMessage());
			}
			responseObj.put("TokenResponseException", e.getMessage());
			builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
			return builder.build();
		} catch (IOException e) {
			responseObj.put("IOException", e.getMessage());
			builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
			return builder.build();
		}
		return builder.build();
	}


}
