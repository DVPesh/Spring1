package ru.peshekhonov.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.peshekhonov.entities.Item;
import ru.peshekhonov.entities.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

@Repository
public class ProductRepository {

    private final EntityManagerFactory entityManagerFactory;

    @Autowired
    public ProductRepository(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public List<Product> findAll() {
        return executeForEntityManager(entityManager ->
                entityManager.createNamedQuery("findAllProducts", Product.class).getResultList());
    }

    public Optional<Product> findById(long id) {
        return executeForEntityManager(entityManager ->
                Optional.ofNullable(entityManager.find(Product.class, id)));
    }

    public void save(Product product) {
        executeInTransaction(entityManager -> {
            if (product.getId() == null) {
                entityManager.persist(product);
            } else {
                entityManager.merge(product);
            }
        });
    }

    public void deleteById(long id) {
        executeInTransaction(entityManager -> entityManager.createNamedQuery("deleteProductById")
                .setParameter("id", id)
                .executeUpdate());
    }

    public List<Item> getItemsById(long id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        List<Item> items = new ArrayList<>(0);
        try {
            em.getTransaction().begin();
            Product product = em.createNamedQuery("getItemsByProductId", Product.class).setParameter("id", id).getSingleResult();
            items = product.getItems();
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
