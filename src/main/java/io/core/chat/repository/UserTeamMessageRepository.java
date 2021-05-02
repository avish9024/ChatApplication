package io.core.chat.repository;

import io.core.chat.model.UserTeamMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTeamMessageRepository extends JpaRepository<UserTeamMessage, Integer> {
}
