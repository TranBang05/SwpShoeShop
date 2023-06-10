package Model;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;


import java.util.Properties;
import java.util.Random;

public class SendMail {
    public String getRandom(){
        Random rnd = new Random();
        int n=rnd.nextInt(999999);
        return String.format("%06d",n);
    }
    public boolean sendEmail(User user){
        boolean test=false;
        String toEmail=user.getEmail();
        String fromEmail="tranxuanbang.yt@gmail.com";
        String password="bang0501@b";
        try{
            Properties pr =new Properties();
            pr.setProperty("mail.smtp.host","smtp.mail.com");
            pr.setProperty("mail.smtp.port","587");
            pr.setProperty("mail.smtp.auth","true");
            pr.setProperty("mail.smtp.starttls.enable","true");
            pr.put("mail.smtp.socketFactory.port","587");
            pr.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");

            Session session = Session.getInstance(pr, new jakarta.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(fromEmail, password);
                }
            });

            Message mess= new MimeMessage(session);
            mess.setRecipient(Message.RecipientType.TO,new InternetAddress(toEmail));
            mess.setSubject("User Email Verification");
            mess.setText("Registerred successfully. Please verify your accout using this code");
            Transport.send(mess);
            test=true;

        }catch (Exception e){
            e.printStackTrace();
        }

        return test;
    }
}
