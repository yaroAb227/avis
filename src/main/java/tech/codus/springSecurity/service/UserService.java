package tech.codus.springSecurity.service;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import tech.codus.springSecurity.TypeRole;
import tech.codus.springSecurity.entity.Role;
import tech.codus.springSecurity.entity.User;
import tech.codus.springSecurity.entity.Validation;
import tech.codus.springSecurity.repository.UserRepository;

import java.time.Instant;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UserService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;
    private ValidationService validationService;
    public void inscription (User user){

        if(!user.getEmail().contains("@")){
            throw  new RuntimeException("Mail invalide");
        }
        Optional<User> userOptional = this.userRepository.findByEmail(user.getEmail());
        if(userOptional.isPresent()){
            throw  new RuntimeException("Votre email est déjà utilisé");
        }
        String pwdencode = this.passwordEncoder.encode(user.getPassword());
        user.setPassword(pwdencode);

        Role role = new Role();
        role.setLibelle(TypeRole.UTILISATEUR);
        user.setRole(role);
        User savedUser = this.userRepository.save(user);

        this.validationService.create(savedUser);

    }

    public void activation(Map<String, String> activation) {

        Validation validation = this.validationService.findByCode(activation.get("code"));
        if(Instant.now().isAfter(validation.getExpiredAt())){
            throw new RuntimeException("Votre code d'activation a expiré");
        }
        User user = this.userRepository.findById(validation.getUser().getId()).orElseThrow(
                () -> new RuntimeException("Utilisateur inconnu")
        );

        this.validationService.activate(validation);
        user.setActif(true);
        this.userRepository.save(user);


    }
}
