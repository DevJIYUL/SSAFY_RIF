package team.a501.rif.Repository.member;

import org.springframework.data.jpa.repository.JpaRepository;
import team.a501.rif.domain.member.Member;

public interface MemberRepository extends JpaRepository<Member,String> {
    Member findByStudentId(String studentId);
}
