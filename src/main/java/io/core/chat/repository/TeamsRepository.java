package io.core.chat.repository;

import io.core.chat.model.TeamDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeamsRepository extends JpaRepository<TeamDetail, Integer> {

    Optional<TeamDetail> findByTeamNameAndDeleted(String teamName, int deleted);
}
