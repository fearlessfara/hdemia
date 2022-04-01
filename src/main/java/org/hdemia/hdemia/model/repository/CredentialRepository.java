package org.hdemia.hdemia.model.repository;

import org.hdemia.hdemia.model.entity.Credential;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CredentialRepository extends JpaRepository<Credential, Long> {
}
