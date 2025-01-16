package by.tms.d_project.service;

import by.tms.d_project.entity.ShaftIcOts;
import by.tms.d_project.repository.ShaftIcOtsRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class ShaftIcOtsService {
    private final ShaftIcOtsRepository shaftIcOtsRepository;

    public ShaftIcOtsService(ShaftIcOtsRepository shaftIcOtsRepository) {
        this.shaftIcOtsRepository = shaftIcOtsRepository;
    }

    @Transactional
    public ShaftIcOts create(ShaftIcOts shaftIcOts) {
        shaftIcOtsRepository.save(shaftIcOts);
        return shaftIcOts;
    }
}