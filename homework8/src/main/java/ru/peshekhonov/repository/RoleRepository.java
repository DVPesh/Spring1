package ru.peshekhonov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.peshekhonov.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}
