package by.tms.d_project.repository;

import by.tms.d_project.entity.Printing;
import org.springframework.data.jpa.repository.JpaRepository;

//import java.util.Optional;

public interface PrintingRepository extends JpaRepository<Printing, String> {
//    Optional<Creek> findById(String id);
//
//    void deleteById(String id);
}