package team.a501.rif.dto.badge;

import lombok.Data;
import lombok.NoArgsConstructor;
import team.a501.rif.domain.badge.Badge;

@NoArgsConstructor
@Data
public class BadgeSaveRequest {

    private final Integer MIN_TIER = 1;
    private final Integer MAX_TIER = 10;
    private final Long MAX_TITLE_LENGTH = 20L;
    private final Long MAX_DESCRIPTION_LENGTH = 100L;

    private Integer tier;
    private String title;
    private String description;
    private String badgeImgPath;

    public Badge toEntity(){

        if(tier < MIN_TIER || tier > MAX_TIER) throw new IllegalArgumentException();
        if(title.length() < 1 || title.length() > MAX_TITLE_LENGTH) throw new IllegalArgumentException();
        if(description.length() < 1 || description.length() > MAX_DESCRIPTION_LENGTH) throw new IllegalArgumentException();

        return Badge.builder()
                .title(this.title)
                .tier(this.tier)
                .description(this.description)
                .badgeImgPath(this.badgeImgPath)
                .build();
    }
}
