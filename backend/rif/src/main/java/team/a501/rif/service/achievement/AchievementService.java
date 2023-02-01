package team.a501.rif.service.achievement;

import team.a501.rif.domain.achievement.Achievement;
import team.a501.rif.dto.achievement.AchievementSaveRequest;

public interface AchievementService {

    Achievement save(AchievementSaveRequest achievementSaveRequest);
    Achievement findById(Long id);
}
