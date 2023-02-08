package team.a501.rif.dto.member;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.a501.rif.dto.badge.BadgeInfo;

@NoArgsConstructor
@Getter
public class BadgeGatchaResponse {

    Boolean reduplicated;

    Integer remainingPoint;

    BadgeInfo badge;

    @Builder
    public BadgeGatchaResponse(Boolean reduplicated, Integer remainingPoint, BadgeInfo badge) {
        this.reduplicated = reduplicated;
        this.remainingPoint = remainingPoint;
        this.badge = badge;
    }

    @Override
    public String toString() {
        return "BadgeGatchaResponse{" +
                "\n reduplicated=" + reduplicated +
                ",\n remainingPoint=" + remainingPoint +
                ",\n badge=" + badge +
                "\n}";
    }
}
