package team.a501.rif.service.achievement;

import team.a501.rif.domain.achievement.Achievement;
import team.a501.rif.dto.achievement.AchievementInfo;
import team.a501.rif.dto.achievement.AchievementSaveRequest;

import java.util.List;

public interface AchievementService {

    AchievementInfo save(AchievementSaveRequest achievementSaveRequest);

    List<AchievementInfo> saveAll(List<AchievementSaveRequest> achievementSaveRequests);

    List<Achievement> findAll();

    Achievement findById(Long id);
}
