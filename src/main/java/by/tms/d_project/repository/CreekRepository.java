package by.tms.d_project.repository;

import by.tms.d_project.entity.Creek;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CreekRepository extends JpaRepository<Creek, UUID> {

    Optional<Creek> findById(UUID id);

//    List<Issue> findByProjectId(Long projectId);
}