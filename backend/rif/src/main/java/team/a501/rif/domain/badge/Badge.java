package team.a501.rif.domain.badge;

import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@NoArgsConstructor
public class Badge {

    @Id
    @GeneratedValue
    private Long id;

    private Integer tier;

    private String description;

    private String badgeImgPath;

    @OneToMany(mappedBy = "badge")
    private List<BadgeAcq> badgeAcqs;

    @Builder
    public Badge(Integer tier, String description, String badgeImgPath){

        this.tier = tier;
        this.description = description;
        this.badgeImgPath = badgeImgPath;
    }

    public Long getId() {
        return id;
    }

    public Integer getTier() {
        return tier;
    }

    public String getDescription() {
        return description;
    }

    public String getBadgeImgPath() {
        return badgeImgPath;
    }

    public List<BadgeAcq> getBadgeAcqs() {
        return badgeAcqs;
    }

    public void setTier(Integer tier) {
        this.tier = tier;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setBadgeImgPath(String badgeImgPath) {
        this.badgeImgPath = badgeImgPath;
    }

    public void addBadgeAcqs(BadgeAcq acq) {

        acq.setBadge(this);
        badgeAcqs.add(acq);
    }

    public void removeBadgeAcqs(BadgeAcq acq){
        acq.setBadge(null);
        badgeAcqs.remove(acq);
    }

    @Override
    public String toString() {
        return "Badge{" +
                "id=" + id +
                ", tier=" + tier +
                ", description='" + description + '\'' +
                ", badgeImgPath='" + badgeImgPath + '\'' +
                '}';
    }
}
