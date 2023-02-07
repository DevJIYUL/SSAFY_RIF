package team.a501.rif.dto.member;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.a501.rif.dto.badge.BadgeAcqInfo;

import java.util.List;

@NoArgsConstructor
@Getter
public class MemberBadgeAcqInfoResponse {

    List<BadgeAcqInfo> badgeAcqInfoList;

    @Builder
    public MemberBadgeAcqInfoResponse(List<BadgeAcqInfo> badgeAcqInfoList) {
        this.badgeAcqInfoList = badgeAcqInfoList;
    }
}
