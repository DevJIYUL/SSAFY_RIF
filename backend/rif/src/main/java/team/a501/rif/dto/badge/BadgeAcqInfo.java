package team.a501.rif.dto.badge;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.a501.rif.domain.badge.BadgeAcq;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class BadgeAcqInfo {

    private Long id;

    private BadgeInfo badgeInfo;

    private Boolean onDisplay;

    private LocalDateTime achievedAt;
    @Builder
    public BadgeAcqInfo(Long id, BadgeInfo badgeInfo, Boolean onDisplay, LocalDateTime achievedAt) {
        this.id = id;
        this.badgeInfo = badgeInfo;
        this.onDisplay = onDisplay;
        this.achievedAt = achievedAt;
    }

    public static BadgeAcqInfo from(BadgeAcq badgeAcq){

        return BadgeAcqInfo.builder()
                .id(badgeAcq.getId())
                .badgeInfo(BadgeInfo.from(badgeAcq.getBadge()))
                .onDisplay(badgeAcq.getOnDisplay())
                .achievedAt(badgeAcq.getCreated())
                .build();
    }
}
