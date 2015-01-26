package com.forms.server.common;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.forms.server.dto.AppuserTO;
import com.forms.server.exception.ApplicationException;

/**
 * 
 * @author vikash
 * 
 */
public class CommonMethods {
	private static final Logger logger = LoggerFactory
			.getLogger(CommonMethods.class);
	private static final ObjectMapper jsonMapper = new ObjectMapper();

	public static boolean isEmpty(Integer value) {
		boolean flag = true;
		// TODO return false in case of null also
		if (value != null && value > 0) {
			flag = false;
		}
		return flag;
	}

	public static boolean isEmpty(String name) {
		boolean flag = true;
		if (name != null && !name.trim().isEmpty()) {
			flag = false;
		}
		return flag;
	}

	public static String toJson(Object object) {
		try {
			return null;
		} catch (Exception e) {
			return null;
		}
	}

	public static <T> T fromJson(String json, Class<T> clazz) {
		try {
			return jsonMapper.readValue(json, clazz);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String encriptedPassword(String password) {
		String generatedPassword = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(password.getBytes());
			byte[] bytes = md.digest();
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) {
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16)
						.substring(1));
			}
			generatedPassword = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return generatedPassword;
	}

	public static String generateActivitionKey(String token,HttpServletRequest request) {
		String url=request.getRequestURL().toString();
		url=url+"/"+token;
		return url;
	}
	public static String generateRandomNumber(){
		Random r = new Random();
		return Long.toString(Math.abs(r.nextLong()), 64);
	}

	public static void sendMail(AppuserTO appuserTO, String activationToken,HttpServletRequest request) throws UnknownHostException, ApplicationException {
		final String username = "vikashmca07@gmail.com";
		final String password = "";//need to set
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("no-reply@form.com"));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(appuserTO.getUsername()));
			message.setSubject("Account activation email ");
			message.setText("Hi "+appuserTO.getFirstName()+",\n\t Please click on mention link to activate your account "+activationToken);
 
			Transport.send(message);
			logger.debug("Email Sent successfully ....");
		} catch (Exception e) {
			throw new ApplicationException("Email Sending failed",e);
		}
	}
}
