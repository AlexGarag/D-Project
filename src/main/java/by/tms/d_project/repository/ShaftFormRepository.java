package by.tms.d_project.repository;

import by.tms.d_project.entity.ShaftForm;
import org.springframework.data.jpa.repository.JpaRepository;

//import java.util.Optional;
import java.util.UUID;

public interface ShaftFormRepository extends JpaRepository<ShaftForm, UUID> {
//    Optional<CreekForm> findByUuid(UUID id);

//    void deleteById(String id);
}