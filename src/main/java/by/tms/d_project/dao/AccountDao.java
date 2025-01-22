package by.tms.d_project.dao;

import by.tms.d_project.entity.Account;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class AccountDao {

    @PersistenceContext
    private EntityManager entityManager;

    public void delete(Long id) {
        Account account = entityManager.find(Account.class, id);
        if (account != null) {
            entityManager.remove(account);
        }
    }
}