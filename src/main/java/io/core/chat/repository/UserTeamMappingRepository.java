package io.core.chat.repository;

import io.core.chat.model.UserTeamMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTeamMappingRepository extends JpaRepository<UserTeamMapping, Integer> {
}
