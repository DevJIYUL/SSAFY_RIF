package team.a501.rif.service.achievement;

import team.a501.rif.domain.achievement.AchievementAcq;

import java.util.List;

public interface AchievementAcqService {

    AchievementAcq save(String memeberId, String achievementId);
    List<AchievementAcq> findByMemberId(String memberId);
    List<AchievementAcq> findByMemberUid(String memberUid);
}
