package ru.peshekhonov.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.peshekhonov.model.Product;

import java.math.BigDecimal;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = """
            select * from products p
            where (:title is null or p.title like :title)
            and (:min is null or p.cost >= :min)
            and (:max is null or p.cost <= :max)
                """,
            countQuery = """
                    select count(*) from products p
                    where (:title is null or p.title like :title)
                    and (:min is null or p.cost >= :min)
                    and (:max is null or p.cost <= :max)
                            """,
            nativeQuery = true)
    Page<Product> productsByFilter(String title, BigDecimal min, BigDecimal max, Pageable pageable);
}
