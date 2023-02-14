package team.a501.rif.dto.riflog;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class RifLogSaveResponse {

    private String name;
    private Integer plasticTotal;
    private Integer plasticOk;
    private Integer recycleTotal;
    private Integer recycleOk;
    private Integer point;
    private Integer exp;
    private LocalDateTime createdAt;

    @Builder
    public RifLogSaveResponse(String name, Integer plasticTotal, Integer plasticOk, Integer recycleTotal, Integer recycleOk, Integer point, Integer exp, LocalDateTime createdAt) {
        this.name = name;
        this.plasticTotal = plasticTotal;
        this.plasticOk = plasticOk;
        this.recycleTotal = recycleTotal;
        this.recycleOk = recycleOk;
        this.point = point;
        this.exp = exp;
        this.createdAt = createdAt;
    }
}
