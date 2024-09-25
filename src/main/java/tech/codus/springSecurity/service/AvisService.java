package tech.codus.springSecurity.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tech.codus.springSecurity.entity.Avis;
import tech.codus.springSecurity.repository.AvisRepository;


@AllArgsConstructor
@Service
public class AvisService {
    private final AvisRepository avisRepository;

    public void create(Avis avis){
        this.avisRepository.save(avis);
    }

}
