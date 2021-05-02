package io.core.chat.repository;

import io.core.chat.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {

    Optional<Users> findByUserIdAndCompanyIdAndDeleted(long userId, long companyId, int deleted);

    Optional<Users> findByUserEmailIdAndDeleted(String mailId, int deleted);
}
