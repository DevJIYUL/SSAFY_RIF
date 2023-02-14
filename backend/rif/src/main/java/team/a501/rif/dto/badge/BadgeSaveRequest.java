package team.a501.rif.dto.badge;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@NoArgsConstructor
@Data
public class BadgeSaveRequest {
    private Integer tier;
    private String title;
    @Column
    private String description;
    private String imgPath;
}
