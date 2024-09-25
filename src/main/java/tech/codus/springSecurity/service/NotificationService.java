package tech.codus.springSecurity.service;

import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import tech.codus.springSecurity.entity.Validation;

@AllArgsConstructor
@Service
public class NotificationService {
    JavaMailSender javaMailSender;
    public void send (Validation validation){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("no-reply@yaro.tech");
        message.setTo(validation.getUser().getEmail());
        message.setSubject("Votre code d'activation");
        String mailMessage = String.format("Bonjour %s Votre code d'activation est %s; A bientôt",
                validation.getUser().getUsername(),
                validation.getCode()
        );
        message.setText(mailMessage);
        javaMailSender.send(message);

    }
}
