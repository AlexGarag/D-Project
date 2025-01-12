package by.tms.d_project.repository;

import by.tms.d_project.entity.ShaftIcOts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ShaftIcOtsRepository extends JpaRepository<ShaftIcOts, UUID> {}