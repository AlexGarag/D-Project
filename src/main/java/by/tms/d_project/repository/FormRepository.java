package by.tms.d_project.repository;

import by.tms.d_project.entity.Form;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FormRepository extends JpaRepository<Form, String> {

    Optional<Form> findById(String id);

//    List<Issue> findByProjectId(Long projectId);
}