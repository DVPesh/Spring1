package ru.peshekhonov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.peshekhonov.model.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

}
