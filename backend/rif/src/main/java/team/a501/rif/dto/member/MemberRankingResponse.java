package team.a501.rif.dto.member;

import lombok.*;

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
