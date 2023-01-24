package team.a501.rif.repository.tmp;

import org.springframework.data.jpa.repository.JpaRepository;
import team.a501.rif.domain.tmp.TempMember;

public interface TempMemberRepository extends JpaRepository<TempMember, String> {
}
