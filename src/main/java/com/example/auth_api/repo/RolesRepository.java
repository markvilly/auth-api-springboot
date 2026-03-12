package com.example.auth_api.repo;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.auth_api.entity.Role;

public interface RolesRepository extends JpaRepository<Role, Long>{

    Optional<Role> findByName(String name);

}