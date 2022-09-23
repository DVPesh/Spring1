package ru.peshekhonov.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.peshekhonov.model.Visitor;

import java.util.Optional;

@Repository
public interface VisitorRepository extends JpaRepository<Visitor, Long> {

    Optional<Visitor> findByUsername(String username);

    @Query(value = """
            select *
            from visitors v
            where (:username is null or v.username like :username)
              and (:role is null or v.id in
                                    (select vr.visitor_id
                                     from visitors_roles vr
                                              join roles r
                                                   on vr.role_id = r.id
                                     where r.name = :role))
                                                """,
            countQuery = """
                                select count(*)
                                from visitors v
                                where (:username is null or v.username like :username)
                                  and (:role is null or v.id in
                                                        (select vr.visitor_id
                                                         from visitors_roles vr
                                                                  join roles r
                                                                       on vr.role_id = r.id
                                                         where r.name = :role))
                    """,
            nativeQuery = true)
    Page<Visitor> visitorsByFilter(String username, String role, Pageable pageable);
}
