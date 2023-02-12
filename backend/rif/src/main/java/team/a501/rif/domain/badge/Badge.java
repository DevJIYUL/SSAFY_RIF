package team.a501.rif.domain.badge;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Entity
public class Badge {

    @Id
    @GeneratedValue
    private Long id;

    private Integer tier;

    @Column(length = 40)
    private String title;

    private String description;

    @Column(length = 40)
    private String imgPath;

    @JsonIgnore
    @OneToMany(mappedBy = "badge")
    private List<BadgeAcq> badgeAcqs;

    @Builder
    public Badge(Integer tier, String title, String description, String imgPath) {

        this.title = title;
        this.tier = tier;
        this.description = description;
        this.imgPath = imgPath;

        this.badgeAcqs = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getTier() {
        return tier;
    }

    public void setTier(Integer tier) {
        this.tier = tier;
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

    public List<BadgeAcq> getBadgeAcqs() {
        return badgeAcqs;
    }

    public void addBadgeAcq(@NotNull BadgeAcq acq) {

        acq.setBadge(this);
        this.badgeAcqs.add(acq);
    }

    public void removeBadgeAcq(@NotNull BadgeAcq acq) {

        acq.setBadge(null);
        this.badgeAcqs.remove(acq);
    }

    @Override
    public String toString() {
        return "Badge{" + "\n id=" + id + ",\n tier=" + tier + ",\n description='" + description + '\'' + ",\n badgeImgPath='" + imgPath + '\'' + "\n}";
    }
}
