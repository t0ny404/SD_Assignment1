package repository;

import model.Package;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.List;

public class PackageRepository {

    private final EntityManager entityManager = Persistence.createEntityManagerFactory("ro.tutorial.lab.SD").createEntityManager();

    public void insert(Package pack, Integer destId) {
        entityManager.getTransaction().begin();
        pack.setDestination(new DestinationRepository().findById(destId));
        entityManager.persist(pack);
        entityManager.getTransaction().commit();
    }

    public List<Package> findAll() {
        entityManager.getTransaction().begin();
        List<Package> ps = (List<Package>) entityManager.createQuery("SELECT p FROM Package p").getResultList();
        entityManager.getTransaction().commit();
        return ps;
    }

    public List<Package> findByDest(Integer id) {
        entityManager.getTransaction().begin();
        List<Package> ps = (List<Package>) entityManager.createQuery("SELECT p FROM Package p Where p.destination.id=:idd").setParameter("idd", id).getResultList();
        entityManager.getTransaction().commit();
        return ps;
    }

    public List<Package> findAvailable() {
        entityManager.getTransaction().begin();
        List<Package> ps = (List<Package>) entityManager.createQuery("SELECT p FROM Package p WHERE p.status <> 'BOOKED'").getResultList();
        entityManager.getTransaction().commit();
        return ps;
    }

    public void delete(Integer id) {
        entityManager.getTransaction().begin();
        entityManager.remove(findById(id));
        entityManager.getTransaction().commit();
    }

    public Package findById(Integer id) {
        entityManager.getTransaction().begin();
        Package pack = (Package) entityManager.createQuery("SELECT p FROM Package p WHERE p.id=:idd").setParameter("idd", id).getResultList().get(0);
        entityManager.getTransaction().commit();
        return pack;
    }
}