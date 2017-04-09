package com.webservice.rest;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;

import org.apache.commons.io.IOUtils;
import org.jboss.resteasy.spi.ResteasyProviderFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import com.webservice.dto.ActivityLogTO;
import com.webservice.service.CommonEJBIf;

@Provider
public class RestActivityLogFilter implements ContainerRequestFilter {

	private static final Logger LOGGER = LoggerFactory.getLogger(RestActivityLogFilter.class);

	private boolean loggingEnabled = true;

	@Override
	public void filter( ContainerRequestContext requestContext) {

		System.out.println("rest activity log");
	
		if(loggingEnabled){

			MDC.put("IP", "Ashishss");
			MDC.put("start-time", String.valueOf(System.currentTimeMillis()));		
			MDC.put("requestURI",requestContext.getUriInfo().getRequestUri().toString());
			MDC.put("cookies",requestContext.getCookies().toString());
			MDC.put("path",requestContext.getUriInfo().getPath());

			try {
				ByteArrayInputStream resettableIS = toResettableStream(requestContext.getEntityStream());
				InputStream is = resettableIS;
				String q = IOUtils.toString(is);
				LOGGER.info("Dataaaa: "+q);
				MDC.put("formdata",q);
				resettableIS.reset();
				requestContext.setEntityStream(resettableIS);
			} catch (Exception e) {
			}
			MDC.put("headers",requestContext.getHeaders().toString());
			MDC.put("method",requestContext.getMethod().toString());
			LOGGER.debug("This is test Data");
			addData();

		}
	}

	private ByteArrayInputStream toResettableStream(InputStream entityStream) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len;
		while ((len = entityStream.read(buffer)) > -1) {
			baos.write(buffer, 0, len);
		}
		baos.flush();
		return new ByteArrayInputStream(baos.toByteArray());
	}

//	@Override
//	public void filter( ContainerRequestContext requestContext,
//			ContainerResponseContext responseContext) throws IOException {
//
//		if(loggingEnabled){
//			LOGGER.info("Data: "+requestContext.getUriInfo().getRequestUri().toString());
//			String reqUrl = MDC.get("requestURI");
//			String cookies = MDC.get("cookies");
//			String formdata = MDC.get("formdata");
//			String headers = MDC.get("headers");
//			String methods = MDC.get("method");
//			String path = MDC.get("path");
//			long stopTime = System.currentTimeMillis();
//			String stopTimee = stopTime+"";
//
//			long executionTime=0;		
//			if (null != stTime && stTime.length() > 0) {
//				long startTime = Long.parseLong(stTime);
//				executionTime = System.currentTimeMillis() - startTime;
//			}
//			String totalTime = executionTime+"";
//			LOGGER.info("reqUrl: "+reqUrl+" cookies:"+cookies);
//			LOGGER.info("reqUrl: "+formdata);
//			MDC.clear();
//		}
//
//	}

	public ActivityLogTO addData(){
		String stTime = MDC.get("start-time");
		ActivityLogTO activityLogTo = new ActivityLogTO();
		activityLogTo.setRequestStartTime(stTime);
		activityLogTo.setRequestURI(MDC.get("requestURI"));
		activityLogTo.setCookies(MDC.get("cookies"));
		activityLogTo.setFormData(MDC.get("formdata"));
		activityLogTo.setHeaders(MDC.get("headers"));
		activityLogTo.setMethod(MDC.get("method"));
		activityLogTo.setPath(MDC.get("path"));
		long stopTime = System.currentTimeMillis();
		activityLogTo.setRequestStopTime(stopTime+"");
		long executionTime=0;		
		if (null != stTime && stTime.length() > 0) {
			long startTime = Long.parseLong(stTime);
			executionTime = System.currentTimeMillis() - startTime;
		}
		activityLogTo.setTotalTimeTaken(executionTime+"");
		saveActivityLog(activityLogTo);
		return activityLogTo;
	}
	
	

	public void saveActivityLog(ActivityLogTO activitLogTo){
		try{
			CommonEJBIf auditif=null;

			final Hashtable jndiProperties = new Hashtable();
			jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
			final Context context = new InitialContext(jndiProperties);
			auditif = (CommonEJBIf) context.lookup("java:app/webservice/CommonEJBImpl");

			// not able to inject EJB here
			HttpServletRequest hr = ResteasyProviderFactory.getContextData(HttpServletRequest.class);
			String sessionId = hr.getSession().getId();
			auditif.activityLogIt(activitLogTo);

		}catch(NameNotFoundException nme){
			return;
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	
}
