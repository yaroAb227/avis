package tech.codus.springSecurity.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tech.codus.springSecurity.entity.User;
import tech.codus.springSecurity.entity.Validation;
import tech.codus.springSecurity.repository.ValidationRepository;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Optional;
import java.util.Random;

@AllArgsConstructor
@Service
public class ValidationService {
    private ValidationRepository validationRepository;
    private NotificationService notificationService;
    public void create(User user){

        Instant createdAt = Instant.now();
        Instant expiredAt = createdAt.plus(10, ChronoUnit.MINUTES);
        Random random = new Random();
        int randomInteger = random.nextInt(9999999);
        String code = String.format("%06d",randomInteger);
        Validation validation = new Validation();
        validation.setUser(user);
        validation.setCreatedAt(createdAt);
        validation.setExpiredAt(expiredAt);
        validation.setCode(code);
        Validation savedValidation = this.validationRepository.save(validation);

        this.notificationService.send(savedValidation);

    }

    public Validation findByCode(String code){
        return this.validationRepository.findByCode(code).orElseThrow(
                ()-> new RuntimeException("Votre code est valide")
        );


    }

    public void activate(Validation validation) {
        validation.setActivatedAt(Instant.now());
        this.validationRepository.save(validation);

    }
}
