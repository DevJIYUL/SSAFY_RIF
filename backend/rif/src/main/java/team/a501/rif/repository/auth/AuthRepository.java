package team.a501.rif.repository.auth;

import org.springframework.data.jpa.repository.JpaRepository;
import team.a501.rif.domain.member.Member;

public interface AuthRepository extends JpaRepository<Member,Long> {
    Member getById(String id);
}
