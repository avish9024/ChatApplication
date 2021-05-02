package io.core.chat.repository;

import io.core.chat.model.DirectMessageMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DirectMessageMappingRepository extends JpaRepository<DirectMessageMapping, Integer> {
}
