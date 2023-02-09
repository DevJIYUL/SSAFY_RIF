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

    @Builder
    public RifLogSaveRequest(String uid, Integer plasticTotal, Integer plasticOk, Integer recycleTotal, Integer recycleOk) {
        this.uid = uid;
        this.plasticTotal = plasticTotal;
        this.plasticOk = plasticOk;
        this.recycleTotal = recycleTotal;
        this.recycleOk = recycleOk;
    }
}
