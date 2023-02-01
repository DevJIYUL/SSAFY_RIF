package team.a501.rif.repository.member;

import org.springframework.data.jpa.repository.JpaRepository;
import team.a501.rif.domain.member.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,String> {
    Optional<Member> findByUid(String uid);
}
