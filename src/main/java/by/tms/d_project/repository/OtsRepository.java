package by.tms.d_project.repository;

import by.tms.d_project.entity.Ots;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OtsRepository extends JpaRepository<Ots, Long> {

    Optional<Ots> findByTitlePrinting(String titlePrinting);
}