package by.tms.d_project.repository;

import by.tms.d_project.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, String> {

    Optional<Account> findByUsername(String username);

    void removeByUsername(String username);


}