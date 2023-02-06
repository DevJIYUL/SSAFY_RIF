package team.a501.rif.dto.riflog;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class RifLogSaveRequest {
    private String memberUid;
    private Integer plasticTotal;
    private Integer plasticOk;
    private Integer recycleTotal;
    private Integer recycleOk;
    private Boolean adviseIgnored;

    @Builder
    public RifLogSaveRequest(String memberUid, Integer plasticTotal, Integer plasticOk, Integer recycleTotal, Integer recycleOk, Boolean adviseIgnored) {
        this.memberUid = memberUid;
        this.plasticTotal = plasticTotal;
        this.plasticOk = plasticOk;
        this.recycleTotal = recycleTotal;
        this.recycleOk = recycleOk;
        this.adviseIgnored = adviseIgnored;
    }
}
