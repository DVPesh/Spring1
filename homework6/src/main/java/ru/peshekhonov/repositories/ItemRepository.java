package ru.peshekhonov.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.peshekhonov.entities.Item;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

@Repository
public class ItemRepository {

    private final EntityManagerFactory entityManagerFactory;

    @Autowired
    public ItemRepository(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public List<Item> findAll() {
        return executeForEntityManager(entityManager ->
                entityManager.createNamedQuery("findAllItems", Item.class).getResultList());
    }

    public Optional<Item> findById(long id) {
        return executeForEntityManager(entityManager ->
                Optional.ofNullable(entityManager.find(Item.class, id)));
    }


    public void save(Item item) {
        executeInTransaction(entityManager -> {
            if (item.getId() == null) {
                entityManager.persist(item);
            } else {
                entityManager.merge(item);
            }
        });
    }

    public void deleteById(long id) {
        executeInTransaction(entityManager -> entityManager.createNamedQuery("deleteItemById")
                .setParameter("id", id)
                .executeUpdate());
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
