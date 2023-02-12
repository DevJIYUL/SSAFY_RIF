package team.a501.rif.dto.achievement;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.a501.rif.domain.achievement.Achievement;
import team.a501.rif.domain.achievement.AchievementAcq;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class AchievementAcqInfo {
    private AchievementInfo achievementInfo;
    private Boolean onDisplay;
    private LocalDateTime achievedAt;
    private Boolean hasAchievement;

    @Builder
    public AchievementAcqInfo(AchievementInfo achievementInfo, Boolean onDisplay, LocalDateTime achievedAt, Boolean hasAchievement) {
        this.achievementInfo = achievementInfo;
        this.onDisplay = onDisplay;
        this.achievedAt = achievedAt;
        this.hasAchievement = hasAchievement;
    }

    public static AchievementAcqInfo from(AchievementAcq achievementAcq) {
        return AchievementAcqInfo.builder()
                .achievementInfo(AchievementInfo.from(achievementAcq.getAchievement()))
                .onDisplay(achievementAcq.getOnDisplay())
                .achievedAt(achievementAcq.getCreated())
                .hasAchievement(true)
                .build();
    }

    public static AchievementAcqInfo from(Achievement achievement) {
        return AchievementAcqInfo.builder()
                .achievementInfo(AchievementInfo.from(achievement))
                .onDisplay(false)
                .achievedAt(null)
                .hasAchievement(false)
                .build();
    }
}
