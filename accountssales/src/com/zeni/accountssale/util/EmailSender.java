package com.zeni.accountssale.util;


import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailSender {
    
    public static void sendEmail(String recipientEmail, String subject, String messageText) {
        
        String host = "sandbox.smtp.mailtrap.io";
        int port = 2525;
        String username = "47fc82bd4d3b02";
        String password = "77000f8725a87d";
        
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", String.valueOf(port));
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        
        // Створення сеансу
        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        
        try {
            // Створення об'єкта MimeMessage
            MimeMessage message = new MimeMessage(session);
            // Встановлення параметрів листа
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject(subject);
            message.setText(messageText);
            
            // Відправлення листа
            Transport.send(message);
            System.out.println("Лист відправлено успішно.");
            
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    
    
}
