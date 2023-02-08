package team.a501.rif.dto.riflog;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.a501.rif.domain.riflog.RifLog;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class RifLogInfo {

    private Long id;
    private Integer plasticTotal;
    private Integer plasticOk;
    private Integer recycleTotal;
    private Integer recycleOk;
    private LocalDateTime createdAt;

    @Builder
    public RifLogInfo(Long id, Integer plasticTotal, Integer plasticOk, Integer recycleTotal, Integer recycleOk, LocalDateTime createdAt) {
        this.id = id;
        this.plasticTotal = plasticTotal;
        this.plasticOk = plasticOk;
        this.recycleTotal = recycleTotal;
        this.recycleOk = recycleOk;
        this.createdAt = createdAt;
    }

    public static RifLogInfo from(RifLog rifLog) {
        return RifLogInfo.builder()
                .id(rifLog.getId())
                .plasticTotal(rifLog.getPlasticTotal())
                .plasticOk(rifLog.getPlasticOk())
                .recycleTotal(rifLog.getRecycleTotal())
                .recycleOk(rifLog.getRecycleOk())
                .createdAt(rifLog.getCreated())
                .build();
    }

    @Override
    public String toString() {
        return "RifLogInfo{" +
                "\n id=" + id +
                ",\n plasticTotal=" + plasticTotal +
                ",\n plasticOk=" + plasticOk +
                ",\n recycleTotal=" + recycleTotal +
                ",\n recycleOk=" + recycleOk +
                ",\n createdAt=" + createdAt +
                "\n}";
    }
}


