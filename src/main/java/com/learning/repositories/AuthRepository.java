package com.learning.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.models.User;

public interface AuthRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}
