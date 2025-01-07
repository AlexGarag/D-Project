package by.tms.d_project.service;

import by.tms.d_project.entity.CreekForm;
import by.tms.d_project.repository.CreekFormRepository;
import org.springframework.stereotype.Service;

@Service
public class CreekFormService {

    private final CreekFormRepository creekFormRepository;

    public CreekFormService(CreekFormRepository creekFormRepository) {
        this.creekFormRepository = creekFormRepository;
    }

    public CreekForm create(CreekForm creekForm) {
        creekFormRepository.save(creekForm);
        return creekForm;
    }

    // todo show, deleteById


}