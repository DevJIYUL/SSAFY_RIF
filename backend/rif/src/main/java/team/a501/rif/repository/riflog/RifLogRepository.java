package team.a501.rif.repository.riflog;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import team.a501.rif.domain.member.Member;
import team.a501.rif.domain.riflog.RifLog;

public interface RifLogRepository extends JpaRepository<RifLog, Long> {

    Slice<RifLog> findByMember(Member member, Pageable pageable);
}
