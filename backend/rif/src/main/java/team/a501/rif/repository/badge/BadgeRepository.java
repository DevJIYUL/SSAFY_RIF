package team.a501.rif.repository.badge;

import org.springframework.data.jpa.repository.JpaRepository;
import team.a501.rif.domain.badge.Badge;

public interface BadgeRepository extends JpaRepository<Badge, Long> {
}
