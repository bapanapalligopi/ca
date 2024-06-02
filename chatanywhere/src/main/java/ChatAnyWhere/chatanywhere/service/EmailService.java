package ChatAnyWhere.chatanywhere.service;

import ChatAnyWhere.chatanywhere.exceptions.OtpFailureException;
import ChatAnyWhere.chatanywhere.models.Otp;
import ChatAnyWhere.chatanywhere.models.OtpGenerator;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender javaMailSender;


    @Autowired
    OtpGenerator otpGenerator;
    public void sendEmail(String to, String subject, String text) {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        try {
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text);
            javaMailSender.send(message);
            System.out.println("Email sent successfully");
        } catch (MessagingException e) {
            e.printStackTrace();
            // Handle exception
        }
    }
    public ResponseEntity<Otp> sendOtp(String  to, String subject, String text) throws Exception
    {
        String otp= otpGenerator.generateOTP();
        Otp otp1= Otp.builder().otp(otp).build();
        text=text+"Your otp for signup: "+otp;
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        try {
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text);
            javaMailSender.send(message);
            return new ResponseEntity<>(otp1, HttpStatus.OK);
        } catch (MessagingException e) {
            throw  new OtpFailureException();
        }
    }
}

