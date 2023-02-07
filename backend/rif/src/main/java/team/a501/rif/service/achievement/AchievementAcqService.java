package team.a501.rif.service.achievement;

import team.a501.rif.domain.achievement.AchievementAcq;

public interface AchievementAcqService {

    AchievementAcq save(String ownerId, Long achievementId);
}
