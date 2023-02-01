package team.a501.rif.domain.achievement;

import lombok.Builder;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Entity
public class Achievement {

    @Id
    @GeneratedValue
    private Long id;

    private Integer tier;

    @Column(length = 20)
    private String title;

    @Column(length = 100)
    private String description;

    @Column(length = 40)
    private String imgPath;

    @OneToMany(mappedBy = "achievement")
    private List<AchievementAcq> achievementAcqs;

    @Builder
    public Achievement(Integer tier, String title, String description, String achievementImgPath) {
        this.tier = tier;
        this.title = title;
        this.description = description;
        this.imgPath = achievementImgPath;
        this.achievementAcqs = new ArrayList<>();
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

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public List<AchievementAcq> getAchievementAcqs() {
        return achievementAcqs;
    }

    public void addAchievementAcq(AchievementAcq acq) {
        acq.setAchievement(this);
        this.achievementAcqs.add(acq);
    }

    public void removeAchievementAcq(@NotNull AchievementAcq acq){
        acq.setAchievement(null);
        this.achievementAcqs.remove(acq);
    }

    @Override
    public String toString() {
        return "Achievement{" + "\n id=" + id + ",\n title='" + title + '\'' + ",\n description='" + description + '\'' + ",\n achievementImgPath='" + imgPath + '\'' + "\n}";
    }
}
