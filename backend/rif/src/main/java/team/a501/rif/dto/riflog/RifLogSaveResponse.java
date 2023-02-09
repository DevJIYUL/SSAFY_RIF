package team.a501.rif.dto.riflog;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class RifLogSaveResponse {

    private Integer point;
    private Integer exp;
    private LocalDateTime createdAt;

    @Builder
    public RifLogSaveResponse(Integer point, Integer exp, LocalDateTime createdAt) {
        this.point = point;
        this.exp = exp;
        this.createdAt = createdAt;
    }
}
