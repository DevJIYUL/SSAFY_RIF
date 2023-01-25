package team.a501.rif.domain.achievement;

import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Entity
public class Achievement {

    @Id
    @GeneratedValue
    private Long id;

    private Integer tier;

    private String title;

    private String description;

    private String achievementImgPath;

    @OneToMany(mappedBy = "achievement")
    private List<AchievementAcq> achievementAcqs = new ArrayList<>();

    @Builder
    public Achievement(Integer tier, String title, String description, String achievementImgPath) {
        this.tier = tier;
        this.title = title;
        this.description = description;
        this.achievementImgPath = achievementImgPath;
    }

    public Long getId() {
        return id;
    }

    public Integer getTier() {
        return tier;
    }

    public void setTier(Integer tier) {
        this.tier = tier;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAchievementImgPath() {
        return achievementImgPath;
    }

    public void setAchievementImgPath(String achievementImgPath) {
        this.achievementImgPath = achievementImgPath;
    }

    public List<AchievementAcq> getAchievementAcqs() {
        return achievementAcqs;
    }

    public void addAchievementAcq(AchievementAcq acq) {
        acq.setAchievement(this);
        achievementAcqs.add(acq);
    }

    @Override
    public String toString() {
        return "Achievement{" + "\n id=" + id + ",\n title='" + title + '\'' + ",\n description='" + description + '\'' + ",\n achievementImgPath='" + achievementImgPath + '\'' + "\n}";
    }
}
