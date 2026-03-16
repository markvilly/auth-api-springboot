package com.example.auth_api.repo;

import com.example.auth_api.entity.Role;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolesRepository extends JpaRepository<Role, Long> {

	Optional<Role> findByName(String name);

}
