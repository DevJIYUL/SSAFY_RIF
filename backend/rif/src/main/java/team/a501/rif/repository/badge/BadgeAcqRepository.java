package team.a501.rif.repository.badge;

import org.springframework.data.jpa.repository.JpaRepository;
import team.a501.rif.domain.badge.BadgeAcq;
import team.a501.rif.domain.tmp.TempMember;

import java.util.List;

public interface BadgeAcqRepository extends JpaRepository<BadgeAcq, Long> {

    List<BadgeAcq> findByMember(TempMember member);
}
