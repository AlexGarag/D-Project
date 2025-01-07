package by.tms.d_project.service;

import by.tms.d_project.entity.Creek;
import by.tms.d_project.repository.CreekRepository;
import org.springframework.stereotype.Service;

@Service
public class CreekService {

    private final CreekRepository creekRepository;

    public CreekService(CreekRepository creekRepository) {
        this.creekRepository = creekRepository;
    }

    public Creek create(Creek creek) {
        creekRepository.save(creek);
        return creek;
    }

    // todo show, deleteById


}