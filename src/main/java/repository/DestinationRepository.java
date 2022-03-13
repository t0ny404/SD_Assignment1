package repository;

import model.Destination;
import model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class DestinationRepository {
    private final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ro.tutorial.lab.SD");

    public void insert(Destination destination) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(destination);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void delete(Integer id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.remove(findById(id));
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public List<Destination> findAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<Destination> ps = (List<Destination>) entityManager.createQuery("SELECT d FROM Destination d").getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return ps;
    }

    public Destination findById(Integer id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Destination dest = (Destination) entityManager.createQuery("SELECT d FROM Destination d WHERE d.id=:idd").setParameter("idd", id).getResultList().get(0);
        entityManager.getTransaction().commit();
        entityManager.close();
        return dest;
    }
}
