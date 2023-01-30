package team.a501.rif.repository.achievement;

import org.springframework.data.jpa.repository.JpaRepository;
import team.a501.rif.domain.achievement.Achievement;

public interface AchievementRepository extends JpaRepository<Achievement, Long> {
}
