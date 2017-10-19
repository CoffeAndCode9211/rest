package com.webservice.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.webservice.dto.SentimentTO;

@Path("/gca")
public interface SentimentAnalysisIf {

	
	@GET
	@Path("/sentiment")
	@Produces({MediaType.APPLICATION_JSON})
	public SentimentTO getSentimentAnalysisScore(@QueryParam("text")String text);
	
}
