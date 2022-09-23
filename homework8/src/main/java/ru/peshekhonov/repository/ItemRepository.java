package ru.peshekhonov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.peshekhonov.model.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

}
