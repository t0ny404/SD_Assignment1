package repository;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class BookingRepository {
    private final EntityManager entityManager = Persistence.createEntityManagerFactory("ro.tutorial.lab.SD").createEntityManager();

}
