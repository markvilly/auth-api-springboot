package com.example.auth_api.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

import com.example.auth_api.entity.User;

public interface UsersRepository extends JpaRepository<User, Long>{
    Optional<User> findByUsername(String username);
}