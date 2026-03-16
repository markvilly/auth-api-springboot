package com.example.auth_api.repo;

import com.example.auth_api.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<User, Long> {

	Optional<User> findByUsername(String username);

}
