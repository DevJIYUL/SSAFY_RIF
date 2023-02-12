package team.a501.rif.dto.member;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class MemberRankingResponse {

    private Integer rank;
    private MemberResponse member;

    @Builder
    public MemberRankingResponse(Integer rank, MemberResponse member) {
        this.rank = rank;
        this.member = member;
    }
}
