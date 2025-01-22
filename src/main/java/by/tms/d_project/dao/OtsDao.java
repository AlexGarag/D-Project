package by.tms.d_project.dao;

import by.tms.d_project.entity.Ots;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class OtsDao {

    @PersistenceContext
    private EntityManager entityManager;

    public void delete(Long id) {
        Ots ots = entityManager.find(Ots.class, id);
        if (ots != null) {
            entityManager.remove(ots);
        }
    }
}