package team.a501.rif.dto.achievement;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.a501.rif.domain.achievement.Achievement;

@NoArgsConstructor
@Getter
public class AchievementInfo {

    private Long id;
    private Integer tier;
    private String title;
    private String description;
    private String imgPath;
    private String tag;
    @Builder
    public AchievementInfo(Long id, Integer tier, String title, String description, String imgPath, String tag) {
        this.id = id;
        this.tier = tier;
        this.title = title;
        this.description = description;
        this.imgPath = imgPath;
        this.tag = tag;
    }

    public static AchievementInfo from(Achievement achievement) {
        return AchievementInfo.builder()
                .id(achievement.getId())
                .imgPath(achievement.getImgPath())
                .tier(achievement.getTier())
                .title(achievement.getTitle())
                .description(achievement.getDescription())
                .tag(achievement.getAchievementTag().toString())
                .build();
    }

    @Override
    public String toString() {
        return "AchievementInfo{" +
                "\nid=" + id +
                ",\n tier=" + tier +
                ",\n title='" + title + '\'' +
                ",\n description='" + description + '\'' +
                ",\n imgPath='" + imgPath + '\'' +
                ",\n tag='" + tag + '\'' +
                "\n}";
    }
}
