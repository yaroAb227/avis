package tech.codus.springSecurity.repository;

import org.springframework.data.repository.CrudRepository;
import tech.codus.springSecurity.entity.Validation;

import java.util.Optional;

public interface ValidationRepository extends CrudRepository<Validation,Integer> {
    Optional<Validation> findByCode(String code);
}
