package org.hdemia.hdemia.model.repository;

import org.hdemia.hdemia.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    default Optional<User> findByEmail(String email) {
        return findByCredential_Username(email);
    }

    Optional<User> findByCredential_Username(String email);
}
