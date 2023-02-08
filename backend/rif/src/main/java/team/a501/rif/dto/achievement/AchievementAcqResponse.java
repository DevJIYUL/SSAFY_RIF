package team.a501.rif.dto.achievement;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class AchievementAcqResponse {

    private Long id;
    private Integer tier;
    private String title;
    private String description;
    private String imgPath;
    private LocalDateTime achievedAt;

    @Builder
    public AchievementAcqResponse(Long id, Integer tier, String title, String description, String imgPath, LocalDateTime achievedAt) {
        this.id = id;
        this.tier = tier;
        this.title = title;
        this.description = description;
        this.imgPath = imgPath;
        this.achievedAt = achievedAt;
    }

    @Override
    public String toString() {
        return "AchievementAcqResponse{" +
                "\n id=" + id +
                ",\n tier=" + tier +
                ",\n title='" + title + '\'' +
                ",\n description='" + description + '\'' +
                ",\n imgPath='" + imgPath + '\'' +
                ",\n achievedAt=" + achievedAt +
                "\n}";
    }
}
