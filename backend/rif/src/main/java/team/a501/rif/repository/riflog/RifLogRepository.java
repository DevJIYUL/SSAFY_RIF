package team.a501.rif.repository.riflog;

import org.springframework.data.jpa.repository.JpaRepository;
import team.a501.rif.domain.riflog.RifLog;

public interface RifLogRepository extends JpaRepository<RifLog, Long> {
}
