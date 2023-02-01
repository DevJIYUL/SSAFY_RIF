package team.a501.rif.dto.achievement;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class AchievementSaveRequest {

    private Integer tier;
    private String title;
    private String description;
    private String imgPath;

    @Builder
    public AchievementSaveRequest(Integer tier, String title, String description, String achievementImgPath) {
        this.tier = tier;
        this.title = title;
        this.description = description;
        this.imgPath = achievementImgPath;
    }
}
