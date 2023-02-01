package team.a501.rif.repository.badge;

import org.springframework.data.jpa.repository.JpaRepository;
import team.a501.rif.domain.badge.Badge;

// @Repository 어노테이션이 없어도 JpaRepository를 상속하므로 해당 인터페이스가 빈으로 등록된다
public interface BadgeRepository extends JpaRepository<Badge, Long> {
}
