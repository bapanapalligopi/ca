package ChatAnyWhere.chatanywhere.controller;

import ChatAnyWhere.chatanywhere.dto.EmailRequest;
import ChatAnyWhere.chatanywhere.models.Otp;
import ChatAnyWhere.chatanywhere.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:5173/")
public class EmailController {
    @Autowired
    private EmailService emailService;

    @PostMapping("/send-email")
    public void sendEmail(@RequestBody EmailRequest request) {
        emailService.sendEmail(request.getTo(), request.getSubject(), request.getText());
    }
    @PostMapping("/send-otp")
    public ResponseEntity<Otp> sendOtp(@RequestBody EmailRequest request) throws Exception{
       return  emailService.sendOtp(request.getTo(), request.getSubject(), request.getText());
    }

}
