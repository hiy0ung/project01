package org.example.springbootpractice.repository;

import org.example.springbootpractice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUserId(String userId);
    boolean existsByUserBusinessNumber(String userBusinessNumber);

    Optional<User> findByUserId(String userId);
}
