package by.tms.d_project.dao;

import by.tms.d_project.entity.IcOts;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class IcOtsDao {

    @PersistenceContext
    private EntityManager entityManager;

    public void delete(Long id) {
        IcOts icOts = entityManager.find(IcOts.class, id);
        if (icOts != null) {
            entityManager.remove(icOts);
        }
    }
}