package by.tms.d_project.service;

import by.tms.d_project.entity.Shaft;
import by.tms.d_project.repository.ShaftRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShaftService {
    private final ShaftRepository shaftRepository;

    @Autowired
    public ShaftService(ShaftRepository shaftRepository) {
        this.shaftRepository = shaftRepository;
    }

    public Shaft create(Shaft shaft) {
        shaftRepository.save(shaft);
        return shaft;
    }

    // todo show, deleteById


}