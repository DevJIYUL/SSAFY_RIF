package team.a501.rif.dto.achievement;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.a501.rif.domain.achievement.AchievementAcq;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class AchievementAcqInfo {

    private Long id;

    private AchievementInfo achievementInfo;

    private Boolean onDisplay;
    private LocalDateTime achievedAt;

    @Builder
    public AchievementAcqInfo(Long id, AchievementInfo achievementInfo, Boolean onDisplay, LocalDateTime achievedAt) {
        this.id = id;
        this.achievementInfo = achievementInfo;
        this.onDisplay = onDisplay;
        this.achievedAt = achievedAt;
    }

    public static AchievementAcqInfo from(AchievementAcq achievementAcq) {
        return AchievementAcqInfo.builder()
                .id(achievementAcq.getId())
                .achievementInfo(AchievementInfo.from(achievementAcq.getAchievement()))
                .onDisplay(achievementAcq.getOnDisplay())
                .achievedAt(achievementAcq.getCreated())
                .build();
    }
}
