package repository;

import model.User;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.List;

public class UserRepository {

    private final EntityManager entityManager = Persistence.createEntityManagerFactory("ro.tutorial.lab.SD").createEntityManager();

    public void insert(User user) {
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();
    }

    public List findByUsername(String userName) {
        entityManager.getTransaction().begin();
        List users = entityManager.createQuery("SELECT u FROM User u WHERE u.username LIKE :username")
                .setParameter("username", userName).getResultList();
        entityManager.getTransaction().commit();
        return users;
    }
}
