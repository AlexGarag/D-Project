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

//    public List<Country> getAll() {
//        return entityManager.createQuery("from Country c order by c.id desc", Country.class).getResultList();
//    }

//    public Country getById(int id) {
//        return entityManager.find(Country.class, id);
//    }

//    public Country create(Country country) {
//        for (City city : country.getCities()) {
//            city.setCountry(country);
//        }
//        entityManager.persist(country);
//        return country;
//    }

//    public Country update(int id, Country country) {
//        Country original = entityManager.find(Country.class, id);
//        if (original != null) {
//            original.setName(country.getName());
//            for (City city : original.getCities()) {
//                entityManager.remove(city);
//            }
//            original.getCities().clear();
//            for (City city : country.getCities()) {
//                city.setCountry(original);
//                original.getCities().add(city);
//                entityManager.persist(city);
//            }
//            entityManager.merge(original);
//        }
//        return original;
//    }
}