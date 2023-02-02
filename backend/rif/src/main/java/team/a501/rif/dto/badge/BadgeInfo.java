package team.a501.rif.dto.badge;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class BadgeInfo {

    private Long id;
    private Integer tier;
    private String title;
    private String description;
    private String imgPath;

    @Builder
    public BadgeInfo(Long id, Integer tier, String title, String description, String imgPath) {
        this.id = id;
        this.tier = tier;
        this.title = title;
        this.description = description;
        this.imgPath = imgPath;
    }
}
