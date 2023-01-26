package team.a501.rif.domain.badge;

import lombok.Builder;
import lombok.NoArgsConstructor;

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

    @Column(length = 100)
    private String description;

    @Column(length = 40)
    private String badgeImgPath;

    @OneToMany(mappedBy = "badge")
    private List<BadgeAcq> badgeAcqs;

    @Builder
    public Badge(Integer tier, String description, String badgeImgPath) {

        this.tier = tier;
        this.description = description;
        this.badgeImgPath = badgeImgPath;

        this.badgeAcqs = new ArrayList<>();
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBadgeImgPath() {
        return badgeImgPath;
    }

    public void setBadgeImgPath(String badgeImgPath) {
        this.badgeImgPath = badgeImgPath;
    }

    public List<BadgeAcq> getBadgeAcqs() {
        return badgeAcqs;
    }

    public void addBadgeAcq(BadgeAcq acq) {

        acq.setBadge(this);
        this.getBadgeAcqs().add(acq);
    }

    public void removeBadgeAcqs(BadgeAcq acq) {
        acq.setBadge(null);
        badgeAcqs.remove(acq.getId());
    }

    @Override
    public String toString() {
        return "Badge{" + "\n id=" + id + ",\n tier=" + tier + ",\n description='" + description + '\'' + ",\n badgeImgPath='" + badgeImgPath + '\'' + "\n}";
    }
}
