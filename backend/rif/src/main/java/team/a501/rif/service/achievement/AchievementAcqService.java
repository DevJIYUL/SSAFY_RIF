package team.a501.rif.service.achievement;

import team.a501.rif.domain.achievement.AchievementAcq;
import team.a501.rif.dto.achievement.AchievementAcqResponse;

import java.util.List;

public interface AchievementAcqService {

    AchievementAcq create(String ownerId, Long achievementId);
    List<AchievementAcq> findByMemberId(String memberId);
    List<AchievementAcq> findByMemberUid(String memberUid);

    List<AchievementAcqResponse> findOnDisplayedItemsOfMember(String memberId);
}
