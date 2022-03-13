package repository;

import model.Package;
import model.User;

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

    public void insert(Package pack) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(pack);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void update(Package pack) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(pack);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public List<Package> findAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<Package> ps = (List<Package>) entityManager.createQuery("SELECT p FROM Package p " +
                " ORDER BY p.status").getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return ps;
    }

    public List<Package> findByDest(Integer id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<Package> ps = (List<Package>) entityManager.createQuery("SELECT p FROM Package p WHERE p.destination.id=:idd").setParameter("idd", id).getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return ps;
    }

    public List<Package> findByPrice(Double price) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<Package> ps = (List<Package>) entityManager.createQuery("SELECT p FROM Package p WHERE p.price >= :price - 0.001 AND p.price <= :price + 0.001").setParameter("price", price).getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return ps;
    }

    public List<Package> findByName(String name) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<Package> ps = (List<Package>) entityManager.createQuery("SELECT p FROM Package p WHERE p.name=:name").setParameter("name", name).getResultList();
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

    public void edit(Package pack) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.createQuery("UPDATE Package p " +
                "SET p.name=:name, p.price=:price, p.limitt=:limitt, p.details=:details, p.start=:start, p.end=:end " +
                "WHERE p.id=:id")
                .setParameter("name", pack.getName()).setParameter("price", pack.getPrice())
                .setParameter("limitt", pack.getLimitt()).setParameter("details", pack.getDetails())
                .setParameter("start", pack.getStart()).setParameter("end", pack.getEnd())
                .setParameter("id", pack.getId()).executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}