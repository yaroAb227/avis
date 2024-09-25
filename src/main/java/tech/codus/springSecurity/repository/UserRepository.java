package tech.codus.springSecurity.repository;

import org.springframework.data.repository.CrudRepository;
import tech.codus.springSecurity.entity.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User,Integer> {
    Optional<User> findByEmail(String email);
}
