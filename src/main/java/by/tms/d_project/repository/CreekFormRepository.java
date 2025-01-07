package by.tms.d_project.repository;

import by.tms.d_project.entity.CreekForm;
import org.springframework.data.jpa.repository.JpaRepository;

//import java.util.Optional;
import java.util.UUID;

public interface CreekFormRepository extends JpaRepository<CreekForm, UUID> {
//    Optional<CreekForm> findByUuid(UUID id);

//    void deleteById(String id);
}