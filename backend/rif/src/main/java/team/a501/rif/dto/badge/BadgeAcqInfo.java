package team.a501.rif.dto.badge;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.a501.rif.domain.badge.Badge;
import team.a501.rif.domain.badge.BadgeAcq;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class BadgeAcqInfo {
    private BadgeInfo badgeInfo;

    private Boolean onDisplay;

    private LocalDateTime achievedAt;

    private Boolean hasBadge;

    @Builder
    public BadgeAcqInfo(BadgeInfo badgeInfo, Boolean onDisplay, LocalDateTime achievedAt, Boolean hasBadge) {
        this.badgeInfo = badgeInfo;
        this.onDisplay = onDisplay;
        this.achievedAt = achievedAt;
        this.hasBadge = hasBadge;
    }

    public static BadgeAcqInfo from(BadgeAcq badgeAcq) {

        return BadgeAcqInfo.builder()
                .badgeInfo(BadgeInfo.from(badgeAcq.getBadge()))
                .onDisplay(badgeAcq.getOnDisplay())
                .achievedAt(badgeAcq.getCreated())
                .hasBadge(true)
                .build();
    }

    public static BadgeAcqInfo from(Badge badge) {
        return BadgeAcqInfo.builder()
                .badgeInfo(BadgeInfo.from(badge))
                .onDisplay(false)
                .achievedAt(null)
                .hasBadge(false)
                .build();
    }
}
