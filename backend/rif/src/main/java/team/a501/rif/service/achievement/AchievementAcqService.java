package team.a501.rif.service.achievement;

import team.a501.rif.domain.achievement.AchievementAcq;
import team.a501.rif.dto.achievement.AchievementAcqInfo;

public interface AchievementAcqService {

    AchievementAcqInfo save(String ownerId, Long achievementId);
}
