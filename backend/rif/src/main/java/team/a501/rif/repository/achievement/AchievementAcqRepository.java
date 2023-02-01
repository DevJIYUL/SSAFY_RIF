package team.a501.rif.repository.achievement;

import org.springframework.data.jpa.repository.JpaRepository;
import team.a501.rif.domain.achievement.AchievementAcq;
import team.a501.rif.domain.member.Member;

import java.util.List;

public interface AchievementAcqRepository extends JpaRepository<AchievementAcq, Long> {

    List<AchievementAcq> findByMember(Member member);

    List<AchievementAcq> findByMemberAndOnDisplay(Member member, Boolean onDisplay);
}
