package repository;

import model.Package;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class PackageRepository {

    private final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ro.tutorial.lab.SD");

    public void insert(Package pack, Integer destId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        pack.setDestination(new DestinationRepository().findById(destId));
        entityManager.persist(pack);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public List<Package> findAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<Package> ps = (List<Package>) entityManager.createQuery("SELECT p FROM Package p").getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return ps;
    }

    public List<Package> findByDest(Integer id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<Package> ps = (List<Package>) entityManager.createQuery("SELECT p FROM Package p Where p.destination.id=:idd").setParameter("idd", id).getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return ps;
    }

    public List<Package> findAvailable() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<Package> ps = (List<Package>) entityManager.createQuery("SELECT p FROM Package p WHERE p.status <> 'BOOKED'").getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return ps;
    }

    public Integer delete(Integer id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Package p = findById(id);
        entityManager.remove(entityManager.contains(p) ? p : entityManager.merge(p));
        entityManager.getTransaction().commit();
        entityManager.close();
        return p.getDestination().getId();
    }

    public Package findById(Integer id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Package pack = (Package) entityManager.createQuery("SELECT p FROM Package p WHERE p.id=:idd").setParameter("idd", id).getResultList().get(0);
        entityManager.getTransaction().commit();
        entityManager.close();
        return pack;
    }
}