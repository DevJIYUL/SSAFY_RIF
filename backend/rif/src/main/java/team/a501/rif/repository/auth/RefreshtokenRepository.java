package team.a501.rif.repository.auth;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import team.a501.rif.domain.auth.RefreshToken;

import java.util.Optional;

public interface RefreshtokenRepository extends CrudRepository<RefreshToken, String> {
    Optional<RefreshToken> findById(String studentId);
}
