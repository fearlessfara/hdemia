package org.hdemia.hdemia.model.repository;

import org.hdemia.hdemia.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
