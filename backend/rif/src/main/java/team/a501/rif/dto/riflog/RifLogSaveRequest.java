package team.a501.rif.dto.riflog;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class RifLogSaveRequest {
    private String uid;
    private Integer plasticTotal;
    private Integer plasticOk;
    private Integer recycleTotal;
    private Integer recycleOk;
    private Boolean adviceIgnored;

    @Builder
    public RifLogSaveRequest(String uid, Integer plasticTotal, Integer plasticOk, Integer recycleTotal, Integer recycleOk, Boolean adviceIgnored) {
        this.uid = uid;
        this.plasticTotal = plasticTotal;
        this.plasticOk = plasticOk;
        this.recycleTotal = recycleTotal;
        this.recycleOk = recycleOk;
        this.adviceIgnored = adviceIgnored;
    }
}
