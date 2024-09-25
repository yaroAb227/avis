package tech.codus.springSecurity.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.codus.springSecurity.entity.User;
import tech.codus.springSecurity.service.UserService;

import java.util.Map;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    private final UserService userService;
    @PostMapping(path = "inscription")
    public void inscription(@RequestBody User user){
        log.info("Inscription");
        this.userService.inscription(user);

    }
    @PostMapping(path = "activation")
    public void activation(@RequestBody Map<String,String> activation){
        log.info("activation");
        this.userService.activation(activation);
        log.info("Utilisateur Activé");

    }
}
