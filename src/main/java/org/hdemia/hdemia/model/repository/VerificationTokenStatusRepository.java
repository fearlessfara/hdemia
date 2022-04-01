package org.hdemia.hdemia.model.repository;

import org.hdemia.hdemia.model.entity.VerificationTokenStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VerificationTokenStatusRepository extends JpaRepository<VerificationTokenStatus, Long> {

    Optional<VerificationTokenStatus> findByToken(String token);
}
