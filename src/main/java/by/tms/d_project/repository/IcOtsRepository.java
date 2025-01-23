package by.tms.d_project.repository;

import by.tms.d_project.entity.IcOts;
import by.tms.d_project.entity.Ots;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IcOtsRepository extends JpaRepository<IcOts, Long> {

    Optional<IcOts> findByTitlePrinting(String titlePrinting);
}