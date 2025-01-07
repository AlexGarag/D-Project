package by.tms.d_project.service;

import by.tms.d_project.entity.ShaftForm;
import by.tms.d_project.repository.ShaftFormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShaftFormService {
    private final ShaftFormRepository shaftFormRepository;

    @Autowired
    public ShaftFormService(ShaftFormRepository shaftFormRepository) {
        this.shaftFormRepository = shaftFormRepository;
    }

    public ShaftForm create(ShaftForm shaftForm) {
        shaftFormRepository.save(shaftForm);
        return shaftForm;
    }

    // todo show, deleteById


}