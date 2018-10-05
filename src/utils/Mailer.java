package utils;




/**
 * PASSWORD:SHARUKH5$
 * USERNAME:sdsharukh9@gmail.com
 * 
 */

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mailer {

	public static boolean sendMail(String toEmailAddress, String EmailContent) {

		final String username = "sdsharukh9@gmail.com";
		final String password = "SHARUKH5$";
		boolean result=false;

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
			message.setFrom(new InternetAddress("sdsharukh9@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(toEmailAddress));
			message.setSubject("Mail from Susanto Logistics LTD.");
			message.setText(EmailContent);

			Transport.send(message);

			return true;

		} catch (MessagingException e) {
			return result;
		}
	}
}