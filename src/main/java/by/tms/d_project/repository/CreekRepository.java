package by.tms.d_project.repository;

import by.tms.d_project.entity.Creek;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CreekRepository extends JpaRepository<Creek, String> {
    Optional<Creek> findById(String id);

    void deleteById(String id);
}