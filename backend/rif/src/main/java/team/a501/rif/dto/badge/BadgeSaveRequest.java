package team.a501.rif.dto.badge;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@NoArgsConstructor
@Data
public class BadgeSaveRequest {
    private Integer tier;
    @Column(length = 40)
    private String title;
    @Column(length = 100)
    private String description;
    @Column(length = 50)
    private String badgeImgPath;
}
