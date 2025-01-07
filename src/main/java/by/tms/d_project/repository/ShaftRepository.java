package by.tms.d_project.repository;

import by.tms.d_project.entity.Shaft;
import org.springframework.data.jpa.repository.JpaRepository;

//import java.util.Optional;
import java.util.UUID;

public interface ShaftRepository extends JpaRepository<Shaft, UUID> {}