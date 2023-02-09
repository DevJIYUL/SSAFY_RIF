package team.a501.rif.dto.badge;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.a501.rif.domain.badge.Badge;

@NoArgsConstructor
@Getter
public class BadgeInfo {

    private Long id;
    private Integer tier;
    private String title;
    private String description;
    private String imgPath;

    public static BadgeInfo from(Badge badge) {

        return BadgeInfo.builder()
                .id(badge.getId())
                .tier(badge.getTier())
                .title(badge.getTitle())
                .description(badge.getDescription())
                .imgPath(badge.getImgPath())
                .build();
    }

    @Builder
    public BadgeInfo(Long id, Integer tier, String title, String description, String imgPath) {
        this.id = id;
        this.tier = tier;
        this.title = title;
        this.description = description;
        this.imgPath = imgPath;
    }

    @Override
    public String toString() {
        return "BadgeInfo{" +
                "\nid=" + id +
                ",\n tier=" + tier +
                ",\n title='" + title + '\'' +
                ",\n description='" + description + '\'' +
                ",\n imgPath='" + imgPath + '\'' +
                "\n}";
    }
}
