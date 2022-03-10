package repository;

import model.User;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class UserRepository {

    private final EntityManager entityManager = Persistence.createEntityManagerFactory("ro.tutorial.lab.SD").createEntityManager();

    public void insert(User user) {
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public User findByUsername(String userName) {
        entityManager.getTransaction().begin();
        User user = (User) entityManager.createQuery("SELECT * FROM user WHERE username=" + userName).getResultList().get(0);
        entityManager.getTransaction().commit();
        entityManager.close();
        return user;
    }
}
