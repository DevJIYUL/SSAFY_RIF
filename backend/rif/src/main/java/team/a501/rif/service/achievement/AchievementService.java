package team.a501.rif.service.achievement;

import team.a501.rif.domain.achievement.Achievement;
import team.a501.rif.dto.achievement.AchievementSaveRequest;

import java.util.List;

public interface AchievementService {

    Achievement save(AchievementSaveRequest achievementSaveRequest);
    List<Achievement> findAll();
    Achievement findById(Long id);
}
