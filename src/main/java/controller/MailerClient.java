package controller;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by Maxim on 27.04.2017.
 */
public class MailerClient {
    final String username = "maxim.stukolov@gmail.com";
    final String password = "carter2014!";

    public static Properties props;
    public static Session session;

    public MailerClient() {
        props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
    }

    public void sender(){

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("maxim.stukolov@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("maxim.stukolov@center2m.ru"));
            message.setSubject("Testing Subject");
            message.setText("Dear Mail Crawler,"
                    + "\n\n No spam to my email, please!");

            Transport.send(message);
            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }

    public static void sender(String msg) {
        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("maxim.stukolov@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("maxim.stukolov@center2m.ru"));
            message.setSubject("Smart Cooler Messages");
            message.setText(msg);

            Transport.send(message);

            System.out.println("Smart Cooler Messages was sent");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
