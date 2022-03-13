package repository;

import model.Destination;
import model.User;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.List;

public class DestinationRepository {
    private final EntityManager entityManager = Persistence.createEntityManagerFactory("ro.tutorial.lab.SD").createEntityManager();

    public void insert(Destination destination) {
        entityManager.getTransaction().begin();
        entityManager.persist(destination);
        entityManager.getTransaction().commit();
    }

    public void delete(Integer id) {
        entityManager.getTransaction().begin();
        entityManager.remove(findById(id));
        entityManager.getTransaction().commit();
    }

    public List<Destination> findAll() {
        entityManager.getTransaction().begin();
        List<Destination> ps = (List<Destination>) entityManager.createQuery("SELECT d FROM Destination d").getResultList();
        entityManager.getTransaction().commit();
        return ps;
    }

    public Destination findById(Integer id) {
        entityManager.getTransaction().begin();
        Destination dest = (Destination) entityManager.createQuery("SELECT d FROM Destination d WHERE d.id=:idd").setParameter("idd", id).getResultList().get(0);
        entityManager.getTransaction().commit();
        return dest;
    }
}
