package OTPSendMail;

import Model.User;
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
        String password="zptgaabhvfqmsvnw";
        try{
            String host = "smtp.gmail.com";
            String port = "587";

            Properties pr= new Properties();
            pr.setProperty("mail.smtp.host", host);
            pr.setProperty("mail.smtp.auth", "true");
            pr.setProperty("mail.smtp.port", port);
            pr.setProperty("mail.smtp.starttls.enable", "true");

            pr.put("mail.smtp.socketFactory.port",port);
            pr.put("mail.smtp.socketFactory.port.class","jakarta.net.ssl.SSLSocketFactory");


            Session session = Session.getInstance(pr, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(fromEmail, password);
                }
            });

            Message mess= new MimeMessage(session);
            mess.setRecipient(Message.RecipientType.TO,new InternetAddress(toEmail));
            mess.setSubject("User Email Verification");
            mess.setText("Registerred successfully. Please verify your accout using this code: "+user.getCode());
            Transport.send(mess);
            System.out.println("Send mail success");
            test=true;

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return test;
    }
}
