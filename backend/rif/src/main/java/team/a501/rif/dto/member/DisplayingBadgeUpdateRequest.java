package team.a501.rif.dto.member;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
public class DisplayingBadgeUpdateRequest {
    // 대표 뱃지를 설정할 때 최대 세개까지 업데이트 할 수 있다
    private List<Long> toDisplay;
    @Builder
    public DisplayingBadgeUpdateRequest(List<Long> toDisplay){
        this.toDisplay = toDisplay;
    }

}
