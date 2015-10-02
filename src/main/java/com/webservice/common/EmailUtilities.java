package com.webservice.common;

import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.webservice.dto.MessageTO;

public class EmailUtilities {

	private static final Logger logger = LoggerFactory.getLogger(EmailUtilities.class);
	private static final String SMTP_AUTH_USER = "ashish@etipl.com";
	private static final String SMTP_AUTH_PWD = "ashish123";
	private static final String SMTP_PORT = "25"; 
	private static final String SMTP_HOST = "mail.etipl.com";


	public static Boolean sendEmail(MessageTO messageTransportTO){
		try{
			
			Properties properties = System.getProperties();
			properties.setProperty("mail.smtp.host", SMTP_HOST);
			properties.setProperty("mail.user", SMTP_AUTH_USER);
			properties.put("mail.smtp.auth", "true");
			properties.put("mail.smtp.user", SMTP_AUTH_USER);
			properties.put("mail.smtp.password", SMTP_AUTH_PWD);
			properties.put("mail.smtp.port", SMTP_PORT );

			Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(SMTP_AUTH_USER, SMTP_AUTH_PWD);
				}
			});

			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(messageTransportTO.getFromEmail()));
			message.addRecipients(Message.RecipientType.TO, messageTransportTO.getToEmail());
			message.setSubject(messageTransportTO.getSubject());
			BodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setText(messageTransportTO.getMsgBody());
			messageBodyPart.setContent("<html>"+messageTransportTO.getMsgBody()+"</html>","text/html");
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);
			message.setContent(multipart );
			Transport.send(message);  
			
		}catch(Exception e){
			logger.error(e.getMessage());
			return false;
		}
		return true;
	}

}
