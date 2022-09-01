package ru.peshekhonov.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.peshekhonov.entities.Customer;
import ru.peshekhonov.entities.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

@Repository
public class CustomerRepository {

    private final EntityManagerFactory entityManagerFactory;

    @Autowired
    public CustomerRepository(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public List<Customer> findAll() {
        return executeForEntityManager(entityManager ->
                entityManager.createNamedQuery("findAllCustomers", Customer.class).getResultList());
    }

    public Optional<Customer> findById(long id) {
        return executeForEntityManager(entityManager ->
                Optional.ofNullable(entityManager.find(Customer.class, id)));
    }

    public void save(Customer customer) {
        executeInTransaction(entityManager -> {
            if (customer.getId() == null) {
                entityManager.persist(customer);
            } else {
                entityManager.merge(customer);
            }
        });
    }

    public void deleteById(long id) {
        executeInTransaction(entityManager -> entityManager.createNamedQuery("deleteCustomerById")
                .setParameter("id", id)
                .executeUpdate());
    }

    public List<Item> getItemsById(long id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        List<Item> items = new ArrayList<>(0);
        try {
            em.getTransaction().begin();
            Customer customer = em.createNamedQuery("getItemsByCustomerId", Customer.class).setParameter("id", id).getSingleResult();
            items = customer.getItems();
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return items;
    }

    private <R> R executeForEntityManager(Function<EntityManager, R> function) {
        EntityManager em = entityManagerFactory.createEntityManager();
        try {
            return function.apply(em);
        } finally {
            em.close();
        }
    }

    private void executeInTransaction(Consumer<EntityManager> consumer) {
        EntityManager em = entityManagerFactory.createEntityManager();
        try {
            em.getTransaction().begin();
            consumer.accept(em);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
}
