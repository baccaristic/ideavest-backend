package com.ideavest.server.Repositories;

import com.ideavest.server.models.User;
import com.ideavest.server.models.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);
    long countByRole(UserRole role);
    List<User> findByRole(UserRole role);
}

