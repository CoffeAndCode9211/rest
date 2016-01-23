package com.webservice.common;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.resource.factory.SmsFactory;
import com.twilio.sdk.resource.instance.Account;


public class SMSUtilities {

	private static final Logger logger = LoggerFactory.getLogger(SMSUtilities.class);
	public static final String FROM_NO = "(11) 222-3333";
	public static final String ACCOUNT_SID = "AC082b2bfd50f5af12de11f2e4396f1d92";
	public static final String AUTH_TOKEN = "51386ea15177f8090dbce3d9c6f479a6";
	String msgout = "";

	public static void sendSMS() 
	{
		try 
		{
			TwilioRestClient client = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN);

			Account mainAccount = client.getAccount();

			SmsFactory smsFactory = mainAccount.getSmsFactory();

			Map<String, String> smsParams = new HashMap<String, String>();

			smsParams.put("To", "+919911499375");
			smsParams.put("From", FROM_NO);
			smsParams.put("Body", "Just to test SMS");

			 smsFactory.create(smsParams);

		} 
		catch (Exception e) 
		{
				e.printStackTrace();
			logger.error(e.getMessage());    
		}

	}
}




