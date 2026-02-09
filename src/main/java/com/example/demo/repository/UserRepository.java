package com.example.demo.repository;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    User findByEmailAndPassword(String username, String password);

    List<User> findByRole(Role role);

    Optional<User> findByResetPasswordToken(String token);

    boolean existsByEmail(String email);

}
