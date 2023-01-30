package team.a501.rif.repository.tmp;

import org.springframework.data.jpa.repository.JpaRepository;
import team.a501.rif.domain.tmp.TempMember;

import java.util.Optional;

public interface TempMemberRepository extends JpaRepository<TempMember, String> {

    Optional<TempMember> findByStudentId(String studentId);
    void deleteByStudentId(String studentId);
}
