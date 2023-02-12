package team.a501.rif.dto.achievement;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class AchievementSaveRequest {

    private Integer tier;
    private String title;
    private String description;
    private String imgPath;
    private String tag;

    @Builder
    public AchievementSaveRequest(Integer tier, String title, String description, String achievementImgPath, String tag) {
        this.tier = tier;
        this.title = title;
        this.description = description;
        this.imgPath = achievementImgPath;
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "AchievementSaveRequest{" +
                "\n tier=" + tier +
                ",\n title='" + title + '\'' +
                ",\n description='" + description + '\'' +
                ",\n imgPath='" + imgPath + '\'' +
                ",\n tag='" + tag + '\'' +
                "\n}";
    }
}